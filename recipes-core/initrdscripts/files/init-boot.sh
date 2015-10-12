#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
UPDATE_SCRIPT="update.bin"
SOURCE1="/run/media/sda1"
SOURCE2="/run/media/sda"
PASSWORD="8fX589i2ed_@YT#xx++]]00$aqe34=="

fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

exec_update() {
	target_sdk_dir=/tmp/update
	mkdir -p $target_sdk_dir
	
	[ -f $1/update-splash.gz ] && zcat $1/update-splash.gz > /dev/fb0
	
	unzip -P$PASSWORD $1/$UPDATE_SCRIPT -d  $target_sdk_dir
	if [ $? -ne 0 ]; then
		export ERROR_UPDATE="UNZIP_ERROR"
	fi	
	
	chmod +x $target_sdk_dir/*.sh

	#tar -C$target_sdk_dir -xvf $target_sdk_dir/update.tar
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
