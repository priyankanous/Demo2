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

package main

import (
	"bytes"
	"encoding/gob"
	"encoding/json"
	"flag"
	"fmt"
	"io/ioutil"
	"net"
	"net/http"
	"os/exec"
	"sync"
	"time"

	"github.com/boltdb/bolt"
	"github.com/docker/distribution/uuid"
	"github.com/docker/libnetwork/drivers/remote/api"
	ipamapi "github.com/docker/libnetwork/ipams/remote/api"
	"github.com/golang/glog"
	"github.com/gorilla/mux"
)

type epVal struct {
	Cveth string
	Hveth string
}

type nwVal struct {
	Bridge  string
	Gateway net.IPNet
}

var counter int

var epMap struct {
	sync.Mutex
	m map[string]*epVal
}

var nwMap struct {
	sync.Mutex
	m map[string]*nwVal
}

var dbFile string
var db *bolt.DB

func init() {
	epMap.m = make(map[string]*epVal)
	nwMap.m = make(map[string]*nwVal)
	dbFile = "/tmp/bolt.db"
}

func sendResponse(resp interface{}, w http.ResponseWriter) error {
	rb, err := json.Marshal(resp)
	glog.Infof("Sending response := %v, %v", resp, err)
	fmt.Fprintf(w, "%s", rb)
	return err
}

func getBody(r *http.Request) ([]byte, error) {
	body, err := ioutil.ReadAll(r.Body)
	glog.Infof("URL [%s] Body [%s] Error [%v]", r.URL.Path[1:], string(body), err)
	return body, err
}

func handler(w http.ResponseWriter, r *http.Request) {
	body, _ := getBody(r)
	resp := api.Response{}
	resp.Err = "Unhandled API request " + string(r.URL.Path[1:]) + " " + string(body)
	sendResponse(resp, w)
}

func handlerPluginActivate(w http.ResponseWriter, r *http.Request) {
	getBody(r)
	//TODO: Where is this encoding?
	resp := `{
    "Implements": ["NetworkDriver", "IpamDriver"]
}`
	fmt.Fprintf(w, "%s", resp)
}

func handlerGetCapabilities(w http.ResponseWriter, r *http.Request) {
	getBody(r)
	resp := api.GetCapabilityResponse{Scope: "local"}
	sendResponse(resp, w)
}

func handlerCreateNetwork(w http.ResponseWriter, r *http.Request) {
	resp := api.CreateNetworkResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.CreateNetworkRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	v, ok := req.Options["com.docker.network.generic"].(map[string]interface{})
	if !ok {
		resp.Err = "Error: network options incorrect or unspecified. Please provide bridge info"
		sendResponse(resp, w)
		return
	}

	bridge, ok := v["bridge"].(string)
	if !ok {
		resp.Err = "Error: network incorrect or unspecified. Please provide bridge info"
		sendResponse(resp, w)
		return
	}

	nwMap.Lock()
	defer nwMap.Unlock()

	//Record the docker network UUID to SDN bridge mapping
	//This has to survive a plugin crash/restart and needs to be persisted
	nwMap.m[req.NetworkID] = &nwVal{
		Bridge:  bridge,
		Gateway: *req.IPv4Data[0].Gateway,
	}

	if err := dbAdd("nwMap", req.NetworkID, nwMap.m[req.NetworkID]); err != nil {
		glog.Errorf("Unable to update db %v", err)
	}

	//This will be done in the SDN controller before the API is invoked
	cmd := "ip"
	args := []string{"link", "add", bridge, "type", "bridge"}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error CreateNetwork: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", bridge, "up"}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error CreateNetwork: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerDeleteNetwork(w http.ResponseWriter, r *http.Request) {
	resp := api.DeleteNetworkResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.DeleteNetworkRequest{}
	if err = json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	glog.Infof("Delete Network := %v", req.NetworkID)

	//This would have already been done in the SDN controller
	//Remove the UUID to bridge mapping in cache and in the
	//persistent data store
	nwMap.Lock()
	bridge := nwMap.m[req.NetworkID].Bridge
	delete(nwMap.m, req.NetworkID)
	if err := dbDelete("nwMap", req.NetworkID); err != nil {
		glog.Errorf("Unable to update db %v", err)
	}
	nwMap.Unlock()

	cmd := "ip"
	args := []string{"link", "del", bridge}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error DeleteNetwork: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)

	return
}

