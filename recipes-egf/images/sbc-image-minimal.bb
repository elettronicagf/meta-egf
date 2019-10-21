SUMMARY = "A small image just capable of allowing a device to boot."

GF_YOCTO_ROOTFS_VERSION = "1.0"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

IMAGE_FSTYPES = "tar.bz2"

export IMAGE_BASENAME = "${MACHINE}-image-minimal-${GF_YOCTO_ROOTFS_VERSION}"
export IMAGE_NAME = "${IMAGE_BASENAME}-${DATETIME}"
export IMAGE_LINK_NAME = "${IMAGE_BASENAME}"
