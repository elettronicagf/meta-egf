SUMMARY = "Script per gestione update OTA egf update system"
DESCRIPTION = "Script per gestione update OTA egf update system"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/egf_ota_read_status;md5=711464c9d375ae6106044e427b95942c"
PR = "r1"

SRC_URI = "file://egf_ota_read_status file://egf_ota_write_status"

FILES_${PN} += "/usr/bin/egf_ota_read_status "
FILES_${PN} += "/usr/bin/egf_ota_write_status "

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
		install -d ${D}/usr/bin
		install -m 0755  ${WORKDIR}/egf_ota_read_status	${D}/usr/bin
		install -m 0755  ${WORKDIR}/egf_ota_write_status	${D}/usr/bin

}
