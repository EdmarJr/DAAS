package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Document;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.persistence.DocumentMock;
import br.jus.stj.saad.persistence.DocumentoUnidadeDAO;

/**
 * Session Bean implementation class Document
 */
@Stateless
public class DocumentBean implements DocumentBusiness {
	
	@Inject
	private DocumentoUnidadeDAO documentoUnidadeDAO;

	@Override
	public List<Documento> getAll() {
		return this.documentoUnidadeDAO.list();
	}

	@Override
	public void removeAll(List<Document> documentList) {

		DocumentMock mockTest = new DocumentMock();

		int iDoc = 0;

		while (iDoc < documentList.size()) {

			mockTest.delete(documentList.get(iDoc));
			iDoc++;
		}

	}

	@Override
	public void add(Document document) throws Exception {

		DocumentMock mock = new DocumentMock();
		mock.insert(document);
	}

	@Override
	public void update(Document document) throws Exception {

		DocumentMock mock = new DocumentMock();
		mock.update(document);

	}
	
	@Override
	public Documento get(Documento document) throws Exception {
		
		return this.documentoUnidadeDAO.find(document);
	}

}
