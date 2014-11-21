package br.jus.stj.saad.view.controller.documento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.util.ConstantesSaad;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;

@ManagedBean
@ViewScoped
public class ConsultarDocumentoControllerOld extends GenericController {

	private static final long serialVersionUID = 1L;

	@EJB
	private DocumentoBean documentoBean;
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	@EJB
	private UsuarioBean usuarioBean;
	@EJB
	private LocalBean localBean;
	@EJB
	private OrgaoBean orgaoBean;

	@Inject
	private LoginBean loginBean;

	private FiltroConsultarDocumentoVO filtroConsultarDocumentoVO;

	private List<Documento> listDocumentoResultado;
	private Documento documentoSelecionado;

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
		filtroConsultarDocumentoVO = (FiltroConsultarDocumentoVO) FacesContext
				.getCurrentInstance().getExternalContext().getRequestMap()
				.get("filtroConsultarDocumentoVO");
		listDocumentoResultado = (List<Documento>) FacesContext
				.getCurrentInstance().getExternalContext().getRequestMap()
				.get("listDocumentoResultado");

		if (filtroConsultarDocumentoVO == null) {
			setFiltroConsultarDocumentoVO(new FiltroConsultarDocumentoVO());
			getFiltroConsultarDocumentoVO().setDocumento(new Documento());
		}

		if (listDocumentoResultado != null) {
			setConsultado(true);
		} else {
			setConsultado(false);
		}

		listaLocais = localBean.obterLocaisAtivosSTj();
		setDataAtual(new Date());
		setListTipoOrigemDocumentoEnum(TipoOrigemDocumentoEnum.values());
		setListTipoDocumento(getTipoDocumentoBean()
				.listarTodosTiposDocumentos());
		setListSituacaoDocumentoEnum(SituacaoDocumentoEnum.values());
		setListClassificacaoEnum(ClassificacaoEnum.values());
	}

	public void consultar() {
		setConsultado(true);
		setListDocumentoResultado(new ArrayList<Documento>());
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

	public void adicionar() {

	}

	public Boolean getOrigemExterna() {
		if (getFiltroConsultarDocumentoVO().getDocumento().getTipoOrigem() != null) {
			return getFiltroConsultarDocumentoVO().getDocumento()
					.getTipoOrigem()
					.equals(TipoOrigemDocumentoEnum.EXTERNO.getValor());
		}

		return false;
	}

	public void atualizarRemetenteInterno() {
		if (getFiltroConsultarDocumentoVO().getLocal() != null) {
			listaUsuarios = usuarioBean.obterUsuariosPorUnidades(
					ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE,
					getFiltroConsultarDocumentoVO().getLocal());
		}
	}

	public List<Orgao> autoCompleteOrgao(String query) {
		List<Orgao> list = orgaoBean.listarPorNome(query);
		return list;
	}

	public Boolean getOrigemInterna() {
		if (getFiltroConsultarDocumentoVO().getDocumento().getTipoOrigem() != null) {
			return getFiltroConsultarDocumentoVO().getDocumento()
					.getTipoOrigem()
					.equals(TipoOrigemDocumentoEnum.INTERNO.getValor());
		}

		return false;
	}

	public Boolean getTipoDocumentoConvite() {
		if (getFiltroConsultarDocumentoVO().getDocumento().getTipo() != null) {
			return getFiltroConsultarDocumentoVO().getDocumento().getTipo()
					.getSigla().equalsIgnoreCase(ConstantesSaad.siglaConvite);
		}

		return false;
	}

	public void adicionarParametrosNaRequisicao(Documento documentoSelecionado, boolean alterar) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("documentoSelecionado", documentoSelecionado);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("filtroConsultarDocumentoVO", getFiltroConsultarDocumentoVO());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("listDocumentoResultado", listDocumentoResultado);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("paginaPai", "/pages/document/consultarDocumento.xhtml");
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

				documentoBean.excluir(documentoSelecionado);
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

	public TipoOrigemDocumentoEnum[] getListTipoOrigemDocumentoEnum() {
		return listTipoOrigemDocumentoEnum;
	}

	public void setListTipoOrigemDocumentoEnum(
			TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum) {
		this.listTipoOrigemDocumentoEnum = listTipoOrigemDocumentoEnum;
	}

	public DocumentoBean getDocumentoBean() {
		return documentoBean;
	}

	public void setDocumentoBean(DocumentoBean documentoBean) {
		this.documentoBean = documentoBean;
	}

	public TipoDocumentoBean getTipoDocumentoBean() {
		return tipoDocumentoBean;
	}

	public void setTipoDocumentoBean(TipoDocumentoBean tipoDocumentoBean) {
		this.tipoDocumentoBean = tipoDocumentoBean;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
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

	public void setListClassificacaoEnum(
			ClassificacaoEnum[] listClassificacaoEnum) {
		this.listClassificacaoEnum = listClassificacaoEnum;
	}

	public List<Documento> getListDocumentoResultado() {
		return listDocumentoResultado;
	}

	public void setListDocumentoResultado(List<Documento> listDocumentoResultado) {
		this.listDocumentoResultado = listDocumentoResultado;
	}

	public String getIdentificadorDocumento() {
		return identificadorDocumento;
	}

	public void setIdentificadorDocumento(String identificadorDocumento) {
		this.identificadorDocumento = identificadorDocumento;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Boolean getConsultado() {
		return consultado;
	}

	public void setConsultado(Boolean consultado) {
		this.consultado = consultado;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public FiltroConsultarDocumentoVO getFiltroConsultarDocumentoVO() {
		return filtroConsultarDocumentoVO;
	}

	public void setFiltroConsultarDocumentoVO(
			FiltroConsultarDocumentoVO filtroConsultarDocumentoVO) {
		this.filtroConsultarDocumentoVO = filtroConsultarDocumentoVO;
	}

	public Documento getDocumentoSelecionado() {
		return documentoSelecionado;
	}

	public void setDocumentoSelecionado(Documento documentoSelecionado) {
		this.documentoSelecionado = documentoSelecionado;
	}
}
