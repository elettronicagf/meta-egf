GF_YOCTO_ROOTFS_VERSION = "1.0"

IMAGE_FEATURES += "\
	package-management \
	read-only-rootfs \
"

IMAGE_FEATURES_remove = " \
    tools-profile \
    nfs-server \
    tools-debug \
    ssh-server-dropbear \
    tools-testapps \
"

IMAGE_INSTALL += " \
    opkg-utils \
    minicom \
    opkg \
    egf-gpio \
    can-utils \
    iproute2 \
    openssh \
    openssh-sftp-server \
    nano \
    strace \
    i2c-tools \
    gdb \
    gdbserver \
    mtd-utils \
    mtd-utils-ubifs \
    dosfstools \
    evtest \
    fbset \
    memtester \
    e2fsprogs-mke2fs \
    init-display-mipi \
"

CORE_IMAGE_EXTRA_INSTALL_remove = " \
    packagegroup-tools-bluetooth \
"

QT5_IMAGE_INSTALL_common_remove = " \
	packagegroup-qt5-toolchain-target \
"

IMAGE_FSTYPES = "tar.bz2 ubi"
UBINIZE_ARGS = " -m 2048 -p 128KiB -s 2048 "
MKUBIFS_ARGS = " -c 3200 -e 126976 -m 2KiB -F"
UBI_VOLNAME = "rootfs"
