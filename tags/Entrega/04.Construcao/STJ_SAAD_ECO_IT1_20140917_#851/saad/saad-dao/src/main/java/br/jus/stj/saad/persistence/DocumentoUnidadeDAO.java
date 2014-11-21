package br.jus.stj.saad.persistence;

import java.util.List;

import javax.persistence.Query;

import br.jus.stj.saad.entity.local.Documento;

public class DocumentoUnidadeDAO extends GenericDAOMssqlImpl<Documento> {

	@SuppressWarnings("unchecked")
	public List<Documento> list() {
		Query query = manager.createNamedQuery("findAllDocumentByFilter");
		return query.getResultList();
	}

	public Documento find(Documento document) {
		Query query = manager.createNamedQuery("findDocument");
		query.setParameter("document", document);
		return (Documento) query.getSingleResult();
	}

}
