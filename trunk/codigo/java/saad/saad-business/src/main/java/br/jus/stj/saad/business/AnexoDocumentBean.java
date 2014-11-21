package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.persistence.AnexoDocumentoDAO;
import br.jus.stj.saad.persistence.GenericDAO;

/**
 * Session Bean implementation class Document
 */
@Stateless
public class AnexoDocumentBean extends Bean<Anexo> {
	
	@Inject
	private AnexoDocumentoDAO anexoDocumentoDAO;

	
	public List<Anexo> obterDocumentosAnexo(TipoDocumento tipoDocumento) {
		
		return anexoDocumentoDAO.obterDocumentosAnexo(tipoDocumento);
		
	}
	
	@Override
	public GenericDAO<Anexo> getDao() {
		return anexoDocumentoDAO;
	}
	
}
