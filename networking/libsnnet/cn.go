//
// Copyright (c) 2016 Intel Corporation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package libsnnet

import (
	"fmt"
	"net"
	"strings"
	"sync"
	"time"

	"github.com/vishvananda/netlink"
)

// NetworkConfig from YAML.
// This is a subset of the top level data center configuration
type NetworkConfig struct {
	ManagementNet []net.IPNet // Enumerates all possible management subnets
	ComputeNet    []net.IPNet // Enumerates all possible compute subnets
	Mode          NetworkMode //The data center networking mode
}

// CnAPICtx contains API level context used to control the behaviour
// of the API, for example, cancellation by invoking close(CancelChan)
type CnAPICtx struct {
	CancelChan chan interface{}
}

// VnicConfig fram YAML
// All these fields originate from the Controller
type VnicConfig struct {
	// Per API Context
	// TODO: Move this outside of the VNIC Cfg.
	// Currently placed within the VnicConfig for API backward compatibility
	CnAPICtx
	VnicRole
	VnicIP     net.IP
	ConcIP     net.IP
	VnicMAC    net.HardwareAddr
	MTU        int
	SubnetKey  int //optional: Currently set to SubnetIP
	Subnet     net.IPNet
	VnicID     string // UUID
	InstanceID string // UUID
	TenantID   string // UUID
	SubnetID   string // UUID
	ConcID     string // UUID
}

// CNSsntpEvent to be generated
type CNSsntpEvent int

const (
	//SsntpNone : Non event
	SsntpNone CNSsntpEvent = 0
	//SsntpTunAdd : Local tunnel added, remote tunnel add required
	SsntpTunAdd CNSsntpEvent = 1 << iota
	//SsntpTunDel : Local tunnel deleted, remote tunnel delete required
	SsntpTunDel
)

// SsntpEventInfo contains the event info that needs to be
// converted to YAML payload and sent to the CNCI via the scheduler
type SsntpEventInfo struct {
	Event             CNSsntpEvent // TYPE: SSNTP Type
	CnciIP            string       // TO: IP Address of the concentrator
	CnIP              string       // FROM: Compute Network IP for this node
	Subnet            string       // Tenant Subnet
	TenantID          string       // Tenant UUID
	SubnetID          string       // Tenant Subnet UUID
	ConcID            string       // CNCI UUID
	CnID              string       // CN UUID
	SubnetKey         int
	containerSubnetID string // Logical name of the container network.
	// Hack: Will be removed once we drop deprecated APIs
}

// CNContainerEvent to be generated
type CNContainerEvent int

const (
	//ContainerNetworkInfo Informative, no action needs to be taken by caller
	ContainerNetworkInfo CNContainerEvent = 0
	//ContainerNetworkAdd Caller has to create the logical docker network before starting
	//a container with this VNIC
	ContainerNetworkAdd CNContainerEvent = 1 << iota
	//ContainerNetworkDel Caller is responsible for logical docker network deletion
	//The corresponding physical network no longer exists
	ContainerNetworkDel
)

//ContainerInfo provides details that needed by docker to create the container
//associated with this VNIC
type ContainerInfo struct {
	CNContainerEvent
	SubnetID string
	Subnet   net.IPNet
	Gateway  net.IP
	Bridge   string
}

type linkInfo struct {
	index int
	name  string
	ready chan struct{}
}

//Network topology of the node
//The linkMap is always authoritative
type cnTopology struct {
	sync.Mutex
	bridgeMap    map[string]map[string]bool //Bridge to VNIC mapping
	linkMap      map[string]*linkInfo       //Alias to Link mapping
	nameMap      map[string]bool            //Link name
	containerMap map[string]bool            //Bridge to container mapping
}

func newCnTopology() (topology *cnTopology) {
	topology = &cnTopology{}
	initCnTopology(topology)
	return topology
}

func initCnTopology(topology *cnTopology) {
	topology.bridgeMap = make(map[string]map[string]bool)
	topology.linkMap = make(map[string]*linkInfo)
	topology.nameMap = make(map[string]bool)
	topology.containerMap = make(map[string]bool)
}

//CnMaxAPIConcurrency default controls internal concurrency
//It determines how many API's are being actively processed
//Can be over-ridden prior to init
var CnMaxAPIConcurrency int = 8

//CnAPITimeout default controls the API timeout
const (
	CnAPITimeout = 6
)

// ComputeNode describes the high level networking setup of a compute node.
// The design allows for multiple links, however in phase 0 only the first
// link is chosen. The remaining are ignored. In the future this allows for
// backup links or link aggregation or failover
type ComputeNode struct {
	ID string //UUID of CN
	*NetworkConfig
	MgtAddr     []netlink.Addr
	MgtLink     []netlink.Link
	ComputeAddr []netlink.Addr
	ComputeLink []netlink.Link
	//APITimeout specifies the amount of time the API will wait for netlink
	//operations to complete. When multiple go routines  invoke the API
	//simulatenously certian netlink calls suffer higher latencies
	APITimeout time.Duration

	*cnTopology
	apiThrottleSem chan int
}