func handlerEndpointOperInfof(w http.ResponseWriter, r *http.Request) {
	resp := api.EndpointInfoResponse{}
	body, err := getBody(r)

	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.EndpointInfoRequest{}
	err = json.Unmarshal(body, &req)

	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerCreateEndpoint(w http.ResponseWriter, r *http.Request) {
	resp := api.CreateEndpointResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.CreateEndpointRequest{}
	if err = json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	nwMap.Lock()
	bridge := nwMap.m[req.NetworkID].Bridge
	nwMap.Unlock()

	epMap.Lock()
	//These are setup by the SDN controller
	counter++
	hVeth := fmt.Sprintf("hveth%d", counter)
	cVeth := fmt.Sprintf("cveth%d", counter)
	epMap.m[req.EndpointID] = &epVal{
		Cveth: cVeth,
		Hveth: hVeth,
	}

	if err := dbAdd("epMap", req.EndpointID, epMap.m[req.EndpointID]); err != nil {
		glog.Errorf("Unable to update db %v", err)
	}
	if err := dbAdd("global", "counter", counter); err != nil {
		glog.Errorf("Unable to update db %v", err)
	}
	epMap.Unlock()

	// This would have been done in the SDN controller
	// Just update the cache and persistent data base
	cmd := "ip"
	args := []string{"link", "add", "dev", hVeth, "type", "veth",
		"peer", "name", cVeth}

	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", hVeth, "mtu", "1400"}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", cVeth, "mtu", "1400", "addr", req.Interface.MacAddress}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", hVeth, "alias", hVeth + "_" + cVeth}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", hVeth, "master", bridge}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	args = []string{"link", "set", hVeth, "up"}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointCreate: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerDeleteEndpoint(w http.ResponseWriter, r *http.Request) {
	resp := api.DeleteEndpointResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.DeleteEndpointRequest{}
	if err = json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	epMap.Lock()
	m := epMap.m[req.EndpointID]
	delete(epMap.m, req.EndpointID)
	if err := dbDelete("epMap", req.EndpointID); err != nil {
		glog.Errorf("Unable to update db %v", err)
	}
	epMap.Unlock()

	//This will be done in the SDN controller once the
	//container is deleted. However at this point there is
	//a disconnect between the docker data base and SDN database
	cmd := "ip"
	args := []string{"link", "del", m.Hveth}
	if err := exec.Command(cmd, args...).Run(); err != nil {
		glog.Infof("ERROR: [%v] [%v] [%v] ", cmd, args, err)
		resp.Err = fmt.Sprintf("Error EndPointDelete: [%v] [%v] [%v]",
			cmd, args, err)
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerJoin(w http.ResponseWriter, r *http.Request) {
	resp := api.JoinResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.JoinRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	nwMap.Lock()
	epMap.Lock()
	nm := nwMap.m[req.NetworkID]
	em := epMap.m[req.EndpointID]
	nwMap.Unlock()
	epMap.Unlock()

	resp.Gateway = nm.Gateway.IP.String()
	resp.InterfaceName = &api.InterfaceName{
		SrcName:   em.Cveth,
		DstPrefix: "eth",
	}
	sendResponse(resp, w)
}

func handlerLeave(w http.ResponseWriter, r *http.Request) {
	resp := api.LeaveResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.LeaveRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerDiscoverNew(w http.ResponseWriter, r *http.Request) {
	resp := api.DiscoveryResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.DiscoveryNotification{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerDiscoverDelete(w http.ResponseWriter, r *http.Request) {
	resp := api.DiscoveryResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.DiscoveryNotification{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerExternalConnectivity(w http.ResponseWriter, r *http.Request) {
	resp := api.ProgramExternalConnectivityResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.ProgramExternalConnectivityRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func handlerRevokeExternalConnectivity(w http.ResponseWriter, r *http.Request) {
	resp := api.RevokeExternalConnectivityResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := api.RevokeExternalConnectivityResponse{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Err = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func ipamGetCapabilities(w http.ResponseWriter, r *http.Request) {
	getBody(r)
	resp := ipamapi.GetCapabilityResponse{RequiresMACAddress: true}
	sendResponse(resp, w)
}

func ipamGetDefaultAddressSpaces(w http.ResponseWriter, r *http.Request) {
	resp := ipamapi.GetAddressSpacesResponse{}
	getBody(r)

	resp.GlobalDefaultAddressSpace = ""
	resp.LocalDefaultAddressSpace = ""
	sendResponse(resp, w)
}

func ipamRequestPool(w http.ResponseWriter, r *http.Request) {
	resp := ipamapi.RequestPoolResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := ipamapi.RequestPoolRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	resp.PoolID = uuid.Generate().String()
	resp.Pool = req.Pool
	sendResponse(resp, w)
}

func ipamReleasePool(w http.ResponseWriter, r *http.Request) {
	resp := ipamapi.ReleasePoolResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := ipamapi.ReleasePoolRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func ipamRequestAddress(w http.ResponseWriter, r *http.Request) {
	resp := ipamapi.RequestAddressResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := ipamapi.RequestAddressRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	//TODO: Should come from the subnet mask for the subnet
	if req.Address != "" {
		resp.Address = req.Address + "/24"
	} else {
		//DOCKER BUG: The preferred address supplied in --ip does not show up.
		//Bug fixed in docker 1.11
		resp.Error = "Error: Request does not have IP address. Specify using --ip"
	}
	sendResponse(resp, w)
}

func ipamReleaseAddress(w http.ResponseWriter, r *http.Request) {
	resp := ipamapi.ReleaseAddressResponse{}

	body, err := getBody(r)
	if err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	req := ipamapi.ReleaseAddressRequest{}
	if err := json.Unmarshal(body, &req); err != nil {
		resp.Error = "Error: " + err.Error()
		sendResponse(resp, w)
		return
	}

	sendResponse(resp, w)
}

func dbTableInit(tables []string) (err error) {

	glog.Infof("dbInit Tables := %v", tables)
	for i, v := range tables {
		glog.Infof("table[%v] := %v, %v", i, v, []byte(v))
	}

	err = db.Update(func(tx *bolt.Tx) error {
		for _, table := range tables {
			_, err := tx.CreateBucketIfNotExists([]byte(table))
			if err != nil {
				return fmt.Errorf("Bucket creation error: %v %v", table, err)
			}
		}
		return nil
	})

	if err != nil {
		glog.Errorf("Table creation error %v", err)
	}

	return err
}

func dbAdd(table string, key string, value interface{}) (err error) {

	err = db.Update(func(tx *bolt.Tx) error {
		var v bytes.Buffer

		if err := gob.NewEncoder(&v).Encode(value); err != nil {
			glog.Errorf("Encode Error: %v %v", err, value)
			return err
		}

		bucket := tx.Bucket([]byte(table))
		if bucket == nil {
			return fmt.Errorf("Bucket %v not found", table)
		}

		err = bucket.Put([]byte(key), v.Bytes())
		if err != nil {
			return fmt.Errorf("Key Store error: %v %v %v %v", table, key, value, err)
		}
		return nil
	})

	return err
}

func dbDelete(table string, key string) (err error) {

	err = db.Update(func(tx *bolt.Tx) error {

		bucket := tx.Bucket([]byte(table))
		if bucket == nil {
			return fmt.Errorf("Bucket %v not found", table)
		}

		err = bucket.Delete([]byte(key))
		if err != nil {
			return fmt.Errorf("Key Delete error: %v %v ", key, err)
		}
		return nil
	})

	return err
}

func dbGet(table string, key string) (value interface{}, err error) {

	err = db.View(func(tx *bolt.Tx) error {

		bucket := tx.Bucket([]byte(table))
		if bucket == nil {
			return fmt.Errorf("Bucket %v not found", table)
		}

		val := bucket.Get([]byte(key))
		if val == nil {
			return nil
		}

		v := bytes.NewReader(val)
		if err := gob.NewDecoder(v).Decode(value); err != nil {
			glog.Errorf("Decode Error: %v %v %v", table, key, err)
			return err
		}

		return nil
	})

	return value, err
}

func initDb() error {

	options := bolt.Options{
		Timeout: 3 * time.Second,
	}

	var err error
	db, err = bolt.Open(dbFile, 0644, &options)
	if err != nil {
		return fmt.Errorf("dbInit failed %v", err)
	}

	tables := []string{"global", "nwMap", "epMap"}
	if err := dbTableInit(tables); err != nil {
		return fmt.Errorf("dbInit failed %v", err)
	}

	c, err := dbGet("global", "counter")
	if err != nil {
		glog.Errorf("dbGet failed %v", err)
		counter = 100
	} else {
		var ok bool
		counter, ok = c.(int)
		if !ok {
			counter = 100
		}
	}

	err = db.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte("nwMap"))

		b.ForEach(func(k, v []byte) error {
			vr := bytes.NewReader(v)
			nVal := &nwVal{}
			if err := gob.NewDecoder(vr).Decode(nVal); err != nil {
				return fmt.Errorf("Decode Error: %v %v %v", string(k), string(v), err)
			}
			nwMap.m[string(k)] = nVal
			glog.Infof("nwMap key=%v, value=%v\n", string(k), nVal)
			return nil
		})
		return nil
	})

	err = db.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte("epMap"))

		b.ForEach(func(k, v []byte) error {
			vr := bytes.NewReader(v)
			eVal := &epVal{}
			if err := gob.NewDecoder(vr).Decode(eVal); err != nil {
				return fmt.Errorf("Decode Error: %v %v %v", string(k), string(v), err)
			}
			epMap.m[string(k)] = eVal
			glog.Infof("epMap key=%v, value=%v\n", string(k), eVal)
			return nil
		})
		return nil
	})

	return err
}

func main() {
	flag.Parse()

	if err := initDb(); err != nil {
		glog.Fatalf("db init failed, quitting %v", err)
	}
	defer db.Close()

	r := mux.NewRouter()
	r.HandleFunc("/Plugin.Activate", handlerPluginActivate)
	r.HandleFunc("/NetworkDriver.GetCapabilities", handlerGetCapabilities)
	r.HandleFunc("/NetworkDriver.CreateNetwork", handlerCreateNetwork)
	r.HandleFunc("/NetworkDriver.DeleteNetwork", handlerDeleteNetwork)
	r.HandleFunc("/NetworkDriver.CreateEndpoint", handlerCreateEndpoint)
	r.HandleFunc("/NetworkDriver.DeleteEndpoint", handlerDeleteEndpoint)
	r.HandleFunc("/NetworkDriver.EndpointOperInfo", handlerEndpointOperInfof)
	r.HandleFunc("/NetworkDriver.Join", handlerJoin)
	r.HandleFunc("/NetworkDriver.Leave", handlerLeave)
	r.HandleFunc("/NetworkDriver.DiscoverNew", handlerDiscoverNew)
	r.HandleFunc("/NetworkDriver.DiscoverDelete", handlerDiscoverDelete)
	r.HandleFunc("/NetworkDriver.ProgramExternalConnectivity", handlerExternalConnectivity)
	r.HandleFunc("/NetworkDriver.RevokeExternalConnectivity", handlerExternalConnectivity)

	r.HandleFunc("/IpamDriver.GetCapabilities", ipamGetCapabilities)
	r.HandleFunc("/IpamDriver.GetDefaultAddressSpaces", ipamGetDefaultAddressSpaces)
	r.HandleFunc("/IpamDriver.RequestPool", ipamRequestPool)
	r.HandleFunc("/IpamDriver.ReleasePool", ipamReleasePool)
	r.HandleFunc("/IpamDriver.RequestAddress", ipamRequestAddress)
	r.HandleFunc("/IpamDriver.ReleaseAddress", ipamReleaseAddress)

	r.HandleFunc("/", handler)
	http.ListenAndServe("127.0.0.1:9999", r)
}
