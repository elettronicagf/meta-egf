FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI_append = " file://bootlogd \
		file://rmrw \
		file://rmrwdata \
		file://volatile.cache \
		file://rmrodata \
		file://rmro "

do_install_append () {
	install -m 0755    ${WORKDIR}/bootlogd		${D}${sysconfdir}/default
	install -m 0755    ${WORKDIR}/volatile.cache	${D}${sysconfdir}
	install -d ${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrw		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmro		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrwdata		${D}${bindir}
	install -m 0755    ${WORKDIR}/rmrodata		${D}${bindir}
}
