#IMAGE_CLASSES += "airdigitalemmc_image_single"
IMAGE_CLASSES += "airdigitalemmc_image_multi"

MACHINE_FEATURES += "qthbbtv qtstalker linuxwifi"

IMAGE_FSTYPES += "airdigitalemmc"
IMAGE_ROOTFS_SIZE ?= "589824"

TARGET_ARCH = "arm"
DEFAULTTUNE = "cortexa15hf-neon-vfpv4"

PREFERRED_VERSION_linux-airdigital = "4.10.12"

PREFERRED_PROVIDER_virtual/egl ?= "airdigital-v3ddriver-${MACHINE}" 
PREFERRED_PROVIDER_virtual/libgles1 ?= "airdigital-v3ddriver-${MACHINE}"
PREFERRED_PROVIDER_virtual/libgles2 ?= "airdigital-v3ddriver-${MACHINE}"
PREFERRED_PROVIDER_virtual/libgbm ?= "airdigital-v3ddriver-${MACHINE}"
PREFERRED_PROVIDER_virtual/libgl ?= "mesa"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa"

# Qt
PACKAGECONFIG_GL_pn-qtbase = "no-opengl linuxfb"

IMAGE_CMD_airdigitalemmc_append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.emmc.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/disk.img; \
    cd ${IMAGE_ROOTFS}; \
    tar -cvf ${DEPLOY_DIR_IMAGE}/rootfs.tar -C ${IMAGE_ROOTFS} .; \
    mv ${DEPLOY_DIR_IMAGE}/rootfs.tar ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.tar; \
    bzip2 ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.tar; \
    cp ${DEPLOY_DIR_IMAGE}/zImage ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
    echo ${IMAGE_VER_STRING} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    echo "rename this file to force to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -Rf ${IMAGEDIR}; \
    "

require conf/machine/include/tune-cortexa15.inc
require conf/machine/include/airdigital-oem.inc