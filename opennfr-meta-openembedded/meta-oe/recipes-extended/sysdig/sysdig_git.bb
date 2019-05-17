SUMMARY = "A New System Troubleshooting Tool Built for the Way You Work"
DESCRIPTION = "Sysdig is open source, system-level exploration: capture \
system state and activity from a running Linux instance, then save, \
filter and analyze."
HOMEPAGE = "http://www.sysdig.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=f8fee3d59797546cffab04f3b88b2d44"

inherit cmake pkgconfig

OECMAKE_GENERATOR = "Unix Makefiles"
JIT ?= "jit"
JIT_mipsarchn32 = ""
JIT_mipsarchn64 = ""
JIT_aarch64 = ""

DEPENDS = "lua${JIT} zlib ncurses jsoncpp tbb jq openssl elfutils protobuf"
RDEPENDS_${PN} = "bash"

SRC_URI = "git://github.com/draios/sysdig.git;branch=dev \
           file://0001-libsinsp-Port-to-build-with-lua-5.2.patch \
           file://0001-Fix-build-with-musl-backtrace-APIs-are-glibc-specifi.patch \
          "
# v0.24.2
SRCREV = "8704653e1bd05fb564783db7829fa74146ed2c2e"
PV = "0.24.2+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "\
                -DBUILD_DRIVER=OFF \
                -DUSE_BUNDLED_DEPS=ON \
                -DUSE_BUNDLED_LUAJIT=OFF \
                -DUSE_BUNDLED_B64=ON \
                -DUSE_BUNDLED_CARES=ON \
                -DUSE_BUNDLED_PROTOBUF=ON \
                -DUSE_BUNDLED_ZLIB=ON \
                -DUSE_BUNDLED_GRPC=ON \
                -DDIR_ETC=${sysconfdir} \
"

FILES_${PN} += " \
    ${DIR_ETC}/* \
    ${datadir}/zsh/* \
    ${prefix}/src/*  \
"

SECURITY_CFLAGS = ""
SECURITY_LDFLAGS = ""
