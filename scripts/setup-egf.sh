cp scripts/egf-setup-release.sh ../../

cd ../poky
git am ../meta-egf/patch/0001-fix-opkg-name-validity-check.patch
cd ../meta-fsl-bsp-release
git am ../meta-egf/patch/0003-fix-missing-imx6ull-platform-in-imx-lib.patch
