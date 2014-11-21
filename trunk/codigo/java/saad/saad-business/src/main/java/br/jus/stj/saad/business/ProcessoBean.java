package br.jus.stj.saad.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Processo;
import br.jus.stj.saad.persistence.ProcessoDAO;

@Stateless
@LocalBean
public class ProcessoBean extends Bean<Processo> {
	@Inject
	private ProcessoDAO dao;

	@Override
	public ProcessoDAO getDao() {
		return dao;
	}
	
	public Processo buscarPorNumeroRegistro(String numeroRegistro){
		return getDao().buscarPorNumeroRegistro(numeroRegistro);
	}

}
