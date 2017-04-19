DESCRIPTION = "Cefla Image"

GF_YOCTO_ROOTFS_VERSION = "1.0"

inherit core-image
inherit distro_features_check

## Select Image Features
IMAGE_FEATURES += " \
    debug-tweaks \
    splash \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-gpio egf-theme \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils \
"

CONFLICT_DISTRO_FEATURES = "directfb"

# Install Freescale QT demo applications
QT5_IMAGE_INSTALL_APPS = ""
QT5_IMAGE_INSTALL_APPS_mx6q = "${@bb.utils.contains("MACHINE_GSTREAMER_1_0_PLUGIN", "imx-gst1.0-plugin", "imx-qtapplications", "", d)}"
QT5_IMAGE_INSTALL_APPS_mx6dl = "${@bb.utils.contains("MACHINE_GSTREAMER_1_0_PLUGIN", "imx-gst1.0-plugin", "imx-qtapplications", "", d)}"

# Install Freescale QT demo applications for X11 backend only
MACHINE_QT5_MULTIMEDIA_APPS = ""
QT5_IMAGE_INSTALL = ""
QT5_IMAGE_INSTALL_common = " \
    packagegroup-qt5-toolchain-target \
    packagegroup-qt5-demos \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxkbcommon', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins', \  
       bb.utils.contains('DISTRO_FEATURES',     'x11', '${QT5_IMAGE_INSTALL_APPS}', \
                                                       '', d), d)} \
    "
QT5_IMAGE_INSTALL_mx6 = " \
    ${QT5_IMAGE_INSTALL_common} \
    gstreamer1.0-plugins-bad-qt \
    "
QT5_IMAGE_INSTALL_mx6sl = "${@bb.utils.contains('DISTRO_FEATURES', 'x11','${QT5_IMAGE_INSTALL_common}', \
    'qtbase qtbase-fonts qtbase-plugins', d)}"

QT5_IMAGE_INSTALL_mx6ul = "${@bb.utils.contains('DISTRO_FEATURES', 'x11','${QT5_IMAGE_INSTALL_common}', \
    'qtbase qtbase-examples qtbase-fonts qtbase-plugins', d)}"

QT5_IMAGE_INSTALL_mx7 = "${@bb.utils.contains('DISTRO_FEATURES', 'x11','${QT5_IMAGE_INSTALL_common}', \
    'qtbase qtbase-examples qtbase-fonts qtbase-plugins', d)}"

QT5_IMAGE_INSTALL_mx8 = " \
    ${QT5_IMAGE_INSTALL_common} \
    gstreamer1.0-plugins-bad-qt \
    "
# Add packagegroup-qt5-webengine to QT5_IMAGE_INSTALL_mx6 and comment out the line below to install qtwebengine to the rootfs.
QT5_IMAGE_INSTALL_remove = " packagegroup-qt5-webengine"

IMAGE_INSTALL += " \
${QT5_IMAGE_INSTALL} \
"
