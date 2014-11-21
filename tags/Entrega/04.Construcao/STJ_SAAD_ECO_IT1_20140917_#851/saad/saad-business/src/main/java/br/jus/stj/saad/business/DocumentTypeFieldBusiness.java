package br.jus.stj.saad.business;

import java.util.List;

import br.jus.stj.saad.entity.local.DocumentTypeField;

public interface DocumentTypeFieldBusiness {

	public List<DocumentTypeField> getAll() throws Exception;

	public void save(List<DocumentTypeField> documentTypeFieldList) throws Exception;

	public void removeAll(List<DocumentTypeField> documentTypeFieldList)
			throws Exception;

	public void add(DocumentTypeField documentTypeField) throws Exception;
	
	public List<DocumentTypeField> getAllSearch() throws Exception;
	
	public List<DocumentTypeField> getAllAdd() throws Exception;

}
