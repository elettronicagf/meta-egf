cp scripts/egf-setup-release.sh ../../

cd ../meta-fsl-arm
git am ../meta-cefla/patch/0002-fix-sdcard-image-creation-to-allow-dtb-with-.-inside.patch

