DESCRIPTION = "A sample image that includes gstreamer packages and \
Freescale's multimedia packages (VPU and GPU) and QT5 libraries."

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d)} \
"

LICENSE = "MIT"

inherit core-image

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils opkg hostapd mc hostap-conf canutils mtd-utils \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver\
    packagegroup-fsl-gstreamer \
    packagegroup-fsl-tools-gpu \
    libusb-compat imx-test \
"

QT5_IMAGE_INSTALL = ""
QT5_IMAGE_INSTALL_common = " \
    packagegroup-qt5-core \
    packagegroup-qt5-qtdeclarative \
    packagegroup-qt5-qtdeclarative-qml \
    packagegroup-qt5-demos \
"

QT5_IMAGE_INSTALL_mx6 = " \
    ${QT5_IMAGE_INSTALL_common} \
    packagegroup-qt5-webkit \
"

X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)} \
"
    
IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${QT5_IMAGE_INSTALL} \
"

MACHINE_FEATURES += " wifi "
