SUMMARY = "grf launcher scripts for mbugrf light"
DESCRIPTION = "Installs grf launcher scripts"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/launchgrf.sh;md5=c5c0f878e27aac8c608c25fff34da744"
PV = "1.0"
PR = "r1"

SRC_URI = "file://launchgrf.sh "

inherit update-rc.d

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/launchgrf.sh ${D}${sysconfdir}/init.d/launchgrf.sh
}

FILES_${PN} += "/etc/init.d/launchgrf.sh "

INITSCRIPT_NAME = "launchgrf.sh"
INITSCRIPT_PARAMS = "start 38 S . "
