package br.jus.stj.saad.view.controller.documento;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.view.controller.documento.consultaandamento.ConsultaAndamentoDocumentoController;


@ManagedBean
@ViewScoped
public class AlteracaoDocumentoRecebidoController extends DocumentoController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Object ultimoEstadoManagedBeanConsultaAndamento = null;
	
	@Override
	protected void inicializar() {
		
		super.inicializar();
		
		ultimoEstadoManagedBeanConsultaAndamento = obterObjetoRequestMap(ConsultaAndamentoDocumentoController.class);
		
		Documento documento = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documento");
		documento = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documento");
		documento = documentoBean.inicializar(documento,"tarefas","anexos","local", "destinatario");
		documento.setInterfaceDestinatario(documento.getDestinatarioDocumento());
		setDocument(documento);
		
	}
	
	public void comandoSalvar() {
		
		try {

			documentoBean.alterar(getDocument());
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Registro alterado com sucesso.");

		} catch (Exception e) {
		
			// TODO Auto-generated catch block
			adicionarFacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu erro na alteração do registro.");
			e.printStackTrace();
		
		}
	}
	
	public String cancelar() {

		salvarEstadoAtualManagedBean(ultimoEstadoManagedBeanConsultaAndamento);
		
		return "stepSearch";

	}
	
}
