package br.jus.stj.saad.view.controller.documento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.util.ConstantesSaad;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;

@ViewScoped
@ManagedBean
public class ConsultarDocumentoController extends DocumentoController {

	private static final long serialVersionUID = 1634619955361122287L;
	
	@Inject
	private LoginBean loginBean;
	
	private FiltroConsultarDocumentoVO filtroConsultarDocumentoVO;
	
	private List<Documento> listDocumentoResultado;
	private Documento documentoSelecionado;
	
	private String classificacaoDocumentoRecebido = ConstantesSaad.classificacaoDocumentoRecebido;
	private String classificacaoDocumentoExpedido = ConstantesSaad.classificacaoDocumentoExpedido;
	
	private TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum;
	private SituacaoDocumentoEnum[] listSituacaoDocumentoEnum;
	private ClassificacaoEnum[] listClassificacaoEnum;
	private List<TipoDocumento> listTipoDocumento;
	
	private String identificadorDocumento;
	private List<Usuario> listaUsuarios;
	private List<Local> listaLocais;
	private Date dataAtual;
	private Boolean consultado;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		super.init();
		getDocumento().setSituacao(null);
		getDocumento().setTipoOrigem(null);
		setFiltroConsultarDocumentoVO((FiltroConsultarDocumentoVO) obterObjetoRequestMap("filtroConsultarDocumentoVO"));
		
		if(getFiltroConsultarDocumentoVO() == null){
			setFiltroConsultarDocumentoVO(new FiltroConsultarDocumentoVO());
			getFiltroConsultarDocumentoVO().setDocumento(getDocumento());
		}
		
		listDocumentoResultado = (List<Documento>) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("listDocumentoResultado");

		if (filtroConsultarDocumentoVO == null) {
			setFiltroConsultarDocumentoVO(new FiltroConsultarDocumentoVO());
			getFiltroConsultarDocumentoVO().setDocumento(getDocumento());
		}

		if (listDocumentoResultado != null) {
			setConsultado(true);
		} else {
			setConsultado(false);
		}

