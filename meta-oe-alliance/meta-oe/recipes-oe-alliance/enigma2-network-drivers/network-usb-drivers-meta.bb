SUMMARY = "meta file for USB Network drivers"
inherit packagegroup

require conf/license/license-gplv2.inc

PR = "r0"

DEPENDS = "\
	  ${@bb.utils.contains("DISTRO_NAME", "opennfr", "enigma2-plugin-drivers-network-usb-rt2800", "enigma2-plugin-drivers-network-usb-rt3070", d)} \        
    "