// Init sets the CN node configuration
// Discovers the physical interfaces and classifies them as management or compute
// Performs any node specific networking setup.
func (cn *ComputeNode) Init() error {

	links, err := netlink.LinkList()

	if err != nil {
		return NewAPIError("CN node init failed " + err.Error())
	}

	phyInterfaces := 0
	cn.MgtAddr = nil
	cn.MgtLink = nil
	cn.ComputeAddr = nil
	cn.ComputeLink = nil
	cn.APITimeout = time.Second * CnAPITimeout
	cn.apiThrottleSem = make(chan int, CnMaxAPIConcurrency)

	for _, link := range links {

		if link.Type() != "device" &&
			link.Type() != "bond" &&
			link.Type() != "vlan" {
			continue
		}

		if link.Attrs().Name == "lo" {
			continue
		}

		addrs, err := netlink.AddrList(link, netlink.FAMILY_V4)
		if err != nil || len(addrs) == 0 {
			continue //Should be safe to ignore this
		}

		phyInterfaces++

		for _, addr := range addrs {

			if cn.ManagementNet == nil {
				cn.MgtAddr = append(cn.MgtAddr, addr)
				cn.MgtLink = append(cn.MgtLink, link)
			} else {
				for _, mgt := range cn.ManagementNet {
					if mgt.Contains(addr.IPNet.IP) {
						cn.MgtAddr = append(cn.MgtAddr, addr)
						cn.MgtLink = append(cn.MgtLink, link)
					}
				}
			}

			if cn.ComputeNet == nil {
				cn.ComputeAddr = append(cn.ComputeAddr, addr)
				cn.ComputeLink = append(cn.ComputeLink, link)
			} else {
				for _, comp := range cn.ComputeNet {
					if comp.Contains(addr.IPNet.IP) {
						cn.ComputeAddr = append(cn.ComputeAddr, addr)
						cn.ComputeLink = append(cn.ComputeLink, link)
					}
				}
			}

		}
	}

	if len(cn.MgtAddr) < 1 {
		return NewAPIError(fmt.Sprintf("unable to associate with management network %v", cn.ManagementNet))
	}
	if len(cn.ComputeAddr) < 1 {
		return NewAPIError(fmt.Sprintf("unable to associate with compute network %v", cn.ComputeNet))
	}

	if (cn.ManagementNet == nil || cn.ComputeNet == nil) && phyInterfaces > 1 {
		return fmt.Errorf("unable to autoconfigure network")
	}

	//TODO: Support all modes
	if cn.Mode != GreTunnel {
		return NewAPIError(fmt.Sprintf("Unsupported network mode %v", cn.Mode))
	}

	cn.cnTopology = newCnTopology()

	return nil
}

type vnicAliases struct {
	bridge   string
	vnic     string
	vnicPeer string
	gre      string
}

const (
	bridgePrefix   = "br_"
	vnicPrefix     = "vnic_"
	grePrefix      = "gre_"
	cnciVnicPrefix = "cncivnic_"
)

func (cn *ComputeNode) genCnciVnicAlias(cfg *VnicConfig) string {
	return fmt.Sprintf("%s%s_%s", cnciVnicPrefix,
		cfg.TenantID,
		cfg.VnicID)

}

func (cn *ComputeNode) checkCnciVnicCfg(cfg *VnicConfig) error {

	switch {
	case cfg.TenantID == "":
		return fmt.Errorf("Invalid CNCI VNIC configuration = TenantID")
	case cfg.VnicID == "":
		return fmt.Errorf("Invalid CNCI VNIC configuration = VnicID")
	}

	return nil
}

func genCnVnicAliases(cfg *VnicConfig) *vnicAliases {

	vnic := &vnicAliases{}

	vnic.bridge = fmt.Sprintf("%s%s_%s_%s_%s", bridgePrefix,
		cfg.TenantID,
		cfg.SubnetID,
		cfg.ConcID,
		cfg.ConcIP)

	vnic.gre = fmt.Sprintf("%s%s_%s_%s_%s", grePrefix,
		cfg.TenantID,
		cfg.SubnetID,
		cfg.ConcID,
		cfg.ConcIP)

	vnic.vnic = fmt.Sprintf("%s%s_%s_%s_%s##%s", vnicPrefix,
		cfg.TenantID,
		cfg.SubnetID,
		cfg.ConcID,
		cfg.ConcIP,
		cfg.VnicIP)

	return vnic
}

