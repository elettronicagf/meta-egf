DESCRIPTION = "A sample image that includes gstreamer packages and \
Freescale's multimedia packages (VPU and GPU) and QT5 libraries."

LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "egf-image-demo-streaming-camera"

IMAGE_FEATURES += "${@base_contains('DISTRO_FEATURES', 'x11', \
                            ' x11-base x11-sato hwcodecs', '', d)}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-wireless-wl18xx egf-gpio \
    openssh openssh-sftp-server gstreamer1.0-plugins-bad-opencv nano strace i2c-tools gdb gdbserver \
    python-threading egf-demo-streaming-camera "

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

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL} \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${MM_IMAGE_INSTALL} \
    "
GF_YOCTO_ROOTFS_VERSION = "1.0"

fix_image() {

		echo ${GF_YOCTO_ROOTFS_VERSION} >> ${IMAGE_ROOTFS}/etc/version.gf
		echo ${IMAGE_BASENAME} >> ${IMAGE_ROOTFS}/etc/version.gf
# il link andrebbe fatto dentro a egf-wireless. Questo pero' non 
# e' stato possibile perche' il pacchetto linux_firmware fornisce
# gia' i firmware ti e questo causerebbe un conflitto
# quindi bypassiamo i controllo facendo il link a valle di tutto		
		ln -s /home/root/firmware/ti-connectivity ${IMAGE_ROOTFS}/lib/firmware/ti-connectivity

		rm -rf ${IMAGE_ROOTFS}/boot/*
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/examples
		rm -rf ${IMAGE_ROOTFS}/usr/bin/qt4/demos
		rm -rf ${IMAGE_ROOTFS}/usr/share/qt4/mkspecs
		rm -rf ${IMAGE_ROOTFS}/usr/share/doc/qt4
		mv ${IMAGE_ROOTFS}/etc/network/interfaces.demo ${IMAGE_ROOTFS}/etc/network/interfaces
		ln -s /etc/init.d/egf-demo ${IMAGE_ROOTFS}/etc/rc5.d/S99egf-demo
		mv ${IMAGE_ROOTFS}/etc/wpa_supplicant.conf.demo ${IMAGE_ROOTFS}/etc/wpa_supplicant.conf
		touch ${IMAGE_ROOTFS}/home/root/egf.camera
}


IMAGE_PREPROCESS_COMMAND += "fix_image"

export IMAGE_BASENAME = "egf-image-demo-streaming-camera"
