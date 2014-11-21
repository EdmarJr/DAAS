package br.jus.stj.saad.view.controller.aviso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;

import br.jus.stj.saad.business.AvisoBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.local.DestinatarioAviso;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoAvisoEnum;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.view.controller.GenericController;
import br.jus.stj.saad.view.controller.lazy.LazyAvisoDaUnidadeDataModel;
import br.jus.stj.saad.view.controller.lazy.LazyAvisoDoUsuarioDataModel;

@ManagedBean
@ViewScoped
public class ConsultaAvisoController extends GenericController{

	private static final long serialVersionUID = -6717661939269839332L;
	
	@EJB
	private AvisoBean avisoBean;
	@EJB
	private UsuarioBean usuarioBean;

	@Inject
	private LoginBean loginBean;

	private List<Aviso> meusAvisos;
	private List<Aviso> avisosUnidade;
	
    private LazyDataModel<Aviso> lazyAvisoDaUnidadeDataModel;
    private LazyDataModel<Aviso> lazyAvisoDoUsuarioDataModel;

    private int totalAvisosPendenteUnidade;
    private int totalAvisosPendenteUsuario;

	@PostConstruct
	private void initialize() {
		lazyAvisoDaUnidadeDataModel = new LazyAvisoDaUnidadeDataModel(avisoBean, loginBean);
		lazyAvisoDoUsuarioDataModel = new LazyAvisoDoUsuarioDataModel(avisoBean, loginBean);
		totalAvisosPendenteUnidade = avisoBean.totalAvisosUnidadeUsuarioPendentes(loginBean.getUnidadeUsuario(), null);
		totalAvisosPendenteUsuario = avisoBean.totalAvisosUnidadeUsuarioPendentes(loginBean.getUnidadeUsuario(), loginBean.getUsuarioLogado());
	}

	public String detalharAviso(Aviso avisoSelecionado) {

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("avisoSelecionado", avisoSelecionado);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
		.put("paginaPai", "/pages/notification/minhasNotificacoes.xhtml");
		
		return "detalharAviso";

	}

	public int getNumAvisosPendentes(List<Aviso> avisos) {
		int numAvisosPendentes = 0;
		if(avisos != null){
			for (Aviso aviso : avisos) {
	
				if (aviso.getSituacao().equals(
						SituacaoAvisoEnum.PENDENTE.getValor()))
					numAvisosPendentes++;
	
			}
		}

		return numAvisosPendentes;
	}

	public List<Aviso> getMeusAvisos() {

		if (meusAvisos == null) {
			Usuario usuario = loginBean.getUsuarioLogado();
			Local local = loginBean.getUnidadeUsuario();

			meusAvisos = avisoBean.obterAvisosDoUsuarioLogado(usuario, local);

		}

		return meusAvisos;

	}

	public String obterListaDestinatariosFormatado(Aviso aviso) {

		int tamnhoLista = aviso.getDestinatariosAviso().size();
		int iteracao = 0;
		String usuarios = "";

		for (DestinatarioAviso destAviso : aviso.getDestinatariosAviso()) {

			iteracao++;

			if (tamnhoLista != iteracao) {

				usuarios = usuarios.concat(destAviso.getUsuario().getNome()
						+ ", ");

			} else {

				usuarios = usuarios.concat(destAviso.getUsuario().getNome());

			}

		}

		return usuarios;

	}

	public void setMeusAvisos(List<Aviso> meusAvisos) {

		this.meusAvisos = meusAvisos;

	}

	public List<Aviso> getAvisosUnidade() {

		if (avisosUnidade == null) {

			avisosUnidade = avisoBean.obterAvisosPorUnidade(loginBean.getUnidadeUsuario());

		}

		return avisosUnidade;

	}

	public SituacaoAvisoEnum obterSituacaoAviso(Character situacaoAviso) {
		return SituacaoAvisoEnum
				.obterSituacaoAvisoEnumPorCharacter(situacaoAviso);
	}

	public void onChange(TabChangeEvent e) {
		if (e.getTab().getTitle().equals("Meus Avisos")) {
			Usuario usuario = loginBean.getUsuarioLogado();
			Local local = loginBean.getUnidadeUsuario();

			setMeusAvisos(avisoBean.obterAvisosDoUsuarioLogado(usuario, local));
		} else if (e.getTab().getTitle().equals("Avisos da Unidade")) {
			setMeusAvisos(avisoBean
					.obterAvisosPorUnidade(usuarioBean.obterUsuarioLogado()
							.getLocaisUsuarios().get(0).getLocal()));
		}
	}

	public LazyDataModel<Aviso> getLazyAvisoDaUnidadeDataModel() {
		return lazyAvisoDaUnidadeDataModel;
	}

	public void setLazyAvisoDaUnidadeDataModel(
			LazyDataModel<Aviso> lazyAvisoDaUnidadeDataModel) {
		this.lazyAvisoDaUnidadeDataModel = lazyAvisoDaUnidadeDataModel;
	}

	public LazyDataModel<Aviso> getLazyAvisoDoUsuarioDataModel() {
		return lazyAvisoDoUsuarioDataModel;
	}

	public void setLazyAvisoDoUsuarioDataModel(
			LazyDataModel<Aviso> lazyAvisoDoUsuarioDataModel) {
		this.lazyAvisoDoUsuarioDataModel = lazyAvisoDoUsuarioDataModel;
	}

	public int getTotalAvisosPendenteUnidade() {
		return totalAvisosPendenteUnidade;
	}

	public void setTotalAvisosPendenteUnidade(int totalAvisosPendenteUnidade) {
		this.totalAvisosPendenteUnidade = totalAvisosPendenteUnidade;
	}

	public int getTotalAvisosPendenteUsuario() {
		return totalAvisosPendenteUsuario;
	}

	public void setTotalAvisosPendenteUsuario(int totalAvisosPendenteUsuario) {
		this.totalAvisosPendenteUsuario = totalAvisosPendenteUsuario;
	}

}
