FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://remove-redundant-RPATH.patch"
