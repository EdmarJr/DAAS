package br.jus.stj.saad.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;

public class AndamentoDocumentoDAOImpl extends
		GenericDAOMssqlImpl<AndamentoDocumento> implements
		AndamentoDocumentoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Documento> obterDocumentosComAndamentos(String sigla,
			String identificacaoDocumentoSTJ, String ano, Local local) {

		String select = " select doc from Documento doc"
				+ " join fetch doc.tipo tipoDoc"
				+ " left outer join fetch doc.andamentosDocumento andamentos"
				+ " where"
				+ " 	doc.identificacaoDocumentoSTJ = :identificacaoDocumentoSTJ"
				+ " and SUBSTRING(doc.dataInclusao, 1, 4) = :ano"
				+ " and tipoDoc.sigla = :sigla"
				+ " and doc.local = :local";

		Query query = manager.createQuery(select, Documento.class);
		query.setParameter("identificacaoDocumentoSTJ",
				identificacaoDocumentoSTJ);
		query.setParameter("ano", ano);
		query.setParameter("sigla", sigla);
		query.setParameter("local", local);

		return removerItensDuplicados(query.getResultList());
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Documento> obterDocumentosComAndamentos(
			TipoDocumento tipoDocumento, Local local) {
		
		String select = " select doc from Documento doc"
				+ " join fetch doc.tipo tipoDoc"
				+ " left outer join fetch doc.andamentosDocumento andamentos"
				+ " where"
				+ " tipoDoc = :tipoDocumento"
				+ " and doc.local = :local";
		
		Query query = manager.createQuery(select, Documento.class);
		query.setParameter("tipoDocumento", tipoDocumento);
		query.setParameter("local", local);
		
		return removerItensDuplicados(query.getResultList());
		
	}
	
	private List<Documento> removerItensDuplicados(List<Documento> listaDocumentos) {
		
		List<Documento> listaItensDupRemovidos = null;
		
		if(listaDocumentos != null && !listaDocumentos.isEmpty()) {
			
			listaItensDupRemovidos = new ArrayList<Documento>();
			
			for (Documento documento : listaDocumentos) {
				
				if(!listaItensDupRemovidos.contains(documento))
					listaItensDupRemovidos.add(documento);
					
			}
			
			
		}
		
		return listaItensDupRemovidos;
		
	}

}
