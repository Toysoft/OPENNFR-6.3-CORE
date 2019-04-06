FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# sh4 boxes require some headers from the kernel modules (for the frameb

SRC_URI_append_sh4 = "\
    file://stmfb.h \
    file://stm_ioctls.h \
    file://bpamem.h \
"

do_install_append_sh4() {
    install -d ${D}/${includedir}/linux/dvb
    install -m 644 ${WORKDIR}/stm_ioctls.h ${D}/${includedir}/linux/dvb
    install -m 644 ${WORKDIR}/stmfb.h ${D}/${includedir}/linux
    install -m 644 ${WORKDIR}/bpamem.h ${D}/${includedir}
}