func checkCnVnicCfg(cfg *VnicConfig) error {

	switch {
	case cfg.TenantID == "":
		return fmt.Errorf("Invalid VNIC configuration - TenantID")
	case cfg.SubnetID == "":
		return fmt.Errorf("Invalid VNIC configuration - SubnetID")
	case cfg.ConcID == "":
		return fmt.Errorf("Invalid VNIC configuration - ConcID")
	case cfg.ConcIP == nil:
		return fmt.Errorf("Invalid VNIC configuration - ConcIP")
	case cfg.VnicID == "":
		return fmt.Errorf("Invalid VNIC configuration - VnicID")
	case cfg.VnicMAC == nil:
		return fmt.Errorf("Invalid VNIC configuration - VnicID")
	case cfg.VnicRole != TenantVM && cfg.VnicRole != TenantContainer:
		return fmt.Errorf("Invalid vnic role %v", cfg)
	}

	return nil
}

type dbOp int

const (
	dbInsVnic dbOp = 1 << iota
	dbDelVnic
	dbInsBr
	dbDelBr
	dbInsIf
	dbDelIf
)

//DbRebuild the CN network database using the information contained
//in the aliases. It can be called if the agent using the library
//crashes and loses network topology information.
//It can also be called, to rebuild the network topology on demand.
func (cn *ComputeNode) DbRebuild(links []netlink.Link) error {

	if cn.NetworkConfig == nil || cn.cnTopology == nil {
		return NewAPIError(fmt.Sprintf("CN has not been initialized %v", cn))
	}

	links, err := netlink.LinkList()
	if err != nil {
		return NewFatalError("Cannot retrieve links" + err.Error())
	}

	cn.cnTopology.Lock()
	defer cn.cnTopology.Unlock()

	initCnTopology(cn.cnTopology)

	//Add the bridges first, vnics added later as we
	//do not control the order of link discovery
	for _, link := range links {
		alias := link.Attrs().Alias
		name := link.Attrs().Name
		cn.nameMap[name] = true

		if alias == "" {
			continue
		}

		cn.linkMap[alias] = &linkInfo{
			index: link.Attrs().Index,
			name:  name,
			ready: make(chan struct{}),
		}
		defer close(cn.linkMap[alias].ready)

		if link.Type() == "bridge" {
			if _, err := cn.dbUpdate(alias, "", dbInsBr); err != nil {
				return NewFatalError("db rebuild " + err.Error())
			}
		}
	}

	//Now build the vnic maps, inefficient but simple
	//This allows us to check if the bridges and tunnels are all present
	for _, link := range links {
		if alias := link.Attrs().Alias; alias != "" {
			if strings.HasPrefix(alias, vnicPrefix) {
				vnic := alias
				id := strings.TrimPrefix(vnic, vnicPrefix)
				id = strings.Split(id, "##")[0]
				bridge := bridgePrefix + id
				gre := grePrefix + id
				if _, err := cn.dbUpdate(bridge, vnic, dbInsVnic); err != nil {
					return NewFatalError("db rebuild: add vnic" + err.Error())
				}
				if _, ok := cn.linkMap[gre]; !ok {
					return NewFatalError("db rebuild: missing gre tunnel " + gre)
				}
				if link.Type() == "veth" {
					cn.containerMap[bridge] = true
				}
			}
		}
	}

	return nil
}

func (cn *ComputeNode) dbUpdate(bridge string, vnic string, op dbOp) (int, error) {

	switch {

	case (op & dbInsBr) == dbInsBr:
		vnicMap, present := cn.bridgeMap[bridge]
		if present {
			return -1, fmt.Errorf("db duplicate bridge %v %v", op, bridge)
		}
		vnicMap = make(map[string]bool)
		cn.bridgeMap[bridge] = vnicMap
		return len(cn.bridgeMap), nil

	case (op & dbDelBr) == dbDelBr:
		_, present := cn.bridgeMap[bridge]
		if !present {
			return -1, fmt.Errorf("db missing bridge %v", bridge)
		}
		delete(cn.bridgeMap, bridge)
		return len(cn.bridgeMap), nil

	case (op & dbInsVnic) == dbInsVnic:
		vnicMap, present := cn.bridgeMap[bridge]
		if !present {
			return -1, fmt.Errorf("db missing bridge %v %v", op, bridge)
		}

		_, present = vnicMap[vnic]
		if present {
			return -1, fmt.Errorf("db duplicate vnic %v %v", op, vnic)
		}
		vnicMap[vnic] = true
		return len(vnicMap), nil

	case (op & dbDelVnic) == dbDelVnic:
		vnicMap, present := cn.bridgeMap[bridge]
		if !present {
			return -1, fmt.Errorf("db missing bridge %v", bridge)
		}

		_, present = vnicMap[vnic]
		if !present {
			return -1, fmt.Errorf("db missing vnic %v", vnic)
		}
		delete(vnicMap, vnic)
		return len(vnicMap), nil

	default:
		return -1, fmt.Errorf("db invalid op %v %v", op, vnic)
	}
}

func (cn *ComputeNode) genLinkName(device interface{}) (string, error) {
	for i := 0; i < ifaceRetryLimit; {
		name, _ := GenIface(device, false)
		if !cn.nameMap[name] {
			cn.nameMap[name] = true
			return name, nil
		}
	}
	return "", fmt.Errorf("Unable to generate unique device name")
}

