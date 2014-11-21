/*
 * AaUsuario.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;

/**
 * Classe que representa a entidade persistente <code>AA_USUARIO</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "aa_usuario")
public class AaUsuario implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@javax.persistence.Column(name = "SEQ_USUARIO")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "NOME_USUARIO", 
							  nullable = false, 
							  length = 70, 
							  unique = false)
	private java.lang.String nomeUsuario;

	@javax.persistence.Column(name = "NOME_NICK_USUARIO", 
							  nullable = false, 
							  length = 15, 
							  unique = false)
	private java.lang.String nomeNicknameUsuario;

	@javax.persistence.Column(name = "NOME_NICK", 
							  nullable = false, 
							  length = 255, 
							  unique = false)
	private java.lang.String nomeNick;
	
	/** Regular field. */
	@javax.persistence.Column(name = "DT_LANCAMENTO", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date dtLancamento;	

	/** Association. */
	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqUsuario atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqUsuario) {
		this.id = seqUsuario;
	}

	/**
	 * @return O identificador desta entidade
	 *
	 * @see br.jus.stj.alp01.arquitetura.entidade.Entidade#getIdentificador()
	 */
	public Serializable getIdentificador() {
		return getId();
	}
	
	/**
	 * @return O valor do atributo nomeUsuario
	 */
	public java.lang.String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param _nomeUsuario atribui um valor 
	 * 		ao atributo nomeUsuario
	 */
	public void setNomeUsuario(
			java.lang.String _nomeUsuario) {
		this.nomeUsuario = _nomeUsuario;
	}
	
	/**
	 * @return O valor do atributo nomeNicknameUsuario
	 */
	public java.lang.String getNomeNicknameUsuario() {
		return nomeNicknameUsuario;
	}

	/**
	 * @param _nomeNicknameUsuario atribui um valor 
	 * 		ao atributo nomeNicknameUsuario
	 */
	public void setNomeNicknameUsuario(
			java.lang.String _nomeNicknameUsuario) {
		this.nomeNicknameUsuario = _nomeNicknameUsuario;
	}
	
	/**
	 * @return O valor do atributo nomeNick
	 */
	public java.lang.String getNomeNick() {
		return nomeNick;
	}

	/**
	 * @param _nomeNick atribui um valor 
	 * 		ao atributo nomeNick
	 */
	public void setNomeNick(
			java.lang.String _nomeNick) {
		this.nomeNick = _nomeNick;
	}

	
	/**
	 * Retorna dtLancamento.
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getDtLancamento() {
		return dtLancamento;
	}

	
	/**
	 * Atribui dtLancamento.
	 * 
	 * @param dtLancamento dtLancamento
	 */
	public void setDtLancamento(java.util.Date dtLancamento) {
		this.dtLancamento = dtLancamento;
	}
	
	
	
}
 