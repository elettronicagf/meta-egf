FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append =" \
		file://ssh_host_dsa_key \
		file://ssh_host_ed25519_key \
		file://ssh_host_ed25519_key.pub \
		file://ssh_host_ecdsa_key \
		file://ssh_host_rsa_key.pub \
		file://ssh_host_dsa_key.pub \
		file://ssh_host_rsa_key \
		file://ssh_host_ecdsa_key.pub \
		file://sshd_config_readonly \
		"

do_install_append () {
	install -m 0600    ${WORKDIR}/ssh_host_dsa_key		${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_ed25519_key	${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_ed25519_key.pub	${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_ecdsa_key	${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_rsa_key.pub	${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_dsa_key.pub	${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_rsa_key		${D}${sysconfdir}/ssh
	install -m 0600    ${WORKDIR}/ssh_host_ecdsa_key.pub	${D}${sysconfdir}/ssh
	install -m 0644    ${WORKDIR}/sshd_config_readonly ${D}${sysconfdir}/ssh/sshd_config_readonly
}
