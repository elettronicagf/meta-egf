SUMMARY = "Tool for reading accelerometer orientation"
DESCRIPTION = "Tool for reading accelerometer orientation"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/main.c;md5=520601b4e7bce6a8ee64550fa2c22118"
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
