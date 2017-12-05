SRCBRANCH_0609equbeimx6q = "0541_evb_pop"
SRCREV_0609equbeimx6q = "3976fcea4f4812ab90c4ff2c57853da163902c79"

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

COMPATIBLE_MACHINE = "(0609equbeimx6q)"
