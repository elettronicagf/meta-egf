FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_append = " \
		file://001-mount-app-partition.patch \
		file://rmrw \
		file://rmrwdata \
		file://rmrodata \
		file://rmro "

do_install_append () {
	install -d ${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrw		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmro		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrwdata		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrodata		${D}${bindir}
}
