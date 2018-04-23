DESCRIPTION = "eGF Image with Qt 5.8"

include egf-image-qt5.bb

IMAGE_INSTALL_remove = "qtbase-fonts"
IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt"
IMAGE_INSTALL_append = "qtquickcontrols2"
