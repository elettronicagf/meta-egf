SRCBRANCH_0556evbpopimx6q = "0556_evb_pop"
SRCREV_0556evbpopimx6q = "1377bcfb5b42be78b85e60d3722e96454e1f650e"

KERNEL_SRC = "git://github.com/elettronicagf/kernel-imx.git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"
SRC_URI[md5sum] = "8ea4945546ba6709862221944a965281"
SRC_URI[sha256sum] = "898bbebea2f13032b670734e6f8c1443f429dd43a1f177b826d79895ed687764"


SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0556evbpopimx6q)"
