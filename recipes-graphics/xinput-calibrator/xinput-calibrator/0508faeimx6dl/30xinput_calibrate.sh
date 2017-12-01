#!/bin/sh

. /etc/formfactor/config

if [ -f /etc/pointercal.xinput ]; then
	TMP=$(cat /etc/pointercal.xinput)
	echo ${TMP:66} | grep -o '[0-9 ]*' >  /sys/devices/soc0/soc.0/2000000.aips-bus/2000000.spba-bus/200c000.ecspi/spi_master/spi1/spi1.1/rawcalibration
	echo 1 > /sys/devices/soc0/soc.0/2000000.aips-bus/2000000.spba-bus/200c000.ecspi/spi_master/spi1/spi1.1/enable_correction
fi

if [ "$HAVE_TOUCHSCREEN" = "1" ]; then
	/usr/bin/xinput_calibrator_once.sh
fi
