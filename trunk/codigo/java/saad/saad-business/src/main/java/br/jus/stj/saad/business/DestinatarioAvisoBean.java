package br.jus.stj.saad.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.persistence.DestinatarioAvisoDAO;

/**
 * Session Bean implementation class Audit
 */
@Stateless
public class DestinatarioAvisoBean extends Bean<DestinatarioAviso> {

	@Inject
	private DestinatarioAvisoDAO destinatarioAvisoDAO;
	
	@Override
	public DestinatarioAvisoDAO getDao() {
		return destinatarioAvisoDAO;
	}

}
