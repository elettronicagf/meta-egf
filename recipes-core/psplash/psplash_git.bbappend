#SRCREV = "eea66d4b757c6f4e26b3691c048a117fe7e7e2d6"
SRCREV = "${AUTOREV}"
PV = "0.1+git${SRCPV}"
PR = "r15"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM = "file://psplash.h;beginline=1;endline=16;md5=840fb2356b10a85bed78dd09dc7745c6"

SRC_URI = "git://bitbucket.org/cefla/psplash.git;protocol=https;user=041fulltouch:041fulltouchcefla;branch=0571_console_smart \
           file://psplash-init \
           ${SPLASH_IMAGES}"
