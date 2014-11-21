package br.jus.stj.saad.view.controller.aviso;

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
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.identificador.IdentificadorUtils;

@ViewScoped
@ManagedBean
public class ConsultarAvisoController extends AvisoController{

	private static final long serialVersionUID = -7973546690749472531L;
	
	private List<Aviso> listResultadoConsulta;
	
	private Aviso avisoSelecionado;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setDataDeInclusao(null);
		setDataDeSolucao(null);
	}
		
	public void consultar(){
		Boolean camposOK = true;
		
		getAviso().setDescricao(getDescricao());
		getAviso().setDataInclusao(getDataDeInclusao());
		getAviso().setSituacao(getSituacaoAvisoEnum().getValor());
		getAviso().setDocumento(getDocumento());
		getAviso().setTipoEnderecamento(getTipoEnderecamentoAviso().getChave());
		getAviso().setLocal(getLoginBean().getUnidadeUsuario());
		getAviso().setDestinatariosAviso(new ArrayList<DestinatarioAviso>());
		getAviso().setDataResolucao(getDataDeSolucao());
		
		if(getDestinatarioEspecificioSelecionado() && getListDestinatario().size() < 1){
			camposOK = false;
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Destinatário não adicionado.", "É preciso adicionar pelo menos um destinatário."));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		for (Usuario usuario : getListDestinatario()) {
			DestinatarioAviso destinatarioAviso = new DestinatarioAviso();
			destinatarioAviso.setUsuario(usuario);
			destinatarioAviso.setAviso(getAviso());
			destinatarioAviso.setSituacao(getSituacaoAvisoEnum().getValor());
			destinatarioAviso.ativo = true;
			getAviso().getDestinatariosAviso().add(destinatarioAviso);
		}
		
		if(camposOK){
			setListResultadoConsulta(getAvisoServico().inicializarLista(getAvisoServico().buscarAviso(getAviso()), "destinatariosAviso"));
			if(getListResultadoConsulta() == null || getListResultadoConsulta().size() < 1){
				setMessage(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Nenhum registro encontrado.", "Nenhum registro foi encontrado para os filtros adicionados."));
				FacesContext.getCurrentInstance().addMessage(null, getMessage());
			}
		}
	}

	public Boolean temResultado(){
		if(listResultadoConsulta != null && listResultadoConsulta.size() > 0){
			return true;
		}
		return false;
	}
	
	public String comandoAlterar() {
		if(avisoSelecionado != null){
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("avisoSelecionado", avisoSelecionado);
			return "alterarAviso";
		}else{
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Selecione um Registro.", "Não é possível alterar."));
			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
		
		return "";
	}

	public void comandoExcluir() {
		if(getAvisoSelecionado() != null){
			getAvisoServico().excluir(getAvisoSelecionado());
			getListResultadoConsulta().remove(getAvisoSelecionado());
			
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Aviso", "excluído com sucesso!"));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}else{
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Selecione um Registro.", "Não é possível excluir."));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
			
		}
		
	}
	
	public SituacaoAvisoEnum obterSituacaoAviso(Character situacaoAviso) {
		return SituacaoAvisoEnum.obterSituacaoAvisoEnumPorCharacter(situacaoAviso);
	 }
	
	public String obterFormatoIdentificador(Documento documento) {
		
		if(documento != null){
		String sigla = documento.getTipo().getSigla();
		String identificacaoDoc = documento.getIdentificacaoDocumentoSTJ();
		Date dataInclusaoDoc = documento.getDataInclusao();

		return IdentificadorUtils.obterIdentificadorDocumentoFormatado(sigla,
				identificacaoDoc, dataInclusaoDoc);
		}
		return "";
	}
	
	public List<Aviso> getListResultadoConsulta() {
		return listResultadoConsulta;
	}

	public void setListResultadoConsulta(List<Aviso> listResultadoConsulta) {
		this.listResultadoConsulta = listResultadoConsulta;
	}
	
	public Aviso getAvisoSelecionado() {
		return avisoSelecionado;
	}

	public void setAvisoSelecionado(Aviso avisoSelecionado) {
		this.avisoSelecionado = avisoSelecionado;
	}
}