// CreateCnciVnic creates a Cnci VNIC and sets all the underlying framework
// to ensure that the Vnic is active. The Cnci VNIC will bind to the first
// compute network interface.
func (cn *ComputeNode) CreateCnciVnic(cfg *VnicConfig) (*CnciVnic, error) {

	if cn.cnTopology == nil || cfg == nil || cfg.VnicRole != DataCenter {
		return nil, NewAPIError("invalid vnic or configuration")
	}

	if err := cn.checkCnciVnicCfg(cfg); err != nil {
		return nil, NewAPIError(err.Error())
	}

	cvnic, err := NewCnciVnic(cn.genCnciVnicAlias(cfg))
	if err != nil {
		return nil, NewAPIError(err.Error())
	}
	cvnic.MACAddr = &cfg.VnicMAC
	cvnic.Link.ParentIndex = cn.ComputeLink[0].Attrs().Index

	// CS Start
	cn.cnTopology.Lock()

	if vLink, present := cn.linkMap[cvnic.GlobalID]; present {
		cn.cnTopology.Unlock()

		cvnic.LinkName, cvnic.Link.Index, err = waitForDeviceReady(vLink, cn.APITimeout)
		if err != nil {
			return nil, NewFatalError(cvnic.GlobalID + err.Error())
		}
		return cvnic, nil
	}

	if cvnic.LinkName, err = cn.genLinkName(cvnic); err != nil {
		cn.cnTopology.Unlock()
		return nil, NewFatalError("Unable to generate unique cvnic name")
	}

	cn.linkMap[cvnic.GlobalID] = &linkInfo{
		name:  cvnic.LinkName,
		ready: make(chan struct{}),
	}
	defer close(cn.linkMap[cvnic.GlobalID].ready)

	cn.cnTopology.Unlock()
	// CS End

	if err := cvnic.Create(); err != nil {
		return nil, NewFatalError(err.Error())
	}
	if err := cvnic.Enable(); err != nil {
		return nil, NewFatalError(err.Error())
	}

	cn.linkMap[cvnic.GlobalID].index = cvnic.Link.Index

	// Now the network is ready and you can create a VM and launch it with this vnic
	// vnic.Name is the interface name, the instanceMAC is the MAC Address
	// qemu-system-x86_64 ...
	//-net nic,model=virtio,macaddr=$(< /sys/class/net/<cvniv.LinkName>/address) \
	//-net tap,fd=3 3<>/dev/tap$(< /sys/class/net/<cvnic.LinkName>/ifindex)
	return cvnic, nil

}

// DestroyCnciVnic destroys a Cnci VNIC.
func (cn *ComputeNode) DestroyCnciVnic(cfg *VnicConfig) error {

	if cn.cnTopology == nil || cfg == nil || cfg.VnicRole != DataCenter {
		return NewAPIError("invalid vnic or configuration")
	}

	if err := cn.checkCnciVnicCfg(cfg); err != nil {
		return NewAPIError(err.Error())
	}

	cvnic, err := NewCnciVnic(cn.genCnciVnicAlias(cfg))
	if err != nil {
		return NewAPIError(err.Error())
	}

	//Start CS
	cn.cnTopology.Lock()
	defer cn.cnTopology.Unlock()

	vLink, present := cn.linkMap[cvnic.GlobalID]
	if !present {
		return nil
	}

	cvnic.LinkName, cvnic.Link.Index, err = waitForDeviceReady(vLink, cn.APITimeout)
	if err != nil {
		return NewFatalError(cvnic.GlobalID + err.Error())
	}
	delete(cn.linkMap, cvnic.GlobalID)
	delete(cn.nameMap, cvnic.LinkName)

	if err := cvnic.Destroy(); err != nil {
		return NewFatalError(err.Error())
	}

	return nil
}

// CreateVnicV2 creates a tenant VNIC that can be used by containers
// or VMs
// This will replace CreateVnic
//
// If this is the first instance of a container Vnic belonging to the
// tenant on this subnet will provide the ContainerInfo which
// should be used by the caller of this API to logically create the Network
// in the Docker network database
// This is typically done using the command line or API equivalen of
// docker  docker network create -d=ciao --ipam-driver=ciao
// --subnet=<ContainerInfo.Subnet> --gateway=<ContainerInfo.Gateway>
// --opt "bridge"=<ContainerInfo.Bridge> ContainerInfo.SubnetID
//
// If this is the first instance of a Vnic belonging to the tenant,
// will provide a SSNTP message to be sent to the Scheduler to notify the
// CNCI of this instantitation. This message is processed by the CNCI which
// will setup the far side of the  tunnel which is required to connect this CN
// tenant bridge to the tenant Subnet
//
// Note: The caller of this function is responsible to send the message to the scheduler
func (cn *ComputeNode) CreateVnicV2(cfg *VnicConfig) (*Vnic, *SsntpEventInfo, *ContainerInfo, error) {
	/* TODO: Need to figure out a better way to set MTU for containers */
	if cfg.VnicRole == TenantContainer {
		if cfg.MTU == 0 {
			cfg.MTU = 1400
		}
	}

	cn.apiThrottleSem <- 1
	defer func() {
		<-cn.apiThrottleSem
	}()

	if apiCancelled(cfg.CancelChan) {
		return nil, nil, nil, NewAPIError("API Cancelled for " + cfg.VnicID)
	}

	return cn.createVnicInternal(cfg)
}

