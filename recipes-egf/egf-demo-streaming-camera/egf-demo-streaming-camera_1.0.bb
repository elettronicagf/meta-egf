SUMMARY = "Demo streaming eGF - lato tx Smart Camera"
DESCRIPTION = "Demo streaming eGF - lato tx Smart Camera"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/egf-demo;md5=4da4c05925956ece567817e0a4d2bfc8"
PR = "r1"

SRC_URI = 	"file://demo-pop-streaming.tar \
		file://egf-demo \
		file://interfaces.demo \
		file://wpa_supplicant.conf.demo "

FILES_${PN} += "/home/root/demo-pop-streaming \
		/etc/network/interfaces.demo \
		/etc/init.d/egf-demo \
		/etc/wpa_supplicant.conf "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
	install -d ${D}/etc/
	install -d ${D}/etc/network/
	install -d ${D}/etc/init.d/
	install -d ${D}/home/
	install -d ${D}/home/root/
	install -d ${D}/home/root/demo-pop-streaming/
	
	install ${WORKDIR}/DemoPopStreaming.py ${D}/home/root/demo-pop-streaming/
        install ${WORKDIR}/gst-pipe-lan.sh ${D}/home/root/demo-pop-streaming/
	install ${WORKDIR}/gst-pipe-wlan.sh ${D}/home/root/demo-pop-streaming/
	install ${WORKDIR}/launchDemo.sh ${D}/home/root/demo-pop-streaming/
	install ${WORKDIR}/interfaces.demo ${D}/etc/network/
	install ${WORKDIR}/egf-demo ${D}/etc/init.d/
	install ${WORKDIR}/wpa_supplicant.conf.demo ${D}/etc/
}
    
