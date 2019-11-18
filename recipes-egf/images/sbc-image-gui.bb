LICENSE = "MIT"

GF_YOCTO_ROOTFS_VERSION = "1.0"

inherit core-image

IMAGE_FEATURES += " \
    debug-tweaks \
    splash \
    tools-debug \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d), d)} \
"

IMAGE_INSTALL += " \
    minicom egf-gpio canutils iproute2 \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils udev-extraconf egf-ota psplash \
    minicom fbset opkg-utils iperf3 iperf2 \
    glibc-gconv-ibm850 glibc-gconv-ibm437 \
    bluez5 atwilc3000 \
    tslib tslib-calibrate tslib-conf tslib-tests \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-audio \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \    
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
"

#    packagegroup-fsl-tools-testapps 
#    packagegroup-fsl-tools-benchmark 


IMAGE_FSTYPES = "tar.bz2"

export IMAGE_BASENAME = "${MACHINE}-image-gui-${GF_YOCTO_ROOTFS_VERSION}"
export IMAGE_NAME = "${IMAGE_BASENAME}-${DATETIME}"
export IMAGE_LINK_NAME = "${IMAGE_BASENAME}"
