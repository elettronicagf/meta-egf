DESCRIPTION = "Immagine per Applicazione JAVA Telaio ITEMA. Modulo 0508"

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'package-management x11-base x11-sato read-only-rootfs', '', d)} \
"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils opkg canutils \
    iproute2 openssh openssh-sftp-server nano strace i2c-tools gdb xserver-xorg-extension-viv-hdmi \
    util-linux itema-bundle "


# "xf86-input-tslib tslib tslib-calibrate tslib-conf tslib-tests"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)}"

#    packagegroup-fsl-pulseaudio 

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    "
IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools \
                         packagegroup-fsl-tools-gpu "

fix_readonly_image() {
		rm ${IMAGE_ROOTFS}/etc/network/interfaces
		ln -s /home/root/etc/network/interfaces ${IMAGE_ROOTFS}/etc/network/interfaces
}

fix_image() {
		#rimozione ts calibration
		#rm ${IMAGE_ROOTFS}/etc/X11/Xinit.d/89xTs_Calibrate
		
		#rimozione calibrazione automatica all'avvio
		#rm ${IMAGE_ROOTFS}/etc/xdg/autostart/xinput_calibrator.desktop

		#attesa avvio applicativo		
		sed -i -e '1isleep 30\' ${IMAGE_ROOTFS}/etc/X11/Xsession.d/90xXWindowManager.sh
}


#IMAGE_PREPROCESS_COMMAND += "fix_readonly_image"
IMAGE_PREPROCESS_COMMAND += "fix_image"

export IMAGE_BASENAME = "egf-image"


