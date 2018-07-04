DESCRIPTION = "MBUGRF2018 light image"

GF_YOCTO_ROOTFS_VERSION = "1.0"

inherit core-image
inherit distro_features_check

## Select Image Features
IMAGE_FEATURES += " \
    splash \
    hwcodecs \
"
#debug-tweaks 

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
"
#packagegroup-fsl-tools-gpu
#packagegroup-fsl-tools-gpu-external

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-gpio \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    udev-extraconf egf-ota grf-light-launcher \
"

CONFLICT_DISTRO_FEATURES = "directfb"

IMAGE_FSTYPES = "tar.bz2"


