SUMMARY = "EDID decoder and conformance tester"
DESCRIPTION = "edid-decode decodes EDID monitor description data in human-readable format"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://edid-decode.c;beginline=1;endline=23;md5=7dd9ce55e66fa96c61558d763a93d83a" 

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRC_URI = "git://linuxtv.org/edid-decode.git;protocol=git"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig
