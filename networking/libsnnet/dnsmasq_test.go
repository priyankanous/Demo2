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

package libsnnet_test

import (
	"io/ioutil"
	"net"
	"strconv"
	"testing"

	"github.com/01org/ciao/networking/libsnnet"
)

//Test normal operation DCHP/DNS server setup for a CNCI
//
//This test created a bridge, assigns an IP to it, attaches
//a bridge local dnsmasq process to serve DHCP and DNS on this
//brigde. It also tests for reload of the dnsmasq, stop and
//restart
//
//Test is expected to pass
func TestDnsmasq_Basic(t *testing.T) {

	id := "concuuid"
	tenant := "tenantuuid"
	reserved := 0
	subnet := net.IPNet{
		IP:   net.IPv4(192, 168, 1, 0),
		Mask: net.IPv4Mask(255, 255, 255, 0),
	}

	bridge, _ := libsnnet.NewBridge("dns_testbr")

	if err := bridge.Create(); err != nil {
		t.Errorf("Bridge creation failed: %v", err)
	}
	defer bridge.Destroy()

	d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge)
	if err != nil {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	if len(d.IPMap) != (256 - reserved - 3) {
		t.Errorf("Incorrect subnet calculation")
	}

	if err := d.Start(); err != nil {
		t.Errorf("DNS Masq Start: %v", err)
	}

	if err := d.Reload(); err != nil {
		t.Errorf("DNS Masq Reload: %v", err)
	}

	if err := d.Restart(); err != nil {
		t.Errorf("DNS Masq Restart: %v", err)
	}

	if err := d.Stop(); err != nil {
		t.Errorf("DNS Masq Stop: %v", err)
	}

	if err := d.Restart(); err != nil {
		t.Errorf("DNS Masq Restart: %v", err)
	}

	if err := d.Reload(); err != nil {
		t.Errorf("DNS Masq Reload: %v", err)
	}

	if err := d.Stop(); err != nil {
		t.Errorf("DNS Masq Stop: %v", err)
	}

}

//Dnsmasq negative test cases
//
//Tests that error conditions are handled gracefully
//Checks that duplicate subnet creation is handled properly
//Note: This test needs improvement
//
//Test is expected to pass
func TestDnsmasq_Negative(t *testing.T) {

	id := "concuuid"
	tenant := "tenantuuid"
	reserved := 10
	subnet := net.IPNet{
		IP:   net.IPv4(192, 168, 1, 0),
		Mask: net.IPv4Mask(255, 255, 255, 0),
	}

	bridge, _ := libsnnet.NewBridge("dns_testbr")

	if err := bridge.Create(); err != nil {
		t.Errorf("Bridge creation failed: %v", err)
	}
	defer bridge.Destroy()

	// Note: Reinstantiate d each time as that
	// is how it will be used
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Start(); err != nil {
			t.Errorf("DNS Masq Start: %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Attach should work
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if pid, err := d.Attach(); err != nil {
			t.Errorf("DNS Masq attach should not have failed %v", err)
		} else {
			t.Logf("attached to pid %v", pid)
			pidStr := strconv.Itoa(pid)
			fileName := "/proc/" + pidStr + "/cmdline"
			contents, err := ioutil.ReadFile(fileName)
			t.Logf("File [%v] has %v %v", fileName, string(contents), err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Restart should work
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Restart(); err != nil {
			t.Errorf("DNS Masq Restart failed: %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Reload should work
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Reload(); err != nil {
			t.Errorf("DNS Masq Reload failed: %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	// Duplicate creation
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Start(); err == nil {
			t.Errorf("DNS Masq Started twice")
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	// Stop it
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Stop(); err != nil {
			t.Errorf("DNS Masq Stop: %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Attach should fail
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if pid, err := d.Attach(); err == nil {
			t.Errorf("DNS Masq attach should have failed %v", pid)
			pidStr := strconv.Itoa(pid)
			fileName := "/proc/" + pidStr + "/cmdline"
			contents, err := ioutil.ReadFile(fileName)
			t.Errorf("File [%v] has %v %v", fileName, string(contents), err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Stop should fail
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Stop(); err == nil {
			t.Errorf("DNS Masq Stop should fail")
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Reload should fail
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Reload(); err == nil {
			t.Errorf("DNS Masq Reload should have failed")
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	//Restart should not fail
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Restart(); err != nil {
			t.Errorf("DNS Masq Restart should have failed %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

	// Stop it
	if d, err := libsnnet.NewDnsmasq(id, tenant, subnet, reserved, bridge); err == nil {
		if err := d.Stop(); err != nil {
			t.Errorf("DNS Masq Stop failed: %v", err)
		}
	} else {
		t.Errorf("DNS Masq New failed: %v", err)
	}

}
