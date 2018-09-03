SUMMARY = "Tool for reading accelerometer orientation"
DESCRIPTION = "Tool for reading accelerometer orientation"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/main.c;md5=4a800036273fd8330015d4b93cd634d4"
PR = "r1"

SRC_URI = 	"file://main.c \
			file://i2c-dev.h \
            file://Makefile "

S = "${WORKDIR}"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} ' 'BUILDDIR=${S}'"

    
do_install () {
	install -d ${D}${bindir}
	install -m 0755    ${WORKDIR}/accelerometerOrientation       ${D}${bindir}
}


FILES_${PN} += "/usr/bin/accelerometerOrientation  "

PARALLEL_MAKE = ""

BBCLASSEXTEND = "native"
