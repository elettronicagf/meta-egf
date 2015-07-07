#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
UPDATE_SCRIPT="update.tar"
SOURCE1="/run/media/sda1"
SOURCE2="/run/media/sda"
fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

exec_update() {
	target_sdk_dir=/tmp/update
	mkdir -p $target_sdk_dir

	tar -C$target_sdk_dir -xvf $1/update.tar
	$target_sdk_dir/setup.sh

	sync
	umount $1
	echo >$CONSOLE
	exec sh
}



mkdir /proc
mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mkdir -p /run
mkdir -p /var/run

mount -t devtmpfs none /dev

/lib/udev/udevd --daemon
udevadm trigger --action=add
udevadm settle --timeout=5

[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

echo "Look For Update Script..."
if [ -f $SOURCE1/$UPDATE_SCRIPT ] ; then
	exec_update $SOURCE1
else	
	if [ -f $SOURCE2/$UPDATE_SCRIPT ] ; then
		exec_update $SOURCE2
	else	
		fatal "update script not found "
	fi
fi
