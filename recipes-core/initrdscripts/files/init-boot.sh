#!/bin/sh
PATH=/sbin:/bin:/usr/sbin:/usr/bin
PASSWORD='47382e)9[xh?'

get_sn_from_cmdline () {
	local CMDLINE=$(cat /proc/cmdline)
	local parameter=" "
	local SN=" "
	for i in $CMDLINE; do
		if [ ${#i} -eq 12 ]; then
			parameter=${i:0:2};
			if [ "$parameter" = "sn" ]; then
				SN=${i:3:12};
			fi;
		fi;
	done;
	echo $SN;
}

get_update_md5_from_cmdline () {
	local CMDLINE=$(cat /proc/cmdline)
	local parameter=" "
	local MD5=" "
	for i in $CMDLINE; do
		if [ ${#i} -eq 43 ]; then
			parameter=${i:0:10};
			if [ "$parameter" = "update_md5" ]; then
				MD5=${i:11:32};
			fi;
		fi;
	done;
	echo $MD5;
}

message() {
	echo "##### $1 #####"
	if [ "$LOG_FILE_PATH" != " " ]; then
		echo "$1" >> $LOG_FILE_PATH
		sync
	fi
}

error_handler() {
	message "Error: $1"
	echo >$CONSOLE
	exec sh
}

get_available_devs_for_update () {
	local AVAILABLE_DEVS=" "
	AVAILABLE_DEVS=$(ls /run/media | grep sd);
	echo $AVAILABLE_DEVS;
}

mkdir /proc
mkdir /sys
mount -t proc proc /proc
mount -t sysfs sysfs /sys

mkdir -p /run
mkdir -p /var/run

mount -t devtmpfs none /dev

udevd --daemon
udevadm trigger --action=add
udevadm settle --timeout=5

[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

echo "-------------------------------------------------------------------"
echo "-------------------------eGF update system-------------------------"
echo "-------------------------------------------------------------------"

SN=$(get_sn_from_cmdline)
NRAND=$(shuf -i 1-65000 -n1)
LOG_FILE_NAME=update-log-$SN-$NRAND
LOG_FILE_PATH=" "

# Retrieve update type and md5 from command line
MD5=$(get_update_md5_from_cmdline)
message "md5 read from cmdline is $MD5"

message "Installing update"

if [ "$MD5" = " " ]; then
	error_handler "Missing update md5 parameter from cmdline";
fi;

# Search for update package
# Update package is searched on USB if update type is usb 
# md5 written on package header must be equal to the one passed
# in cmdline from bootloader
message "Searching for storage devices..."
AVAILABLE_DEVS=$(get_available_devs_for_update)
if [ "$AVAILABLE_DEVS" = " " ]; then
	error_handler "No available devices for update found";
fi;

UPDATE_PATH=" "

for dev in $AVAILABLE_DEVS; do
	if [ ! -e /run/media/$dev/update.eup ]; then
		continue;
	fi;
	PATH_TO_TRY=/run/media/$dev/update.eup
	HEADER=$(dd if=$PATH_TO_TRY bs=1 count=4 2>/dev/null)
	FILEMD5SUM=$(dd if=$PATH_TO_TRY bs=1 count=32 skip=4 2>/dev/null)
	if [ "$HEADER" = "eGF1" ] && [ "$FILEMD5SUM" = $MD5 ]; then
		message "Found update media $dev";
		UPDATE_PATH=$PATH_TO_TRY;
		export LOG_FILE_PATH=/run/media/$dev/$LOG_FILE_NAME
		break;
	fi;
done;

if [ "$UPDATE_PATH" = " " ]; then
	error_handler "Update package not found";
fi

message "Upgrade package path is $UPDATE_PATH"

# Validate update package payload checksum
# Must be equal to md5 written in the package header
message "Validating update package md5sum, expected $MD5"
SUM=$(tail -c +37 $UPDATE_PATH | md5sum | awk '{print $1;}')
message "Update package calculated md5sum is $SUM"

if [ "$SUM" != "$MD5" ]; then
  error_handler "Update package checksum is not valid";
fi;

#Extract setup.sh script
message "Extracting setup.sh script from update package"
tail -c +16777253 $UPDATE_PATH | openssl enc -aes-256-cbc -d -pass pass:$PASSWORD 2> /dev/null | tar xm --occurrence=1 -C / setup.sh

if [ $? -ne 0 ]; then
  error_handler "Unable to extract setup.sh script from update package"
fi;

#Launch setup.sh script
message "Launching setup.sh script"
chmod +x /setup.sh
/setup.sh $UPDATE_PATH

if [ $? -ne 0 ]; then
	error_handler "Script setup.sh terminated with errors"
else
	message "Update succesfully terminated"
fi

echo >$CONSOLE
exec sh
