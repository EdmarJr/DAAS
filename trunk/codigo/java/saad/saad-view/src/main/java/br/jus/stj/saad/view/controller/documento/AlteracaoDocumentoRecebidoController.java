package br.jus.stj.saad.view.controller.documento;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;

@ManagedBean
@ViewScoped
public class AlteracaoDocumentoRecebidoController extends DocumentoControllerOld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FiltroConsultarDocumentoVO filtroConsultarDocumentoVO;
	private List<Documento> listDocumentoResultado;
	private String paginaPai;

	@Override
	protected void inicializar() {
		filtroConsultarDocumentoVO = (FiltroConsultarDocumentoVO) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("filtroConsultarDocumentoVO");
		listDocumentoResultado = (List<Documento>) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("listDocumentoResultado");
		paginaPai = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("paginaPai");
	
		super.inicializar();
		setAlterar((Boolean) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("alterar"));
		Documento documento = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documentoSelecionado");
		documento = documentoBean.inicializar(documento, "tarefas", "anexos","local", "destinatario");

		if (documento.getEvento() != null) {
			documento.setRelacionadoAEvento(true);
		}
		setDocument(documento);
	}

	public void comandoSalvar() {
		try {
			documentoBean.alterar(getDocument());
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Registro alterado com sucesso.");

		} catch (Exception e) {
			adicionarFacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Ocorreu erro na alteração do registro.");
			e.printStackTrace();
		}
	}

	public String cancelar() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			.put("filtroConsultarDocumentoVO", filtroConsultarDocumentoVO);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
			.put("listDocumentoResultado", listDocumentoResultado);
		
		return paginaPai;
	}
}
