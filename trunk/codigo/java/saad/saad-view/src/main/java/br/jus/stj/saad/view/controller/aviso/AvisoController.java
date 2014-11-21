package br.jus.stj.saad.view.controller.aviso;

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

import br.jus.stj.saad.business.AvisoBean;
import br.jus.stj.saad.business.DestinatarioAvisoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.RelacionadoDocumentoEnum;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.view.controller.GenericController;

@ViewScoped
@ManagedBean
public class AvisoController extends GenericController{

	private static final long serialVersionUID = 1L;

	@EJB
	private AvisoBean avisoServico;
	
	@EJB
	private UsuarioBean usuarioBean;
	
	@EJB
	private DestinatarioAvisoBean destinatarioAvisoBean;
	
	@EJB
	private DocumentoBean documentoBean;
	
	@EJB
	private LocalBean localBean;
	
	@Inject
	private LoginBean loginBean;
	
	private Aviso aviso;
	
	private TipoEnderecamentoAvisoEnum tipoEnderecamentoAviso;
	
	private SituacaoAvisoEnum situacaoAvisoEnum;
	
	private RelacionadoDocumentoEnum relacionadoDocumentoEnum;
	
	private List<TipoEnderecamentoAvisoEnum> listTipoEnderecamentoAviso;
	
	private SituacaoAvisoEnum[] listSituacaoAvisoEnum;
	
	private List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum;
	
	private List<TipoDocumento> listTipoDocumento;
	
	private TipoDocumento tipoDocumento;
	
	private Documento documento;
	
	private Boolean destinatarioEspecificioSelecionado;
	
	private Boolean documentoRelacionadoSelecionado;
	
	private List<Usuario> listDestinatario;
	
	private Usuario destinatario;
	
	private FacesMessage message;
	
	private String descricao;
	
	private Date dataDeInclusao;
	
	private Date dataDeSolucao;
	
	private String numeroDocumento;
	
	private String identificadorDocumento;
	
