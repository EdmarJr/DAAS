package br.jus.stj.saad.view.controller.localusuario;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.triagem.ArquivoUtils;
import br.jus.stj.saad.view.controller.GenericController;

@ManagedBean
@SessionScoped
public class LocalUsuarioController extends GenericController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginBean loginBean;
	@EJB
	private UsuarioBean usuarioBean;
	
	@PostConstruct
	public void inicializar() {
		loginBean.setUsuarioLogado(usuarioBean.inicializar(loginBean.getUsuarioLogado(), "locaisUsuarios"));
	}
	
	
	public void comandoEntrar() {
		try {
			verificarExistenciaDaPastaDaUnidade();
			FacesContext.getCurrentInstance().getExternalContext().redirect(obterContextPath() + "/pages/notification/minhasNotificacoes.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void verificarExistenciaDaPastaDaUnidade(){
		String caminhoPastaUnidade = ArquivoUtils.obterEnderecoPastaTriagem() + "/" + ArquivoUtils.retornoAmigavel(loginBean.getUnidadeUsuario().getNome());
		ArquivoUtils.criarCaminho(new File(caminhoPastaUnidade).toPath());
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public void comandoAlterarLocal() {
		try {
			verificarExistenciaDaPastaDaUnidade();
			FacesContext.getCurrentInstance().getExternalContext().redirect(obterContextPath() + "/pages/notification/minhasNotificacoes.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