// CreateVnic creates a tenant VM VNIC and sets all the underlying framework
//
// This version of the API has been deprecated
//
// to ensure that the Vnic is active. In addition if this is the first instance
// of the Vnic belonging to the tenant, will provide a SSNTP message to be
// sent to the Scheduler to notify the CNCI of this instantitation. This
// message is processed by the CNCI which will setup the far side of the
// tunnel which is required to connect this CN tenant bridge to the tenant Subnet
// Note: The caller of this function is responsible to send the message to the scheduler
func (cn *ComputeNode) CreateVnic(cfg *VnicConfig) (*Vnic, *SsntpEventInfo, error) {
	if cfg.VnicRole != TenantVM {
		return nil, nil, NewAPIError("invalid vnic role")
	}
	v, s, _, err := cn.createVnicInternal(cfg)
	return v, s, err
}

func (cn *ComputeNode) createVnicInternal(cfg *VnicConfig) (*Vnic, *SsntpEventInfo, *ContainerInfo, error) {
	var gLink *linkInfo
	var cInfo *ContainerInfo

	if cn.cnTopology == nil || cfg == nil {
		return nil, nil, nil, NewAPIError("invalid vnic or configuration")
	}

	if err := checkCnVnicCfg(cfg); err != nil {
		return nil, nil, nil, NewAPIError("invalid vnic or configuration")
	}

	if err := checkCnVnicCfg(cfg); err != nil {
		return nil, nil, nil, NewAPIError(err.Error())
	}
	alias := genCnVnicAliases(cfg)

	bridge, err := NewBridge(alias.bridge)
	if err != nil {
		return nil, nil, nil, NewAPIError(err.Error())
	}

	var vnic *Vnic
	switch cfg.VnicRole {
	case TenantVM:
		vnic, err = NewVnic(alias.vnic)
	case TenantContainer:
		vnic, err = NewContainerVnic(alias.vnic)
	}
	if err != nil {
		return nil, nil, nil, NewAPIError(err.Error())
	}
	vnic.MACAddr = &cfg.VnicMAC
	vnic.MTU = cfg.MTU

	local := cn.ComputeAddr[0].IPNet.IP
	gre, err := NewGreTunEP(alias.gre, local, cfg.ConcIP, uint32(cfg.SubnetKey))
	if err != nil {
		return nil, nil, nil, NewAPIError(err.Error())
	}

	// CS Start
	cn.cnTopology.Lock()

	vLink, present := cn.linkMap[vnic.GlobalID]
	if present {
		bLink, present := cn.linkMap[bridge.GlobalID]
		cn.cnTopology.Unlock()

		vnic.LinkName, vnic.Link.Attrs().Index, err = waitForDeviceReady(vLink, cn.APITimeout)
		if err != nil {
			return nil, nil, nil, NewFatalError(vnic.GlobalID + err.Error())
		}
		if cfg.VnicRole == TenantVM {
			return vnic, nil, nil, nil
		}

		//Retrieve the bridge for the VNIC, which should already exist
		//This is not strictly needed, but helps the caller identify the container
		//network ID without resorting to any sort of caching
		if !present {
			return nil, nil, nil, NewFatalError(vnic.GlobalID + " Bridge not present")
		}
		bridge.LinkName, bridge.Link.Attrs().Index, err = waitForDeviceReady(bLink, cn.APITimeout)
		if err != nil {
			return nil, nil, nil, NewFatalError(vnic.GlobalID + err.Error())
		}

		cInfo := getContainerInfo(cfg, vnic, bridge)
		return vnic, nil, cInfo, nil
	}

	if err := cn.logicallyCreateVnic(vnic); err != nil {
		cn.cnTopology.Unlock()
		return nil, nil, nil, NewFatalError(err.Error())
	}
	vLink = cn.linkMap[vnic.GlobalID]
	defer close(vLink.ready)

	bLink, present := cn.linkMap[bridge.GlobalID]
	if present {
		if _, err := cn.dbUpdate(bridge.GlobalID, vnic.GlobalID, dbInsVnic); err != nil {
			cn.cnTopology.Unlock()
			return nil, nil, nil, NewFatalError(err.Error())
		}

		var needsContainerNetwork bool
		if vnic.Role == TenantContainer && !cn.containerMap[bridge.GlobalID] {
			cn.containerMap[bridge.GlobalID] = true
			needsContainerNetwork = true
		}

		cn.cnTopology.Unlock()

		bridge.LinkName, bridge.Link.Index, err = waitForDeviceReady(bLink, cn.APITimeout)
		if err != nil {
			return nil, nil, nil, NewFatalError(bridge.GlobalID + err.Error())
		}

		if err := createAndEnableVnic(vnic, bridge); err != nil {
			return nil, nil, nil, NewFatalError(err.Error())
		}
		vLink.index = vnic.Link.Attrs().Index

		cInfo = getContainerInfo(cfg, vnic, bridge)
		if needsContainerNetwork {
			cInfo.CNContainerEvent = ContainerNetworkAdd
		}
		return vnic, nil, cInfo, nil
	}

	if err := cn.logicallyCreateBridge(bridge, gre, vnic); err != nil {
		cn.cnTopology.Unlock()
		return nil, nil, nil, NewFatalError(err.Error())
	}

	gLink = cn.linkMap[gre.GlobalID]
	defer close(gLink.ready)

	bLink = cn.linkMap[bridge.GlobalID]
	defer close(bLink.ready)

	if vnic.Role == TenantContainer {
		cn.containerMap[bridge.GlobalID] = true
	}

	cn.cnTopology.Unlock()
	// CS End

	//The actual device creation is time consuming
	//but is outside the critical section
	//The defer close(ready) ensures that
	//the channel will close even on failure
	brCreateMsg := &SsntpEventInfo{
		Event:     SsntpTunAdd,
		CnciIP:    cfg.ConcIP.String(),
		ConcID:    cfg.ConcID,
		TenantID:  cfg.TenantID,
		SubnetID:  cfg.SubnetID,
		SubnetKey: cfg.SubnetKey,
		Subnet:    cfg.Subnet.String(),
		CnIP:      local.String(),
		CnID:      cn.ID,
	}

	if err := createAndEnableBridge(bridge, gre); err != nil {
		return nil, brCreateMsg, cInfo, NewFatalError(err.Error())
	}
	bLink.index = bridge.Link.Index
	gLink.index = gre.Link.Index

	if err := createAndEnableVnic(vnic, bridge); err != nil {
		return nil, brCreateMsg, cInfo, NewFatalError(err.Error())
	}
	vLink.index = vnic.Link.Attrs().Index

	cInfo = getContainerInfo(cfg, vnic, bridge)
	cInfo.CNContainerEvent = ContainerNetworkAdd

	// Now the network is ready and you can create a VM and launch it with this vnic
	// vnic.Name is the interface name, the instanceMAC is the MAC Address
	// qemu-system-x86_64 ... -net nic,model=virtio,macaddr=xxxx -net tap,ifname=vnic.Name ...
	return vnic, brCreateMsg, cInfo, nil
}

