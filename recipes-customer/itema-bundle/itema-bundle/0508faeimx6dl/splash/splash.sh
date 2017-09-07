case $1 in
        start)
        logo=logo-itema-loading.bgr.gz
        ;;

        stop)
        fbset -depth 24
        sleep 1
        logo=logo-itema.bgr.gz
        ;;
esac

zcat /usr/share/itema/$logo /usr/share/itema/$logo /usr/share/itema/$logo > /dev/fb0

