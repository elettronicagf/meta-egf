SUMMARY = "Display MIPI setup"
DESCRIPTION = "EGF Display MIPI setup"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/setup-display.sh;md5=d9bd7aa3c8f49f735b1f5d8f25c744d1"
PR = "r1"

SRC_URI = 	"file://setup-display.sh"

FILES_${PN} += "/etc/init.d/setup-display.sh "

inherit update-rc.d

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

INITSCRIPT_NAME = "setup-display.sh"
INITSCRIPT_PARAMS = "start 10 S . "

do_install_append () {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755    ${WORKDIR}/setup-display.sh   ${D}${sysconfdir}/init.d/setup-display.sh
}
