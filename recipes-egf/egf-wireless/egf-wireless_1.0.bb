SUMMARY = "File binari configurazione modulo TiWi-BLE"
DESCRIPTION = "File binari configurazione modulo TiWi-BLE"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ti-connectivity/wl1271-nvs.bin;md5=f16669fe9c81adce0a368eacff40b62d"
PR = "r1"

SRC_URI = "file://ti-connectivity.tar"

FILES_${PN} += "/home/root/firmware/ti-connectivity/ "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
		mkdir -p ${D}/home/root/firmware/ti-connectivity		
        cp -av ${WORKDIR}/ti-connectivity/*	${D}/home/root/firmware/ti-connectivity/
}
