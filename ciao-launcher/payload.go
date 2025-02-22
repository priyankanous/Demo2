/*
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
*/

package main

import (
	"bufio"
	"bytes"
	"encoding/gob"
	"fmt"
	"net"
	"os"
	"path"
	"regexp"
	"strings"

	"github.com/golang/glog"

	"github.com/01org/ciao/networking/libsnnet"
	"github.com/01org/ciao/payloads"

	"gopkg.in/yaml.v2"
)

type payloadError struct {
	err  error
	code string
}

type vmConfig struct {
	Cpus        int
	Mem         int
	Disk        int
	Instance    string
	Image       string
	Legacy      bool
	Container   bool
	NetworkNode bool
	VnicMAC     string
	VnicIP      string
	ConcIP      string
	SubnetIP    string
	TennantUUID string
	ConcUUID    string
	VnicUUID    string
	SSHPort     int
}

type extractedDoc struct {
	doc       []string
	realStart int
	realEnd   int
}

var indentedRegexp *regexp.Regexp
var startRegexp *regexp.Regexp
var uuidRegexp *regexp.Regexp

func init() {
	indentedRegexp = regexp.MustCompile("\\s+.*")
	startRegexp = regexp.MustCompile("^start\\s*:\\s*$")
	uuidRegexp = regexp.MustCompile("^[0-9a-fA-F]+(-[0-9a-fA-F]+)*$")
}

func printCloudinit(data *payloads.Start) {
	start := &data.Start
	glog.Info("cloud-init file content")
	glog.Info("-----------------------")
	glog.Infof("Instance UUID:        %v", start.InstanceUUID)
	glog.Infof("Disk image UUID:      %v", start.ImageUUID)
	glog.Infof("FW Type:              %v", start.FWType)
	glog.Infof("VM Type:              %v", start.VMType)
	glog.Infof("TennantUUID:          %v", start.TenantUUID)
	net := &start.Networking
	glog.Infof("VnicMAC:              %v", net.VnicMAC)
	glog.Infof("VnicIP:               %v", net.PrivateIP)
	glog.Infof("ConcIP:               %v", net.ConcentratorIP)
	glog.Infof("SubnetIP:             %v", net.Subnet)
	glog.Infof("ConcUUID:             %v", net.ConcentratorUUID)
	glog.Infof("VnicUUID:             %v", net.VnicUUID)

	glog.Info("Requested resources:")
	for i := range start.RequestedResources {
		glog.Infof("%8s:     %v", start.RequestedResources[i].Type,
			start.RequestedResources[i].Value)
	}
}

func computeSSHPort(networkNode bool, vnicIP string) int {
	if networkNode || vnicIP == "" {
		return 0
	}

	ip := net.ParseIP(vnicIP)
	if ip == nil {
		return 0
	}

	ip = ip.To4()
	if ip == nil {
		return 0
	}

	port, err := libsnnet.DebugSSHPortForIP(ip)
	if err != nil {
		return 0
	}

	return port
}

func parseStartPayload(data []byte) (*vmConfig, *payloadError) {
	var clouddata payloads.Start

	err := yaml.Unmarshal(data, &clouddata)
	if err != nil {
		return nil, &payloadError{err, payloads.InvalidPayload}
	}
	printCloudinit(&clouddata)

	start := &clouddata.Start

	instance := strings.TrimSpace(start.InstanceUUID)
	if !uuidRegexp.MatchString(instance) {
		err = fmt.Errorf("Invalid instance id received: %s", instance)
		return nil, &payloadError{err, payloads.InvalidData}
	}

	fwType := start.FWType
	if fwType != "" && fwType != payloads.Legacy && fwType != payloads.EFI {
		err = fmt.Errorf("Invalid fwtype received: %s", fwType)
		return nil, &payloadError{err, payloads.InvalidData}
	}
	legacy := fwType == payloads.Legacy

	vmType := start.VMType
	if vmType != "" && vmType != payloads.QEMU && vmType != payloads.Docker {
		err = fmt.Errorf("Invalid vmtype received: %s", vmType)
		return nil, &payloadError{err, payloads.InvalidData}
	}

	var disk, cpus, mem int
	var networkNode bool
	var image string

	container := vmType == payloads.Docker
	if container {
		image = start.DockerImage
	} else {
		image = start.ImageUUID
	}

	for i := range start.RequestedResources {
		switch start.RequestedResources[i].Type {
		case payloads.VCPUs:
			cpus = start.RequestedResources[i].Value
		case payloads.MemMB:
			mem = start.RequestedResources[i].Value
		case payloads.DiskMB:
			disk = start.RequestedResources[i].Value
		case payloads.NetworkNode:
			networkNode = start.RequestedResources[i].Value != 0
		}
	}

	net := &start.Networking
	vnicIP := strings.TrimSpace(net.PrivateIP)
	sshPort := computeSSHPort(networkNode, vnicIP)

	return &vmConfig{Cpus: cpus,
		Mem:         mem,
		Disk:        disk,
		Instance:    instance,
		Image:       image,
		Legacy:      legacy,
		Container:   container,
		NetworkNode: networkNode,
		VnicMAC:     strings.TrimSpace(net.VnicMAC),
		VnicIP:      vnicIP,
		ConcIP:      strings.TrimSpace(net.ConcentratorIP),
		SubnetIP:    strings.TrimSpace(net.Subnet),
		TennantUUID: strings.TrimSpace(start.TenantUUID),
		ConcUUID:    strings.TrimSpace(net.ConcentratorUUID),
		VnicUUID:    strings.TrimSpace(net.VnicUUID),
		SSHPort:     sshPort,
	}, nil
}

