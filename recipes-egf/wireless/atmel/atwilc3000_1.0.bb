SUMMARY = "Firmware wifi/ble atmel ATWILC3000"
DESCRIPTION = "Firmware wifi/ble atmel ATWILC3000"
SECTION = "wireless"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=b5aee967899c0de3fb19e1891675dd87"
PR = "r1"

#tag "wilc_linux_15_3_2"
SRCREV = "1d25b5b09411461f86b1323351206ac06a45a14b"
SRC_URI = "git://github.com/linux4wilc/firmware.git;protocol=https;branch=dev"
SRC_URI[md5sum] = "aa3933211a23a5d899b2dd8f8f6f8b07"

FILES_${PN} += "/lib/firmware/mchp"

S = "${WORKDIR}/git"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
	install -d ${D}/lib/firmware/mchp
    install -m 0755 ${S}/wilc3000*  ${D}/lib/firmware/mchp
}
