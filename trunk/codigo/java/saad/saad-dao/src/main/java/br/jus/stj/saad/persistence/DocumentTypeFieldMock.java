package br.jus.stj.saad.persistence;

import java.util.LinkedList;
import java.util.List;

import br.jus.stj.saad.entity.local.DocumentTypeField;

public class DocumentTypeFieldMock {

	private static LinkedList<DocumentTypeField> mockedList = new LinkedList<DocumentTypeField>();

	static {

		String[] namesDocType = new String[] { "Identificador", "Nome",
				"Pasta Física", "Pasta do servidor da rede" };

		int iDocTypeField = 0;
		while (iDocTypeField < namesDocType.length) {

			DocumentTypeField field = new DocumentTypeField();
			field.setId(new Long(iDocTypeField));
			field.setName(namesDocType[iDocTypeField]);

			if (iDocTypeField % 2 == 0) {
				field.setAddVisibility(false);
				field.setSearchVisibility(true);
			} else {
				field.setAddVisibility(true);
				field.setSearchVisibility(false);
			}

			mockedList.add(field);

			iDocTypeField++;
		}
	}

	public DocumentTypeFieldMock() {

	}

	public List<DocumentTypeField> getAll() {

		return mockedList;
	}

	public void update(DocumentTypeField documentTypeField) {

		int iDocTypeField = 0;
		while (iDocTypeField < mockedList.size()) {

			DocumentTypeField docType = mockedList.get(iDocTypeField);

			if (docType.getId().equals(documentTypeField.getId())) {

				mockedList.set(iDocTypeField, documentTypeField);
				break;
			}

			iDocTypeField++;
		}

	}

	public void delete(DocumentTypeField documentTypeField) {

		mockedList.remove(documentTypeField);
	}

	public void insert(DocumentTypeField documentTypeField) {

		Long lastId = mockedList.getLast().getId();
		documentTypeField.setId(lastId + 1);

		mockedList.add(documentTypeField);
	}

	public LinkedList<DocumentTypeField> getAllSearch() {

		LinkedList<DocumentTypeField> searchList = new LinkedList<DocumentTypeField>();

		int iDocTypeField = 0;
		while (iDocTypeField < mockedList.size()) {

			DocumentTypeField docType = mockedList.get(iDocTypeField);

			if (docType.isSearchVisibility()) {

				searchList.add(docType);
			}

			iDocTypeField++;
		}

		return searchList;

	}

	public LinkedList<DocumentTypeField> getAllAdd() {

		LinkedList<DocumentTypeField> addList = new LinkedList<DocumentTypeField>();

		int iDocTypeField = 0;
		while (iDocTypeField < mockedList.size()) {

			DocumentTypeField docType = mockedList.get(iDocTypeField);

			if (docType.isAddVisibility()) {

				addList.add(docType);
			}

			iDocTypeField++;
		}

		return addList;

	}

}
