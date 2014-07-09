IMAGE_FEATURES += "splash package-management x11-base x11-sato "

LICENSE = "MIT"

inherit core-image

#tslib tslib-calibrate tslib-conf tslib-tests 
IMAGE_INSTALL += " alsa-utils gst-fsl-plugin tslib \
		       gst-plugins-good gst-plugins-good-video4linux2 \
                       firmware-imx-vpu-imx53 \
                       iproute2 mtd-utils canutils i2c-tools nano strace\
                       gdb gdbserver openssh openssh-sftp-server gst-plugins-good-souphttpsrc qt4-x11-free extrabinaries "


fix_tmp_to_volatile () {
	rm -r ${IMAGE_ROOTFS}/tmp
	ln -s /var/tmp ${IMAGE_ROOTFS}/tmp
	touch ${IMAGE_ROOTFS}/var/run/resolv.conf
	chmod 0644 ${IMAGE_ROOTFS}/var/run/resolv.conf
	ln -s ../var/run/resolv.conf ${IMAGE_ROOTFS}/etc/
	
	ln -s ../../run/dbus/machine-id ${IMAGE_ROOTFS}/var/lib/dbus/machine-id
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}



IMAGE_PREPROCESS_COMMAND += "fix_tmp_to_volatile"

