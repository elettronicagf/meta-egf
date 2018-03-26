DESCRIPTION = "Cefla Image with Qt 5.8"

include cefla-image-minimal-qt5-1GBflash.bb

IMAGE_INSTALL_remove = "qtbase-fonts"
IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt"
