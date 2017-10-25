SRCBRANCH_0556evbpopimx6q = "0556_evb_pop"
SRCREV_0556evbpopimx6q = "56cb6b2ad9262f638adc61bd46ffe2a0c3505d68"

#KERNEL_SRC = "git://github.com/elettronicagf/kernel-imx.git"
KERNEL_SRC = "http://androidbuilder/imx53/kernel.git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "95e8844562f770d7ae1df33a11f559e2"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0556evbpopimx6q)"
