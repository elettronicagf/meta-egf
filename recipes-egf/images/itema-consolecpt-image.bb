DESCRIPTION = "Immagine per Applicazione Telaio ITEMA. Modulo 0508"

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'package-management x11-base x11-sato read-only-rootfs', '', d)} \
"

LICENSE = "MIT"

inherit core-image

#egf-gpio
CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils opkg canutils mtd-utils minicom \
    iproute2 openssh openssh-sftp-server nano i2c-tools gdb gdbserver \
    strace xserver-xorg-extension-viv-hdmi icu x11vnc \
    util-linux itema-bundle zip unzip lighttpd samba cifs-utils"
    
# "xf86-input-tslib tslib tslib-calibrate tslib-conf tslib-tests"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Add in Graphics
X11_IMAGE_INSTALL = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    packagegroup-fsl-gstreamer \
    libxkbcommon \
    liberation-fonts', '', d)}"

#OPENCV_INSTALL = "opencv opencv-dev opencv-apps opencv-samples"

QT5_IMAGE_INSTALL = ""
QT5_IMAGE_INSTALL_common = " \
    packagegroup-qt5-core \
    packagegroup-qt5-qtdeclarative \
    packagegroup-qt5-qtdeclarative-qml \
    ${X11_IMAGE_INSTALL} \
    "
#    ${OPENCV_INSTALL} 
#    packagegroup-qt5-demos     
    
QT5_IMAGE_INSTALL_mx6 = " \
    ${QT5_IMAGE_INSTALL_common} \
    "
#    packagegroup-qt5-webkit     
    
#QT5_IMAGE_INSTALL_mx6sl = "${@base_contains('DISTRO_FEATURES', 'x11','${QT5_IMAGE_INSTALL_common}', 
#    'packagegroup-qt5-core 
#     qtbase-examples', d)}"

IMAGE_INSTALL += " \
${QT5_IMAGE_INSTALL} \
"

IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools \
                         packagegroup-fsl-tools-gpu "

fix_readonly_image() {
		rm ${IMAGE_ROOTFS}/etc/network/interfaces
		ln -s /home/root/etc/network/interfaces ${IMAGE_ROOTFS}/etc/network/interfaces
}

fix_image() {

		echo ${GF_YOCTO_ROOTFS_VERSION} > ${IMAGE_ROOTFS}/etc/version.gf

		#rimozione ts calibration
		#rm ${IMAGE_ROOTFS}/etc/X11/Xinit.d/89xTs_Calibrate
		
		#rimozione calibrazione automatica all'avvio
		#rm ${IMAGE_ROOTFS}/etc/xdg/autostart/xinput_calibrator.desktop

		#attesa avvio applicativo		
		sed -i -e '1isleep 30\' ${IMAGE_ROOTFS}/etc/X11/Xsession.d/90xXWindowManager.sh
}


#IMAGE_PREPROCESS_COMMAND += "fix_readonly_image"
IMAGE_PREPROCESS_COMMAND += "fix_image"

export IMAGE_BASENAME = "itema-console-image"


