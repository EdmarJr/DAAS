package br.jus.stj.saad.view.controller.documento;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.jus.stj.saad.business.CargoBean;
import br.jus.stj.saad.business.DestinatarioBean;
import br.jus.stj.saad.business.DestinatarioDocumentoBean;
import br.jus.stj.saad.business.DestinatarioOcupacaoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.enumerators.RelativoProcessoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.service.DocumentoService;
import br.jus.stj.saad.util.ConstantesSaad;
import br.jus.stj.saad.view.controller.DocumentoControllerBehavior;
import br.jus.stj.saad.view.controller.EnderecamentoExternoUtil;
import br.jus.stj.saad.view.controller.EnderecamentoInternoUtil;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.view.controller.arquivos.ArquivoController;
import br.jus.stj.saad.view.controller.tarefa.TarefaController;
import br.jus.stj.saad.vo.FiltroConsultarDocumentoVO;

@ViewScoped
@ManagedBean
public class DocumentoController extends GenericController {

	private static final long serialVersionUID = 1634619955361122287L;

	@EJB
	private UsuarioBean usuarioBean;
	@EJB
	private DocumentoBean documentoBean;
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	@EJB
	private LocalBean localBean;
	@EJB
	private OrgaoBean orgaoBean;
	@EJB
	private CargoBean cargoBean;
	@EJB
	private DestinatarioBean destinatarioBean;
	@EJB
	private DocumentoService documentoService;
	@EJB
	private DestinatarioDocumentoBean destinatarioDocumentoBean;
	@EJB
	private DestinatarioOcupacaoBean destinatarioOcupacaoBean;

	@ManagedProperty("#{documentoControllerBehavior}")
	private DocumentoControllerBehavior behavior;

	@ManagedProperty("#{tarefaController}")
	private TarefaController tarefaController;
	
	@ManagedProperty("#{arquivoController}")
	private ArquivoController arquivoController;
	
	private FiltroConsultarDocumentoVO filtroConsultarDocumentoVO;
	
	private List<Documento> listDocumentoResultado;
	
	private String paginaPai;

	private Documento documento;

	private Boolean alterar = true;

	private EnderecamentoInternoUtil enderecamentoInternoRemetenteUtil;

	private EnderecamentoInternoUtil enderecamentoInternoDestinatarioUtil;

	private EnderecamentoExternoUtil enderecamentoExternoRemetenteUtil;

	private EnderecamentoExternoUtil enderecamentoExternoDestinatarioUtil;

	private String enderecamentoRemetente = ConstantesSaad.enderecamentoRemetente;

	private String enderecamentoDestinatario = ConstantesSaad.enderecamentoDestinatario;

	private TipoEnderecamentoEnum[] listTipoEnderecamentoEnum;

	private List<TipoDocumento> listTipoDocumento;

	private TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum;

	private List<RelativoProcessoEnum> relacionamentoProcessosSelecionados;

	private Date dataAtual = new Date();

	@SuppressWarnings("unchecked")
	public void init() {
		Documento documentoSelecionado = (Documento) obterObjetoRequestMap("documentoSelecionado");
		filtroConsultarDocumentoVO = (FiltroConsultarDocumentoVO) obterObjetoRequestMap("filtroConsultarDocumentoVO");
		listDocumentoResultado = (List<Documento>) obterObjetoRequestMap("listDocumentoResultado");
		paginaPai = (String) obterObjetoRequestMap("paginaPai");
		
		if(documentoSelecionado != null){
			documentoSelecionado = getDocumentoBean().inicializar(documentoSelecionado, "anexos", "tarefas");
			setDocumento(documentoSelecionado);
			enderecamentoInternoRemetenteUtil = new EnderecamentoInternoUtil(
					usuarioBean, localBean, documentoSelecionado.getEnderecamentoRemetente());
			
			enderecamentoInternoDestinatarioUtil = new EnderecamentoInternoUtil(
					usuarioBean, localBean, documentoSelecionado.getEnderecamentoDestinatario());
			
			enderecamentoExternoRemetenteUtil = new EnderecamentoExternoUtil(
					orgaoBean, cargoBean, destinatarioBean,
					destinatarioDocumentoBean, destinatarioOcupacaoBean,
					documentoSelecionado.getEnderecamentoRemetente());
			
			enderecamentoExternoDestinatarioUtil = new EnderecamentoExternoUtil(
					orgaoBean, cargoBean, destinatarioBean,
					destinatarioDocumentoBean, destinatarioOcupacaoBean,
					documentoSelecionado.getEnderecamentoDestinatario());
		}else{
			setDocumento(new Documento());
			enderecamentoInternoRemetenteUtil = new EnderecamentoInternoUtil(
					usuarioBean, localBean, new EnderecamentoInterno());
			
			enderecamentoInternoDestinatarioUtil = new EnderecamentoInternoUtil(
					usuarioBean, localBean, new EnderecamentoInterno());
			
			enderecamentoExternoRemetenteUtil = new EnderecamentoExternoUtil(
					orgaoBean, cargoBean, destinatarioBean,
					destinatarioDocumentoBean, destinatarioOcupacaoBean,
					new EnderecamentoExterno());
			
			enderecamentoExternoDestinatarioUtil = new EnderecamentoExternoUtil(
					orgaoBean, cargoBean, destinatarioBean,
					destinatarioDocumentoBean, destinatarioOcupacaoBean,
					new EnderecamentoExterno());

			documentoBean.inicializarNovoDocumento(getDocumento());

		}
		
		if(getDocumento().getId() == null){
			getDocumento().setDataInclusao(new Date());
		}
		
		getTarefaController().setDocumento(documento);
		getBehavior().setDocumento(documento);
		getArquivoController().setDocumento(documento);
		
		listTipoDocumento = tipoDocumentoBean.listarTodosTiposDocumentos();
		listTipoEnderecamentoEnum = TipoEnderecamentoEnum.values();
		listTipoOrigemDocumentoEnum = TipoOrigemDocumentoEnum.values();
	}

