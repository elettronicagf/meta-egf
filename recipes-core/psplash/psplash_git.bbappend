SRCREV = "afd4e228c606a9998feae44a3fed4474803240b7"
PV = "0.1+git${SRCPV}"
PR = "r15"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM = "file://psplash.h;beginline=1;endline=16;md5=840fb2356b10a85bed78dd09dc7745c6"

SRC_URI_append = "file://0001-psplash-generic-logo.patch \
				  file://psplash-init \
				  ${SPLASH_IMAGES}"
