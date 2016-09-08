LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel 
#fsl-kernel-localversion fsl-vivante-kernel-driver-handler

GIT_REPO = "git://androidbuilder/imx53/kernel.git;protocol=ssh;user=git"
SRC_URI = "${GIT_REPO};branch=${SRCBRANCH}"

SRC_URI[md5sum] = "93b126a72aa1f14c35a3d14d95b89bab"