	public void consultaProcessoJudOuAdmin() {
		if (documento.getNumeroRegistro() == null
				|| documento.getNumeroRegistro().equals("")) {
			adicionarFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Para realizar a consulta, o número do registro deve ser informado.",
					null);
			return;
		}
		String numero = documento.getNumeroRegistro().replace("/", "")
				.replace("-", "");
		RequestContext
				.getCurrentInstance()
				.execute(
						"window.open('http://www.stj.jus.br/webstj/processo/justica/jurisprudencia.asp?valor="
								+ numero + "')");

	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
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

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public TipoEnderecamentoEnum[] getListTipoEnderecamentoEnum() {
		return listTipoEnderecamentoEnum;
	}

	public void setListTipoEnderecamentoEnum(
			TipoEnderecamentoEnum[] listTipoEnderecamentoEnum) {
		this.listTipoEnderecamentoEnum = listTipoEnderecamentoEnum;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public TipoOrigemDocumentoEnum[] getListTipoOrigemDocumentoEnum() {
		return listTipoOrigemDocumentoEnum;
	}

	public void setListTipoOrigemDocumentoEnum(
			TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum) {
		this.listTipoOrigemDocumentoEnum = listTipoOrigemDocumentoEnum;
	}

	public Boolean getAlterar() {
		return alterar;
	}

	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}

	public String getEnderecamentoRemetente() {
		return enderecamentoRemetente;
	}

	public void setEnderecamentoRemetente(String enderecamentoRemetente) {
		this.enderecamentoRemetente = enderecamentoRemetente;
	}

	public String getEnderecamentoDestinatario() {
		return enderecamentoDestinatario;
	}

	public void setEnderecamentoDestinatario(String enderecamentoDestinatario) {
		this.enderecamentoDestinatario = enderecamentoDestinatario;
	}

	public EnderecamentoInternoUtil getEnderecamentoInternoRemetenteUtil() {
		return enderecamentoInternoRemetenteUtil;
	}

	public void setEnderecamentoInternoRemetenteUtil(
			EnderecamentoInternoUtil enderecamentoInternoRemetenteUtil) {
		this.enderecamentoInternoRemetenteUtil = enderecamentoInternoRemetenteUtil;
	}

	public EnderecamentoInternoUtil getEnderecamentoInternoDestinatarioUtil() {
		return enderecamentoInternoDestinatarioUtil;
	}

	public void setEnderecamentoInternoDestinatarioUtil(
			EnderecamentoInternoUtil enderecamentoInternoDestinatarioUtil) {
		this.enderecamentoInternoDestinatarioUtil = enderecamentoInternoDestinatarioUtil;
	}

	public EnderecamentoExternoUtil getEnderecamentoExternoRemetenteUtil() {
		return enderecamentoExternoRemetenteUtil;
	}

	public void setEnderecamentoExternoRemetenteUtil(
			EnderecamentoExternoUtil enderecamentoExternoRemetenteUtil) {
		this.enderecamentoExternoRemetenteUtil = enderecamentoExternoRemetenteUtil;
	}

