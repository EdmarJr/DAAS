package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.persistence.LocalDAO;

@Stateless
public class LocalBean extends Bean<Local> {

	@Inject
	private LocalDAO dao;
	
	public Local obterPrimeiro() {
		return dao.obterPrimeiro();
	}
	
	public List<Local> obterLocaisAtivosSTj(String... camposEager) {
		return getDao().obterLocaisAtivosDisponiveisNoSTJ(camposEager);
	}

	@Override
	public LocalDAO getDao() {
		return dao;
	}


}
