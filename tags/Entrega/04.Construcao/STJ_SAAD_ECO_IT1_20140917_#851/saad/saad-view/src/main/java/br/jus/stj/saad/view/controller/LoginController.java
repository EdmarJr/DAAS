package br.jus.stj.saad.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController extends GenericController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2363094347291257081L;
	
	private String usuario;
	private String senha;
	
	public String confirmar() {
		System.out.println("Processar o login......");
		System.out.println("Usuário " + this.usuario);
		System.out.println("Senha " + this.senha);
		
		return "success";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
