SUMMARY = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"

PV = "${IMAGE_VERSION}"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    ca-certificates \
    hddtemp \
    oe-alliance-base \
    opennfr-enigma2 \
    opennfr-bootlogo \
    opennfr-version-info \
    opennfr-cam \
    opennfr-settings \    
    openssh-sftp-server \
    python-imaging \
    python-cfscrape \
    python-js2py \
    python-requests \
    python-ipaddress  \
    python-netifaces \
    python-pysnmp-se \
    python-pyexecjs \
    python-asn1crypto \   
    python-incremental \
    python-constantly \
    python-hyperlink \ 
    udpxy \
    nodejs \
    tar \
    zip \
    ofgwrite \
    python-gdata \
    libshowiframe \
    dvbsnoop \
    librtmp \
    rtmpdump \
    iperf3 \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs-server \
    packagegroup-base-nfs \
    enigma2-plugin-drivers-usbserial \
    ${@bb.utils.contains("TUNE_FEATURES", "armv", "glibc-compat", "", d)} \
    ${@bb.utils.contains_any("FLASHSIZE", "64", "", " \
        iproute2 \
        ntfs-3g \
        unrar \
    ", d)} \
    "
