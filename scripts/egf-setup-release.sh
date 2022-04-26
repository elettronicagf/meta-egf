
#!/bin/sh
#
# ElettronicaGF Build Enviroment Setup Script
#

if [ -z "$MACHINE" ]; then
    echo setting to default machine
    MACHINE='0510smggrf'
fi

source ./fsl-setup-release.sh "$@"

echo "BBLAYERS += \" \${BSPDIR}/sources/meta-egf \"" >> $BUILD_DIR/conf/bblayers.conf

if [ $(hostname) = "androidbuilder" ]; then

YOCTO_BASE_PATH=\"/DataRaid/yocto\"
DL_DIR=\"/DataRaid/yocto/yocto-input/downloads\"
SSTATE_DIR=\"/DataM2/yocto/yocto-output/\${MACHINE}/sstate-cache\"
TMPDIR=\"/DataRaid/yocto/yocto-output/\${MACHINE}/tmp\"

echo YOCTO_BASE_PATH = $YOCTO_BASE_PATH >> ./conf/local.conf
echo DL_DIR = $DL_DIR >> ./conf/local.conf
echo SSTATE_DIR = $SSTATE_DIR >> ./conf/local.conf
echo TMPDIR = $TMPDIR >> ./conf/local.conf

elif [ $(hostname) = "dockerbuilder" ]; then

YOCTO_BASE_PATH=\"/home/yocto\"
DL_DIR=\"/home/yocto/downloads\"
SSTATE_DIR=\"/home/yocto/yocto-output/\${MACHINE}/sstate-cache\"
TMPDIR=\"/home/yocto/yocto-output/\${MACHINE}/tmp\"

echo YOCTO_BASE_PATH = $YOCTO_BASE_PATH >> ./conf/local.conf
echo DL_DIR = $DL_DIR >> ./conf/local.conf
echo SSTATE_DIR = $SSTATE_DIR >> ./conf/local.conf
echo TMPDIR = $TMPDIR >> ./conf/local.conf

else
	echo "no dedicated variables for this host"
fi

echo "PACKAGE_CLASSES = \"package_ipk\"" >> $BUILD_DIR/conf/local.conf
echo "RM_OLD_IMAGE = \"1\"" >> $BUILD_DIR/conf/local.conf
echo "INHERIT += \"rm_work\"" >> $BUILD_DIR/conf/local.conf
echo "DISTRO_FEATURES_append += \"pam\"" >> $BUILD_DIR/conf/local.conf

