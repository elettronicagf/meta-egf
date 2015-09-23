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
    opkg-utils opkg mc mtd-utils minicom \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    packagegroup-fsl-gstreamer \
    packagegroup-fsl-tools-gpu \
    libusb-compat imx-test \
"

QT5_IMAGE_INSTALL = ""
QT5_IMAGE_INSTALL_common = " \
    packagegroup-qt5-core \
    packagegroup-qt5-qtdeclarative \
    packagegroup-qt5-qtdeclarative-qml \
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
fix_image() {

		echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf
# il link andrebbe fatto dentro a egf-wireless. Questo pero' non 
# e' stato possibile perche' il pacchetto linux_firmware fornisce
# gia' i firmware ti e questo causerebbe un conflitto
# quindi bypassiamo i controllo facendo il link a valle di tutto		
		ln -s /home/root/firmware/ti-connectivity ${IMAGE_ROOTFS}/lib/firmware/ti-connectivity

		rm -rf ${IMAGE_ROOTFS}/lib/modules/
		rm -rf ${IMAGE_ROOTFS}/boot/*
}


IMAGE_PREPROCESS_COMMAND += "fix_image"
