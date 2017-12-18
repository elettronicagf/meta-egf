DESCRIPTION = "Florence is a virtual keyboard (also called on-screen-keyboard), which allows the user to input text through a touchscreen."
HOMEPAGE = "http://florence.sourceforge.net/english.html"

#NOTICE: If florence can't find its gconf settings, you need to start florence with --config for one time and save the configuration once.

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PR = "r1"

DEPENDS = "cairo gconf glib-2.0-native gnome-doc-utils gstreamer1.0 gtk+3 intltool-native libglade libnotify librsvg libxml2 libxtst"

PROVIDES += "florence"

RPROVIDES_${PN} += "florence"
RREPLACES_${PN} += "florence"
RCONFLICTS_${PN} += "florence"

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/florence/florence/${PV}/florence-${PV}.tar.bz2 \
    file://0001-src-Makefile.am-add-missing-dependency.patch \
"
SRC_URI[md5sum] = "8775051d7352f75dec5a86dc9964e8e0"
SRC_URI[sha256sum] = "422992fd07d285be73cce721a203e22cee21320d69b0fda1579ce62944c5091e"
S = "${WORKDIR}/florence-${PV}"
inherit gettext autotools gconf pkgconfig gsettings

FILES_${PN} += "${datadir}/florence"

EXTRA_OECONF = "--disable-scrollkeeper --without-docs --without-at-spi --without-panelapplet --without-xrecord --without-sound"
