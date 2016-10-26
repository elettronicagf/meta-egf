SUMMARY = "Demo camera HDMI eGF per panel PC"
DESCRIPTION = "Demo camera HDMI eGF per Panel PC"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/images/egfDemoCamera.png;md5=c9a2291d918180eb7e454af04b7242fb"
PR = "r1"

SRC_URI = 	"file://demo-camera-hdmi.tar \
		file://egf-demo-camera-hdmi.desktop "

FILES_${PN} += "/home/root/demo-camera-hdmi \
		/usr/share/applications/egf-demo-camera-hdmi.desktop \
		/usr/share/pixmaps/egfDemoCamera.png "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
	install -d ${D}/home/root/demo-camera-hdmi/
	install -d ${D}/home/root/demo-camera-hdmi/images/
	install -d ${D}/usr/share/applications/
	install -d ${D}/usr/share/pixmaps/
	
	install ${WORKDIR}/DemoCameraHDMI.py ${D}/home/root/demo-camera-hdmi/
	install ${WORKDIR}/images/egfDemoCamera.png ${D}/home/root/demo-camera-hdmi/images/
	install -m 0755 ${WORKDIR}/gstcommand.sh ${D}/home/root/demo-camera-hdmi/
	install -m 0755 ${WORKDIR}/launchDemo.sh ${D}/home/root/demo-camera-hdmi/
	install ${WORKDIR}/egf-demo-camera-hdmi.desktop ${D}/usr/share/applications/
	install ${WORKDIR}/images/egfDemoCamera.png ${D}/usr/share/pixmaps/
}
    
