DESCRIPTION = "A sample image that includes gstreamer packages and \
Freescale's multimedia packages (VPU and GPU) and QT5 libraries."

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'x11-base ', '', d)} \
"

LICENSE = "MIT"

inherit core-image

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils minicom opkg mc mtd-utils minicom egf-gpio iproute2 \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    packagegroup-fsl-gstreamer util-linux canutils x11vnc icu \
    packagegroup-fsl-tools-gpu \
"

QT4_IMAGE_INSTALL = " \
	qt4-x11-free \
"

X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)} \
"
    
IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${QT4_IMAGE_INSTALL} \
"

fix_image() {

		echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf

		rm -rf ${IMAGE_ROOTFS}/lib/modules/
		rm -rf ${IMAGE_ROOTFS}/boot/*
		
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/examples
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/demos
		rm -rf ${IMAGE_ROOTFS}/usr/share/qt4/mkspecs
		rm -rf ${IMAGE_ROOTFS}/usr/share/doc/qt4
}


IMAGE_PREPROCESS_COMMAND += "fix_image"
