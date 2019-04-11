require pseudo.inc

SRC_URI = "git://git.yoctoproject.org/pseudo \
           file://0001-configure-Prune-PIE-flags.patch \
           file://fallback-passwd \
           file://fallback-group \
           file://moreretries.patch \
           file://toomanyfiles.patch \
           "

SRCREV = "6ebc7d6bc8ab973d0ba949eeb363821811ce8dc5"
S = "${WORKDIR}/git"
PV = "1.9.0+git${SRCPV}"

