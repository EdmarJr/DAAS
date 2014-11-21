package br.jus.stj.saad.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Evento;
import br.jus.stj.saad.persistence.EventoDAO;

@Stateless
@LocalBean
public class EventoBean extends Bean<Evento> {

	@Inject
	private EventoDAO dao;

	@Override
	public EventoDAO getDao() {
		return dao;
	}
	
	public Evento obterPorDocumento(Documento documento){
		return getDao().obterPorDocumento(documento);
	}

}
