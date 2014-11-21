package br.jus.stj.saad.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.business.DocumentTypeFieldBusiness;
import br.jus.stj.saad.entity.local.DocumentTypeField;

@ManagedBean(name = "localSystemPreferenceController")
@RequestScoped
public class LocalSystemPreferenceController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547929276040627758L;

	private List<DocumentTypeField> documentTypeFieldList;

	private String documentTypeFieldName;

	FacesMessage message = null;

	@EJB
	private DocumentTypeFieldBusiness documentTypeFieldBusiness;

	public LocalSystemPreferenceController() {

	}

	@PostConstruct
	private void init() throws Exception {

		this.documentTypeFieldList = this.documentTypeFieldBusiness.getAll();
		this.message = null;

	}

	public List<DocumentTypeField> getDocumentTypeFieldList() {
		return documentTypeFieldList;
	}

	public void setDocumentTypeFieldList(
			List<DocumentTypeField> documentTypeFieldList) {
		this.documentTypeFieldList = documentTypeFieldList;
	}

	public String getDocumentTypeFieldName() {
		return documentTypeFieldName;
	}

	public void setDocumentTypeFieldName(String documentTypeFieldName) {
		this.documentTypeFieldName = documentTypeFieldName;
	}

	public void saveDocumentTypeFieldVisibility() throws Exception {

		System.out.println("saveDocumentTypeFieldVisibility().........");

		this.documentTypeFieldBusiness.save(this.documentTypeFieldList);

		this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de 'Tipo de Documentos' efetuada com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage(null, this.message);
	}

	public void addDocumentTypeField() throws Exception {

		System.out.println("addDocumentTypeField()........................... "
				+ this.documentTypeFieldName);

		if (this.documentTypeFieldName != null
				&& !this.documentTypeFieldName.equalsIgnoreCase("")) {

			DocumentTypeField documentTypeField = new DocumentTypeField();
			documentTypeField.setName(this.documentTypeFieldName);

			this.documentTypeFieldBusiness.add(documentTypeField);

			this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Campo adicionado em 'Tipo de Documentos' com sucesso!", "");

			FacesContext.getCurrentInstance().addMessage(null, this.message);

		}
	}

	public void deleteDocumentTypeField(DocumentTypeField documentTypeField)
			throws Exception {

		System.out.println("deleteDocumentTypeField................. "
				+ documentTypeField);

		List<DocumentTypeField> removeDocumentTypeFieldList = new ArrayList<DocumentTypeField>();
		removeDocumentTypeFieldList.add(documentTypeField);

		this.documentTypeFieldBusiness.removeAll(removeDocumentTypeFieldList);

		this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Exclusão do campo '" + documentTypeField.getName()
						+ "' efetuada com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage(null, this.message);
	}

}
