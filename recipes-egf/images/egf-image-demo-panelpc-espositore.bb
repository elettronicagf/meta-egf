DESCRIPTION = "A sample image that includes gstreamer packages and \
Freescale's multimedia packages (VPU and GPU) and QT5 libraries."

LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "egf-image-demo-panelpc-espositore"

IMAGE_FEATURES += "${@base_contains('DISTRO_FEATURES', 'x11', \
                            ' x11-base x11-sato hwcodecs', '', d)}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-theme egf-gpio python-pyqt \
    openssh openssh-sftp-server gstreamer1.0-plugins-bad-opencv nano strace i2c-tools gdb gdbserver "

CONFLICT_DISTRO_FEATURES = "directfb"

# X11 packages
X11_IMAGE_INSTALL = ""
X11_IMAGE_INSTALL_mx6 = "${@base_contains('DISTRO_FEATURES', 'x11', 'packagegroup-fsl-pulseaudio', '', d)}"
X11_IMAGE_INSTALL_mx6ul = "${@base_contains('DISTRO_FEATURES', 'x11', 'packagegroup-fsl-pulseaudio', '', d)}"
X11_IMAGE_INSTALL_mx7 = "${@base_contains('DISTRO_FEATURES', 'x11', 'packagegroup-fsl-pulseaudio', '', d)}"
X11_IMAGE_INSTALL_append_mx6sl = " libopenvg-mx6"

# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)}"

# set mm image install specific to SOC
MM_IMAGE_INSTALL = ""
MM_IMAGE_INSTALL_mx6 = "packagegroup-fsl-multimedia-gstreamer1.0-core"
MM_IMAGE_INSTALL_mx6ul = "packagegroup-fsl-multimedia-gstreamer1.0-core"
MM_IMAGE_INSTALL_mx7 = "packagegroup-fsl-multimedia-gstreamer1.0-core"

#mtd-utils mtd-utils-ubifs bluez5

#QT4_IMAGE_INSTALL = " 
#	qt4-x11-free 
#"

#QT5_IMAGE_INSTALL = ""
#QT5_IMAGE_INSTALL_common = " 
#    packagegroup-qt5-core 
#    packagegroup-qt5-qtdeclarative 
#    packagegroup-qt5-qtdeclarative-qml \
#"

#QT5_IMAGE_INSTALL_mx6 = " 
#    ${QT5_IMAGE_INSTALL_common} 
#    packagegroup-qt5-webkit 
#"
    
#IMAGE_INSTALL += " 
#    ${X11_IMAGE_INSTALL_GRAPHICS} 
#    ${QT5_IMAGE_INSTALL} 
#    ${QT4_IMAGE_INSTALL} 
#"

GF_YOCTO_ROOTFS_VERSION = "1.0"

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL} \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${MM_IMAGE_INSTALL} \
    "

#IMAGE_INSTALL += "${QT4_IMAGE_INSTALL}"

#MACHINE_FEATURES += " wifi "
fix_image() {

		echo ${GF_YOCTO_ROOTFS_VERSION} >> ${IMAGE_ROOTFS}/etc/version.gf
		echo ${IMAGE_BASENAME} >> ${IMAGE_ROOTFS}/etc/version.gf	
	
		rm -rf ${IMAGE_ROOTFS}/boot/*
		rm -rf ${IMAGE_ROOTFS}/lib/modules
		mv ${IMAGE_ROOTFS}/usr/share/pixmaps/matchbox-keyboard-new.png ${IMAGE_ROOTFS}/usr/share/pixmaps/matchbox-keyboard.png		
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/examples
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/demos
		rm -rf ${IMAGE_ROOTFS}/usr/share/qt4/mkspecs
		rm -rf ${IMAGE_ROOTFS}/usr/share/doc/qt4
		sed -i -e 's/Sato/GF/g' ${IMAGE_ROOTFS}/etc/matchbox/session
		sed -i -e 's/Sato/GF/g' ${IMAGE_ROOTFS}/etc/gconf/gconf.xml.defaults/desktop/poky/interface/%gconf.xml
		#Change Default matchbox tab order
		rm ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
		echo "All" >> ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
		echo "Applications" >> ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
		echo "Utilities" >> ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
		echo "Games" >> ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
		echo "Settings" >> ${IMAGE_ROOTFS}/usr/share/matchbox/vfolders/Root.order
}


IMAGE_PREPROCESS_COMMAND += "fix_image"

