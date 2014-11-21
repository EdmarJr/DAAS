package br.jus.stj.saad.view.controller.login;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.view.controller.GenericController;

@SessionScoped
@Named
public class LoginSessionController extends GenericController{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB 
	private UsuarioBean usuarioBean;
	
	
	public Usuario obterUsuarioLogado() {
		return usuarioBean.obterUsuarioLogado();
	}
}
