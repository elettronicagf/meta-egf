# Copyright (C) 2015 Freescale Semiconductor
# Copyright 2017-2020 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-fsl/images/imx-image-multimedia.bb

inherit populate_sdk_qt5

GF_YOCTO_ROOTFS_VERSION = "1.02"

CONFLICT_DISTRO_FEATURES = "directfb"

inherit core-image

IMAGE_FEATURES += " \
    splash \
"

#IMAGE_FEATURES += " \
#    hwcodecs \
#"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
"

# Added for egf image
IMAGE_INSTALL += " egf-wireless-atwilc3000 "
IMAGE_INSTALL += " egf-gpio "
IMAGE_INSTALL += " openssh "
IMAGE_INSTALL += " openssh-sftp-server "
#IMAGE_INSTALL += " gdb gdb-server "
IMAGE_INSTALL += " minicom "
IMAGE_INSTALL += " libgpiod"
IMAGE_INSTALL += " opkg opkg-utils "
IMAGE_INSTALL += " mc "
IMAGE_INSTALL += " nano strace i2c-tools mtd-utils "
IMAGE_INSTALL += " lighttpd "
IMAGE_INSTALL += " python3 python3-pip "
IMAGE_INSTALL += " lighttpd-module-alias "
IMAGE_INSTALL += " lighttpd-module-redirect "
IMAGE_INSTALL += " glibc-utils "
IMAGE_INSTALL += " localedef "
IMAGE_INSTALL += " curl "
IMAGE_INSTALL += " ntp "
IMAGE_INSTALL += " p7zip "
#IMAGE_INSTALL += " gstreamer1.0-plugins-imx "
IMAGE_INSTALL += " wget "
IMAGE_INSTALL += " qtquickcontrols "
IMAGE_INSTALL += " qtquickcontrols2 "
IMAGE_INSTALL += " qtmultimedia "
IMAGE_INSTALL += " qtmultimedia-plugins "
IMAGE_INSTALL += " qtmultimedia-qmlplugins "
IMAGE_INSTALL += " gstreamer1.0 "
IMAGE_INSTALL += " gstreamer1.0-plugins-good "
#IMAGE_INSTALL += " gstreamer1.0-plugins-imx "

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
#OPENCV_PKGS       ?= ""
#OPENCV_PKGS_imxgpu = " \
#    opencv-apps \
#    opencv-samples \
#    python3-opencv \
#"

#IMAGE_INSTALL += " \
#    ${OPENCV_PKGS} \
#"
IMAGE_INSTALL += " \
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
