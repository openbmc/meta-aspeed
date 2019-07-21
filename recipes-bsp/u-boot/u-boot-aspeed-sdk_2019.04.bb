require u-boot-common-aspeed-sdk_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"
DEPENDS += "bc-native dtc-native"
