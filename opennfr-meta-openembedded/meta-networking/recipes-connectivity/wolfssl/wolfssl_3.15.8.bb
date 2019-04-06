SUMMARY = "wolfSSL Lightweight Embedded SSL/TLS Library"
DESCRIPTION = "wolfSSL, formerly CyaSSL, is a lightweight SSL library written \
               in C and optimized for embedded and RTOS environments. It can \
               be up to 20 times smaller than OpenSSL while still supporting \
               a full TLS client and server, up to TLS 1.3"
HOMEPAGE = "https://www.wolfssl.com/products/wolfssl"
BUGTRACKER = "https://github.com/wolfssl/wolfssl/issues"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "cyassl"
RPROVIDES_${PN} = "cyassl"

SRC_URI = "https://www.wolfssl.com/wolfssl-${PV}.zip"
SRC_URI = "https://github.com/wolfSSL/wolfssl/archive/v${PV}.zip"
SRC_URI[md5sum] = "5ba7af2bae27716a3d90e55565a08784"
SRC_URI[sha256sum] = "34605bb540ed091b1e4b2f14a1f6c7e3f26302f7c65f46378a27a6f0aa944881"

inherit autotools

BBCLASSEXTEND += "native nativesdk"
