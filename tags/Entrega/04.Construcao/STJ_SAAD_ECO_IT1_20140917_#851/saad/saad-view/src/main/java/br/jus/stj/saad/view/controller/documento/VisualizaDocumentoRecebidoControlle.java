package br.jus.stj.saad.view.controller.documento;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class VisualizaDocumentoRecebidoControlle  {

	/**
	 * 
	
	private static final long serialVersionUID = 1L;

	@EJB
	private DocumentoBean documentoBean;

	private Documento documento;

	@PostConstruct
	private void init() {

		documento = (Documento) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap()
				.get("documentoSelecionado");
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
 */
}
