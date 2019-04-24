SUMMARY = "Unbound is a validating, recursive, and caching DNS resolver"
DESCRIPTION = "Unbound's design is a set of modular components which incorporate \
	features including enhanced security (DNSSEC) validation, Internet Protocol \
	Version 6 (IPv6), and a client resolver library API as an integral part of the \
	architecture"

HOMEPAGE = "https://www.unbound.net/"
SECTION = "net"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5308494bc0590c0cb036afd781d78f06"

SRC_URI = "git://github.com/NLnetLabs/unbound.git;protocol=http;branch=master"
SRCREV="61a28c2ee5e60132f26725583b66c1cf8214bb78"

inherit autotools pkgconfig

DEPENDS = "openssl libevent libtool-native bison-native expat"
RDEPENDS_${PN} = "bash openssl-bin"

S = "${WORKDIR}/git"

EXTRA_OECONF = "libtool=${TARGET_PREFIX}libtool"
