package br.jus.stj.saad.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.vfs2.FileObject;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.jus.stj.saad.business.DocumentTypeBusiness;
import br.jus.stj.saad.business.DocumentTypeFieldBusiness;
import br.jus.stj.saad.entity.local.DocumentTypeField;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.local.Unit;

@ManagedBean(name = "documentTypeController")
@ViewScoped
public class DocumentTypeController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3525371211837108239L;

	private boolean modalUpdate;

	private List<TipoDocumento> documentTypeList;

	private List<Object[]> documentTypeObjectList;

	private List<DocumentTypeField> documentTypeFieldSearchList;
	private List<DocumentTypeField> documentTypeFieldAddList;

	private List<Unit> relatedUnitList;

	private TipoDocumento documentType;

	private TreeNode actualizeTree;

	private FacesMessage message;

	@EJB
	private DocumentTypeBusiness documentTypeBusiness;

	@EJB
	private DocumentTypeFieldBusiness documentTypeFieldBusiness;

	public DocumentTypeController() {

	}

	@PostConstruct
	private void init() throws Exception {

		this.modalUpdate = false;
		this.documentTypeList = new ArrayList<TipoDocumento>();
		this.documentType = new TipoDocumento();

		this.documentTypeFieldSearchList = this.documentTypeFieldBusiness
				.getAllSearch();
		this.documentTypeFieldAddList = this.documentTypeFieldBusiness
				.getAllAdd();

		// this.documentType.setField(new
		// DocumentTypeField[this.documentTypeFieldAddList.size()]);

	}

	public List<DocumentTypeField> getDocumentTypeFieldSearchList() {
		return documentTypeFieldSearchList;
	}

	public void setDocumentTypeFieldSearchList(
			List<DocumentTypeField> documentTypeFieldSearchList) {
		this.documentTypeFieldSearchList = documentTypeFieldSearchList;
	}

	public List<DocumentTypeField> getDocumentTypeFieldAddList() {
		return documentTypeFieldAddList;
	}

	public void setDocumentTypeFieldAddList(
			List<DocumentTypeField> documentTypeFieldAddList) {
		this.documentTypeFieldAddList = documentTypeFieldAddList;
	}

	public List<TipoDocumento> getDocumentTypeList() {
		return documentTypeList;
	}

	public void setDocumentTypeList(List<TipoDocumento> documentTypeList) {
		this.documentTypeList = documentTypeList;
	}

	public List<Object[]> getDocumentTypeObjectList() {
		return documentTypeObjectList;
	}

	public void setDocumentTypeObjectList(List<Object[]> documentTypeObjectList) {
		this.documentTypeObjectList = documentTypeObjectList;
	}

	public List<Unit> getRelatedUnitList() {
		return relatedUnitList;
	}

	public void setRelatedUnitList(List<Unit> relatedUnitList) {
		this.relatedUnitList = relatedUnitList;
	}

	public TipoDocumento getDocumentType() {
		return documentType;
	}

	public void setDocumentType(TipoDocumento documentType) {
		this.documentType = documentType;
	}

	public boolean isModalUpdate() {
		return modalUpdate;
	}

	public void setModalUpdate(boolean modalUpdate) {
		this.modalUpdate = modalUpdate;
	}

	public TreeNode getActualizeTree() {

		return this.actualizeTree;
	}

	public void setActualizeTree(TreeNode actualizeTree) {
		this.actualizeTree = actualizeTree;
	}

	private void getDirectory(TreeNode directoryNode, FileObject directoryFile)
			throws Exception {

		FileObject[] childrenDir = directoryFile.getChildren();

		if (childrenDir != null && childrenDir.length > 0) {

			int iChildren = 0;

			while (iChildren < childrenDir.length) {

				FileObject child = childrenDir[iChildren];

				if (child.isFolder() && !child.isHidden()) {

					TreeNode treeNode = new DefaultTreeNode(child,
							directoryNode);
					treeNode.setExpanded(true);

					this.getDirectory(treeNode, child);

				}

				iChildren++;
			}
		}

	}

	public void loadModal(AjaxBehaviorEvent event) {

		try {

			this.documentType = (TipoDocumento) ((CommandButton) event
					.getSource()).getAttributes().get("documentType");

			if (this.documentType != null) {

				this.modalUpdate = true;

			} else {

				this.modalUpdate = false;
				this.documentType = new TipoDocumento();
			}

			// FileObject actualizeDirectory = this.documentTypeBusiness
			// .getNetworkDirectoryStructure();

			FileObject actualizeDirectory = null;

			this.actualizeTree = new DefaultTreeNode("actualizeRoot", null);

			this.getDirectory(actualizeTree, actualizeDirectory);
			this.actualizeTree.setExpanded(true);

		} catch (Exception e) {

			this.message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao acessar estrutura de diretório de Tipo de Documento na rede!",
					e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, message);

		}
	}

	public void add() {

		System.out.println("add...");

		try {

			if (this.documentType != null) {

				/*
				 * 
				 * FileObject documentTypeRoot = (FileObject) this.actualizeTree
				 * .getChildren().get(0).getData();
				 * 
				 * String root = documentTypeRoot.getName().getBaseName()
				 * .concat("/"); this.documentTypeBusiness.add(documentType,
				 * root);
				 * 
				 * 
				 * this.actualizeTree = new DefaultTreeNode("actualizeRoot",
				 * null);
				 * 
				 * FileObject actualizeDirectory = this.documentTypeBusiness
				 * .getNetworkDirectoryStructure();
				 * 
				 * this.getDirectory(actualizeTree, actualizeDirectory);
				 * this.actualizeTree.setExpanded(true);
				 */

				this.documentTypeBusiness.add(documentType, null);
				this.documentTypeList = this.documentTypeBusiness.getAll();

				this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Tipo(s) de documento(s) incluído(s) com sucesso!", "");

			}

		} catch (Exception e) {
			this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Detalhe do erro:", e.getLocalizedMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, this.message);

	}

	public void delete() throws Exception {

		System.out.println("delete....");

		List<TipoDocumento> removeList = new ArrayList<TipoDocumento>();

		int iDocType = 0;
		while (iDocType < this.documentTypeList.size()) {

			TipoDocumento documentType = this.documentTypeList.get(iDocType);

			if (documentType.isSelected()) {
				removeList.add(documentType);
			}

			iDocType++;
		}

		this.documentTypeBusiness.removeAll(removeList);

		this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Tipo(s) de documento(s) excluído(s) com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage(null, this.message);

		this.documentTypeList = this.documentTypeBusiness.getAll();

	}

	public void search() throws Exception {

		this.documentTypeObjectList = this.documentTypeBusiness
				.getAllByFilter(this.documentType);

	}

	public void update() throws Exception {

		System.out.println("update................. " + documentType);

		this.documentTypeBusiness.update(this.documentType);

		this.documentTypeList = this.documentTypeBusiness.getAll();

		this.message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Tipo de documento atualizado com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage(null, this.message);
	}

	public void confirmUnitRelationship() throws Exception {

		System.out.println("confirmRelationshipUnit....");

		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_INFO,
				"Tipo(s) de documento(s) relacionado(s) à esta unidade com sucesso!",
				"");

		FacesContext.getCurrentInstance().addMessage(null, message);

		this.relatedUnitList = new ArrayList<Unit>();

	}

}
