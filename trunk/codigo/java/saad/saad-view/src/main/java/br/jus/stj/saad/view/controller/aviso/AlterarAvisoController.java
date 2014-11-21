package br.jus.stj.saad.view.controller.aviso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.RelacionadoDocumentoEnum;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoAvisoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;

@ViewScoped
@ManagedBean
public class AlterarAvisoController extends AvisoController{

	private static final long serialVersionUID = 7332638726169481351L;
		
	private Date dataLimiteAlterarAviso;
	
	private Aviso avisoRequest;
	
	private List<Aviso> avisoResultadoRequest;
	
	@PostConstruct
	@Override
	public void init() {
		avisoRequest = (Aviso) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoRequest");
		
		avisoResultadoRequest = (List<Aviso>) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoResultadoRequest");
		
		setDataLimiteAlterarAviso(new Date());
		setAviso((Aviso) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("avisoSelecionado"));
		
		setAviso(getAvisoServico().inicializar(getAviso(), "destinatariosAviso", "local"));
		setDataDeInclusao(getAviso().getDataInclusao());
				
		if(getAviso().getDataResolucao() != null){
			setDataDeSolucao(getAviso().getDataResolucao());
		}
		
		setDescricao(getAviso().getDescricao());
		
		if(getAviso().getDocumento() != null){
			setDocumento(getAviso().getDocumento());
			setNumeroDocumento(getDocumento().getNumeroDocumentoOrigem());
					
			String sigla = getDocumento().getTipo().getSigla();
			String identificacaoDoc = getDocumento().getNumeroControleIdentificador();
			Date dataInclusaoDoc = getDocumento().getDataInclusao();

			setIdentificadorDocumento(IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla, identificacaoDoc,
					dataInclusaoDoc));
			
			setTipoDocumento(getDocumento().getTipo());
			setDocumentoRelacionadoSelecionado(true);
			setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum.S);
		}else{
			setDocumento(new Documento());
			setDocumentoRelacionadoSelecionado(false);
			setRelacionadoDocumentoEnum(RelacionadoDocumentoEnum.N);
		}
		
		setListDestinatario(new ArrayList<Usuario>());
		
		if(getAviso().getDestinatariosAviso().size() > 0){
			setDestinatarioEspecificioSelecionado(true);
			List<DestinatarioAviso> list = getAviso().getDestinatariosAviso();
			for (DestinatarioAviso destinatarioAviso : list) {
				getListDestinatario().add(destinatarioAviso.getUsuario());
			}
			setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum.DESTINATARIO_ESPECIFICO);

		}else{
			setTipoEnderecamentoAviso(TipoEnderecamentoAvisoEnum.TODOS);
			setDestinatarioEspecificioSelecionado(false);
		}

		getAviso().setTipoEnderecamento(TipoEnderecamentoAvisoEnum.TODOS.getChave());

		setSituacaoAvisoEnum(SituacaoAvisoEnum.getEnumPorChave(getAviso().getSituacao()));
		
		setListTipoEnderecamentoAviso(gerarListaTipoEnderecamentoAviso());
		setListSituacaoAvisoEnum(gerarListaSituacaoAvisoEnum());
		setListRelacionadoDocumentoEnum(gerarListaRelacionadoDocumentoEnum());
		setListTipoDocumento(getAvisoServico().listarTodosTiposDocumentos());
	}
	
	public void salvar(){
		Boolean camposOk = true;
		
		if(getDescricao() != null && !"".equalsIgnoreCase(getDescricao())){
			getAviso().setDescricao(getDescricao());
		}else{
			camposOk = false;
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"O Campo de Descrição não pode ficar em branco.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		if(getDataDeInclusao() != null){
			getAviso().setDataInclusao(getDataDeInclusao());
		}else{
			camposOk = false;
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"O Campo de Data de Inclusão não pode ficar em branco.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		if(getSituacaoAvisoEnum() != null){
			getAviso().setSituacao(getSituacaoAvisoEnum().getValor());
		}else{
			camposOk = false;
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"O Campo de Situação não pode ficar em branco.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		if(getDocumentoRelacionadoSelecionado()){
			if(validaDocumento()){
				getAviso().setDocumento(getDocumento());
			}else{
				if(getAviso().getDocumento() == null){
					setMessage(new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Nenhum documento adicionado.", ""));
	
					FacesContext.getCurrentInstance().addMessage(null, getMessage());
				}
			}
		}
		
		if(getDestinatarioEspecificioSelecionado() && getListDestinatario().size() < 1){
			camposOk = false;
			
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"É preciso adicionar pelo menos um destinatário.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		if(camposOk){
			getAviso().setTipoEnderecamento(getTipoEnderecamentoAviso().getChave());
			getAviso().setLocal(getLoginBean().getUnidadeUsuario());
			getAviso().setDataResolucao(getDataDeSolucao());
			getAvisoServico().alterar(getAviso());
			
			setMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Aviso alterado com sucesso.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/saad/pages/notification/minhasNotificacoes.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void adicionarDestinatario(){
		if(getDestinatario() != null && !getAviso().getDestinatariosAviso().contains(getDestinatario())){
			getListDestinatario().add(getDestinatario());
			
			DestinatarioAviso destinatarioAviso = new DestinatarioAviso();
			destinatarioAviso.setUsuario(getDestinatario());
			destinatarioAviso.setAviso(getAviso());
			destinatarioAviso.setSituacao(getSituacaoAvisoEnum().getValor());
			destinatarioAviso.ativo = true;
			
			getAviso().getDestinatariosAviso().add(destinatarioAviso);
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
	
	@Override
	public void removerDestinatario(Usuario destinatario){
		if(getListDestinatario().contains(destinatario)){
			getListDestinatario().remove(destinatario);
			DestinatarioAviso destinatarioAviso = retornaDestinatarioPorUsuario(getAviso().getDestinatariosAviso(), destinatario);
			getAviso().getDestinatariosAviso().remove(getAviso().getDestinatariosAviso().indexOf(destinatarioAviso));
		}
	}
	
	@Override
	public void verificarEnderecamento(){
		if(getTipoEnderecamentoAviso().equals(TipoEnderecamentoAvisoEnum.TODOS)){
			for (Usuario usuario : getListDestinatario()) {
				DestinatarioAviso destinatarioAviso = retornaDestinatarioPorUsuario(getAviso().getDestinatariosAviso(), usuario);
				getAviso().getDestinatariosAviso().remove(getAviso().getDestinatariosAviso().indexOf(destinatarioAviso));
			}

			getListDestinatario().clear();
		}
	}
	
	public DestinatarioAviso retornaDestinatarioPorUsuario(List<DestinatarioAviso> list, Usuario usuario){
		for (DestinatarioAviso destinatarioAviso : list) {
			if(destinatarioAviso.getUsuario().getId() == usuario.getId()){
				return destinatarioAviso;
			}
		}
		
		return null;
	}
	
	public String voltar(){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("avisoRequest", avisoRequest);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("avisoResultadoRequest", avisoResultadoRequest);

		return "consultarAviso";
	}

	public Date getDataLimiteAlterarAviso() {
		return dataLimiteAlterarAviso;
	}

	public void setDataLimiteAlterarAviso(Date dataLimiteAlterarAviso) {
		this.dataLimiteAlterarAviso = dataLimiteAlterarAviso;
	}

}
