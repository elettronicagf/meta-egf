#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

ROOT_MOUNT="/rootfs/"
ROOT_IMAGE="rootfs.img"
MOUNT="/bin/mount"
UMOUNT="/bin/umount"
ISOLINUX=""
UNIONFS="no"
UPDATE_SCRIPT="update_system.sh"
PASSWORD="238fn93v432mw084543"

early_setup() {
    mkdir -p /proc
    mkdir -p /sys
    mount -t proc proc /proc
    mount -t sysfs sysfs /sys

    # support modular kernel
    modprobe isofs 2> /dev/null

    mkdir -p /run
    udevd --daemon
    udevadm trigger --action=add
}

read_args() {
    [ -z "$CMDLINE" ] && CMDLINE=`cat /proc/cmdline`
    for arg in $CMDLINE; do
        optarg=`expr "x$arg" : 'x[^=]*=\(.*\)'`
        case $arg in
            root=*)
                ROOT_DEVICE=$optarg ;;
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
                fi
        esac
    done
}

boot_live_root() {
    killall udevd 2>/dev/null

    # use devtmpfs if available
    if grep -q devtmpfs /proc/filesystems; then
        mount -t devtmpfs devtmpfs $ROOT_MOUNT/dev
    fi

    cd $ROOT_MOUNT
    exec switch_root -c /dev/console $ROOT_MOUNT /sbin/init
}

fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

early_setup

[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

read_args

echo "Look For Update Script..."
if [ -f /var/volatile/sda1/$UPDATE_SCRIPT ] ; then
	
	target_sdk_dir=/tmp/update
	mkdir -p $target_sdk_dir
	unzip -P$PASSWORD /var/volatile/sda1/update.bin -d  $target_sdk_dir
	if [ $? -ne 0 ]; then
		export ERROR_UPDATE="UNZIP_ERROR"
	fi
	

	tar -C$target_sdk_dir -xvf $target_sdk_dir/update.tar
	$target_sdk_dir/setup.sh

	#/var/volatile/sda1/$UPDATE_SCRIPT&
	#MY_PID=$!

	#while [ true ]
	#do
##		echo 400 > 	/sys/devices/platform/pwm-beep.0/freq
	    #sleep 1
##		echo 0   > 	/sys/devices/platform/pwm-beep.0/freq
		#if [ ! -d /proc/$MY_PID ]; then
			#break;
		#fi	
##	    sleep 5
	#done
	sync
	umount /var/volatile/sda1
#	echo 400 > 	/sys/devices/platform/pwm-beep.0/freq
#	sleep 2
#	echo 0   > 	/sys/devices/platform/pwm-beep.0/freq
	echo >$CONSOLE
	exec sh
else	
	fatal "update script not found "
fi




