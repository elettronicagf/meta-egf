SUMMARY = "File binari e Script di supporto ad immagine ITEMA"
DESCRIPTION = "File binari e Script di supporto ad immagine ITEMA."
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/rmrw;md5=a2b539fd267cead9cd4b4325d944c87b"
PR = "r1"


SRC_URI = "file://java8u33.tar.bz2 \
				file://rmrw \
                file://rmro "

do_install_append () {
        install -d ${D}${bindir}
        cp -av ${WORKDIR}/opt 		${D}
        install -m 0755    ${WORKDIR}/rmrw              ${D}${bindir}
        install -m 0755    ${WORKDIR}/rmro              ${D}${bindir}
}
