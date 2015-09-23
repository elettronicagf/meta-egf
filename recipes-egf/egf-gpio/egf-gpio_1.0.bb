SUMMARY = "Script manipolazione GPIO"
DESCRIPTION = "Script manipolazione GPIO"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/setGpio;md5=58963329adfb446cf8a450e6dc6a3dc2"
PR = "r1"

SRC_URI = 	"file://getGpio_in \
			file://getGpio_out \
            file://setGpio "

FILES_${PN} += "/usr/bin/getGpio_in /usr/bin/getGpio_out /usr/bin/setGpio  "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
	install -d ${D}${bindir}
	install -m 0755    ${WORKDIR}/getGpio_in              ${D}${bindir}
	install -m 0755    ${WORKDIR}/getGpio_out              ${D}${bindir}
	install -m 0755    ${WORKDIR}/setGpio              ${D}${bindir}
}
    
