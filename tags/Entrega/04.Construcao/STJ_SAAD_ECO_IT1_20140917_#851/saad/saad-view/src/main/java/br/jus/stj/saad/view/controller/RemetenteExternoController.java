package br.jus.stj.saad.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.business.CargoBean;
import br.jus.stj.saad.business.DocumentoBean;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;

@ManagedBean
@ViewScoped
public class RemetenteExternoController extends GenericController {

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

	private Orgao orgao;

	private Cargo cargo;
	
	private String nomeDestinatario;

	private boolean salvarDestinatarioDocumento;
	
	private boolean alterarDocumento = false;

	public boolean isAlterarDocumento() {
		return alterarDocumento;
	}

	public void setAlterarDocumento(boolean alterarDocumento) {
		this.alterarDocumento = alterarDocumento;
	}

	@PostConstruct
	private void initialize() {
		if(documento == null){
			documento = (Documento) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("documento");
			if(documento != null){
				documento = documentoBean.inicializar(documento,"tarefas","anexos","local", "destinatario");
				if(documento.getDestinatarioDocumento() != null){
					nomeDestinatario = documento.getDestinatarioDocumento().getNomeDestinatario();
				}
				documento.setInterfaceDestinatario(documento.getDestinatarioDocumento());
			}
		}		
	}

	public List<String> autoCompleteOrgao(String query) {
		setNomeDestinatario(query);
		List<Orgao> list = orgaoBean.listarPorNome(query);
		
		List<String> retorno = new ArrayList<String>();

		for (Orgao orgao : list) {
			retorno.add(orgao.getNome());
		}
		
		if(retorno.size() < 1){
			documento.getInterfaceDestinatario().setNomeDestinatario(nomeDestinatario);
			setSalvarDestinatarioDocumento(true);
			
		}else{
			setSalvarDestinatarioDocumento(false);
		}
		
		return retorno;
	}

	private Boolean seOrgaoNoBanco(Orgao orgao) {
		if (orgao != null && orgao.getId() != null) {
			setSalvarDestinatarioDocumento(false);
			return true;
		} else {
			setSalvarDestinatarioDocumento(true);
			return false;
		}
	}

	public void preencherOrgao() {
		List<Orgao> list = orgaoBean.listarPorNome(getNomeDestinatario());
		
		if(list.size() > 0){
			setOrgao(list.get(0));
			if (seOrgaoNoBanco(getOrgao())) {
				inicializarListaDestinatarios();
				if (orgao.getDestinatarioOcupacao() != null) {
					documento.setInterfaceDestinatario(orgao.getDestinatarioOcupacao().getDestinatario());
					ajustarEnderecamentoExterno((Destinatario) documento.getInterfaceDestinatario());
				}
				nomeDestinatario = getOrgao().getNome();
			}else{
				documento.getInterfaceDestinatario().setNomeDestinatario(nomeDestinatario);
			}
		}else{
			getDocumento().getInterfaceDestinatario().setNomeDestinatario(nomeDestinatario);
		}
	}
	
	public void preencherDestinatario() {
		if (!seOrgaoNoBanco(getOrgao())){
			getDocumento().getInterfaceDestinatario().setNomeDestinatario(nomeDestinatario);
		}
	}

	public void ajustarEnderecamentoExterno(Destinatario destinatario) {
		EnderecamentoExterno enderecamentoExterno = (EnderecamentoExterno) getDocumento()
				.getEnderecamento();
		enderecamentoExterno.setDestinatario(destinatario);
		enderecamentoExterno.setNomeEnderecado(destinatario.getNome());
	}

	private void inicializarListaDestinatarios() {
		setOrgao(orgaoBean.inicializar(getOrgao(), "destinatariosOcupacao"));
	}

	public List<Cargo> autoCompleteCargo(String query) {
		return cargoBean.listarPorNome(query);
	}

	public void preencherCargo() {

	}

	public Boolean isDocumentExternal() {
		if (getDocumento().getTipoOrigem().equals(
				TipoOrigemDocumentoEnum.EXTERNO.getValor())) {
			return true;
		}
		return false;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean getSalvarDestinatarioDocumento() {
		return salvarDestinatarioDocumento;
	}

	public void setSalvarDestinatarioDocumento(
			boolean salvarDestinatarioDocumento) {
		this.salvarDestinatarioDocumento = salvarDestinatarioDocumento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

}