		listaLocais = getLocalBean().obterLocaisAtivosSTj();
		setDataAtual(new Date());
		setListTipoOrigemDocumentoEnum(TipoOrigemDocumentoEnum.values());
		setListTipoDocumento(getTipoDocumentoBean().listarTodosTiposDocumentos());
		setListSituacaoDocumentoEnum(SituacaoDocumentoEnum.values());
		setListClassificacaoEnum(ClassificacaoEnum.values());
	}
	
	public void consultar() {
		setConsultado(true);
		setListDocumentoResultado(new ArrayList<Documento>());
		getDocumento().setEnderecamentoRemetente(getEnderecamentoRemetenteUtilizado());
		getDocumento().setEnderecamentoDestinatario(getEnderecamentoDestinatarioUtilizado());
		
		if (StringUtils.validaStringComRegex(getIdentificadorDocumento(),
				ConstantesSaad.regexIdentificadorDocumento)) {
			Documento documento = getDocumentoBean().buscarDocumento(null,
					getIdentificadorDocumento(),
					getLoginBean().getUnidadeUsuario());
			if (documento != null) {
				getListDocumentoResultado().add(
						getDocumentoBean().inicializar(documento,
								"andamentosDocumento"));
			}
		} else {
			setListDocumentoResultado(getDocumentoBean().inicializarLista(
					getDocumentoBean()
							.buscarDocumentoPorFiltroConsultarDocumentoVO(
									getFiltroConsultarDocumentoVO()),
					"andamentosDocumento"));
		}
	}
	
	public void adicionarParametrosNaRequisicao(Documento documentoSelecionado, boolean alterar) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("documentoSelecionado", documentoSelecionado);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("filtroConsultarDocumentoVO", getFiltroConsultarDocumentoVO());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("listDocumentoResultado", listDocumentoResultado);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("paginaPai", "/pages/documento/consultarDocumento.xhtml");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("alterar", alterar);
	}
	
	public String comandoAlterar() {
		if (documentoSelecionado != null) {
			adicionarParametrosNaRequisicao(documentoSelecionado, true);
			return "/pages/documento/alterarDocumento.xhtml";
		} else {
			adicionarFacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Selecione um registro para alteração.");
		}

		return null;
	}

	public String comandoVisualizar(Documento documentoSelecionado) {
		if (documentoSelecionado != null) {
			adicionarParametrosNaRequisicao(documentoSelecionado, false);
			return "/pages/documento/visualizarDocumento.xhtml";
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

			// RequestContext context = RequestContext.getCurrentInstance();
			// context.execute("PF('confirmDialogDelVar').show();");

			abrirModal("PF('confirmDialogDelVar')");

		}

	}

	public void comandoExcluir() {

		if (documentoSelecionado != null) {

			try {

				getDocumentoBean().excluir(documentoSelecionado);
				listDocumentoResultado.remove(documentoSelecionado);
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
	
	public Boolean getTipoDocumentoConvite() {
		if (getFiltroConsultarDocumentoVO().getDocumento().getTipo() != null) {
			return getFiltroConsultarDocumentoVO().getDocumento().getTipo()
					.getSigla().equalsIgnoreCase(ConstantesSaad.siglaConvite);
		}

		return false;
	}

	public FiltroConsultarDocumentoVO getFiltroConsultarDocumentoVO() {
		return filtroConsultarDocumentoVO;
	}

	public void setFiltroConsultarDocumentoVO(
			FiltroConsultarDocumentoVO filtroConsultarDocumentoVO) {
		this.filtroConsultarDocumentoVO = filtroConsultarDocumentoVO;
	}

	public String getIdentificadorDocumento() {
		return identificadorDocumento;
	}

	public void setIdentificadorDocumento(String identificadorDocumento) {
		this.identificadorDocumento = identificadorDocumento;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Boolean getConsultado() {
		return consultado;
	}

	public void setConsultado(Boolean consultado) {
		this.consultado = consultado;
	}

	public TipoOrigemDocumentoEnum[] getListTipoOrigemDocumentoEnum() {
		return listTipoOrigemDocumentoEnum;
	}

	public void setListTipoOrigemDocumentoEnum(
			TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum) {
		this.listTipoOrigemDocumentoEnum = listTipoOrigemDocumentoEnum;
	}

	public SituacaoDocumentoEnum[] getListSituacaoDocumentoEnum() {
		return listSituacaoDocumentoEnum;
	}

	public void setListSituacaoDocumentoEnum(
			SituacaoDocumentoEnum[] listSituacaoDocumentoEnum) {
		this.listSituacaoDocumentoEnum = listSituacaoDocumentoEnum;
	}

	public ClassificacaoEnum[] getListClassificacaoEnum() {
		return listClassificacaoEnum;
	}

	public void setListClassificacaoEnum(ClassificacaoEnum[] listClassificacaoEnum) {
		this.listClassificacaoEnum = listClassificacaoEnum;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public List<Documento> getListDocumentoResultado() {
		return listDocumentoResultado;
	}

	public void setListDocumentoResultado(List<Documento> listDocumentoResultado) {
		this.listDocumentoResultado = listDocumentoResultado;
	}

	public Documento getDocumentoSelecionado() {
		return documentoSelecionado;
	}

	public void setDocumentoSelecionado(Documento documentoSelecionado) {
		this.documentoSelecionado = documentoSelecionado;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public String getClassificacaoDocumentoRecebido() {
		return classificacaoDocumentoRecebido;
	}

	public void setClassificacaoDocumentoRecebido(
			String classificacaoDocumentoRecebido) {
		this.classificacaoDocumentoRecebido = classificacaoDocumentoRecebido;
	}

	public String getClassificacaoDocumentoExpedido() {
		return classificacaoDocumentoExpedido;
	}

	public void setClassificacaoDocumentoExpedido(
			String classificacaoDocumentoExpedido) {
		this.classificacaoDocumentoExpedido = classificacaoDocumentoExpedido;
	}

	public Boolean requisitosEnderecamentoPreenchidos(String classificacaoDocumento){
		Boolean b = !StringUtils.charIsEmpty(getDocumento().getTipoClassificacaoDocumento()) && !StringUtils.charIsEmpty(getDocumento().getTipoOrigem());
		
		if(ConstantesSaad.classificacaoDocumentoExpedido.equalsIgnoreCase(classificacaoDocumento)){
			if(b && getDocumento().isDocumentoExpedido()){
				return true;
			}
		}else{
			if(b && !getDocumento().isDocumentoExpedido()){
				return true;
			}
		}
		
		return false;
	}
	
}
