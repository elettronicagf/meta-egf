SUMMARY = "File binari configurazione modulo wl18xx"
DESCRIPTION = "File binari configurazione modulo wl18xx"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ti-connectivity/wl18xx-fw-4.bin;md5=3af3beaf16bccc2208e2fa0625783465"
PR = "r1"

SRC_URI = "file://ti-connectivity.tar"

FILES_${PN} += "/home/root/firmware/ti-connectivity/ "

RDEPENDS_${PN} += " libnl "

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
		mkdir -p ${D}/home/root/firmware/ti-connectivity		
        cp -av ${WORKDIR}/ti-connectivity/*	${D}/home/root/firmware/ti-connectivity/
}
