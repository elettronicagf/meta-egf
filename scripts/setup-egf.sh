cp scripts/egf-setup-release.sh ../../
cd ../poky
git am ../meta-egf/patch/0001-fix-udev-conflict.patch

cd ../meta-fsl-arm
git am ../meta-egf/patch/0002-fix-sdcard-image-creation-to-allow-dtb-with-.-inside.patch

cd ../meta-fsl-bsp-release
git checkout dfbc90ee74624ce3be636c8bd2a47114fa2b71aa

