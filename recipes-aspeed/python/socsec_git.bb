SUMMARY = "Secure-boot utilities for ASPEED BMC SoCs"
HOMEPAGE = "https://github.com/AspeedTech-BMC/socsec/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d50b901333b4eedfee074ebcd6a6d611"

# Switch to [1] once [2] is merged
#
# [1] https://github.com/AspeedTech-BMC/socsec/
# [2] https://github.com/AspeedTech-BMC/socsec/pull/4
SRC_URI = "git://github.com/amboar/socsec/;protocol=https;branch=packaging"

PV = "1.6+git${SRCPV}"
SRCREV = "3352a126cd490799278fa7fa4737f74851dd1bba"

S = "${WORKDIR}/git"

inherit python3native setuptools3

RDEPENDS_${PN} += "python3-bitarray"
RDEPENDS_${PN} += "python3-core"
RDEPENDS_${PN} += "python3-hexdump"
RDEPENDS_${PN} += "python3-jsonschema"
RDEPENDS_${PN} += "python3-jstyleson"
RDEPENDS_${PN} += "python3-pycryptodome"

BBCLASSEXTEND = "native nativesdk"
