DESCRIPTION = "Immagine per Applicazione JAVA Telaio ITEMA. Modulo 0508"

IMAGE_FEATURES += " splash"

IMAGE_FEATURES += "\
    ${@base_contains('DISTRO_FEATURES', 'x11', 'package-management x11-base x11-sato read-only-rootfs', '', d)} \
"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += " \
    opkg-utils opkg canutils\
    iproute2 openssh openssh-sftp-server nano strace i2c-tools gdb xserver-xorg-extension-viv-hdmi \
    itema-bundle \
"


 


PACKAGE_ARCH = "${MACHINE_ARCH}"


# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'xorg-minimal-fonts \
    liberation-fonts', '', d)}"

#    packagegroup-fsl-pulseaudio 

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    "
IMAGE_INSTALL_remove += "packagegroup-fsl-bluez5-tools"
IMAGE_INSTALL_remove += "packagegroup-fsl-tools-gpu"
export IMAGE_BASENAME = "egf-image"


