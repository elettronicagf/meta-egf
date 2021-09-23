#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
UPDATE_SCRIPT="update.bin"
SOURCE1="/run/media/sda1"
SOURCE2="/run/media/sda"
PASSWORD="8fX589i2ed_@YT#xx++]]00$aqe34=="

message() {
	if [ $CONSOLE_DEV == "null" ]; then
		echo $1 >> $SOURCE1/updatelog.txt
	else
		echo $1 >$CONSOLE
	fi
}

fatal() {
	if [ $CONSOLE_DEV == "null" ]; then
		echo $1 >> $SOURCE1/updatelog.txt
	else
		echo $1 >$CONSOLE
		echo >$CONSOLE
	fi
    exec sh
}

exec_update() {
	target_sdk_dir=/tmp/update
	mkdir -p $target_sdk_dir
	message "Starting update"
	sync
	unzip -P$PASSWORD $1/$UPDATE_SCRIPT -d  $target_sdk_dir >> $SOURCE1/updatelog.txt

	if [ $? -ne 0 ]; then
		export ERROR_UPDATE="UNZIP_ERROR"
	fi	
	
	geometry=$(fbset | grep geometry)

	sync
	xres=$(echo $geometry | awk -F " " '{print $2}')
	yres=$(echo $geometry | awk -F " " '{print $3}')

	res=${xres}x${yres}
	sync

	[ -f $target_sdk_dir/update-splash-$res.gz ] && zcat $target_sdk_dir/update-splash-$res.gz > /dev/fb0	
	
	chmod +x $target_sdk_dir/*.sh

	message "Launching setup.sh"
	sync
	$target_sdk_dir/setup.sh

	sync
	umount $1
	
	if [ $CONSOLE_DEV != "null" ]; then
		echo >$CONSOLE
	fi

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

CONSOLE_DEV=$(cat /proc/cmdline | sed -e 's/^.*console=//' -e 's/,.*$//')
echo $CONSOLE_DEV >> $SOURCE1/updatelog.txt

message "Look For Update Script..."


if [ -f $SOURCE1/$UPDATE_SCRIPT ] ; then
	exec_update $SOURCE1
else	
	if [ -f $SOURCE2/$UPDATE_SCRIPT ] ; then
		exec_update $SOURCE2
	else	
		fatal "update script not found "
	fi
fi
