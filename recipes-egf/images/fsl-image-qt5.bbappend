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
    opkg-utils minicom opkg egf-gpio can-utils iproute2 \
    openssh openssh-sftp-server nano strace i2c-tools gdb gdbserver \
    mtd-utils \
"

IMAGE_INSTALL_remove = " \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-audio \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-tools-testapps \
    packagegroup-fsl-tools-benchmark \
"

IMAGE_FSTYPES = "tar.bz2 ubi"
UBINIZE_ARGS = " -m 2048 -p 128KiB -s 2048 "
MKUBIFS_ARGS = " -c 3200 -e 126976 -m 2KiB -F"
UBI_VOLNAME = "rootfs"
