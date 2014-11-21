package br.jus.stj.saad.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.jus.stj.saad.entity.local.Document;
import br.jus.stj.saad.entity.local.DocumentType;

public class DocumentMock {

	private static LinkedList<Document> mockedList = new LinkedList<Document>();

	static {

		int iDocType = 0;
		while (iDocType < 30) {

			Date entryDate = new Date();
			entryDate.setTime(entryDate.getTime() - (iDocType * 999999999));

			Document document = new Document();
			document.setId(new Long(iDocType));

			StringBuffer documentNumber = new StringBuffer(iDocType);

			while (documentNumber.length() < 5) {
				documentNumber.insert(0, "0");
			}

			document.setNumber(documentNumber.toString() + "-"
					+ new SimpleDateFormat("yyyy").format(new Date()));

			document.setResponsible("Responsável " + iDocType);

			document.setEntryDate(entryDate);

			DocumentType documentType = new DocumentType();

			if (iDocType % 2 >= 1) {
				document.setRegistryNumber(new Long(iDocType + 20));
				documentType.setName("carta " + iDocType);
				documentType.setIdentification("CAR_" + iDocType);

			} else if (iDocType % 3 >= 1) {
				document.setRegistryNumber(new Long(iDocType + 20));
				documentType.setName("ofício " + iDocType);
				documentType.setIdentification("OFI_" + iDocType);
			} else {
				document.setRegistryNumber(new Long(iDocType + 20));
				documentType.setName("aviso " + iDocType);
				documentType.setIdentification("AVI_" + iDocType);
			}

			document.setDocumentType(documentType);

			document.setPhysicalCabinet("Armário" + iDocType);

			mockedList.add(document);

			iDocType++;
		}
	}

	public DocumentMock() {

	}

	public List<Document> getAll() {

		return mockedList;
	}

	public void delete(Document document) {

		mockedList.remove(document);
	}

	public void insert(Document document) {

		Long lastId = mockedList.getLast().getId();
		document.setId(lastId + 1);

		mockedList.add(document);
	}

	public void update(Document document) {

		int iDocType = 0;
		while (iDocType < mockedList.size()) {

			Document doc = mockedList.get(iDocType);

			if (doc.getId().equals(document.getId())) {

				mockedList.set(iDocType, document);
				break;
			}

			iDocType++;
		}

	}
	
	public Document find(Document document) {

		Document foundDocument = null;

		if (document != null && document.getId() != null) {

			int iDoc = 0;
			while (iDoc < mockedList.size()) {

				foundDocument = mockedList.get(iDoc);

				if (foundDocument.getId().equals(document.getId())) {
					break;
				} else {
					foundDocument = null;
				}

				iDoc++;
			}
		}
		
		return foundDocument;

	}

}
