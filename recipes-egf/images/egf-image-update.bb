
LICENSE = "MIT"
#IMAGE_INSTALL = "initramfs-live-boot  busybox udev base-passwd"
PACKAGE_INSTALL = "initramfs-boot   busybox udev base-passwd udev-extraconf dosfstools e2fsprogs"
#initramfs-live-install initramfs-live-install-efi
#IMAGE_FEATURES += "splash  "

export IMAGE_BASENAME = "core-image-minimal-initramfs"
IMAGE_LINGUAS = ""


IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
#USE_DEVFS = "1"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

#tslib tslib-calibrate tslib-conf tslib-tests 
#IMAGE_INSTALL += " i2c-tools nano strace openssh openssh-sftp-server unzip util-linux"
IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL_remove += "packagegroup-fsl-tools-gpu"

write_version () {
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_LIVE_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
	
	mkdir ${IMAGE_ROOTFS}/dev
	
	#mknod ${IMAGE_ROOTFS}/dev/tty0 c 4 0
	#chown root:root ${IMAGE_ROOTFS}/dev/tty0
	
	mknod ${IMAGE_ROOTFS}/dev/console c 5 1
	chown root:root ${IMAGE_ROOTFS}/dev/console
	
}

IMAGE_PREPROCESS_COMMAND += "write_version"

BAD_RECOMMENDATIONS += "busybox-syslog"
