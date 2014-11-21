package br.jus.stj.saad.view.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.commons.view.enums.SimNaoEnum;
import br.jus.stj.saad.business.CargoBean;
import br.jus.stj.saad.business.DocumentBusiness;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.LocalUsuario;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.RelativoProcessoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.util.DocumentEntryType;
import br.jus.stj.saad.util.Status;

@ManagedBean(name = "documentController")
@ViewScoped
public class DocumentController extends GenericController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1634619955361122287L;
	
	@EJB
	private UsuarioBean usuarioBean;
	@EJB
	private LocalBean localBean;
	@EJB
	private OrgaoBean orgaoBean;
	@EJB
	private CargoBean cargoBean;
	@EJB
	private DocumentoBean documentoBean;

	
	@ManagedProperty("#{documentControllerBehavior}")
	private DocumentoControllerBehavior behavior;

	private TarefaDemandaDocumento tarefaNova;

	StringBuffer messageError;
	StringBuffer messageSuccess;

	private List<Usuario> usuariosDisponiveis;
	private List<TipoDocumento> tiposDocumentosDisponiveis;
	

	private Documento document;

	private UploadedFile uploadedFile;

	private List<Documento> documentList;
	private List<RelativoProcessoEnum> relacionamentoProcessosSelecionados;

	@EJB
	private TipoDocumentoBean tipoDocumentoBean;

	@EJB
	private DocumentBusiness documentBusiness;


	@PostConstruct
	private void init() {
		setDocument(new Documento());
		getDocument().setInterfaceDestinatario(new DestinatarioDocumento());
		setUsuariosDisponiveis(usuarioBean
				.obterUsuariosDisponiveisPorUnidadeDoUsuarioLogado(ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE));
		setTiposDocumentosDisponiveis(tipoDocumentoBean.listar(TipoDocumento.class));
		getDocument().setSituacaoDocumentoEvento(SimNaoEnum.SIM.getValor());
		getDocument().setTarefas(new ArrayList<TarefaDemandaDocumento>());
		getDocument().setTipoOrigem(TipoOrigemDocumentoEnum.INTERNO.getValor());
		getDocument().setEnderecamento(TipoOrigemDocumentoEnum.INTERNO.getEnderecamento());
		getDocument().setTipoClassificacaoDocumento(ClassificacaoEnum.RECEBIDO.getValor());
		getDocument().setSituacao(SituacaoDocumentoEnum.PENDENTE.getValor());
	}

	public TipoOrigemDocumentoEnum[] obterTiposOrigem() {
		return TipoOrigemDocumentoEnum.values();
	}


	public List<TipoDocumento> getTiposDocumentosDisponiveis() {
		return tiposDocumentosDisponiveis;
	}

	public void setTiposDocumentosDisponiveis(
			List<TipoDocumento> tiposDocumentosDisponiveis) {
		this.tiposDocumentosDisponiveis = tiposDocumentosDisponiveis;
	}

	public TarefaDemandaDocumento getTarefaNova() {
		return tarefaNova;
	}

	public void setTarefaNova(TarefaDemandaDocumento tarefaNova) {
		this.tarefaNova = tarefaNova;
	}


	public List<TipoDocumento> getDocumentTypeList() {
		return tiposDocumentosDisponiveis;
	}

	public void setDocumentTypeList(List<TipoDocumento> documentTypeList) {
		this.tiposDocumentosDisponiveis = documentTypeList;
	}

	public Documento getDocument() {
		return document;
	}

	public void setDocument(Documento document) {
		this.document = document;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Usuario> getUsuariosDisponiveis() {
		return usuariosDisponiveis;
	}

	public void setUsuariosDisponiveis(List<Usuario> usuariosDisponiveis) {
		this.usuariosDisponiveis = usuariosDisponiveis;
	}

	public List<Documento> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Documento> documentList) {
		this.documentList = documentList;
	}

	public DocumentEntryType[] getDocumentEntryType() {
		return DocumentEntryType.values();
	}

	public Status[] getStatus() {
		return Status.values();
	}

	
	
	
	public List<RelativoProcessoEnum> getRelacionamentoProcessosSelecionados() {
		return relacionamentoProcessosSelecionados;
	}

	public void setRelacionamentoProcessosSelecionados(
			List<RelativoProcessoEnum> relacionamentoProcessosSelecionados) {
		this.relacionamentoProcessosSelecionados = relacionamentoProcessosSelecionados;
	}
	

	public RelativoProcessoEnum[] obterTiposRelacionamentoProcessos() {
		return RelativoProcessoEnum.getEnumsPrincipais();
	}

	public SituacaoDocumentoEnum[] obterSituacoesPossiveis() {
		return SituacaoDocumentoEnum.values();
	}
	
	
	
	public void ajustarEnderecamentoExterno(Destinatario destinatario) {
		EnderecamentoExterno enderecamentoExterno = (EnderecamentoExterno) getDocument().getEnderecamento();
		enderecamentoExterno.setDestinatario(destinatario);
		enderecamentoExterno.setNomeEnderecado(destinatario.getNome());
	}
	
	public void comandoSalvar() {
		documentoBean.incluir(getDocument());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("incluirDocumentoRecebido.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private boolean salvarDestinatarioDocumento;
	
	private List<Usuario> listaUsuariosRemetenteInterno;
	
	private List<Local> listaLocaisDestinatarioInterno;
	
	private Usuario remetenteInternoSelecionado;
	
	private Local localRemetenteInternoSelecionado;
	
	private String remetenteInternoEmail;

	public List<Orgao> autoCompleteOrgao(String query) {
		return orgaoBean.listarPorNome(query);
	}
	
	public void atualizarLocaisRemetenteInterno(){
		if(remetenteInternoSelecionado != null){
			listaLocaisDestinatarioInterno = new ArrayList<Local>();
			remetenteInternoSelecionado = usuarioBean.inicializar(remetenteInternoSelecionado, "locaisUsuarios");
			List<LocalUsuario> listaLocalUsuario = remetenteInternoSelecionado.getLocaisUsuarios();
			
			for (LocalUsuario localUsuario : listaLocalUsuario) {
				listaLocaisDestinatarioInterno.add(localUsuario.getLocal());
			}
		}
	}

	public boolean getSalvarDestinatarioDocumento() {
		return salvarDestinatarioDocumento;
	}

	public void setSalvarDestinatarioDocumento(boolean salvarDestinatarioDocumento) {
		this.salvarDestinatarioDocumento = salvarDestinatarioDocumento;
	}
	
	public List<Usuario> getListaUsuariosRemetenteInterno() {
		return listaUsuariosRemetenteInterno;
	}

	public void setListaUsuariosRemetenteInterno(
			List<Usuario> listaUsuariosRemetenteInterno) {
		this.listaUsuariosRemetenteInterno = listaUsuariosRemetenteInterno;
	}

	public Usuario getRemetenteInternoSelecionado() {
		return remetenteInternoSelecionado;
	}

	public void setRemetenteInternoSelecionado(
			Usuario remetenteInternoSelecionado) {
		this.remetenteInternoSelecionado = remetenteInternoSelecionado;
	}

	public List<Local> getListaLocaisDestinatarioInterno() {
		return listaLocaisDestinatarioInterno;
	}

	public DocumentoControllerBehavior getBehavior() {
		return behavior;
	}

	public void setListaLocaisDestinatarioInterno(
			List<Local> listaLocaisDestinatarioInterno) {
		this.listaLocaisDestinatarioInterno = listaLocaisDestinatarioInterno;
	}

	public Local getLocalRemetenteInternoSelecionado() {
		return localRemetenteInternoSelecionado;
	}

	public void setLocalRemetenteInternoSelecionado(
			Local localRemetenteInternoSelecionado) {
		this.localRemetenteInternoSelecionado = localRemetenteInternoSelecionado;
	}

	public String getRemetenteInternoEmail() {
		return remetenteInternoEmail;
	}

	public void setRemetenteInternoEmail(String remetenteInternoEmail) {
		this.remetenteInternoEmail = remetenteInternoEmail;
	}


	public void consultaProcessoJudOuAdmin() {

		String numero = document.getNumeroRegistro().replace("/", "")
				.replace("-", "");
		RequestContext
				.getCurrentInstance()
				.execute(
						"window.open('http://www.stj.jus.br/webstj/processo/justica/jurisprudencia.asp?valor="
								+ numero + "')");

	}
	// ////////////////////CAMILO DAQUI PRA CIMA

	public void setBehavior(DocumentoControllerBehavior behavior) {
		this.behavior = behavior;
	}

	
	
}
