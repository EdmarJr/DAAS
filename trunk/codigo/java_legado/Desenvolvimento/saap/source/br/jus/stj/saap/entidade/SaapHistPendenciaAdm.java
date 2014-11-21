/*
 * SaapHistPendenciaAdm.java
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
 * Classe que representa a entidade persistente <code>SAAP_HIST_PENDENCIA_ADM</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_HIST_PENDENCIA_ADM")
public class SaapHistPendenciaAdm implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_HIST_PENDENCIA")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_HIST_PENDENCIA", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsHistPendencia;

	@javax.persistence.Column(name = "NOME_ACAO_HIST_PENDENCIA", 
							  nullable = false, 
							  length = 15, 
							  unique = false)
	private java.lang.String nomeAcaoHistPendencia;

	@javax.persistence.Column(name = "SEQ_USUARIO", 
							  nullable = false, 
							  length = 10, 
							  unique = false)
	private java.lang.Integer seqUsuario;

	/** Association. */
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumns({
		@javax.persistence.JoinColumn(
			name = "SEQ_PENDENCIA_ADM"		),
	})
	private br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia;

	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqHistPendencia atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqHistPendencia) {
		this.id = seqHistPendencia;
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
	 * @return O valor do atributo tsHistPendencia
	 */
	public java.util.Date getTsHistPendencia() {
		return tsHistPendencia;
	}

	/**
	 * @param _tsHistPendencia atribui um valor 
	 * 		ao atributo tsHistPendencia
	 */
	public void setTsHistPendencia(
			java.util.Date _tsHistPendencia) {
		this.tsHistPendencia = _tsHistPendencia;
	}
	
	/**
	 * @return O valor do atributo nomeAcaoHistPendencia
	 */
	public java.lang.String getNomeAcaoHistPendencia() {
		return nomeAcaoHistPendencia;
	}

	/**
	 * @param _nomeAcaoHistPendencia atribui um valor 
	 * 		ao atributo nomeAcaoHistPendencia
	 */
	public void setNomeAcaoHistPendencia(
			java.lang.String _nomeAcaoHistPendencia) {
		this.nomeAcaoHistPendencia = _nomeAcaoHistPendencia;
	}
	
	/**
	 * @return O valor do atributo seqUsuario
	 */
	public java.lang.Integer getSeqUsuario() {
		return seqUsuario;
	}

	/**
	 * @param _seqUsuario atribui um valor 
	 * 		ao atributo seqUsuario
	 */
	public void setSeqUsuario(
			java.lang.Integer _seqUsuario) {
		this.seqUsuario = _seqUsuario;
	}
	
	/**
	 * @return O valor do atributo saapPendenciaAdmPresidencia;
	 */
	public br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia getSaapPendenciaAdmPresidencia() {
		return saapPendenciaAdmPresidencia;
	}

	/**
	 * @param _saapPendenciaAdmPresidencia atribui um valor 
	 * 		ao atributo this.saapPendenciaAdmPresidencia
	 */
	public void setSaapPendenciaAdmPresidencia(
			br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia _saapPendenciaAdmPresidencia) {
		this.saapPendenciaAdmPresidencia = _saapPendenciaAdmPresidencia;
	}
	
}
 