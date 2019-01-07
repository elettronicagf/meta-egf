require qt4-x11-free.inc
require qt4-${PV}.inc

QT_SQL_DRIVER_FLAGS_remove = "-system-sqlite"

QT_CONFIG_FLAGS_append_arm = "${@bb.utils.contains("TUNE_FEATURES", "neon", "", " -no-neon" ,d)}"

QT_CONFIG_FLAGS += " \
 -no-embedded \
 -xrandr \
 -x11"
 
QT_WEBKIT = "" 
QT_CONFIG_FLAGS += "\
 -no-declarative \
 -no-scripttools \
 -no-script \
"

RRECOMMENDS_${PN}_remove = "libqtdeclarative4 libqtdesigner4 \
							libqtdesignercomponents4 \ 
							libqthelp4 "
