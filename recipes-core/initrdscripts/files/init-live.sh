#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

ROOT_MOUNT="/rootfs/"
ROOT_IMAGE="rootfs.img"
MOUNT="/bin/mount"
UMOUNT="/bin/umount"
ISOLINUX=""

ROOT_DISK=""

# Copied from initramfs-framework. The core of this script probably should be
# turned into initramfs-framework modules to reduce duplication.
udev_daemon() {
	OPTIONS="/sbin/udev/udevd /sbin/udevd /lib/udev/udevd /lib/systemd/systemd-udevd"

	for o in $OPTIONS; do
		if [ -x "$o" ]; then
			echo $o
			return 0
		fi
	done

	return 1
}

_UDEV_DAEMON=`udev_daemon`

early_setup() {
    mkdir -p /proc
    mkdir -p /sys
    mount -t proc proc /proc
    mount -t sysfs sysfs /sys
    mount -t devtmpfs none /dev

    # support modular kernel
    modprobe isofs 2> /dev/null

    mkdir -p /run
    mkdir -p /var/run

    $_UDEV_DAEMON --daemon
    udevadm trigger --action=add
}

read_args() {
    [ -z "$CMDLINE" ] && CMDLINE=`cat /proc/cmdline`
    for arg in $CMDLINE; do
        optarg=`expr "x$arg" : 'x[^=]*=\(.*\)'`
        case $arg in
            root=*)
                ROOT_DEVICE=$optarg ;;
            rootimage=*)
                ROOT_IMAGE=$optarg ;;
            rootfstype=*)
                modprobe $optarg 2> /dev/null ;;
            LABEL=*)
                label=$optarg ;;
            video=*)
                video_mode=$arg ;;
            vga=*)
                vga_mode=$arg ;;
            console=*)
                if [ -z "${console_params}" ]; then
                    console_params=$arg
                else
                    console_params="$console_params $arg"
                fi ;;
            debugshell*)
                if [ -z "$optarg" ]; then
                        shelltimeout=30
                else
                        shelltimeout=$optarg
                fi 
        esac
    done
}



fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

exec_update() {
	target_sdk_dir=/tmp/update
	mkdir -p $target_sdk_dir
	unzip -P$PASSWORD $1/update.bin -d  $target_sdk_dir
	if [ $? -ne 0 ]; then
		export ERROR_UPDATE="UNZIP_ERROR"
	fi
	

	tar -C$target_sdk_dir -xvf $target_sdk_dir/update.tar
	$target_sdk_dir/setup.sh

	sync
	umount $1
	echo >$CONSOLE
	exec sh
}


early_setup

[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

read_args



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


