#!/bin/sh
### BEGIN INIT INFO
# Provides:          mountall
# Required-Start:    mountvirtfs
# Required-Stop: 
# Default-Start:     S
# Default-Stop:
# Short-Description: Mount all filesystems.
# Description:
### END INIT INFO

. /etc/default/rcS

#
# Mount local filesystems in /etc/fstab. For some reason, people
# might want to mount "proc" several times, and mount -v complains
# about this. So we mount "proc" filesystems without -v.
#mount application partition on Nand or on SD

ROOT_IS_ON_NAND=`mount | grep "ubi0 on /"`

if [ -z "$ROOT_IS_ON_NAND" ]; then
	echo "booted from sd"
#	mount -t /dev/mmcblk0p2 -o defaults,sync,noauto /home/root
else
	ubiattach /dev/ubi_ctrl -m 3 -d1
	mount -t ubifs -o defaults,noatime,rw,sync ubi1:userfs1 /home/root
	mount -n -o remount,ro /home/root/ 
	echo "booted from nand"
fi

#
test "$VERBOSE" != no && echo "Mounting local filesystems..."
mount -at nonfs,nosmbfs,noncpfs 2>/dev/null



#
# We might have mounted something over /dev, see if /dev/initctl is there.
#
if test ! -p /dev/initctl
then
	rm -f /dev/initctl
	mknod -m 600 /dev/initctl p
fi
kill -USR1 1

#
# Execute swapon command again, in case we want to swap to
# a file on a now mounted filesystem.
#
swapon -a 2> /dev/null

: exit 0

