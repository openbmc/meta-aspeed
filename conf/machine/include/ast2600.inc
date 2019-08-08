SOC_FAMILY = "aspeed-g6"
include conf/machine/include/soc-family.inc
include conf/machine/include/arm/arch-armv7a.inc
require conf/machine/include/aspeed.inc

UBOOT_ENTRYPOINT = "0x80001000"
UBOOT_LOADADDRESS = "0x80001000"
