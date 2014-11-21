package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.persistence.DestinatarioDAO;

@Stateless
@LocalBean
public class DestinatarioBean extends Bean<Destinatario> {
	@Inject
	private DestinatarioDAO dao;

	@Override
	public DestinatarioDAO getDao() {
		return dao;
	}

	public List<Destinatario> destinatariosPorOrgao(Orgao orgao) {
		return getDao().destinatariosPorOrgao(orgao);
	}

	public List<Destinatario> destinatariosPorCargoOrgao(Cargo cargo,
			Orgao orgao) {
		return getDao().destinatariosPorCargoOrgao(cargo, orgao);
	}

}
