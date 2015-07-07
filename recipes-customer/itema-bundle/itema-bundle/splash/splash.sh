case $1 in
        start)
        logo=logo-itema-loading.bgr.gz
        ;;

        stop)
        logo=logo-itema.bgr.gz
        ;;
esac

fbset -depth 24
zcat /usr/share/itema/$logo > /dev/fb0

