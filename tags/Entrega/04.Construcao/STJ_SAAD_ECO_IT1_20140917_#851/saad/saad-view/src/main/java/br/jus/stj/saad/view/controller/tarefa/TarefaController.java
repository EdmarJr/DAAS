package br.jus.stj.saad.view.controller.tarefa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoTarefaEnum;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@ViewScoped
public class TarefaController extends GenericController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Documento documento;
	private List<Usuario> usuariosDisponiveis;
	private TarefaDemandaDocumento tarefaSelecionada;
	private TarefaDemandaDocumento tarefaNova;
	private FacesMessage message;
	private Usuario novoUsuarioResponsavel;
	private List<TarefaDemandaDocumento> tarefasSelecionadas;
	
	@EJB
	private UsuarioBean usuarioBean;

	@PostConstruct
	private void initialize() {
		setTarefaNova(new TarefaDemandaDocumento());
		
		setUsuariosDisponiveis(usuarioBean
				.obterUsuariosDisponiveisPorUnidadeDoUsuarioLogado(ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE));
		
		if(getUsuariosDisponiveis() != null && getUsuariosDisponiveis().size() > 0){
			setNovoUsuarioResponsavel(getUsuariosDisponiveis().get(0));
		}
		if (getDocumento() != null) {
			
			getDocumento().setTarefas(new ArrayList<TarefaDemandaDocumento>());
		}
		
	}
	
	public SituacaoTarefaEnum obterSituacaoTarefaResolvidoEnum() {
		return SituacaoTarefaEnum.RESOLVIDO;
	}

	public void comandoAdicionarTarefa() {
		if(documento.getTarefas().contains(getTarefaNova())){
			documento.getTarefas().remove(tarefaNova);
			getTarefaNova().setDocumento(documento);
			documento.getTarefas().add(getTarefaNova());
		}else{
			getTarefaNova().setDocumento(documento);
			documento.getTarefas().add(getTarefaNova());
		}
		
		setTarefaNova(new TarefaDemandaDocumento());
	}

	public void comandoExcluir() {
		if(getTarefasSelecionadas().size() > 0){
			for (TarefaDemandaDocumento tarefaSelecionada : getTarefasSelecionadas()) {
				getDocumento().getTarefas().remove(tarefaSelecionada);
			}
		}else{
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"", "Nenhuma tarefa selecionada."));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
	}
	
	public void abrirDialogoTransferencia(){
		if(getTarefasSelecionadas().size() > 0){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialogTransferirTarefa').show();");
		}else{
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"", "Nenhuma tarefa selecionada."));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
	}

	public void comandoAlterarTarefa() {
		if(getTarefaSelecionada() != null){
			if(getTarefasSelecionadas().size() == 1){
				setTarefaNova(getTarefasSelecionadas().get(0));
			}else{
				setMessage(new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"", "Só é possível alterar uma tarefa por vez."));

				FacesContext.getCurrentInstance().addMessage(null, getMessage());
			}			
		}else{
			setMessage(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"", "Nenhuma tarefa selecionada."));

			FacesContext.getCurrentInstance().addMessage(null, getMessage());
		}
	}

	public void comandoTransferir() {
		for (TarefaDemandaDocumento tarefaSelecionada : getTarefasSelecionadas()) {
			int indexOf = documento.getTarefas().indexOf(tarefaSelecionada);
			documento.getTarefas().get(indexOf).setUsuario(getNovoUsuarioResponsavel());			
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogTransferirTarefa').hide();");
	}

	public TarefaDemandaDocumento getTarefaNova() {
		return tarefaNova;
	}

	public void setTarefaNova(TarefaDemandaDocumento tarefaNova) {
		this.tarefaNova = tarefaNova;
	}

	public SituacaoTarefaEnum[] obterSituacoesTarefaPossiveis() {
		return SituacaoTarefaEnum.values();
	}

	public List<Usuario> getUsuariosDisponiveis() {
		return usuariosDisponiveis;
	}

	public void setUsuariosDisponiveis(List<Usuario> usuariosDisponiveis) {
		this.usuariosDisponiveis = usuariosDisponiveis;
	}

	public TarefaDemandaDocumento getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(TarefaDemandaDocumento tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

	public SituacaoTarefaEnum obterSituacaoTarefa(Character situacao) {
		return SituacaoTarefaEnum.obterSituacaoTarefaPorValor(situacao);
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public Usuario getNovoUsuarioResponsavel() {
		return novoUsuarioResponsavel;
	}

	public void setNovoUsuarioResponsavel(Usuario novoUsuarioResponsavel) {
		this.novoUsuarioResponsavel = novoUsuarioResponsavel;
	}

	public List<TarefaDemandaDocumento> getTarefasSelecionadas() {
		return tarefasSelecionadas;
	}

	public void setTarefasSelecionadas(
			List<TarefaDemandaDocumento> tarefasSelecionadas) {
		this.tarefasSelecionadas = tarefasSelecionadas;
	}
}
