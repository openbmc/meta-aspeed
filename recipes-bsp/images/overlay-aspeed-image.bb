DESCRIPTION = "An empty filesystem for creating overlays."

PACKAGE_INSTALL = ""

# Do not pollute the overlay image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "overlay-aspeed-image"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${OVERLAY_FSTYPE}"
OVERLAY_FSTYPE ?= "jffs2"

overlay_aspeed_clear_rootfs() {
	rm -rf ${IMAGE_ROOTFS}/*
}

# no systemd presets - skip.
IMAGE_PREPROCESS_COMMAND_remove = " systemd_preset_all;"

# nothing to prelink -  skip.
IMAGE_PREPROCESS_COMMAND_remove = " prelink_setup;"
IMAGE_PREPROCESS_COMMAND_remove = " prelink_image;"

# mklibs - nothing to optimizee - skip.
IMAGE_PREPROCESS_COMMAND_remove = " mklibs_optimize_image;"

IMAGE_PREPROCESS_COMMAND_append = " overlay_aspeed_clear_rootfs;"

inherit image
