DESCRIPTION = "Immagine per collaudo modulo 0508"

IMAGE_FEATURES += " splash"

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'package-management x11-base x11-sato', '', d)} \
"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils opkg canutils python-pyserial mtd-utils \
    iproute2 openssh openssh-sftp-server nano strace i2c-tools gdb xserver-xorg-extension-viv-hdmi \
    util-linux dosfstools e2fsprogs xf86-input-tslib \
    tslib tslib-calibrate tslib-conf tslib-tests python-pygtk python-threading procps hdparm python-pexpect \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"


# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)}"

#    packagegroup-fsl-pulseaudio 

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    "
IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL_remove += "packagegroup-fsl-tools-gpu"

export IMAGE_BASENAME = "egf-image-collaudo"

SERIAL_CONSOLE = "115200 ttymxc1"

overwrite_interfaces () {
	rm ${IMAGE_ROOTFS}/etc/network/interfaces
	echo " " >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "auto lo" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "iface lo inet loopback" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "auto eth0" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "iface eth0 inet static" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	address 172.16.130.94" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	netmask 255.255.0.0" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "auto usb0" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "iface usb0 inet static" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	address 192.168.130.94" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	netmask 255.255.255.0" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "auto eth1" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "iface eth1 inet static" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	address 192.168.130.254" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "	netmask 255.255.255.0" >> ${IMAGE_ROOTFS}/etc/network/interfaces
	echo "" >> ${IMAGE_ROOTFS}/etc/network/interfaces
}

create_pointercal_for_blc1089 () {
	rm ${IMAGE_ROOTFS}/etc/pointercal.xinput
	echo "xinput set-int-prop \"SX8652 Touchscreen\" \"Evdev Axis Calibration\" 32 239 3848 3599 468; xinput set-int-prop \"SX8652 Touchscreen\" \"Evdev Axes Swap\" 8 0;" >> ${IMAGE_ROOTFS}/etc/pointercal.xinput
}

fix_image () {
	rm ${IMAGE_ROOTFS}/etc/init.d/connman
	sed -i '/rc_mxc.S/d' ${IMAGE_ROOTFS}/etc/inittab
	
}

IMAGE_PREPROCESS_COMMAND += "fix_image;"
IMAGE_PREPROCESS_COMMAND += "overwrite_interfaces;"
IMAGE_PREPROCESS_COMMAND += "create_pointercal_for_blc1089"
