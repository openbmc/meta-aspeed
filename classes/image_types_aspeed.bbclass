inherit image_types

UBOOT_SUFFIX ?= "bin"
INITRAMFS_IMAGE ?= "initramfs-aspeed-image"
INITRAMFS_IMAGE_NAME ?= "${INITRAMFS_IMAGE}-${MACHINE}"
KERNEL_FIT_LINK_NAME ?= "${MACHINE}"

ASPEED_MTD_KERNEL_IMAGE ?= "fitImage-${INITRAMFS_IMAGE_NAME}-${KERNEL_FIT_LINK_NAME}"
ASPEED_MTD_ROOTFS_TYPE ?= "squashfs-xz"
OVERLAY_TYPE ?= "jffs2"
ASPEED_MTD_OVERLAY_IMAGE ?= "overlay-aspeed-image"
ASPEED_MTD_UBOOT_OFFSET ?= "0"
ASPEED_MTD_KERNEL_OFFSET ?= "512"
ASPEED_MTD_ROOTFS_OFFSET ?= "4864"
ASPEED_MTD_OVERLAY_OFFSET ?= "28672"
ASPEED_MTD_SIZE ?= "32786"

MTDIMG = "${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.aspeed-mtdimg"

IMAGE_TYPEDEP_aspeed-mtdimg = "${ASPEED_MTD_ROOTFS_TYPE}"

do_image_aspeed_mtdimg[depends] = " \
        virtual/kernel:do_deploy \
        virtual/bootloader:do_deploy \
        ${ASPEED_MTD_OVERLAY_IMAGE}:do_image_complete \
        "

IMAGE_CMD_aspeed-mtdimg () {
	dd if=/dev/zero bs=1k count=${ASPEED_MTD_SIZE} \
		| tr '\000' '\377' > ${MTDIMG}

	dd bs=1k conv=notrunc seek=${ASPEED_MTD_UBOOT_OFFSET} \
		if=${DEPLOY_DIR_IMAGE}/u-boot.${UBOOT_SUFFIX} \
		of=${MTDIMG}

	dd bs=1k conv=notrunc seek=${ASPEED_MTD_KERNEL_OFFSET} \
		if=${DEPLOY_DIR_IMAGE}/${ASPEED_MTD_KERNEL_IMAGE} \
		of=${MTDIMG}

	dd bs=1k conv=notrunc seek=${ASPEED_MTD_ROOTFS_OFFSET} \
		if=${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${ASPEED_MTD_ROOTFS_TYPE} \
		of=${MTDIMG}

	dd bs=1k conv=notrunc seek=${ASPEED_MTD_OVERLAY_OFFSET} \
		if=${DEPLOY_DIR_IMAGE}/${ASPEED_MTD_OVERLAY_IMAGE}-${MACHINE}.${OVERLAY_TYPE} \
		of=${MTDIMG}
}