func getContainerInfo(cfg *VnicConfig, vnic *Vnic, bridge *Bridge) *ContainerInfo {
	//TODO. Create a ciao gateway function so that in the future
	//if we ever change our gateway algorithm it will propage everywhere
	gateway := cfg.Subnet.IP.To4().Mask(cfg.Subnet.Mask)
	gateway[3]++
	return &ContainerInfo{
		CNContainerEvent: ContainerNetworkInfo, //Default. Caller to override
		SubnetID:         bridge.LinkName,
		Bridge:           bridge.GlobalID,
		Subnet:           cfg.Subnet,
		Gateway:          gateway,
	}
}

//TODO: Use interfaces here to perform the name and index assignment
func waitForDeviceReady(devInfo *linkInfo, timeout time.Duration) (devName string, devIndex int, err error) {
	select {
	case <-devInfo.ready:
		return devInfo.name, devInfo.index, nil
	case <-time.After(timeout):
		return "", 0, fmt.Errorf("Timeout waiting for device ready [%v] [%v]", devInfo.index, devInfo.name)
	}
}

func (cn *ComputeNode) logicallyCreateVnic(vnic *Vnic) (err error) {

	if vnic.LinkName, err = cn.genLinkName(vnic); err != nil {
		return err
	}

	vLink := &linkInfo{
		name:  vnic.LinkName,
		ready: make(chan struct{}),
	}

	cn.linkMap[vnic.GlobalID] = vLink
	return nil
}

//Logically instantiates the bridge and tunnel in the topology
//The physical devices are not yet created but thier names aliases
//are added to the topology reserving them
//TODO: Check for global topology issues. E.g. Two tenants with same CNCI
func (cn *ComputeNode) logicallyCreateBridge(bridge *Bridge, gre *GreTunEP, vnic *Vnic) (err error) {
	if bridge.LinkName, err = cn.genLinkName(bridge); err != nil {
		return err
	}
	if gre.LinkName, err = cn.genLinkName(gre); err != nil {
		return err
	}
	if _, err = cn.dbUpdate(bridge.GlobalID, "", dbInsBr); err != nil {
		return err
	}
	if _, err = cn.dbUpdate(bridge.GlobalID, vnic.GlobalID, dbInsVnic); err != nil {
		return err
	}

	cn.linkMap[gre.GlobalID] = &linkInfo{
		name:  gre.LinkName,
		ready: make(chan struct{}),
	}

	cn.linkMap[bridge.GlobalID] = &linkInfo{
		name:  bridge.LinkName,
		ready: make(chan struct{}),
	}

	return nil
}

