rmrw

rm -f /etc/pointercal.xinput

#calibrazione senza correzione non linearità
echo 0 > /sys/devices/soc0/soc.0/2000000.aips-bus/2000000.spba-bus/200c000.ecspi/spi_master/spi1/spi1.1/enable_correction

#calibrazione senza correzione per determinare il centro fisico del touch
xinput_calibrator_once.sh

TMP=$(cat /etc/pointercal.xinput)
echo ${TMP:66} | grep -o '[0-9 ]*' > /sys/devices/soc0/soc.0/2000000.aips-bus/2000000.spba-bus/200c000.ecspi/spi_master/spi1/spi1.1/rawcalibration

#abilitazione correzione
echo 1 > /sys/devices/soc0/soc.0/2000000.aips-bus/2000000.spba-bus/200c000.ecspi/spi_master/spi1/spi1.1/enable_correction

rm -f /etc/pointercal.xinput

#calibrazione con correzione
xinput_calibrator_once.sh

rmro
