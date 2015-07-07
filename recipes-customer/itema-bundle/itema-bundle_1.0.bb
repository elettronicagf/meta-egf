SUMMARY = "File binari e Script di supporto ad immagine ITEMA"
DESCRIPTION = "File binari e Script di supporto ad immagine ITEMA."
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/rmrw;md5=a2b539fd267cead9cd4b4325d944c87b"
PR = "r1"

SRC_URI = "file://java8u33.tar.bz2 \
				file://rmrw \
				file://rmro \
				file://rmrwdata \
                file://rmrodata \
                file://itema.desktop \
                file://autostart-itema.desktop \
                file://splash/splash.sh \
                file://splash/logo-itema-loading.bgr.g \
                file://splash/logo-itema.bgr.g "

FILES_${PN} += "${datadir}/itema /opt/java8u33"

DEPENDS = "libx11"

INITSCRIPT_NAME = "splash.sh"
INITSCRIPT_PARAMS = "start 0 S . stop 20 0 1 6 ."

inherit update-rc.d

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/init.d
        
        cp -av ${WORKDIR}/opt 		${D}
        
        install -m 0755    ${WORKDIR}/rmrw              ${D}${bindir}
        install -m 0755    ${WORKDIR}/rmro              ${D}${bindir}
        install -m 0755    ${WORKDIR}/rmrwdata          ${D}${bindir}
        install -m 0755    ${WORKDIR}/rmrodata          ${D}${bindir}
        
        install -d ${D}/usr/share/applications
        install ${WORKDIR}/itema.desktop                ${D}/usr/share/applications/
        
        install -d ${D}/etc/xdg/autostart
        install ${WORKDIR}/autostart-itema.desktop      ${D}/etc/xdg/autostart/itema.desktop
        
        install -m 0755 ${WORKDIR}/splash/${INITSCRIPT_NAME}	${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
        
        #voglio installare i bmp in formato gz!
        install -d ${D}/usr/share/itema
        install ${WORKDIR}/splash/logo-itema.bgr.g				${D}/usr/share/itema/logo-itema.bgr.gz
        install ${WORKDIR}/splash/logo-itema-loading.bgr.g		${D}/usr/share/itema/logo-itema-loading.bgr.gz
}
