SUMMARY = "Demo streaming eGF - lato ricevente Panel PC"
DESCRIPTION = "Demo streaming eGF - lato ricevente Panel PC"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/images/logoegf_small.png;md5=70fa09470aa38c57d950c44cf8b85757"
PR = "r1"

SRC_URI = 	"file://demo-panelpc-streaming.tar \
		file://egf-demo-streaming.desktop \
		file://dhcpd.conf.demo \
		file://dhcp-server.demo \
		file://interfaces.demo \
		file://hostapd.conf.demo "

FILES_${PN} += "/home/root/demo-panelpc-streaming \
		/usr/share/applications/egf-demo-streaming.desktop \
		/usr/share/pixmaps/egfDemoCamera.png \
		/etc/dhcp/dhcpd.conf.demo \
		/etc/default/dhcp-server.demo \
		/etc/network/interfaces.demo \
		/etc/hostapd.conf.demo "


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "ldflags"

do_install_append () {
	install -d ${D}/home/root/demo-panelpc-streaming/
	install -d ${D}/home/root/demo-panelpc-streaming/images/
	install -d ${D}/usr/share/applications/
	install -d ${D}/usr/share/pixmaps/
	install -d ${D}/etc/
	install -d ${D}/etc/dhcp/
	install -d ${D}/etc/network/
	install -d ${D}/etc/default/
	
	install ${WORKDIR}/CheckBatteryLevel.py ${D}/home/root/demo-panelpc-streaming/
        install ${WORKDIR}/Ui_MainWindow.py ${D}/home/root/demo-panelpc-streaming/
	install ${WORKDIR}/DemoStreaming.py ${D}/home/root/demo-panelpc-streaming/
	install ${WORKDIR}/MainWindow.py ${D}/home/root/demo-panelpc-streaming/
	install ${WORKDIR}/images/egfDemoCamera.png ${D}/home/root/demo-panelpc-streaming/images/
	install ${WORKDIR}/images/powercfg_battery.png ${D}/home/root/demo-panelpc-streaming/images/
	install ${WORKDIR}/images/logoegf_small.png ${D}/home/root/demo-panelpc-streaming/images/
	install -m 0755 ${WORKDIR}/gstcommand-1280x800.sh ${D}/home/root/demo-panelpc-streaming/
        install -m 0755 ${WORKDIR}/gstcommand-640x480.sh ${D}/home/root/demo-panelpc-streaming/
        install -m 0755 ${WORKDIR}/gstcommand-800x480.sh ${D}/home/root/demo-panelpc-streaming/
        install -m 0755 ${WORKDIR}/gstcommand-800x600.sh ${D}/home/root/demo-panelpc-streaming/
        install -m 0755 ${WORKDIR}/gstcommand-1024x600.sh ${D}/home/root/demo-panelpc-streaming/
	install -m 0755 ${WORKDIR}/launchDemo.sh ${D}/home/root/demo-panelpc-streaming/
	install ${WORKDIR}/egf-demo-streaming.desktop ${D}/usr/share/applications/
	install ${WORKDIR}/images/egfDemoCamera.png ${D}/usr/share/pixmaps/
	install ${WORKDIR}/dhcpd.conf.demo ${D}/etc/dhcp/
	install ${WORKDIR}/dhcp-server.demo ${D}/etc/default/
	install ${WORKDIR}/interfaces.demo ${D}/etc/network/
	install ${WORKDIR}/hostapd.conf.demo ${D}/etc/
}
    
