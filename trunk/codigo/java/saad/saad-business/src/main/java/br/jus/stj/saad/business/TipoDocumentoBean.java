package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.persistence.TipoDocumentoDAO;

/**
 * Session Bean implementation class DocumentType
 */
@Stateless
@LocalBean
public class TipoDocumentoBean extends Bean<TipoDocumento> {
	
	@Inject
	private TipoDocumentoDAO dao;
	
	@Override
	public TipoDocumentoDAO getDao() {
		return dao;
	}
	
    public List<TipoDocumento> listarTodosTiposDocumentos() {
    	return getDao().list();
    }



}
