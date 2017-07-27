# Freescale imx extra configuration 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mx6 = " file://001-ttymxc4-cmdline-1.patch "


