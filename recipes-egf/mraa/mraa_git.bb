require mraa.inc

#SRC_URI = "https://github.com/intel-iot-devkit/mraa/archive/v${PV}.tar.gz"
SRC_URI = "git://github.com/intel-iot-devkit/mraa.git"
SRC_URI += "\
    file://0001-eQube-add-platform-support.patch \
"

PV = "1.8.0+gitr${SRCPV}"
SRCREV = "eb7238d9afea044701dba1c26cc5076854e3238c"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE  =  "0609equbeimx6q"