	@PostConstruct
	public void init() {
			setDocumento(null);
			setDataDeInclusao(new Date());
			setDataDeSolucao(null);
			setDescricao("");
			setNumeroDocumento(null);
			setIdentificadorDocumento(null);
			setSituacaoAvisoEnum(SituacaoAvisoEnum.PENDENTE);
			setDestinatarioEspecificioSelecionado(false);
			setDocumentoRelacionadoSelecionado(false);
			setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum.N);
			setListDestinatario(new ArrayList<Usuario>());
			setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum.TODOS);
			setListTipoEnderecamentoAviso(gerarListaTipoEnderecamentoAviso());
			setListSituacaoAvisoEnum(gerarListaSituacaoAvisoEnum());
			setListRelacionadoDocumentoEnum(gerarListaRelacionadoDocumentoEnum());
			setAviso(new Aviso());
			getAviso().setTipoEnderecamento(TipoEnderecamentoAvisoEnum.TODOS.getChave());
			setListTipoDocumento(getAvisoServico().listarTodosTiposDocumentos());
	}
		
	public void limpar(){
		init();
	}	
	
	public Boolean validaDocumento(){
		if(getDocumento() != null){
			return true;
		}	
		
		return false;
	}
	
	public void verificarEnderecamento(){
		if(getTipoEnderecamentoAviso().equals(TipoEnderecamentoAvisoEnum.TODOS)){
			getListDestinatario().clear();
		}
	}
			
	public List<TipoEnderecamentoAvisoEnum> gerarListaTipoEnderecamentoAviso(){
		List<TipoEnderecamentoAvisoEnum> listTipoEnderecamentoAviso = new ArrayList<TipoEnderecamentoAvisoEnum>();
		listTipoEnderecamentoAviso.add(TipoEnderecamentoAvisoEnum.TODOS);
		listTipoEnderecamentoAviso.add(TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO);
		return listTipoEnderecamentoAviso;
	}
	
	public SituacaoAvisoEnum[] gerarListaSituacaoAvisoEnum(){
		return SituacaoAvisoEnum.values();
	}
	
	public List<RelacionadoDocumentoEnum> gerarListaRelacionadoDocumentoEnum(){
		List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum = new ArrayList<RelacionadoDocumentoEnum>();
		listRelacionadoDocumentoEnum.add(RelacionadoDocumentoEnum.S);
		listRelacionadoDocumentoEnum.add(RelacionadoDocumentoEnum.N);
		return listRelacionadoDocumentoEnum;
	}
	
	public List<Usuario> completeUsuarios(String query) {
		List<Local> listLocal = new ArrayList<Local>();
		listLocal.add(loginBean.getUnidadeUsuario());
		
		return usuarioBean.listarUsuariosPorNomeUnidade(query, loginBean.getUnidadeUsuario());
	}
	
	public void adicionarDestinatario(){
		if(getDestinatario() != null && !getListDestinatario().contains(getDestinatario())){
			getListDestinatario().add(getDestinatario());
		}else{
			if(getDestinatario() == null){
				setMessage(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Não foi possível completar sua ação.", ""));
	
				FacesContext.getCurrentInstance().addMessage(null, getMessage());
			}else{
				setMessage(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Destinatário já adicionado.", "Não é possível adicionar o mesmo destinatário mais de uma vez."));
	
				FacesContext.getCurrentInstance().addMessage(null, getMessage());
			}
		}
		
		setDestinatario(new Usuario());
	}
	
	public void removerDestinatario(Usuario destinatario){
		if(getListDestinatario().contains(destinatario)){
			getListDestinatario().remove(destinatario);
		}
	}
		
	public String classificacaoDocumentoAmigavel(String a){
		if("E".equalsIgnoreCase(a)){
			return "Expedido";
		}else{
			return "Recebido";
		}
	}
	
	public void atualizarDataDeSolucao(){
		if(!getSituacaoAvisoEnum().equals(SituacaoAvisoEnum.RESOLVIDO)){
			setDataDeSolucao(null);
		}
	}
	
	public String origemDocumentoAmigavel(String a){
		if("E".equalsIgnoreCase(a)){
			return "Externo";
		}else{
			return "Interno";
		}
	};
	
	public String voltar(){
		
		String page = comandoVoltar();
		if(page != null)
			return page; 
		else
			return null;
	}

	public String detalharAviso(Aviso avisoSelecionado) {
		salvarEstadoAtualManagedBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("avisoSelecionado", avisoSelecionado);
		return "/pages/notification/detalharAviso.xhtml";

	}
	
	public DestinatarioAvisoBean getDestinatarioAvisoBean() {
		return destinatarioAvisoBean;
	}

	public void setDestinatarioAvisoBean(DestinatarioAvisoBean destinatarioAvisoBean) {
		this.destinatarioAvisoBean = destinatarioAvisoBean;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public TipoEnderecamentoAvisoEnum getTipoEnderecamentoAviso() {
		return tipoEnderecamentoAviso;
	}

	public void setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum tipoEnderecamentoAviso) {
		this.tipoEnderecamentoAviso = tipoEnderecamentoAviso;
		if(getAviso() != null){
			getAviso().setTipoEnderecamento(tipoEnderecamentoAviso.getChave());
		
			if(tipoEnderecamentoAviso.equals(TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO)){
				setDestinatarioEspecificioSelecionado(true);
			}else{
				setDestinatarioEspecificioSelecionado(false);
			}
		}
	}

	public SituacaoAvisoEnum getSituacaoAvisoEnum() {
		return situacaoAvisoEnum;
	}

	public void setSituacaoAvisoEnum(SituacaoAvisoEnum situacaoAvisoEnum) {
		this.situacaoAvisoEnum = situacaoAvisoEnum;
	}

	public RelacionadoDocumentoEnum getRelacionadoDocumentoEnum() {
		return relacionadoDocumentoEnum;
	}

	public void setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum relacionadoDocumentoEnum) {
		this.relacionadoDocumentoEnum = relacionadoDocumentoEnum;
		
		if(relacionadoDocumentoEnum.equals(RelacionadoDocumentoEnum.S)){
			setDocumentoRelacionadoSelecionado(true);
		}else{
			setDocumentoRelacionadoSelecionado(false);
		}
	}

	public List<TipoEnderecamentoAvisoEnum> getListTipoEnderecamentoAviso() {
		return listTipoEnderecamentoAviso;
	}

	public void setListTipoEnderecamentoAviso(
			List<TipoEnderecamentoAvisoEnum> listTipoEnderecamentoAviso) {
		this.listTipoEnderecamentoAviso = listTipoEnderecamentoAviso;
	}

	public SituacaoAvisoEnum[] getListSituacaoAvisoEnum() {
		return listSituacaoAvisoEnum;
	}

	public void setListSituacaoAvisoEnum(SituacaoAvisoEnum[] listSituacaoAvisoEnum) {
		this.listSituacaoAvisoEnum = listSituacaoAvisoEnum;
	}

	public List<RelacionadoDocumentoEnum> getListRelacionadoDocumentoEnum() {
		return listRelacionadoDocumentoEnum;
	}

	public void setListRelacionadoDocumentoEnum(
			List<RelacionadoDocumentoEnum> listRelacionadoDocumentoEnum) {
		this.listRelacionadoDocumentoEnum = listRelacionadoDocumentoEnum;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Boolean getDestinatarioEspecificioSelecionado() {
		return destinatarioEspecificioSelecionado;
	}

	public void setDestinatarioEspecificioSelecionado(
			Boolean destinatarioEspecificioSelecionado) {
		this.destinatarioEspecificioSelecionado = destinatarioEspecificioSelecionado;
	}

	public Boolean getDocumentoRelacionadoSelecionado() {
		return documentoRelacionadoSelecionado;
	}

	public void setDocumentoRelacionadoSelecionado(
			Boolean documentoRelacionadoSelecionado) {
		this.documentoRelacionadoSelecionado = documentoRelacionadoSelecionado;
	}

	public List<Usuario> getListDestinatario() {
		return listDestinatario;
	}

	public void setListDestinatario(List<Usuario> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataDeInclusao() {
		return dataDeInclusao;
	}

	public void setDataDeInclusao(Date dataDeInclusao) {
		this.dataDeInclusao = dataDeInclusao;
	}

	public Date getDataDeSolucao() {
		return dataDeSolucao;
	}

	public void setDataDeSolucao(Date dataDeSolucao) {
		this.dataDeSolucao = dataDeSolucao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getIdentificadorDocumento() {
		return identificadorDocumento;
	}

	public void setIdentificadorDocumento(String identificadorDocumento) {
		this.identificadorDocumento = identificadorDocumento;
	}

	public AvisoBean getAvisoServico() {
		return avisoServico;
	}

	public void setAvisoServico(AvisoBean avisoServico) {
		this.avisoServico = avisoServico;
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public LocalBean getLocalBean() {
		return localBean;
	}

	public void setLocalBean(LocalBean localBean) {
		this.localBean = localBean;
	}

}
