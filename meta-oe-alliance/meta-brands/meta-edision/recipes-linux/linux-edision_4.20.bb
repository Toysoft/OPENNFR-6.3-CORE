SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

KERNEL_RELEASE = "4.20"

inherit kernel machine_kernel_pr

SRC_URI[md5sum] = "a8ad894efc418017afb81a4f280cdb17"
SRC_URI[sha256sum] = "8a00501e49859270d456cce2cb3fde37b503050004a6a2d8418ba672e45ba6c0"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-brcmstb-${PV}/COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "flex-native bison-native openssl-native"

MACHINE_KERNEL_PR_append = ".4"

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_${KERNEL_PACKAGE_NAME}-base = "kernel-base"
PKG_${KERNEL_PACKAGE_NAME}-image = "kernel-image"
RPROVIDES_${KERNEL_PACKAGE_NAME}-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI += "http://source.mynonpublic.com/edision/linux-edision-${PV}.tar.gz \
    file://defconfig \
    file://cpuinfo.patch \
    file://findkerneldevice.py \
    "

S = "${WORKDIR}/linux-brcmstb-${PV}"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"

FILES_${KERNEL_PACKAGE_NAME}-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}* ${KERNEL_IMAGEDEST}/findkerneldevice.py"

kernel_do_install_append () {
    install -d ${D}/${KERNEL_IMAGEDEST}
    install -m 0644 ${KERNEL_OUTPUT_DIR}/${KERNEL_IMAGETYPE} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
    install -m 0644 ${WORKDIR}/findkerneldevice.py ${D}/${KERNEL_IMAGEDEST}
}

pkg_postinst_kernel-image () {
    if [ "x$D" == "x" ]; then
        if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ] ; then
            if grep -q 'root=/dev/mmcblk' /proc/cmdline ; then
                python /${KERNEL_IMAGEDEST}/findkerneldevice.py
                dd if=/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} of=/dev/kernel
            else
                flash_erase /dev/${MTD_KERNEL} 0 0
                nandwrite -p /dev/${MTD_KERNEL} /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
                rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
            fi
        fi
    fi
    true
}

pkg_postrm_kernel-image () {
}

do_rm_work() {
}

# extra tasks
addtask kernel_link_images after do_compile before do_install