	public EnderecamentoExternoUtil getEnderecamentoExternoDestinatarioUtil() {
		return enderecamentoExternoDestinatarioUtil;
	}

	public void setEnderecamentoExternoDestinatarioUtil(
			EnderecamentoExternoUtil enderecamentoExternoDestinatarioUtil) {
		this.enderecamentoExternoDestinatarioUtil = enderecamentoExternoDestinatarioUtil;
	}

	public Enderecamento getEnderecamentoRemetenteUtilizado() {
		if (getEnderecamentoExternoRemetenteUtil().getEnderecamento().getUtilizado()) {
			return getEnderecamentoExternoRemetenteUtil().getEnderecamento();
		} else if (getEnderecamentoInternoRemetenteUtil().getEnderecamento().getUtilizado()) {
			return getEnderecamentoInternoRemetenteUtil().getEnderecamento();
		} else {
			return null;
		}
	}

	public Enderecamento getEnderecamentoDestinatarioUtilizado() {
		if (getEnderecamentoInternoDestinatarioUtil().getEnderecamento().getUtilizado()) {
			return getEnderecamentoInternoDestinatarioUtil().getEnderecamento();
		} else if (getEnderecamentoExternoDestinatarioUtil().getEnderecamento().getUtilizado()) {
			return getEnderecamentoExternoDestinatarioUtil().getEnderecamento();
		} else {
			return null;
		}
	}

	public DocumentoControllerBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(DocumentoControllerBehavior behavior) {
		this.behavior = behavior;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<RelativoProcessoEnum> getRelacionamentoProcessosSelecionados() {
		return relacionamentoProcessosSelecionados;
	}

	public void setRelacionamentoProcessosSelecionados(
			List<RelativoProcessoEnum> relacionamentoProcessosSelecionados) {
		this.relacionamentoProcessosSelecionados = relacionamentoProcessosSelecionados;
	}

	public TarefaController getTarefaController() {
		return tarefaController;
	}

	public void setTarefaController(TarefaController tarefaController) {
		this.tarefaController = tarefaController;
	}

	public ArquivoController getArquivoController() {
		return arquivoController;
	}

	public void setArquivoController(ArquivoController arquivoController) {
		this.arquivoController = arquivoController;
	}

	public LocalBean getLocalBean() {
		return localBean;
	}

	public void setLocalBean(LocalBean localBean) {
		this.localBean = localBean;
	}

	public DocumentoService getDocumentoService() {
		return documentoService;
	}

	public void setDocumentoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	public SituacaoDocumentoEnum[] obterSituacoesPossiveis() {
		return SituacaoDocumentoEnum.values();
	}
	
	public String alterarDocumento(){
		incluirObjetoRequestMap("documentoSelecionado", getDocumento());
		incluirObjetoRequestMap("paginaPai", "/pages/notification/minhasNotificacoes.xhtml");
		
		return "/pages/documento/alterarDocumento.xhtml";
	}
		
	public void alterarTipoEnderecamentoExterno(String enderecamento){
		if(getEnderecamentoDestinatario().equalsIgnoreCase(enderecamentoRemetente)){
			if(TipoOrigemDocumentoEnum.EXTERNO.getValor().equals(getDocumento().getTipoOrigem())){
				setEnderecamentoExternoDestinatarioUtil(new EnderecamentoExternoUtil(orgaoBean, cargoBean, destinatarioBean, destinatarioDocumentoBean, destinatarioOcupacaoBean, new EnderecamentoExterno()));
			}else{
				setEnderecamentoInternoDestinatarioUtil(new EnderecamentoInternoUtil(usuarioBean, localBean, new EnderecamentoInterno()));
			}			
		}else if(getEnderecamentoRemetente().equalsIgnoreCase(enderecamento)){
			if(TipoOrigemDocumentoEnum.EXTERNO.getValor().equals(getDocumento().getTipoOrigem())){
				setEnderecamentoExternoRemetenteUtil(new EnderecamentoExternoUtil(orgaoBean, cargoBean, destinatarioBean, destinatarioDocumentoBean, destinatarioOcupacaoBean, new EnderecamentoExterno()));
			}			else{
				setEnderecamentoInternoRemetenteUtil(new EnderecamentoInternoUtil(usuarioBean, localBean, new EnderecamentoInterno()));
			}
		}
	}
	
	public String voltarParaConsultarDocumento(){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("filtroConsultarDocumentoVO", filtroConsultarDocumentoVO);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("listDocumentoResultado", listDocumentoResultado);
	
		return paginaPai;
	}

}
