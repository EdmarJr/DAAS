package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.persistence.OrgaoDAO;

@Stateless
@LocalBean
public class OrgaoBean extends Bean<Orgao> {
	@Inject
	private OrgaoDAO dao;

	@Override
	public OrgaoDAO getDao() {
		return dao;
	}

	public List<Orgao> listarPorNome(String query){
		return getDao().listarPorNome(query);
	}

}
