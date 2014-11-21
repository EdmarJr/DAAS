package br.jus.stj.saad.persistence;

import java.util.List;

import javax.persistence.Query;

import br.jus.stj.saad.entity.local.TipoDocumento;

public class TipoDocumentoDAOImpl extends GenericDAOMssqlImpl<TipoDocumento>
		implements TipoDocumentoDAO {

	@SuppressWarnings("unchecked")
	public List<TipoDocumento> list() {
		Query query = manager.createNamedQuery("findAllDocumentType");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listByFilter(TipoDocumento documentType) {
		Query query = manager.createNamedQuery("findAllDocumentTypeByFilter");
		query.setParameter("idLocal", 1L);
		return query.getResultList();
	}

	public TipoDocumento find(TipoDocumento documentType) {
		Query query = manager.createNamedQuery("findDocumentType");
		query.setParameter("documentType", documentType);
		return (TipoDocumento) query.getSingleResult();
	}
}
