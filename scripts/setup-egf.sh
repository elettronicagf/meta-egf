cp scripts/egf-setup-release.sh ../../

cd ../meta-fsl-arm
git am ../meta-cefla/patch/0002-fix-sdcard-image-creation-to-allow-dtb-with-.-inside.patch
cd ../meta-fsl-bsp-release
git am ../meta-cefla/patch/0003-fix-gpuload-offline-install.patch

