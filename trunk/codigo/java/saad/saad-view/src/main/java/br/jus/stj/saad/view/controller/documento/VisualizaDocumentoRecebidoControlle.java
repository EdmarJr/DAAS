package br.jus.stj.saad.view.controller.documento;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;

@ManagedBean
@ViewScoped
public class VisualizaDocumentoRecebidoControlle extends GenericController {

	private static final long serialVersionUID = 1L;

	private Documento documentoSelecionado;

	private FiltroConsultarDocumentoVO filtroConsultarDocumentoVO;

	private List<Documento> listDocumentoResultado;
	
	private String paginaPai;
	
	@PostConstruct
	private void init() {
		documentoSelecionado = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documentoSelecionado");
		filtroConsultarDocumentoVO = (FiltroConsultarDocumentoVO) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("filtroConsultarDocumentoVO");
		listDocumentoResultado = (List<Documento>) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("listDocumentoResultado");
		paginaPai = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("paginaPai");
	}

	public String voltar(){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("documentoSelecionado", documentoSelecionado);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			.put("filtroConsultarDocumentoVO", filtroConsultarDocumentoVO);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			.put("listDocumentoResultado", listDocumentoResultado);
		
		return paginaPai;
	}
	
	public Documento getDocumentoSelecionado() {
		return documentoSelecionado;
	}

	public void setDocumentoSelecionado(Documento documentoSelecionado) {
		this.documentoSelecionado = documentoSelecionado;
	}

	public FiltroConsultarDocumentoVO getFiltroConsultarDocumentoVO() {
		return filtroConsultarDocumentoVO;
	}

	public void setFiltroConsultarDocumentoVO(
			FiltroConsultarDocumentoVO filtroConsultarDocumentoVO) {
		this.filtroConsultarDocumentoVO = filtroConsultarDocumentoVO;
	}

	public List<Documento> getListDocumentoResultado() {
		return listDocumentoResultado;
	}

	public void setListDocumentoResultado(List<Documento> listDocumentoResultado) {
		this.listDocumentoResultado = listDocumentoResultado;
	}	

}
