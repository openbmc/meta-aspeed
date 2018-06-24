PREFERRED_PROVIDER_virtual/kernel ?= "linux-aspeed"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-aspeed"
PREFERRED_PROVIDER_u-boot ?= "u-boot-aspeed"
PREFERRED_PROVIDER_u-boot-fw-utils ?= "u-boot-fw-utils-aspeed"

MACHINEOVERRIDES =. "aspeed:"

IMAGE_CLASSES += "image_types_aspeed"
IMAGE_FSTYPES ?= "aspeed-mtdimg"
INITRAMFS_FSTYPES ?= "cpio.lzma"
