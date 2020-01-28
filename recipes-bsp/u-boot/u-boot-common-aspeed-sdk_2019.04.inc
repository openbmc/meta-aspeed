HOMEPAGE = "https://github.com/AspeedTech-BMC/u-boot"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "e87eb2705bcb5850b6af8ca79d3ba1d6a51f0a26"

UBRANCH = "aspeed-dev-v2019.04"
SRC_URI = "git://github.com/AspeedTech-BMC/u-boot;branch=${UBRANCH};protocol=https"

# There are different BOOTCOMMANDS for differently sized raw flash modules.
ASPEED_IMAGE_SIZE_KB ?= "32768"
MACHINEOVERRIDES .= ":flash-${ASPEED_IMAGE_SIZE_KB}"
SRC_URI_append_flash-131072 = " file://bootcmd.cfg"

S = "${WORKDIR}/git"

PV = "v2019.04+git${SRCPV}"