//Phsyically create the devices by calling into the kernel
//TODO: Try to be more fault tolerant here. We may miss errors but try to
// honor the request  e.g. If bridge exists use it and try and create tunnel
func createAndEnableBridge(bridge *Bridge, gre *GreTunEP) error {
	if err := bridge.Create(); err != nil {
		return fmt.Errorf("Bridge creation failed %s %s", bridge.GlobalID, err.Error())
	}
	if err := gre.Create(); err != nil {
		return fmt.Errorf("GRE creation failed %s %s", gre.GlobalID, err.Error())
	}
	if err := gre.Attach(bridge); err != nil {
		return fmt.Errorf("GRE attach failed %s %s %s", gre.GlobalID, bridge.GlobalID, err.Error())
	}

	if err := gre.Enable(); err != nil {
		return fmt.Errorf("GRE enable failed %s %s %s", gre.GlobalID, bridge.GlobalID, err.Error())
	}
	if err := bridge.Enable(); err != nil {
		return fmt.Errorf("Bridge enable failed %s %s %s", gre.GlobalID, bridge.GlobalID, err.Error())
	}
	return nil
}

//Physically create the VNIC and attach it to the bridge
func createAndEnableVnic(vnic *Vnic, bridge *Bridge) error {
	if err := vnic.Create(); err != nil {
		return fmt.Errorf("VNIC creation failed %s %s", vnic.GlobalID, err.Error())
	}
	if err := vnic.SetHardwareAddr(*vnic.MACAddr); err != nil {
		return fmt.Errorf("VNIC Set MAC Address %s %s", vnic.GlobalID, err.Error())
	}
	if err := vnic.SetMTU(vnic.MTU); err != nil {
		return fmt.Errorf("VNIC Set MTU Address %s %s", vnic.GlobalID, err.Error())
	}
	if err := vnic.Attach(bridge); err != nil {
		return fmt.Errorf("VNIC attach failed %s %s %s", vnic.GlobalID, bridge.GlobalID, err.Error())
	}
	vnic.BridgeID = bridge.LinkName
	if err := vnic.Enable(); err != nil {
		return fmt.Errorf("VNIC enable failed %s %s %s", vnic.GlobalID, bridge.GlobalID, err.Error())
	}
	return nil
}

func apiCancelled(cancel chan interface{}) bool {
	select {
	case <-cancel:
		return true
	default:
		return false
	}
}

// DestroyVnicV2 destroys a tenant VNIC. If this happens to be the last vnic for
// this tenant subnet on this CN, the bridge and gre tunnel will also be
// destroyed and SSNTP message generated.
//
// This will replace the DestroyVnic method
//
// This message needs to be sent to the CNCI which will teardown the tunnel.
// Note: The caller of this function is responsible to send the message to the
// scheduler or CNCI
// If the ContainerInfo is set, the container logical network has to
// be deleted using the command line or API equivalent of
// docker network rm ContainerInfo.SubnetID>
func (cn *ComputeNode) DestroyVnicV2(cfg *VnicConfig) (*SsntpEventInfo, *ContainerInfo, error) {
	var cInfo *ContainerInfo

	cn.apiThrottleSem <- 1
	defer func() {
		<-cn.apiThrottleSem
	}()

	if apiCancelled(cfg.CancelChan) {
		return nil, nil, NewAPIError("API Cancelled for " + cfg.VnicID)
	}

	s, err := cn.DestroyVnic(cfg)
	if s != nil && s.containerSubnetID != "" {
		cInfo = &ContainerInfo{
			CNContainerEvent: ContainerNetworkDel,
			SubnetID:         s.containerSubnetID,
		}
	}

	return s, cInfo, err
}

