LICENSE = "MIT"
PACKAGE_INSTALL = "initramfs-boot busybox udev base-passwd udev-extraconf dosfstools e2fsprogs mtd-utils"

export IMAGE_BASENAME = "core-image-minimal-initramfs"
IMAGE_LINGUAS = ""


IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL_remove += "packagegroup-fsl-tools-gpu"

write_version () {
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_LIVE_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
	
	mkdir ${IMAGE_ROOTFS}/dev	
	mknod ${IMAGE_ROOTFS}/dev/console c 5 1
	chown root:root ${IMAGE_ROOTFS}/dev/console
	
}

IMAGE_PREPROCESS_COMMAND += "write_version"

BAD_RECOMMENDATIONS += "busybox-syslog"
