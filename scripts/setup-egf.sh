cp scripts/egf-setup-release.sh ../../

cd ../meta-fsl-arm
git am ../meta-egf/patch/0002-fix-sdcard-image-creation-to-allow-dtb-with-.-inside.patch
cd ../meta-fsl-bsp-release
git am ../meta-egf/patch/0003-fix-gpuload-offline-install.patch
cd ../meta-qt5
git checkout -b qt5.8_egf ff073f04109900fc07bf81e2f1df63c626caf342
cd ../meta-fsl-bsp-release
git am ../meta-egf/patch/0004-remove-fix-eglfs_kms_egldevice-builderror.patch.patch
cd ..
git clone -b krogoth https://github.com/rossburton/meta-printing
cd meta-printing
git reset --hard ff4aae27aa4bb9fc0df7edd6e8cd6c9a1377282c

