# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2020 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-fsl/images/imx-image-multimedia.bb

inherit populate_sdk_qt5

GF_YOCTO_ROOTFS_VERSION = "1.0"

CONFLICT_DISTRO_FEATURES = "directfb"

# Added for egf image
IMAGE_INSTALL += " egf-wireless-atwilc3000 "
IMAGE_INSTALL += " egf-gpio "
IMAGE_INSTALL += " openssh "
IMAGE_INSTALL += " openssh-sftp-server "
#IMAGE_INSTALL += " gdb gdb-server "
IMAGE_INSTALL += " minicom "
IMAGE_INSTALL += " opkg opkg-utils "
IMAGE_INSTALL += " mc "
IMAGE_INSTALL += " nano strace i2c-tools mtd-utils "


# Add machine learning for certain SoCs
ML_PKGS                   ?= ""
ML_STATICDEV              ?= ""
ML_PKGS_mx8                = "packagegroup-imx-ml"
ML_STATICDEV_mx8           = ""
ML_PKGS_mx8dxl             = ""
ML_STATICDEV_mx8dxl        = ""
ML_PKGS_mx8phantomdxl      = ""
ML_STATICDEV_mx8phantomdxl = ""
ML_PKGS_mx8mnul            = ""
ML_STATICDEV_mx8mnul       = ""

# Add opencv for i.MX GPU
OPENCV_PKGS       ?= ""
OPENCV_PKGS_imxgpu = " \
    opencv-apps \
    opencv-samples \
    python3-opencv \
"

IMAGE_INSTALL += " \
    ${OPENCV_PKGS} \
    ${ML_PKGS} \
    packagegroup-qt5-imx \
    tzdata \
"

TOOLCHAIN_TARGET_TASK += " \
    ${ML_STATICDEV} \
"

write_version () {
	echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
}

IMAGE_PREPROCESS_COMMAND += "write_version;"