func generateStartError(instance string, startErr *startError) (out []byte, err error) {
	sf := &payloads.ErrorStartFailure{
		InstanceUUID: instance,
		Reason:       startErr.code,
	}
	return yaml.Marshal(sf)
}

func generateStopError(instance string, stopErr *stopError) (out []byte, err error) {
	sf := &payloads.ErrorStopFailure{
		InstanceUUID: instance,
		Reason:       stopErr.code,
	}
	return yaml.Marshal(sf)
}

func generateRestartError(instance string, restartErr *restartError) (out []byte, err error) {
	rf := &payloads.ErrorRestartFailure{
		InstanceUUID: instance,
		Reason:       restartErr.code,
	}
	return yaml.Marshal(rf)
}

func generateDeleteError(instance string, deleteErr *deleteError) (out []byte, err error) {
	df := &payloads.ErrorDeleteFailure{
		InstanceUUID: instance,
		Reason:       deleteErr.code,
	}
	return yaml.Marshal(df)
}

func generateNetEventPayload(ssntpEvent *libsnnet.SsntpEventInfo, agentUUID string) ([]byte, error) {
	var event interface{}
	var eventData *payloads.TenantAddedEvent

	switch ssntpEvent.Event {
	case libsnnet.SsntpTunAdd:
		add := &payloads.EventTenantAdded{}
		event = add
		eventData = &add.TenantAdded
	case libsnnet.SsntpTunDel:
		del := &payloads.EventTenantRemoved{}
		event = del
		eventData = &del.TenantRemoved
	default:
		return nil, fmt.Errorf("Unsupported ssntpEventInfo type: %d",
			ssntpEvent.Event)
	}

	eventData.AgentUUID = agentUUID
	eventData.AgentIP = ssntpEvent.CnIP
	eventData.TenantUUID = ssntpEvent.TenantID
	eventData.TenantSubnet = ssntpEvent.SubnetID
	eventData.ConcentratorUUID = ssntpEvent.ConcID
	eventData.ConcentratorIP = ssntpEvent.CnciIP
	eventData.SubnetKey = ssntpEvent.SubnetKey

	return yaml.Marshal(event)
}

func parseRestartPayload(data []byte) (string, *payloadError) {
	var clouddata payloads.Restart

	err := yaml.Unmarshal(data, &clouddata)
	if err != nil {
		return "", &payloadError{err, payloads.RestartInvalidPayload}
	}

	instance := strings.TrimSpace(clouddata.Restart.InstanceUUID)
	if !uuidRegexp.MatchString(instance) {
		err = fmt.Errorf("Invalid instance id received: %s", instance)
		return "", &payloadError{err, payloads.RestartInvalidData}
	}
	return instance, nil
}

func parseDeletePayload(data []byte) (string, *payloadError) {
	var clouddata payloads.Delete

	err := yaml.Unmarshal(data, &clouddata)
	if err != nil {
		return "", &payloadError{err, payloads.DeleteInvalidPayload}
	}

	instance := strings.TrimSpace(clouddata.Delete.InstanceUUID)
	if !uuidRegexp.MatchString(instance) {
		err = fmt.Errorf("Invalid instance id received: %s", instance)
		return "", &payloadError{err, payloads.DeleteInvalidData}
	}
	return instance, nil
}

func parseStopPayload(data []byte) (string, *payloadError) {
	var clouddata payloads.Stop

	err := yaml.Unmarshal(data, &clouddata)
	if err != nil {
		glog.Errorf("YAML error: %v", err)
		return "", &payloadError{err, payloads.StopInvalidPayload}
	}

	instance := strings.TrimSpace(clouddata.Stop.InstanceUUID)
	if !uuidRegexp.MatchString(instance) {
		err = fmt.Errorf("Invalid instance id received: %s", instance)
		return "", &payloadError{err, payloads.StopInvalidData}
	}
	return instance, nil
}

