
LICENSE = "MIT"
IMAGE_INSTALL = "initramfs-live-boot  busybox udev base-passwd"

IMAGE_FEATURES += "splash  "

export IMAGE_BASENAME = "core-image-minimal-initramfs"
IMAGE_LINGUAS = ""


IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

#tslib tslib-calibrate tslib-conf tslib-tests 
IMAGE_INSTALL += " mtd-utils canutils i2c-tools nano strace openssh openssh-sftp-server extrabinaries unzip"


write_version () {
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_LIVE_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}



IMAGE_PREPROCESS_COMMAND += "write_version"
