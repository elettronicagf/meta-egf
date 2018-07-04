#!/bin/sh
echo "copio grfA8 + MASK" > /dev/console
mkdir /tmp/mask
TMPDIR=/mnt/.psplash /usr/bin/psplash-write "PROGRESS 60" || true
cp /home/root/grfA8 /tmp/
cp /home/root/version.ini /tmp/
cp -R /home/root/setting/ /tmp/setting/
p1=$(cat /home/root/setting/setting.txt)
p2=$(grep mask /home/root/setting/set_$p1.txt)
export p1
export p2
echo cp -R /home/root/$p2/ /tmp/mask/
echo "unzip MASK" > /dev/console
TMPDIR=/mnt/.psplash /usr/bin/psplash-write "PROGRESS 80" || true
unzip /home/root/$p2.zip -d /tmp/mask/
TMPDIR=/mnt/.psplash /usr/bin/psplash-write "PROGRESS 100" || true
echo sync
TMPDIR=/mnt/.psplash /usr/bin/psplash-write "QUIT" || true
echo "finito creazione application fs grfA8 + MASK" > /dev/console
cd /tmp
pwd > /dev/console
./grfA8 &> /dev/console &
echo "go grfA8" > /dev/console
