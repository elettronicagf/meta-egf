require mraa.inc

#SRC_URI = "https://github.com/intel-iot-devkit/mraa/archive/v${PV}.tar.gz"
SRC_URI = "git://github.com/intel-iot-devkit/mraa.git"
SRC_URI += "\
    file://0001-eQube-add-platform-support.patch \
    file://0002-support-for-0609-boards.patch \
"

PV = "1.9.0+gitr${SRCPV}"
SRCREV = "fbb7d9232067eac3f4508a37a8f7ea0c4fcebacb"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE  =  "0609equbeimx6q"
