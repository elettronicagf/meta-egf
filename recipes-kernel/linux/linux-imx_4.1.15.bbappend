SRCBRANCH_0571consolesmart = "0571_console_smart"
SRCREV_0571consolesmart = "c16dfe2d937e975a26d45ba070aef82a1a0769b8"

SRCBRANCH_0572consolefulltouch = "0572_console_fulltouch"
SRCREV_0572consolefulltouch = "617dbc9efd2963b7e58c085ee9f36d808286ffc8"

KERNEL_SRC = "git://bitbucket.org/cefla/kernel-imx.git;protocol=ssh;user=git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "95e8844562f770d7ae1df33a11f559e2"

SCMVERSION = "n"

do_copy_defconfig () {
    # copy latest imx_v7_defconfig to use
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/imx_v7_egf_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(0571consolesmart|0572consolefulltouch)"
