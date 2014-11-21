package br.jus.stj.saad.view.controller.documento;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.jus.stj.commons.util.ConstantesGerais;
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
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.RelativoProcessoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.util.DocumentEntryType;
import br.jus.stj.saad.util.Status;
import br.jus.stj.saad.view.controller.DocumentoControllerBehavior;
import br.jus.stj.saad.view.controller.GenericController;

public abstract class DocumentoController extends GenericController {

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
	protected DocumentoBean documentoBean;

	@ManagedProperty("#{documentoControllerBehavior}")
	private DocumentoControllerBehavior behavior;

	private TarefaDemandaDocumento tarefaNova;

	StringBuffer messageError;
	StringBuffer messageSuccess;

	private FacesMessage message;

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

		inicializar();

		getDocument().setInterfaceDestinatario(new DestinatarioDocumento());

		setUsuariosDisponiveis(usuarioBean
				.obterUsuariosDisponiveisPorUnidadeDoUsuarioLogado(ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE));

		setTiposDocumentosDisponiveis(tipoDocumentoBean
				.listar(TipoDocumento.class));
	}

	protected void inicializar() {
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
		EnderecamentoExterno enderecamentoExterno = (EnderecamentoExterno) getDocument()
				.getEnderecamento();
		enderecamentoExterno.setDestinatario(destinatario);
		enderecamentoExterno.setNomeEnderecado(destinatario.getNome());
	}

	public DocumentoControllerBehavior getBehavior() {
		return behavior;
	}

	public void consultaProcessoJudOuAdmin() {
		if (document.getNumeroRegistro() == null
				|| document.getNumeroRegistro().equals("")) {
			adicionarFacesMessage(FacesMessage.SEVERITY_ERROR,
					"Para realizar a consulta, o número do registro deve ser informado.", null);
			return;
		}
		String numero = document.getNumeroRegistro().replace("/", "")
				.replace("-", "");
		RequestContext
				.getCurrentInstance()
				.execute(
						"window.open('http://www.stj.jus.br/webstj/processo/justica/jurisprudencia.asp?valor="
								+ numero + "')");

	}

	public void setBehavior(DocumentoControllerBehavior behavior) {
		this.behavior = behavior;
	}

	protected void limpar() {

		setDocument(new Documento());
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();

	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

}
