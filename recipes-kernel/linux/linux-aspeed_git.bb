KBRANCH ?= "dev-5.0"
LINUX_VERSION ?= "5.0.9"

SRCREV="2607f6f30291522bf07912d254f9cc07fb139b81"

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
