LICENSE = "MIT"
PACKAGE_INSTALL = "initramfs-boot busybox udev base-passwd udev-extraconf dosfstools e2fsprogs "

GF_YOCTO_ROOTFS_LIVE_VERSION = "1.1"

IMAGE_LINGUAS = ""


IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL_remove += "packagegroup-fsl-tools-gpu"

PACKAGE_INSTALL += " mtd-utils mtd-utils-ubifs unzip nano util-linux"

write_version () {
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_LIVE_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
	
	mkdir ${IMAGE_ROOTFS}/dev	
	mknod ${IMAGE_ROOTFS}/dev/console c 5 1
	chown root:root ${IMAGE_ROOTFS}/dev/console
	
}

IMAGE_PREPROCESS_COMMAND += "write_version"

BAD_RECOMMENDATIONS += "busybox-syslog"

export IMAGE_BASENAME = "egf-image-update-${GF_YOCTO_ROOTFS_VERSION}"
export IMAGE_NAME = "${IMAGE_BASENAME}-${DATETIME}"
export IMAGE_LINK_NAME = "${IMAGE_BASENAME}"
