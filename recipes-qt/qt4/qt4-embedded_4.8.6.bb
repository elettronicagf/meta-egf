require qt4-${PV}.inc
require qt4-embedded.inc

QT_CONFIG_FLAGS_append_arm = "${@bb.utils.contains("TUNE_FEATURES", "neon", "", " -no-neon" ,d)}"

QT_CONFIG_FLAGS += " \
 -exceptions \
"

QT_WEBKIT = "" 
QT_CONFIG_FLAGS += "\
 -no-declarative \
 -no-scripttools \
 -no-script \
"

RRECOMMENDS_${PN}_remove = "libqtdeclarative4 libqtdesigner4 \
							libqtdesignercomponents4 \ 
							libqthelp4 libqttest4 libqtclucene4 \
							libqtnetwork4"
