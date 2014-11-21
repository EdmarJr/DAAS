package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.persistence.DestinatarioDocumentoDAO;

/**
 * Session Bean implementation class Audit
 */
@Stateless
public class DestinatarioDocumentoBean extends Bean<DestinatarioDocumento> {

	
	@Inject
	private DestinatarioDocumentoDAO dao;
	
	@Override
	public DestinatarioDocumentoDAO getDao() {
		return dao;
	}
	
	public List<DestinatarioDocumento> listarPorNome(String query){
		return getDao().listarPorNome(query);
	}

}
