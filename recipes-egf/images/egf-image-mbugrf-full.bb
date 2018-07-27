DESCRIPTION = "eGF Image MBUGRF Full"

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
	x11-base \
"

IMAGE_INSTALL += " \
    opkg-utils minicom opkg mc egf-gpio \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils udev-extraconf egf-ota hplip cups \
"

IMAGE_INSTALL += "qtbase \
                  qtbase-tools \
                  qtbase-plugins \
                  qtdeclarative \
                  qtquickcontrols2 \
                  qtsvg \
                  qtgraphicaleffects-qmlplugins \
                  packagegroup-fsl-gstreamer1.0-full \
                  cifs-utils \
"

IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt"

CONFLICT_DISTRO_FEATURES = "directfb"

IMAGE_FSTYPES = "tar.bz2"

