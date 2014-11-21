package br.jus.stj.saad.view.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.saad.business.CargoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.interfaces.IDestinatario;
import br.jus.stj.saad.entity.local.DestinatarioDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;

@ManagedBean
@ViewScoped
public class RemetenteInternoController extends GenericController {

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

	private Documento documento;
	
	private IDestinatario destinatario;

	private boolean salvarDestinatarioDocumento;
	
	private List<Usuario> listaRemetente;
	
	private List<Local> listaLocais;

	private Usuario remetente;
	
	private Local local;
	
	@PostConstruct
	private void initialize() {
		setDestinatario(new DestinatarioDocumento());
		listaLocais = localBean.obterLocaisAtivosSTj();
		
		if(documento == null){
			documento = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documento");
			if(documento != null){
				if(documento.getEnderecamento() != null){
					local = documento.getEnderecamento().getLocal();
					remetente = documento.getEnderecamento().getUsuario();
					atualizarRemetenteInterno();
				}
			}
		}
	}
	
	public void atualizarRemetenteInterno() {
		if (local != null) {
			listaRemetente = usuarioBean.obterUsuariosPorUnidades(
					ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE, local);
		}
	}
	
	public Boolean isDocumentInternal() {
		if (getDocumento().getTipoOrigem()
				.equals(TipoOrigemDocumentoEnum.INTERNO.getValor())) {
			return true;
		}
		return false;
	}
	
	public IDestinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(IDestinatario destinatario) {
		this.destinatario = destinatario;
	}

	public boolean getSalvarDestinatarioDocumento() {
		return salvarDestinatarioDocumento;
	}

	public void setSalvarDestinatarioDocumento(boolean salvarDestinatarioDocumento) {
		this.salvarDestinatarioDocumento = salvarDestinatarioDocumento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Usuario> getListaRemetente() {
		return listaRemetente;
	}

	public void setListaRemetente(List<Usuario> listaRemetente) {
		this.listaRemetente = listaRemetente;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		if(documento != null){
			documento.getEnderecamento().setLocal(getLocal());
			documento.getEnderecamento().setNomeEnderecado(remetente.getNome());
			documento.getEnderecamento().setUsuario(remetente);
		}
		this.remetente = remetente;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
	
}
