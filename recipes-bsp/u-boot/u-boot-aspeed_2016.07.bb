require u-boot-common-aspeed_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"
DEPENDS += "dtc-native"

SRC_URI += "file://0001-aspeed-flash-Add-MT25QL01GB-chip.patch"

# FIXME this can/should be removed when we have rebased off our
# 2016 snapshot onto a more modern (circa late 2018) u-boot tree.
BUILD_CFLAGS_remove = "-isystem${STAGING_INCDIR_NATIVE}"
