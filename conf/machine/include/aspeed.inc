PREFERRED_PROVIDER_virtual/kernel ?= "linux-aspeed"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-aspeed"
PREFERRED_PROVIDER_u-boot ?= "u-boot-aspeed"
PREFERRED_PROVIDER_u-boot-fw-utils ?= "u-boot-fw-utils-aspeed"

# Build a FIT image and stuff core-image-minimal in it by default.
KERNEL_IMAGETYPE ?= "fitImage"
KERNEL_CLASSES ?= "kernel-fitimage"
INITRAMFS_IMAGE ?= "core-image-minimal"

MACHINEOVERRIDES =. "aspeed:"
