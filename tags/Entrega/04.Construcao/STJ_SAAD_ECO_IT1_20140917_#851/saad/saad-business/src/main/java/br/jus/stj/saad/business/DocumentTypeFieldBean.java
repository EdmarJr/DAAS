package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;

import br.jus.stj.saad.entity.local.DocumentTypeField;
import br.jus.stj.saad.persistence.DocumentTypeFieldMock;

/**
 * Session Bean implementation class DocumentType
 */
@Stateless
public class DocumentTypeFieldBean implements DocumentTypeFieldBusiness {

	@Override
	public List<DocumentTypeField> getAll() throws Exception {
		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();
		return mock.getAll();
	}

	@Override
	public void save(List<DocumentTypeField> documentTypeFieldList)
			throws Exception {

		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();

		int iDocType = 0;

		while (iDocType < documentTypeFieldList.size()) {

			mock.update(documentTypeFieldList.get(iDocType));
			iDocType++;
		}
	}

	@Override
	public void removeAll(List<DocumentTypeField> documentTypeFieldList)
			throws Exception {

		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();

		int iDocType = 0;

		while (iDocType < documentTypeFieldList.size()) {

			mock.delete(documentTypeFieldList.get(iDocType));
			iDocType++;
		}

	}

	@Override
	public void add(DocumentTypeField documentTypeField) throws Exception {

		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();
		mock.insert(documentTypeField);
	}

	@Override
	public List<DocumentTypeField> getAllSearch() throws Exception {
		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();
		return mock.getAllSearch();
	}

	@Override
	public List<DocumentTypeField> getAllAdd() throws Exception {
		DocumentTypeFieldMock mock = new DocumentTypeFieldMock();
		return mock.getAllAdd();
	}

}
