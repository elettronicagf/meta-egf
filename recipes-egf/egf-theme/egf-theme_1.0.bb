SUMMARY = "File tema e icone GF per matchbox"
DESCRIPTION = "File tema e icone GF per matchbox"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/themes/GF/index.theme;md5=4cb0102648cffb6a60d0b2ecd5177f2e"
PR = "r1"

SRC_URI = "file://egf-theme.tar"

FILES_${PN} += "/usr/share/ "

do_install() {
	install -d ${D}/usr/share/themes/GF/matchbox
	install -d ${D}/usr/share/themes/GF/gtk-2.0
	install -d ${D}/usr/share/icons/GF/22x22/apps
	install -d ${D}/usr/share/icons/GF/32x32/apps
	install -d ${D}/usr/share/pixmaps
	
	install ${WORKDIR}/themes/GF/index.theme ${D}/usr/share/themes/GF/
	install ${WORKDIR}/themes/GF/matchbox/* ${D}/usr/share/themes/GF/matchbox/		
	install ${WORKDIR}/themes/GF/gtk-2.0/* ${D}/usr/share/themes/GF/gtk-2.0/	
	install ${WORKDIR}/pixmaps/* ${D}/usr/share/pixmaps/
	install ${WORKDIR}/icons/GF/icon-theme.cache ${D}/usr/share/icons/GF/
	install ${WORKDIR}/icons/GF/index.theme ${D}/usr/share/icons/GF/
	install ${WORKDIR}/icons/GF/22x22/apps/* ${D}/usr/share/icons/GF/22x22/apps/
	install ${WORKDIR}/icons/GF/32x32/apps/* ${D}/usr/share/icons/GF/32x32/apps/
}
