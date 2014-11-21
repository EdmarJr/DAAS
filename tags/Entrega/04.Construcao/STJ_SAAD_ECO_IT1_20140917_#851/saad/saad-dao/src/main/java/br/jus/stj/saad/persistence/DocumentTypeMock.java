package br.jus.stj.saad.persistence;

import java.util.LinkedList;
import java.util.List;

import br.jus.stj.saad.entity.local.DocumentType;

public class DocumentTypeMock {

	private static LinkedList<DocumentType> mockedList = new LinkedList<DocumentType>();

	static {

		int iDocType = 0;
		while (iDocType < 30) {

			DocumentType documentType = new DocumentType();

			documentType.setId(new Long(iDocType));

			if (iDocType % 2 >= 1) {
				documentType.setName("aviso " + iDocType);
				documentType.setIdentification("AVI_" + iDocType);
			} else if (iDocType % 3 >= 1) {
				documentType.setName("carta " + iDocType);
				documentType.setIdentification("CAR_" + iDocType);
			} else {
				documentType.setName("oficio " + iDocType);
				documentType.setIdentification("OFI_" + iDocType);
			}

			documentType.setPhysicalCabinet("Pasta " + iDocType);
			documentType.setPhysicalNetworkDirectory("//fake/"
					+ documentType.getName());

			mockedList.add(documentType);

			iDocType++;
		}
	}

	public DocumentTypeMock() {

	}

	public List<DocumentType> getAll() {

		return mockedList;
	}

	public void delete(DocumentType documentType) {

		mockedList.remove(documentType);
	}

	public void insert(DocumentType documentType) {

		Long lastId = mockedList.getLast().getId();
		documentType.setId(lastId + 1);

		mockedList.add(documentType);
	}

	public void update(DocumentType documentType) {

		int iDocType = 0;
		while (iDocType < mockedList.size()) {

			DocumentType docType = mockedList.get(iDocType);

			if (docType.getId().equals(documentType.getId())) {

				mockedList.set(iDocType, documentType);
				break;
			}

			iDocType++;
		}

	}

}
