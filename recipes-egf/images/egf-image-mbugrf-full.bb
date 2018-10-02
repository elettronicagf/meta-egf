DESCRIPTION = "MBUGRF2018 Image Full"

GF_YOCTO_ROOTFS_VERSION = "2.0"

inherit core-image

## Select Image Features
IMAGE_FEATURES += " \
    splash \
    hwcodecs \
"

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
	x11-base x11-sato \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-gpio \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils udev-extraconf egf-ota  \
    cups cups-filters ghostscript hplip hplip-cups hplip-ppd hplip-backend hplip-hal hplip-filter \
    grf-light-launcher x11vnc \
"

IMAGE_INSTALL += "qtbase \
                  qtbase-tools \
                  qtbase-plugins \
                  qtdeclarative \
                  qtquickcontrols2 \
                  qtsvg \
                  qtgraphicaleffects-qmlplugins \
                  cifs-utils \
"

IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt"

CONFLICT_DISTRO_FEATURES = "directfb"

IMAGE_FSTYPES = "tar.bz2"

export IMAGE_BASENAME = "0510mbugrfimx6-full-${GF_YOCTO_ROOTFS_VERSION}"
export IMAGE_NAME = "${IMAGE_BASENAME}-${DATETIME}"
export IMAGE_LINK_NAME = "${IMAGE_BASENAME}"

