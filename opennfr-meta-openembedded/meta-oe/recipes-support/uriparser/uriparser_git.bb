SUMMARY = "RFC 3986 compliant URI parsing library"
HOMEPAGE = "https://uriparser.github.io"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=fc3bbde670fc6e95392a0e23bf57bda0"

PV = "0.9.3+git${SRCPV}"

SRCREV = "737e95f67bc2e5d8b90a1392797b353b52e5124a"
SRC_URI = "git://github.com/uriparser/${BPN}.git"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DURIPARSER_BUILD_DOCS:BOOL=OFF -DURIPARSER_BUILD_TESTS:BOOL=OFF"

BBCLASSEXTEND += "native"
