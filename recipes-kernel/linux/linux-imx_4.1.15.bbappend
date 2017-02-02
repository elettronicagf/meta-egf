SRCBRANCH_0533panelpcimx6dl = "0533_panel_pc"
SRCREV_0533panelpcimx6dl = "c82faf0d711b84b3f4a6d70d2a4debaf9ad482e4"
SRCBRANCH_0533panelpcimx6q = "0533_panel_pc"
SRCREV_0533panelpcimx6q = "c82faf0d711b84b3f4a6d70d2a4debaf9ad482e4"
SRCBRANCH_0533panelpcimx6qdlsolo = "0533_panel_pc"
SRCREV_0533panelpcimx6qdlsolo = "c82faf0d711b84b3f4a6d70d2a4debaf9ad482e4"
SRCBRANCH_0541evbpopimx6q = "0541_evb_pop"
SRCREV_0541evbpopimx6q = "172cd75cd4567af709c435785eebcbf2afa2d73b"

KERNEL_SRC = "git://github.com/elettronicagf/kernel-imx.git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "95e8844562f770d7ae1df33a11f559e2"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0541evbpopimx6q|0533panelpcimx6dl|0533panelpcimx6q|0533panelpcimx6qdlsolo)"
