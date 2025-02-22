# ciao-launcher

ciao-launcher is an SSNTP client that manages VM and container instances.  It runs on
compute and network nodes executing commands it receives from SSNTP servers,
primarily [scheduler](https://github.com/01org/ciao/blob/master/ciao-scheduler/README.md).  Its current feature set includes:

1. Launching, stopping, restarting and deleting of docker containers and qemu VMs on compute and network nodes
2. Basic monitoring of VMs and containers
3. Collection and transmission of compute node and instance (container or VM) statistics
4. Reconnection to existing VMs and containers on start up

We'll take a look at these features in more detail a little later on.  First,
let's see what is required to install and run launcher.

# Installation

## Getting the code

ciao-launcher can be downloaded and installed using go get

```go get github.com/01org/ciao/ciao-launcher```

The resulting binary will be placed in $GOPATH/bin, which you should already have
in your PATH.

## Installing Certificates

LINK NEEDED TO CERTIFICATE CREATION PROCESS

Secondly you need to generate a certificate pair to allow launcher to connect to
the SSNTP server.  The default location for these certificates is /etc/pki/ciao
So you can either copy the certs to this location, e.g.,

```
sudo mkdir -p /etc/pki/ciao
cp CAcert-server-localhost.pem /etc/pki/ciao
cp cert-client-localhost.pem /etc/pki/ciao
```

or provide alternative locations for these files via the -cert and -cacert command
line options.

## Install Dependencies

ciao-launcher has dependencies on five external packages:

1. qemu-system-x86_64 and qemu-img, to launch the VMs and create qcow images
2. xorriso, to create ISO images for cloudinit
3. ovmf, EFI firmware required for some images
4. fuser, part of most distro's psmisc package
5. docker, to manage docker containers

All of these packages need to be installed on your compute node before launcher
can be run.

An optimized OVMF is available from ClearLinux.  Download the OVMF.fd
[file](https://download.clearlinux.org/image/OVMF.fd) and save it to
/usr/share/qemu/OVMF.fd on each node that will run launcher.

To create a new instance, launcher needs a template iso image to use as a backing file.
Currently, launcher requires all such backing files to be stored in
/var/lib/ciao/images.  The names of these image files must exactly match the
image_uuid field passed in the payload of the START command.  Here's an example setup

```
 /var/lib/ciao/images/
 └── b286cd45-7d0c-4525-a140-4db6c95e41fa
```

The images should have cloudinit installed and configured to use the ConfigDrive data source.
Currently, this is the only data source supported by launcher.

## Launching ciao-launcher

ciao-launcher can be launched from the command line as follows
```
sudo ciao-launcher -server <URL of my server> -network [cn|nn|none]
```

Currently, launcher needs to be run as root so that it can create network links and
launcher docker containers.

As previously mentioned the -cacert and -cert options can be used to override the SSNTP
certificates.

ciao-launcher uses glog for logging.  By default launcher stores logs in files written to
/var/lib/ciao/logs.  This behaviour can be overridden using a number of different
command line arguments added by glog, e.g., -alsologtostderr.

Here is a full list of the command line parameters supported by launcher.

```
Usage of ./launcher:
  -alsologtostderr
    	log to standard error as well as files
  -cacert string
    	Client certificate (default "/etc/pki/ciao/CAcert-server-localhost.pem")
  -cert string
    	CA certificate (default "/etc/pki/ciao/cert-client-localhost.pem")
  -compute-net string
    	Compute Subnet
  -cpuprofile string
    	write profile information to file
  -disk-limit
    	Use disk usage limits (default true)
  -hard-reset
    	Kill and delete all instances, reset networking and exit
  -log_backtrace_at value
    	when logging hits line file:N, emit a stack trace (default :0)
  -log_dir string
    	If non-empty, write log files in this directory
  -logtostderr
    	log to standard error instead of files
  -mem-limit
    	Use memory usage limits (default true)
  -mgmt-net string
    	Management Subnet
  -network value
    	Can be none, cn (compute node) or nn (network node) (default none)
  -server string
    	URL of SSNTP server (default "localhost")
  -simulation
    	Launcher simulation
  -stderrthreshold value
    	logs at or above this threshold go to stderr
  -v value
    	log level for V logs
  -vmodule value
    	comma-separated list of pattern=N settings for file-filtered logging
  -with-ui value
    	Enables virtual consoles on VM instances.  Can be 'none', 'spice', 'nc' (default nc)
```

The --with-ui and --cpuprofile options are disabled by default.  To enable them use the debug
and profile tags,  respectively.

# Commands
## START

START is used to create and launch a new VM instance.  Some example payloads
are discussed below:

The [first payload](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/start_legacy.yaml) creates a new CN VM instance using the backing file stored in
/var/lib/ciao/images/b286cd45-7d0c-4525-a140-4db6c95e41fa.  The disk
image has a maximum size of 80GBs and the VM will be run with two CPUS and
370MBs of memory.  The first part of the payload corresponds to the
cloudinit user-data file.  This data will be extracted from the payload
stored in an ISO image and passed to the VM instance.  Assuming cloudinit is
correctly configured on the backing image, the file /etc/bootdone will be
created and the hostname of the image will be set to the instance uuid.

The [second payload](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/start_efi.yaml) creates a CN VM instance using a different image that
needs to be booted with EFI.

The [third payload](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/start_nn.yaml)
is an example of starting a VM instance on a NN.  Note that the networking parameters are different.

ciao-launcher detects and returns a number of errors when executing the start command.
These are listed below:

- invalid\_payload: if the YAML is corrupt

- invalid\_data: if the start section of the payload is corrupt or missing
information such as image-id

- already\_running: if you try to start an existing instance that is already running

- instance\_exists: if you try to start an instance that has already been created
but is not currently running

- image\_failure: If launcher is unable to prepare the file for the instance, e.g., the
image_uuid refers to an non-existant backing image

- network_failure: It was not possible to initialise networking for the instance

- full_cn: The node has insufficient resources to start the requested instance

- launch\_failure: If the instance has been successfully created but could not be launched.
Actually, this is sort of an odd situation as the START command partially succeeded.
ciao-launcher returns an error code, but the instance has been created and could be booted a
later stage via RESTART.

If the user specifies a size for disk_mb that is smaller than the virtual size of the
backing image, launcher ignores the user specified value and creates an image for the
instance whose virtual size matches that size of the chosen backing image.

ciao-launcher only supports persistent instances at the moment.  Any VM instances created
by the START command are persistent, i.e., the persistence YAML field is currently
ignored.


## DELETE

DELETE can be used to destroy an existing VM instance.  It removes all the
files associated with that instance from the compute node.  If the VM instance
is running when the DELETE command is received it will be powered down.

See [here](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/delete_legacy.yaml) for an example of the DELETE command.

## STOP

STOP can be used to power down an existing VM instance.  The state associated
with the VM remains intact on the compute node and the instance can be restarted
at a later date via the RESTART command

See [here](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/stop_legacy.yaml) for an example of the STOP command.

## RESTART

RESTART can be used to power up an existing VM instance that has either been
powered down by the user explicitly or shut down via the STOP command.  The instance
will be restarted with the settings contained in the payload of the START command
that originally created it.  It is not possible to override these settings, e.g.,
change the number of CPUs used, via the RESTART command, even though the payload itself allows these values to be specified.

See [here](https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/restart_legacy.yaml) for an example of the RESTART command.

# Recovery

When launcher starts up it checks to see if any VM instances exist and if they
do it tries to connect to them.  This means that you can easily kill launcher,
restart it and continue to use it to manage previously created VMs.  One thing
that it does not yet do is to restart VM instances that have been powered down.
We might want to do this if the machine reboots, but I need to think about how
best this should be done.


# Reporting

ciao-launcher sends STATS commands and STATUS updates to the SSNTP server to which
it is connected.  STATUS updates are sent when launcher connects to the SSNTP
server.  They are also sent when a VM instance is successfully created or
destroyed, informing the upper levels of the stack that the capacity of
launcher's compute node has changed.  The STATS command is sent when launcher
connects to the SSNTP server and every 30 seconds thereafter.

ciao-launcher computes the information that it sends back in the STATS command and
STATUS update payloads as follows:

<table border=1>
<tr><th>Datum</th><th>Source</th></tr>
<tr><td>MemTotalMB</td><td>/proc/meminfo:MemTotal</td></tr>
<tr><td>MemAvailableMB</td><td>/proc/meminfo:MemFree + Active(file) + Inactive(file)</td></tr>
<tr><td>DiskTotalMB</td><td>statfs("/var/lib/ciao/instances")</td></tr>
<tr><td>DiskAvailableMB</td><td>statfs("/var/lib/ciao/instances")</td></tr>
<tr><td>Load</td><td>/proc/loadavg (Average over last minute reported)</td></tr>
<tr><td>CpusOnLine</td><td>Number of cpu[0-9]+ entries in /proc/stat</td></tr>
</table>

And instance statistics are computed like this

<table border=1>
<tr><th>Datum</th><th>Source</th></tr>
<tr><td>SSHIP</td><td>IP of the concentrator node, see below</td></tr>
<tr><td>SSHPort</td><td>Port number on the concentrator node which can be used to ssh into the instance</td></tr>
<tr><td>MemUsageMB</td><td>pss of qemu of docker process id</td></tr>
<tr><td>DiskUsageMB</td><td>Size of rootfs</td></tr>
<tr><td>CPUUsage</td><td>Amount of cpuTime consumed by instance over 30 second period, normalized for number of VCPUs</td></tr>
</table>

ciao-launcher sends two different STATUS updates, READY and FULL.  FULL is sent
when launcher determines that there is insufficient memory or disk space available
on the node on which it runs to launch another instance.  It also returns FULL
if it determines that the launcher process is running low on file descriptors.
The memory and disk space checks can be disabled using the -mem-limit and
-disk-limit command line options.  The file descriptor limit check cannot be
disabled.

# Testing ciao-launcher in Isolation

ciao-launcher is part of the ciao network statck and is usually run and tested
in conjunction with the other ciao components.  However, it is often
useful in debugging and development to test ciao-launcher in isolation of the
other ciao components.  This can be done using two tools in the
tests directory.

The first tool, ciao-launcher-server, is an simple SSNTP server.  It can be
used to send commands to and receive events from multiple launchers.  
ciao-launcher-server exposes a REST API.  Commands can be sent to it
directly using curl, if you know the URLs, or directly with the tool, ciaolc.
We'll look at some examples of using ciaolc below.

To get started copy the test certs in https://github.com/01org/ciao/tree/master/ciao-launcher/tests/ciao-launcher-server to /etc/pki/ciao.  Then run
ciao-launcher-server.

Open a new terminal and start ciao-launcher, e.g.,

./ciao-launcher --logtostderr --network cn

Open a new terminal and try some ciaolc commands

To retrieve a list of instances type

```
$ ciaolc instances
d7d86208-b46c-4465-9018-fe14087d415f
67d86208-b46c-4465-9018-e14287d415f
```

To retrieve detailed information about the instances

```
$ cialoc istats
UUID					Status	SSH			Mem	Disk	CPU
d7d86208-b46c-4465-9018-fe14087d415f	running	192.168.42.21:35050	492 MB	339 MB	0%
67d86208-b46c-4465-9018-e14287d415f	running	192.168.200.200:61519	14 MB	189 MB	0%
```

Both of the above commands take a filter parameter.  So to see only the pending
instances type

```
$ ciaolc instances --filter pending
```

A new instance can be started using the startf command.  You need to provide
a file containing a valid start payload.  There are some examples 
[here](https://github.com/01org/ciao/tree/master/ciao-launcher/tests/examples)

```
$ ciaolc startf start_legacy.yaml
```

Instances can be stopped, restarted and deleted using the stop, restart and
delete commands.  Each of these commands require an instance-uuid, e.g.,

```
$ ciaolc stop d7d86208-b46c-4465-9018-fe14087d415f
```

The most recent stats returned by the launcher can be retrieved using the
stats command, e.g,

```
$ ciaolc stats
NodeUUID:	 dacf409a-7c7e-48c7-b382-546168ab6cdf
Status:		 READY
MemTotal:	 7856 MB
MemAvailable:	 5220 MB
DiskTotal:	 231782 MB
DiskAvailable:	 166163 MB
Load:		 0
CpusOnline:	 4
NodeHostName:	 
Instances:	 2 (2 running 0 exited 0 pending)
```

You can retrieve a list of events and errors received for a
ciao-launcher instance using the drain command:

```
$ ciaolc drain
- instance_deleted:
    instance_uuid: d7d86208-b46c-4465-9018-fe14087d415f
```

Once drained, the events are deleted from inside the ciao-launcher-server.
Running subsequent drain commands will return nothing, assuming that no
new events have been generated.

Finally, you can connect mulitple ciao-launchers to the ciao-server-launcher
instance.  If you do this you need to specify which launcher you would like
to command when issuing a command via ciaolc.  This can be done via the 
--client option.

e.g.,

```
$ ciaolc drain --client dacf409a-7c7e-48c7-b382-546168ab6cdf
- instance_deleted:
    instance_uuid: d7d86208-b46c-4465-9018-fe14087d415f
```

A list of connected clients can be obtained with the clients command.

# Connecting to QEMU Instances

There are two options.  The preferred option is to create a user and associate
an ssh key with that user in the cloud-init payload that gets sent with the
START comnmand that creates the VM.  You can see an example of such a payload
here:

https://github.com/01org/ciao/blob/master/ciao-launcher/tests/examples/start_efi.yaml

Once the start command has succeeded and the instance has been launched you can
connect to it via SSH using the IP address of the concentrator.  You also need to
specify a port number, which can be computed using the following formula:

33000 + ip[2] << 8 + ip[3]

where ip is the ip address of the instance as specified in the START command.
For example, if the IP address of the instance is 192.168.0.2, the ssh port
would be 33002.  Launcher actually sends the SSH IP address and port number
of each instance in the stats commands.  This information should normally be
shown in the ciao UI.

Please note that ssh is typically only used when you are running a complete
ciao stack, including ciao-scheduler, ciao-controller and a network node.
Howevever, it should be possible to get it to work by manually starting
a concentrator instance on a network node before you launch your instance.

The second method is to compile launcher with the debug tag, e.g.,
go build --tags debug.  This will add a new command line option that can
be used to connect to an instance via netcat or spice.  To use this method you
need to look in the launcher logs when launching an instance.  You should see
some instructions in the logs telling you how to connect to the instance.
Here's an example,

```
I0407 14:38:10.874786    8154 qemu.go:375] ============================================
I0407 14:38:10.874830    8154 qemu.go:376] Connect to vm with netcat 127.0.0.1 5909
I0407 14:38:10.874849    8154 qemu.go:377] ============================================
```

netcat 127.0.0.1 5909 will give you a login prompt.  You might need to press return to see the login.   Note this will only work if the VM allows login on the
console port, i.e., is running getty on ttyS0.

# Connecting to Docker Container Instances

This can only be done from the compute note that is running the docker
container.

1. Install nsenter
2. sudo docker ps -a | grep <instance-uuid>
3. copy the container-id
4. PID=$(sudo docker inspect -f {{.State.Pid}} <container-id>)
5. sudo nsenter --target $PID --mount --uts --ipc --net --pid

See [here](https://blog.docker.com/tag/nsenter/) for more information.
