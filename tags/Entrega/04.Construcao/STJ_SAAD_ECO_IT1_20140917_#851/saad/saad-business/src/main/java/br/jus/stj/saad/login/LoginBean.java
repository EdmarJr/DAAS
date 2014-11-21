package br.jus.stj.saad.login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;

@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioLogado;
	private Local unidadeUsuario;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Local getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(Local unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

}
