package br.jus.stj.saad.view.controller.documento.consultaandamento;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.view.controller.lazy.DocumentoLazyList;
import br.jus.stj.saad.vo.FiltroDocumentoConsultaAndamentoVO;

@ManagedBean
@ViewScoped
public class ConsultaAndamentoDocumentoController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private DocumentoBean bean;
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;

	@Inject
	private LoginBean loginBean;

	private String numero;


	private FiltroDocumentoConsultaAndamentoVO filtro;
	private LazyDataModel<Documento> listaDocumentosConsulta;
	private List<Documento> documentosConsulta;
	
	
	private List<TipoDocumento> tiposDocumentosDisponiveisConsulta;

	private TipoDocumento tipo;

	private FacesMessage message;

	private Documento documentoSelecionado;

	@PostConstruct
	private void initialize() {
		setFiltro(new FiltroDocumentoConsultaAndamentoVO());
		setTiposDocumentosDisponiveisConsulta(tipoDocumentoBean
				.listar(TipoDocumento.class));

		recuperarUltimoEstadoManagedBean(this);

	}

	public FiltroDocumentoConsultaAndamentoVO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroDocumentoConsultaAndamentoVO filtro) {
		this.filtro = filtro;
	}

	public void comandoConsultar() {

		String numeroDocumento = getNumero();
		String regex = "[a-zA-Z]{3}-\\d{6}/\\d{4}";

		filtro.setLocal(loginBean.getUnidadeUsuario());

		if (numeroDocumento == null
				|| StringUtils.validaStringComRegex(numeroDocumento, regex)) {

			filtro.setIdentificador(numeroDocumento);
		}

		setListaDocumentosConsulta(new DocumentoLazyList(filtro, bean));

		if (listaDocumentosConsulta.getRowIndex() < 0) {

			documentosConsulta = listaDocumentosConsulta.load(0, 14, null,
					null, null);

		} else {

			documentosConsulta = listaDocumentosConsulta.load(
					listaDocumentosConsulta.getRowIndex(),
					listaDocumentosConsulta.getPageSize(), null, null, null);

		}

		if (documentosConsulta == null || documentosConsulta.isEmpty())
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Nenhum registro encontrado.");

	}

	public String comandoAlterar() {
		
		if (documentoSelecionado != null) {
			
			salvarEstadoAtualManagedBean(this);
			
			incluirObjetoRequestMap("documento", documentoSelecionado);
			
			if (documentoSelecionado.getTipoClassificacaoDocumento().equals(
					ClassificacaoEnum.EXPEDIDO.getValor())) {
				
				return "/pages/document/alterarDocumentoExpedido.xhtml";
				
			} else if (documentoSelecionado.getTipoClassificacaoDocumento()
					.equals(ClassificacaoEnum.RECEBIDO.getValor())) {
				
				return "/pages/document/alterarDocumentoRecebido.xhtml";
				
			}
			
		} else {
			
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Selecione um registro para alteração.");
			
		}
		
		return null;
		
	}
	
	public void validarRegistroExclusao() {

		if (documentoSelecionado == null) {

			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Selecione um registro para exclusão.");

		} else {
			
//			RequestContext context = RequestContext.getCurrentInstance();
//			context.execute("PF('confirmDialogDelVar').show();");

			abrirModal("PF('confirmDialogDelVar')");
			
		}

	}

	public void comandoExcluir() {	

		if (documentoSelecionado != null) {

			try {

				bean.excluir(documentoSelecionado);
				documentosConsulta.remove(documentoSelecionado);
				adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
						"Registro excluído com sucesso");

			} catch (Exception e) {
				
				// TODO: handle exception
				adicionarFacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
						"Erro na exclusão do registro.");
				e.printStackTrace();

			}

		} else {
			
			adicionarFacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso",
					"Selecione um registro para exclusão.");
			
		}

	}
	
	
	public void onRowSelectNavigate(SelectEvent event) {
	    if (event != null || event.getObject() != null) {
	    	
	    	setDocumentoSelecionado((Documento)event.getObject());
	    }
	}
	public Documento getDocumentoSelecionado() {
		return documentoSelecionado;
	}

	public void setDocumentoSelecionado(Documento documentoSelecionado) {
		this.documentoSelecionado = documentoSelecionado;
	}

	public LazyDataModel<Documento> getListaDocumentosConsulta() {
		return listaDocumentosConsulta;
	}

	public void setListaDocumentosConsulta(
			LazyDataModel<Documento> listaDocumentosConsulta) {
		this.listaDocumentosConsulta = listaDocumentosConsulta;
	}

	public ClassificacaoEnum[] obterListaClassificacoes() {
		return ClassificacaoEnum.values();
	}

	public TipoEnderecamentoEnum[] obterListaEnderecamento() {
		return TipoEnderecamentoEnum.values();
	}

	public SituacaoDocumentoEnum[] obterListaSituacaoDocumento() {
		return SituacaoDocumentoEnum.values();
	}

	public List<TipoDocumento> getTiposDocumentosDisponiveisConsulta() {
		return tiposDocumentosDisponiveisConsulta;
	}

	public void setTiposDocumentosDisponiveisConsulta(
			List<TipoDocumento> tiposDocumentosDisponiveisConsulta) {
		this.tiposDocumentosDisponiveisConsulta = tiposDocumentosDisponiveisConsulta;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String detalharDocumento(Documento documentoSelecionado) {

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("documentoSelecionado", documentoSelecionado);
		return "visualizarDocumentoRecebido";

	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<Documento> getDocumentosConsulta() {
		return documentosConsulta;
	}

	public void setDocumentosConsulta(List<Documento> documentosConsulta) {
		this.documentosConsulta = documentosConsulta;
	}
	

}
