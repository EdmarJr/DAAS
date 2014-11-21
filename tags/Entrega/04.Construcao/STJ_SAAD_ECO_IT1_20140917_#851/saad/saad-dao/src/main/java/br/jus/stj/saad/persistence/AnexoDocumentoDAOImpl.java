package br.jus.stj.saad.persistence;

import java.util.List;

import javax.persistence.Query;

import br.jus.stj.saad.entity.local.Anexo;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;


public class AnexoDocumentoDAOImpl extends GenericDAOMssqlImpl<Anexo> implements
	AnexoDocumentoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Anexo> obterDocumentosAnexo(TipoDocumento tipoDocumento) {
		
		String jpql = "select anexo from Anexo anexo"
				+ " join fetch anexo.documento doc"
				+ " join fetch doc.tipo tipoDoc"
				+ " where tipoDoc = :tipoDocumento";
		
		Query query = manager.createQuery(jpql, Anexo.class);
		query.setParameter("tipoDocumento", tipoDocumento);
		
		return query.getResultList();
		
	}

}
