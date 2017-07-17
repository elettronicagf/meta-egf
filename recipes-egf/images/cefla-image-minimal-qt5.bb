DESCRIPTION = "Cefla Image"

GF_YOCTO_ROOTFS_VERSION = "1.0"

inherit core-image

## Select Image Features
IMAGExx_FEATURES += " \
    splash \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"
#debug-tweaks 

CORExx_IMAGE_EXTRA_INSTALL += " \
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
    opkg-utils minicom opkg mc egf-gpio canutils iproute2 \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils mtd-utils-ubifs udev-extraconf \
"

IMAGE_INSTALL += "qtbase qtbase-tools qtbase-plugins"

CONFLICT_DISTRO_FEATURES = "directfb"

IMAGE_FSTYPES = "tar.bz2 ubi"
UBINIZE_ARGS = " -m 2048 -p 128KiB -s 2048 "
MKUBIFS_ARGS = " -c 1600 -e 126976 -m 2KiB -F"
UBI_VOLNAME = "rootfs"
