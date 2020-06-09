cp scripts/egf-setup-release.sh ../../
cd ../poky
git am ../meta-egf/patch/0001-fix-udev-conflict.patch

cd ../meta-fsl-arm
git am ../meta-egf/patch/0002-fix-sdcard-image-creation-to-allow-dtb-with-.-inside.patch

cd ../meta-fsl-bsp-release
git checkout dfbc90ee74624ce3be636c8bd2a47114fa2b71aa

grep -r git.freescale.com/imx ../* | awk -F: '{print $1}' | xargs -I% sed -i "s/git:\/\/git.freescale.com\/imx/git:\/\/source.codeaurora.org\/external\/imx/g" %
grep -r linux-2.6-imx.git ../* | awk -F: '{print $1}' | xargs -I% sed -i "s/linux-2.6-imx.git/linux-imx.git/g" %

