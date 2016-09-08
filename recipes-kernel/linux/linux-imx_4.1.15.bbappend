SRCBRANCH = "0533_panel_pc"
SRCREV = "78a6d66d4236f5b5738d2e8d1f5ee860fe29e7dd"

KERNEL_SRC = "git://androidbuilder/imx53/kernel.git;protocol=ssh;user=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "93b126a72aa1f14c35a3d14d95b89bab"

#LOCALVERSION = "-0533-001"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0533panelpcimx6dl|0533panelpcimx6q)"
