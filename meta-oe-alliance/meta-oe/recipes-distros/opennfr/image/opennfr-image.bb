DESCRIPTION = "OPENNFR Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "OPENNFR team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "${BUILD_VERSION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_rootfs[deptask] = "do_rm_work"

IMAGE_INSTALL = "opennfr-base \
    ${@bb.utils.contains("MACHINE_FEATURES", "dvbc-only", "", "enigma2-plugin-settings-defaultsat", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "no-cl-svr", "", \
    " \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs-server \
    packagegroup-base-nfs \
    ", d)} \
    "

IMAGE_FEATURES += "package-management"

inherit image

image_preprocess() {
			curdir=$PWD
			cd ${IMAGE_ROOTFS}

			# because we're so used to it
			ln -s opkg usr/bin/ipkg || true
			ln -s opkg-cl usr/bin/ipkg-cl || true
			cd $curdir

			cd ${IMAGE_ROOTFS}/media
			mkdir -p ${IMAGE_ROOTFS}/media/card
			mkdir -p ${IMAGE_ROOTFS}/media/cf
			mkdir -p ${IMAGE_ROOTFS}/media/hdd
			mkdir -p ${IMAGE_ROOTFS}/media/net
			mkdir -p ${IMAGE_ROOTFS}/media/upnp
			mkdir -p ${IMAGE_ROOTFS}/media/usb
			touch ${IMAGE_ROOTFS}/media/hdd/.fstab
			touch ${IMAGE_ROOTFS}/media/usb/.fstab
			cd $curdir
			
			cd ${IMAGE_ROOTFS}/etc
			rm -rf ${IMAGE_ROOTFS}/etc/passwd
			rm -rf ${IMAGE_ROOTFS}/etc/shadow
			mv ${IMAGE_ROOTFS}/etc/passwd-neu ${IMAGE_ROOTFS}/etc/passwd
			mv ${IMAGE_ROOTFS}/etc/shadow-neu ${IMAGE_ROOTFS}/etc/shadow
			rm -rf ${IMAGE_ROOTFS}/etc/passwd-neu
			rm -rf ${IMAGE_ROOTFS}/etc/shadow-neu
			mv ${IMAGE_ROOTFS}/etc/pwd.lock ${IMAGE_ROOTFS}/etc/.pwd.lock
			cd $curdir
			
			cd ${IMAGE_ROOTFS}/etc/network	
			rm -rf ${IMAGE_ROOTFS}/etc/network/interfaces
			mv ${IMAGE_ROOTFS}/etc/network/interfaces-neu ${IMAGE_ROOTFS}/etc/network/interfaces
			rm -rf ${IMAGE_ROOTFS}/etc/network/interfaces-neu
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES		
			rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2.mo
			mv ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2-neu.mo ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2.mo
			rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/po/de/LC_MESSAGES/enigma2-neu.mo
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib
			ln -s libbz2.so.1.0.6 libbz2.so.0.0.0 || true
			ln -s libcrypto.so.1.0.2 libcrypto.so.0.9.8 || true
			ln -s libcrypto.so.1.0.2 libcrypto.so.0.9.7 || true
			ln -s libcrypto.so.1.0.2 libcrypto.so.1.0.0 || true
			ln -s libssl.so.1.0.2 libssl.so.1.0.0 || true		
			
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/emu
				if [ "${TARGET_ARCH}" = "mipsel" ]; then
					tar xvpzf ${IMAGE_ROOTFS}/usr/emu/CCcam230.tar.gz -C ${IMAGE_ROOTFS}/usr/emu/
					tar xvpzf ${IMAGE_ROOTFS}/usr/emu/oscam.tar.gz -C ${IMAGE_ROOTFS}/usr/emu/
					rm -rf ${IMAGE_ROOTFS}/usr/emu/oscam.tar.gz
					rm -rf ${IMAGE_ROOTFS}/usr/emu/oscam-arm.tar.gz
					rm -rf ${IMAGE_ROOTFS}/usr/emu/CCcam230.tar.gz
				else
					tar xvpzf ${IMAGE_ROOTFS}/usr/emu/oscam-arm.tar.gz -C ${IMAGE_ROOTFS}/usr/emu/
					rm -rf ${IMAGE_ROOTFS}/usr/emu/oscam.tar.gz
					rm -rf ${IMAGE_ROOTFS}/usr/emu/oscam-arm.tar.gz
					rm -rf ${IMAGE_ROOTFS}/usr/emu/CCcam230.tar.gz
				fi
			cd $curdir

			cd ${IMAGE_ROOTFS}/var
			mkdir ${IMAGE_ROOTFS}/var/volatile/run
			cp ${IMAGE_ROOTFS}/var/smbd.pid ${IMAGE_ROOTFS}/var/volatile/run/smbd.pid
			cp ${IMAGE_ROOTFS}/var/nmbd.pid ${IMAGE_ROOTFS}/var/volatile/run/nmbd.pid
			rm -rf ${IMAGE_ROOTFS}/var/smbd.pid
			rm -rf ${IMAGE_ROOTFS}/var/nmbd.pid
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter
				if [ "${TARGET_ARCH}" = "mipsel" ]; then
					tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/ 
			        	mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so
					rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips.tar.gz
					rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips	
				else
					rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so-mips.tar.gz
				fi
			cd $curdir

			cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unrar-free.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/
				tar xvpzf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unzip.tar.gz -C ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unrar-free.tar.gz
				rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/unzip.tar.gz		
			cd $curdir

			cd ${IMAGE_ROOTFS}/etc/opkg
				if [ "${TARGET_ARCH}" = "mipsel" ]; then
					rm -rf ${IMAGE_ROOTFS}/etc/opkg/secret-feed-arm.conf
				else
					rm -rf ${IMAGE_ROOTFS}/etc/opkg/secret-feed.conf
					mv ${IMAGE_ROOTFS}/etc/opkg/secret-feed-arm.conf ${IMAGE_ROOTFS}/etc/opkg/secret-feed.conf 					
				fi
			cd $curdir			
}

INHIBIT_DEFAULT_DEPS = "1"

do_package_index[nostamp] = "1"
do_package_index[depends] += "${PACKAGEINDEXDEPS}"

python do_package_index() {
    from oe.rootfs import generate_index_files
    generate_index_files(d)
}

IMAGE_PREPROCESS_COMMAND += "image_preprocess; "

addtask do_package_index after do_rootfs before do_image_complete
