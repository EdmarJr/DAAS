package br.jus.stj.saad.view.controller.documento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.TipoDocumentoBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class IncludeConsultarDocumentoController extends GenericController {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private DocumentoBean documentoBean;
	
	@EJB
	private TipoDocumentoBean tipoDocumentoBean;
	
	@Inject
	private LoginBean loginBean;
	
	private Documento documento;
	private Documento documentoRetorno;
	private List<Documento> listDocumentoResultado;
	
	private TipoOrigemDocumentoEnum[] listTipoOrigemDocumentoEnum;
	private SituacaoDocumentoEnum[] listSituacaoDocumentoEnum;
	private ClassificacaoEnum[] listClassificacaoEnum;
	private List<TipoDocumento> listTipoDocumento;
	private String identificadorDocumento;
	private Boolean consultado;
	
	@PostConstruct
	public void init(){
		setDocumento(new Documento());
		setListTipoOrigemDocumentoEnum(TipoOrigemDocumentoEnum.values());
		setListTipoDocumento(getTipoDocumentoBean().listarTodosTiposDocumentos());
		setListSituacaoDocumentoEnum(SituacaoDocumentoEnum.values());
		setListClassificacaoEnum(ClassificacaoEnum.values());
		setConsultado(false);
	}
	
	public void consultar(){
		setConsultado(true);
		setListDocumentoResultado(new ArrayList<Documento>());
		if(StringUtils.validaStringComRegex(getIdentificadorDocumento(), "[a-zA-Z]{3}-\\d{6}/\\d{4}")){
			Documento documento = getDocumentoBean().buscarDocumento(null, getIdentificadorDocumento(), getLoginBean().getUnidadeUsuario());
			if(documento != null){
				getListDocumentoResultado().add(getDocumentoBean().inicializar(documento, "andamentosDocumento"));
			}		
		}else{
			setListDocumentoResultado(getDocumentoBean().inicializarLista(getDocumentoBean().buscarDocumentoPorDocumento(getDocumento()), "andamentosDocumento"));
		}
	}
	
	public void adicionar(){
		
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Documento getDocumentoRetorno() {
		return documentoRetorno;
	}

	public void setDocumentoRetorno(Documento documentoRetorno) {
		this.documentoRetorno = documentoRetorno;
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

	public void setListClassificacaoEnum(ClassificacaoEnum[] listClassificacaoEnum) {
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
}
