SUMMARY = "File binari configurazione modulo atwilc3000"
DESCRIPTION = "File binari configurazione modulo atwilc3000"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/wilc3000_wifi_firmware.bin;md5=ba289e68f2e451f936adb081d27292aa"
PR = "r1"

SRC_URI = "file://wifi.tar.bz2"

FILES_${PN} += "/lib/firmware/mchp "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_install_append () {
		install -d ${D}/lib/firmware/mchp
		install -m 0644 ${WORKDIR}/*.bin ${D}/lib/firmware/mchp
}
