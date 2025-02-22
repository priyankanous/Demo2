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
	"errors"
	"fmt"
	"github.com/01org/ciao/ciao-controller/types"
	"github.com/golang/glog"
	"time"
)

func (c *controller) evacuateNode(nodeID string) error {
	// should I bother to see if nodeID is valid?
	go c.client.EvacuateNode(nodeID)
	return nil
}

func (c *controller) restartInstance(instanceID string) error {
	// should I bother to see if instanceID is valid?
	// get node id.  If there is no node id we can't send a restart
	nodeID, state, err := c.ds.GetInstanceInfo(instanceID)
	if err != nil {
		return err
	}

	if nodeID == "" {
		return errors.New("Instance Not Assigned to Node")
	}

	if state != "exited" {
		return errors.New("You may only restart paused instances")
	}

	go c.client.RestartInstance(instanceID, nodeID)
	return nil
}

func (c *controller) stopInstance(instanceID string) error {
	// get node id.  If there is no node id we can't send a delete
	nodeID, state, err := c.ds.GetInstanceInfo(instanceID)
	if err != nil {
		return err
	}

	if nodeID == "" {
		return errors.New("Instance Not Assigned to Node")
	}

	if state == "pending" {
		return errors.New("You may not stop a pending instance")
	}

	go c.client.StopInstance(instanceID, nodeID)
	return nil
}

func (c *controller) deleteInstance(instanceID string) error {
	// get node id.  If there is no node id we can't send a delete
	nodeID, _, err := c.ds.GetInstanceInfo(instanceID)
	if err != nil {
		return err
	}

	if nodeID == "" {
		return errors.New("Instance Not Assigned to Node")
	}

	go c.client.DeleteInstance(instanceID, nodeID)
	return nil
}

func (c *controller) startWorkload(workloadID string, tenantID string, instances int, trace bool, label string) ([]*types.Instance, error) {
	var e error

	if instances == 0 {
		return nil, errors.New("Missing number of instances to start")
	}

	wl, err := c.ds.GetWorkload(workloadID)
	if err != nil {
		return nil, err
	}

	if !isCNCIWorkload(wl) {
		tenant, err := c.ds.GetTenant(tenantID)
		if err != nil {
			return nil, err
		}

		if tenant == nil {
			if *noNetwork {
				_, err := c.ds.AddTenant(tenantID)
				if err != nil {
					return nil, err
				}
			} else {

				err = c.addTenant(tenantID)
				if err != nil {
					return nil, err
				}
			}
		} else if tenant.CNCIIP == "" {
			if !*noNetwork {
				_ = c.addTenant(tenantID)
				tenant, err = c.ds.GetTenant(tenantID)
				if tenant.CNCIIP == "" {
					return nil, errors.New("Unable to Launch Tenant CNCI")
				}
			}
		}
	}

	var newInstances []*types.Instance

	for i := 0; i < instances; i++ {
		startTime := time.Now()
		instance, err := newInstance(c, tenantID, wl)
		if err != nil {
			glog.V(2).Info("error newInstance")
			e = err
			continue
		}
		instance.startTime = startTime

		ok, err := instance.Allowed()
		if ok {
			err = instance.Add()
			if err != nil {
				glog.V(2).Info("error adding instance")
				instance.Clean()
				e = err
				continue
			}

			newInstances = append(newInstances, &instance.Instance)
			if trace == false {
				go c.client.StartWorkload(instance.newConfig.config)
			} else {
				go c.client.StartTracedWorkload(instance.newConfig.config, instance.startTime, label)
			}
		} else {
			instance.Clean()
			if err != nil {
				e = err
				continue
			} else {
				// stop if we are over limits
				return nil, errors.New("Over Tenant Limits")
			}
		}
	}

	return newInstances, e
}

func (c *controller) launchCNCI(tenantID string) (err error) {
	workloadID, err := c.ds.GetCNCIWorkloadID()
	if err != nil {
		return err
	}

	ch := make(chan bool)

	c.ds.AddTenantChan(ch, tenantID)

	_, err = c.startWorkload(workloadID, tenantID, 1, false, "")
	if err != nil {
		return err
	}

	success := <-ch

	if success {
		return nil
	}
	msg := fmt.Sprintf("Failed to Launch CNCI for %s", tenantID)
	return errors.New(msg)
}

func (c *controller) addTenant(id string) error {
	// create new entry in datastore
	_, err := c.ds.AddTenant(id)
	if err != nil {
		return err
	}

	// start up a CNCI. this will block till the
	// CNCI started event is returned
	return c.launchCNCI(id)
}
