SUMMARY = "File binari configurazione modulo wl18xx"
DESCRIPTION = "File binari configurazione modulo wl18xx"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ti-connectivity/wl18xx-fw-4.bin;md5=72416d3f2ae04cf96670208962739daf"
PR = "r1"

SRC_URI = "file://ti-connectivity.tar"

FILES_${PN} += "/home/root/firmware/ti-connectivity/ "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
		mkdir -p ${D}/home/root/firmware/ti-connectivity		
        cp -av ${WORKDIR}/ti-connectivity/*	${D}/home/root/firmware/ti-connectivity/
}
