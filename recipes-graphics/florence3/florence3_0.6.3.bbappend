FILESEXTRAPATHS_prepend := "${THISDIR}/florence3:"
SRC_URI += "file://0001-make-sound-a-configure-option.patch"
EXTRA_OECONF += " --without-sound"

