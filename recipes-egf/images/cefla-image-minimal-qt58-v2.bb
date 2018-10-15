DESCRIPTION = "Cefla Image with Qt 5.8"

include cefla-image-minimal-qt5-v2.bb

IMAGE_INSTALL_remove = "qtbase-fonts"
IMAGE_INSTALL_remove = "gstreamer1.0-plugins-bad-qt"
IMAGE_INSTALL_append = "qtquickcontrols \
						qtquickcontrols2 \
						qtvirtualkeyboard \
						"
