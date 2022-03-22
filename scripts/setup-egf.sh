chmod +x scripts/egf-setup-release.sh
cp scripts/egf-setup-release.sh ../../

# Chromium patch n.1
cp ./patch/0001-Only-DRI-for-x11.patch ../meta-browser/meta-chromium/recipes-browser/chromium/files/0013-Only-DRI-for-x11.patch
cp ./meta-browser/meta-chromium/recipes-browser/chromium/chromium-gn.inc ../meta-browser/meta-chromium/recipes-browser/chromium/

# Chromium patch n.2
cp ./meta-imx/meta-sdk/recipes-fsl/images/imx-image-multimedia.bb ../meta-imx/meta-sdk/recipes-fsl/images/
