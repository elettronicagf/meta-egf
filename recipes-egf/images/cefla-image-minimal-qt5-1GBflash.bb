include cefla-image-minimal-qt5.bb

UBINIZE_ARGS = " -m 4096 -p 256KiB -s 4096 "
MKUBIFS_ARGS = " -c 800 -e 253952 -m 4KiB -F"
