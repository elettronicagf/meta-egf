#include linux-egf.inc
require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

GIT_REPO = "git://androidbuilder/imx53/kernel.git;protocol=ssh;user=git"
SRC_URI = "${GIT_REPO};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "93b126a72aa1f14c35a3d14d95b89bab"

SRCBRANCH = "0533_panel_pc"
SRCREV = "78a6d66d4236f5b5738d2e8d1f5ee860fe29e7dd"
#LOCALVERSION = "-0533-001"

KBUILD_DEFCONFIG_KMACHINE = "imx7_v7_egf_defconfig"

COMPATIBLE_MACHINE = "(0533panelpcimx6dl|0533panelpcimx6q)"
