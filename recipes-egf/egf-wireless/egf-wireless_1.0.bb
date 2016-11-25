SUMMARY = "File binari configurazione modulo TiWi-BLE"
DESCRIPTION = "File binari configurazione modulo TiWi-BLE"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ti-connectivity/wl1271-nvs.bin;md5=7f61b55463d65ec701ab44ab1acdf15f"
PR = "r1"

SRC_URI = "file://ti-connectivity.tar"

FILES_${PN} += "/home/root/firmware/ti-connectivity/ "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
		mkdir -p ${D}/home/root/firmware/ti-connectivity		
        cp -av ${WORKDIR}/ti-connectivity/*	${D}/home/root/firmware/ti-connectivity/
}