func loadVMConfig(instanceDir string) (*vmConfig, error) {
	cfgFilePath := path.Join(instanceDir, instanceState)
	cfgFile, err := os.Open(cfgFilePath)
	if err != nil {
		glog.Errorf("Unable to open instance file %s", cfgFilePath)
		return nil, err
	}

	dec := gob.NewDecoder(cfgFile)
	cfg := &vmConfig{}
	err = dec.Decode(cfg)
	_ = cfgFile.Close()

	if err != nil {
		glog.Error("Unable to retrieve state info")
		return nil, err
	}

	return cfg, nil
}

func linesToBytes(doc []string, buf *bytes.Buffer) {
	for _, line := range doc {
		_, _ = buf.WriteString(line)
		_, _ = buf.WriteString("\n")
	}
}

func extractDocument(doc *extractedDoc, buf *bytes.Buffer) {
	linesToBytes(doc.doc[doc.realStart:doc.realEnd], buf)
}

func extractStartYaml(lines []string, start int, s, ci *bytes.Buffer) {
	cnStart := 0

	docStartFound := false
	for ; cnStart < start; cnStart++ {
		line := lines[cnStart]
		if strings.HasPrefix(line, "---") {
			docStartFound = true
			cnStart++
			break
		}
	}

	if !docStartFound {
		cnStart = 0
	}

	linesToBytes(lines[cnStart:start], ci)

	i := start
	if i < len(lines) {
		_, _ = s.WriteString(lines[i])
		_, _ = s.WriteString("\n")
		i++
	}
	for ; i < len(lines) && (indentedRegexp.MatchString(lines[i]) || lines[i] == ""); i++ {
		_, _ = s.WriteString(lines[i])
		_, _ = s.WriteString("\n")
	}

	if i < len(lines) && !strings.HasPrefix(lines[i], "...") {
		linesToBytes(lines[i:], ci)
	}
}

func findDocument(lines []string) (doc *extractedDoc, endOfNextDoc int) {
	var realStart int
	var realEnd int
	docStartFound := false
	docEndFound := false

	start := len(lines) - 1
	line := lines[start]
	if strings.HasPrefix(line, "...") {
		docEndFound = true
		realEnd = start
		start--
	}

	for ; start >= 0; start-- {
		line := lines[start]
		if strings.HasPrefix(line, "---") {
			docStartFound = true
			break
		}
		if strings.HasPrefix(line, "...") {
			start++
			break
		}
	}

	if docStartFound {
		realStart = start + 1
		for start = start - 1; start >= 0; start-- {
			line := lines[start]
			if !strings.HasPrefix(line, "%") {
				break
			}
		}
		start++
	} else {
		if start < 0 {
			start = 0
		}
		realStart = start
	}

	if !docEndFound {
		realEnd = len(lines)
	}

	realStart -= start
	realEnd -= start

	return &extractedDoc{lines[start:len(lines)], realStart, realEnd}, start
}

func splitYaml(data []byte) ([]byte, []byte, []byte) {

	var s bytes.Buffer
	var ci bytes.Buffer
	var md bytes.Buffer

	foundStart := -1
	lines := make([]string, 0, 256)
	docs := make([]*extractedDoc, 0, 3)

	reader := bytes.NewReader(data)
	scanner := bufio.NewScanner(reader)
	for scanner.Scan() {
		line := scanner.Text()
		if foundStart == -1 && startRegexp.MatchString(line) {
			foundStart = len(lines)
		}
		lines = append(lines, line)
	}

	endOfNextDoc := len(lines)

	for endOfNextDoc > 0 {
		var doc *extractedDoc
		doc, endOfNextDoc = findDocument(lines[:endOfNextDoc])
		docs = append([]*extractedDoc{doc}, docs...)
	}

	if len(docs) == 1 {
		if foundStart != -1 {
			extractStartYaml(docs[0].doc, foundStart, &s, &ci)
		} else {
			extractDocument(docs[0], &ci)
		}
	} else if len(docs) == 2 {
		if foundStart != -1 {
			if foundStart < len(docs[0].doc) {
				extractStartYaml(docs[0].doc, foundStart, &s, &ci)
				extractDocument(docs[1], &md)
			} else {
				extractStartYaml(docs[1].doc, foundStart-len(docs[0].doc), &s, &ci)
				extractDocument(docs[0], &md)
			}
		} else {
			extractDocument(docs[0], &ci)
			extractDocument(docs[1], &md)
		}
	} else if foundStart != -1 && foundStart < len(docs[0].doc)+len(docs[1].doc)+len(docs[2].doc) {
		notStart := make([]*extractedDoc, 0, 2)
		sum := 0
		for i := 0; i < 3; i++ {
			newSum := sum + len(docs[i].doc)
			if foundStart >= sum && foundStart < newSum {
				extractDocument(docs[i], &s)
			} else {
				notStart = append(notStart, docs[i])
			}
			sum = newSum
		}
		extractDocument(notStart[0], &ci)
		extractDocument(notStart[1], &md)
	} else {
		glog.Warning("Unable to split payload into documents")
	}

	return s.Bytes(), ci.Bytes(), md.Bytes()
}
