SRCBRANCH_0533panelpcimx6dl = "0533_panel_pc"
SRCREV_0533panelpcimx6dl = "b2961962a7b165d3792b207cb10182eb93341001"
SRCBRANCH_0533panelpcimx6q = "0533_panel_pc"
SRCREV_0533panelpcimx6q = "b2961962a7b165d3792b207cb10182eb93341001"
SRCBRANCH_0541evbpopimx6q = "0541_evb_pop"
SRCREV_0541evbpopimx6q = "6d0491ec215112e60e688b87b3273dca8af5cca7"

KERNEL_SRC = "git://androidbuilder/imx53/kernel.git;protocol=ssh;user=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "93b126a72aa1f14c35a3d14d95b89bab"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0541evbpopimx6q|0533panelpcimx6dl|0533panelpcimx6q)"
