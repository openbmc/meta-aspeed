KBRANCH ?= "dev-5.0"
LINUX_VERSION ?= "5.0.11"

SRCREV="8bf6567e77f7aa68975b7c9c6d044bba690bf327"

require linux-aspeed.inc

do_kernel_configme_append() {
    # Remove previous CONFIG_LOCALVERSION
    sed -i '/CONFIG_LOCALVERSION/d' ${B}/.config

    # Latest version after yocto patched (if any)
    latestVersion="-$(git rev-parse --verify HEAD)"
    shortLatestVersion="$(echo ${latestVersion} | cut -c1-8)"

    shortLinuxVersionExt="$(echo ${LINUX_VERSION_EXTENSION} | cut -c1-8)"

    if [ "${latestVersion}" != "${LINUX_VERSION_EXTENSION}" ]; then
        dirtyString="-dirty"
        echo "CONFIG_LOCALVERSION="\"${shortLinuxVersionExt}${dirtyString}${shortLatestVersion}\" >> ${B}/.config
    else
        echo "CONFIG_LOCALVERSION="\"${shortLinuxVersionExt}\" >> ${B}/.config
    fi
}
