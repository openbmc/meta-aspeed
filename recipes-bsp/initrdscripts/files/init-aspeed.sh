#!/bin/sh

fslist="proc sys dev run"
rodev=/dev/mtdblock4
rwdev=/dev/mtdblock5
rodir=run/initramfs/ro
rwdir=run/initramfs/rw
rofst=squashfs
rwfst=jffs2
upper=$rwdir/cow
work=$rwdir/work

cd /
mkdir -p $fslist
mount dev dev -tdevtmpfs
mount sys sys -tsysfs
mount proc proc -tproc
mount tmpfs run -ttmpfs -o mode=755,nodev

mkdir -p $rodir $rwdir
mount $rodev $rodir -t$rofst -o ro
while [ $? -ne 0 ]; do
    sleep .1
    mount $rodev $rodir -t$rofst -o ro
done
mount $rwdev $rwdir -t$rwfst -o rw
rm -rf $work
mkdir -p $upper $work
mount cow root -toverlay -o lowerdir=$rodir,upperdir=$upper,workdir=$work

for f in $fslist
do
	mount --move $f root/$f
done

exec chroot /root /sbin/init
