SUMMARY = "Custom Binaries"
DESCRIPTION = "custom binaries"
SECTION = "base"
PR = "r1"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SRC_URI = "file://GPLv2.patch "

SRC_URI_append_0424carrier = " file://ceflaBranding \
		file://ceflaHwRev \
		file://kobs-ng-1.2 \
		"


do_configure() {
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755    ${WORKDIR}/ceflaBranding	${D}${bindir}
	install -m 0755    ${WORKDIR}/ceflaHwRev	${D}${bindir}
	install -m 0755    ${WORKDIR}/kobs-ng-1.2	${D}${bindir}/kobs-ng
}
