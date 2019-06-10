DESCRIPTION = "SMGGRF Image"

GF_YOCTO_ROOTFS_VERSION = "1.1"

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
"

#	x11-base x11-sato 

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-gpio \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils udev-extraconf egf-ota  \
    gstreamer1.0-plugins-imx \
    tslib tslib-calibrate tslib-conf tslib-tests \
    openvpn rsyslog fbset \
"

#    x11vnc 


#QT 5.8 - not required now
IMAGE_INSTALL_QT5 += "qtbase \
                  qtbase-tools \
                  qtbase-plugins \
                  qtdeclarative \
                  qtquickcontrols2 \
                  qtsvg \
                  qtgraphicaleffects-qmlplugins \
                  cifs-utils \
"

IMAGE_INSTALL += "qt4-embedded "

IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt virtual/kernel"

CONFLICT_DISTRO_FEATURES = "directfb"

write_version () {
#scrittura versione su filesystem
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}

IMAGE_PREPROCESS_COMMAND += "write_version"

IMAGE_FSTYPES = "tar.bz2"

export IMAGE_BASENAME = "0510smggrf-${GF_YOCTO_ROOTFS_VERSION}"
export IMAGE_NAME = "${IMAGE_BASENAME}-${DATETIME}"
export IMAGE_LINK_NAME = "${IMAGE_BASENAME}"

