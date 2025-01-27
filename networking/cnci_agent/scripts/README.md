# CNCI Image Creation Tools #

## Overview ##

Helper scripts to provison and test CNCI Images

## CNCI Image Provisoning ##

The CNCI Image creation scripts helps you create a CNCI Image from
a clear linux cloud image. Clear cloud images can be obtained from

https://download.clearlinux.org/image/

The scripts are used to provison the image with the CNCI Agent and
the certificates it needs to connect to the ciao-scheduler.

0. The image has to be preprovisoned with the following tools 
	- dnsmasq
	- iptables
1. Place the appropriate certificates under the certs directory

```
	├── certs
	│   ├── CAcert-server-localhost.pem
	│   ├── cert-client-localhost.pem
```


2. Ensure that you have built and installed the cnci agent 
```
	cd $GOPATH/src/github.com/01org/ciao/networking/cnci_agent
   	go install
```
3. Update the image
```
./update_cnci_cloud_image.sh
```

This will yield a provisoned image. This can be used as a CNCI VM.

## CNCI Verification ##

A simple script to launch the CNCI VM using QEMU and a sample cloud-init
configuration. The cloud-init is setup to check if the CNCI Agent can
be sucessfully launched within this VM

0. Customize the cloud-init files

```
	├── ciao
	│   └──	ciao.yaml
	├── seed
	│   └── openstack
	│       └── latest
	├── meta_data.json
	│           └── user_data
```

1. Launch the VM (it will cloud-init the image)

```
 sudo ./run_cnci_vm.sh
```
2. Log into the VM using the cloud-init provisioned user/password (default ciao/ciao)
3. Verify the successful launch of the CNCI using
   systemctl status cnci-agent

An output of the form shown below indicates a successful provisoning of
the agent.

```
ciao@cncihostname ~ $ systemctl status cnci-agent -l
● cnci-agent.service - Ciao CNCI Agent
   Loaded: loaded (/usr/lib/systemd/system/cnci-agent.service; enabled; vendor preset: disabled)
   Active: active (running) since Thu 2016-04-07 20:34:40 UTC; 27s ago
 Main PID: 229 (cnci_agent)
   CGroup: /system.slice/cnci-agent.service
           └─229 /usr/sbin/cnci_agent -server auto -v 3
```

Note: This boot will result in the cloud-init of the image. Hence the orginal
image generated prior to the verification should be used as the CNCI image.

