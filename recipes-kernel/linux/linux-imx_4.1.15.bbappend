SRCBRANCH_0574evbimx7 = "0574_evb_mx7"
SRCREV_0574evbimx7 = "e07854f00ed14cadd8370abad0d60c2071c2226b"

KERNEL_SRC = "git://github.com/elettronicagf/kernel-imx.git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "95e8844562f770d7ae1df33a11f559e2"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0574evbimx7)"
