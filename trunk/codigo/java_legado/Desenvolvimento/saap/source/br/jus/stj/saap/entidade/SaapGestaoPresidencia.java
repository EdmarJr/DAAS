/*
 * SaapGestaoPresidencia.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;

/**
 * Classe que representa a entidade persistente <code>SAAP_GESTAO_PRESIDENCIA</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_GESTAO_PRESIDENCIA")
public class SaapGestaoPresidencia implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_GESTAO_PRESIDENCIA")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "DT_INICIAL_VIG_GESTAO", 
							  nullable = false, 
							  length = 23, 
							  unique = false)
	private java.util.Date dtInicialVigGestao;

	@javax.persistence.Column(name = "DT_FINAL_VIG_GESTAO", 
							  nullable = true, 
							  length = 23, 
							  unique = false)
	private java.util.Date dtFinalVigGestao;

	@javax.persistence.Column(name = "NOME_MINISTRO_PRESIDENTE", 
							  nullable = false, 
							  length = 50, 
							  unique = false)
	private java.lang.String nomeMinistroPresidente;

	
	
	/** Association. */
	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqGestaoPresidencia atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqGestaoPresidencia) {
		this.id = seqGestaoPresidencia;
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
	 * @return O valor do atributo dtInicialVigGestao
	 */
	public java.util.Date getDtInicialVigGestao() {
		return dtInicialVigGestao;
	}

	/**
	 * @param _dtInicialVigGestao atribui um valor 
	 * 		ao atributo dtInicialVigGestao
	 */
	public void setDtInicialVigGestao(
			java.util.Date _dtInicialVigGestao) {
		this.dtInicialVigGestao = _dtInicialVigGestao;
	}

	/**
	 * @return O valor do atributo dtFinalVigGestao
	 */
	public java.util.Date getDtFinalVigGestao() {
		return dtFinalVigGestao;
	}

	/**
	 * Retorna true se a data final da vigência da gestão estiver preenchido, 
	 * caso contrário, retorna false.
	 * 
	 * @return boolean
	 */
	public boolean isDtFinalVigGestaoPreenchido(){
		boolean retorno = false;
		if (getDtFinalVigGestao()!=null){
			retorno = true;
		}
		return retorno;
	}

	/**
	 * @param _dtFinalVigGestao atribui um valor 
	 * 		ao atributo dtFinalVigGestao
	 */
	public void setDtFinalVigGestao(
			java.util.Date _dtFinalVigGestao) {
		this.dtFinalVigGestao = _dtFinalVigGestao;
	}
	
	/**
	 * @return O valor do atributo nomeMinistroPresidente
	 */
	public java.lang.String getNomeMinistroPresidente() {
		return nomeMinistroPresidente;
	}

	/**
	 * @param _nomeMinistroPresidente atribui um valor 
	 * 		ao atributo nomeMinistroPresidente
	 */
	public void setNomeMinistroPresidente(
			java.lang.String _nomeMinistroPresidente) {
		this.nomeMinistroPresidente = _nomeMinistroPresidente;
	}
}
 