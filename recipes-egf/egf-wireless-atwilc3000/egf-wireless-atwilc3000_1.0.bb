SUMMARY = "File binari configurazione modulo atwilc3000"
DESCRIPTION = "File binari configurazione modulo atwilc3000"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://wilc3000_wifi_firmware.bin;md5=ba289e68f2e451f936adb081d27292aa"
PR = "r1"

SRCREV = "wilc_linux_15_2_1"
SRC_URI = "git://github.com/linux4wilc/firmware.git;protocol=https"
SRC_URI[md5sum] = "1d6c908e3d254e07230ac0db30f12f92"

S = "${WORKDIR}/git"

do_compile() {
	:
}

do_install () {
		install -d ${D}/lib/firmware/mchp
		install -m 0644 *.bin ${D}/lib/firmware/mchp
}

FILES_${PN} += "/lib/firmware/mchp "

ALLOW_EMPTY_${PN} = "1"
