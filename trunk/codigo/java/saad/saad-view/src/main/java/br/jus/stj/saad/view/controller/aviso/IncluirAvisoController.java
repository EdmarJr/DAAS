package br.jus.stj.saad.view.controller.aviso;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;

@ViewScoped
@ManagedBean
public class IncluirAvisoController extends AvisoController {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	@Override
	public void init() {
		setDocumento(new Documento());
		super.init();
		setListSituacaoAvisoEnum(getListSituacaoAvisoEnum());
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
				camposOk = false;
				setMessage(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Nenhum documento adicionado.", ""));

				FacesContext.getCurrentInstance().addMessage(null, getMessage());				
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
			getAviso().setLocal(getLocalBean().sincronizar(getLoginBean().getUnidadeUsuario()));
			getAviso().setDestinatariosAviso(new ArrayList<DestinatarioAviso>());
			getAviso().setDataResolucao(getDataDeSolucao());
			getAviso().setUsuario(getLoginBean().getUsuarioLogado());
			for (Usuario usuario : getListDestinatario()) {
				DestinatarioAviso destinatarioAviso = new DestinatarioAviso();
				destinatarioAviso.setUsuario(usuario);
				destinatarioAviso.setAviso(getAviso());
				destinatarioAviso.setSituacao(getSituacaoAvisoEnum().getValor());
				destinatarioAviso.ativo = true;
				getAviso().getDestinatariosAviso().add(destinatarioAviso);
			}
			
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Aviso inserido com sucesso.", ""));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
			
			getAvisoServico().incluir(getAviso());
			
			limpar();
		}
	}
	
	@Override
	public SituacaoAvisoEnum[] gerarListaSituacaoAvisoEnum(){
		SituacaoAvisoEnum[] situacaoAvisoEnums = new SituacaoAvisoEnum[2];
		situacaoAvisoEnums[0] = SituacaoAvisoEnum.PENDENTE;
		situacaoAvisoEnums[1] = SituacaoAvisoEnum.EM_ANDAMENTO;
		return situacaoAvisoEnums;
	}
}