// DestroyVnic destroys a tenant VNIC. If this happens to be the last vnic for
// this tenant subnet on this CN, the bridge and gre tunnel will also be
// destroyed and SSNTP message generated.
//
// This API has been deperecated
//
// This message needs to be sent to the CNCI which will teardown the tunnel.
// Note: The caller of this function is responsible to send the message to the
// scheduler or CNCI
func (cn *ComputeNode) DestroyVnic(cfg *VnicConfig) (*SsntpEventInfo, error) {
	var brDeleteMsg *SsntpEventInfo

	if cfg == nil || cn.cnTopology == nil {
		return nil, NewAPIError("invalid vnic or configuration")
	}

	if err := checkCnVnicCfg(cfg); err != nil {
		return nil, NewAPIError(err.Error())
	}

	alias := genCnVnicAliases(cfg)
	vnic, err := NewVnic(alias.vnic)
	if err != nil {
		return nil, NewAPIError(err.Error())
	}

	// The entire delete has to be performed in a CS
	// as there is a non zero possibility that link names
	// may be reused or the same GRE tunnels created
	cn.cnTopology.Lock()
	defer cn.cnTopology.Unlock()

	vLink, present := cn.linkMap[alias.vnic]
	if !present {
		return nil, nil
	}

	vnic.LinkName, vnic.Link.Attrs().Index, err = waitForDeviceReady(vLink, cn.APITimeout)
	if err != nil {
		return nil, NewFatalError(vnic.GlobalID + err.Error())
	}
	err = vnic.Destroy()
	if err != nil {
		return nil, NewFatalError(err.Error())
	}
	delete(cn.linkMap, vnic.GlobalID)
	delete(cn.nameMap, vnic.LinkName)

	vnicCount, err := cn.dbUpdate(alias.bridge, alias.vnic, dbDelVnic)
	if err != nil {
		return nil, NewFatalError(err.Error())
	}

	if vnicCount != 0 {
		return nil, nil
	}

	bridge, err := NewBridge(alias.bridge)
	if err != nil {
		return nil, NewFatalError(err.Error())
	}

	gre, err := NewGreTunEP(alias.gre, nil, nil, 0)
	if err != nil {
		return nil, NewFatalError(err.Error())
	}

	brDeleteMsg = &SsntpEventInfo{
		Event:     SsntpTunDel,
		CnciIP:    cfg.ConcIP.String(),
		ConcID:    cfg.ConcID,
		TenantID:  cfg.TenantID,
		SubnetID:  cfg.SubnetID,
		SubnetKey: cfg.SubnetKey,
		Subnet:    cfg.Subnet.String(),
		CnIP:      cn.ComputeAddr[0].IPNet.IP.String(),
		CnID:      cn.ID,
	}

	//TODO: Try and make forward progress even on error
	gLink, present := cn.linkMap[alias.gre]
	if present {
		gre.LinkName, gre.Link.Index, err = waitForDeviceReady(gLink, cn.APITimeout)
		if err != nil {
			return nil, NewFatalError(gre.GlobalID + err.Error())
		}

		err := gre.Destroy()
		if err != nil {
			return nil, NewFatalError("gre destroy " + gre.GlobalID + err.Error())
		}
		delete(cn.nameMap, gre.LinkName)
		delete(cn.linkMap, gre.GlobalID)

	} else {
		//TODO: Consider logging this and continue to delete bridge
		return nil, NewFatalError(fmt.Sprintf("gre tunnel not present %s", gre.GlobalID))
	}

	bLink, present := cn.linkMap[alias.bridge]
	if present {
		bridge.LinkName, bridge.Link.Index, err = waitForDeviceReady(bLink, cn.APITimeout)
		if err != nil {
			return nil, NewFatalError(bridge.GlobalID + err.Error())
		}

		if err := bridge.Destroy(); err != nil {
			return nil, NewFatalError("bridge destroy failed " + err.Error())
		}
		// We delete the container network when the bridge is deleted
		if cn.containerMap[alias.bridge] {
			brDeleteMsg.containerSubnetID = bridge.LinkName
			cn.containerMap[alias.bridge] = false
		}
		delete(cn.nameMap, bridge.LinkName)
		delete(cn.linkMap, bridge.GlobalID)

		if _, err := cn.dbUpdate(alias.bridge, "", dbDelBr); err != nil {
			return nil, NewFatalError("db del br " + err.Error())
		}

	} else {
		return nil, NewFatalError(fmt.Sprintf("bridge not present %s", bridge.GlobalID))
	}

	return brDeleteMsg, nil
}

//ResetNetwork will attempt to clean up all network interfaces
//created. It will not clean up any interfaces created manually
func (cn *ComputeNode) ResetNetwork() error {

	links, err := netlink.LinkList()
	if err != nil {
		return NewFatalError("Cannot retrieve links" + err.Error())
	}

	cn.cnTopology.Lock()
	defer cn.cnTopology.Unlock()
	initCnTopology(cn.cnTopology)

	//Delete everything with an alias
	for _, link := range links {
		alias := link.Attrs().Alias
		name := link.Attrs().Name

		if alias == "" {
			continue
		}

		err := netlink.LinkDel(link)
		//TODO: Log this and continue
		if err != nil {
			fmt.Printf("Unable to delete link %s %v %v", name, link, err)
		}
	}

	//Check if we see any remanants
	//Attempt one last time to delete them
	links, err = netlink.LinkList()
	var badLinks []string
	for _, link := range links {
		alias := link.Attrs().Alias
		name := link.Attrs().Name

		if !validSnPrefix(name) {
			continue
		}

		// Be paranoid
		switch link.Type() {
		case "device":
			continue
		case "bond":
			continue
		}

		err := netlink.LinkDel(link)
		if err != nil {
			badLinks = append(badLinks, name+"::"+alias)
		}
	}

	if badLinks != nil {
		return fmt.Errorf("Failed to cleanup links %v", badLinks)
	}

	return nil
}
