SRCBRANCH_0571consolesmart = "0571_console_smart"
SRCREV_0571consolesmart = "e2de22d00c826672be45c90400a12f5c84be61c8"

KERNEL_SRC = "git://androidbuilder/imx53/kernel.git;protocol=ssh;user=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "95e8844562f770d7ae1df33a11f559e2"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0571consolesmart)"
