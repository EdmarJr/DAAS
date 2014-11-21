/*
 * SaapHistAdmtAdm.java
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
 * Classe que representa a entidade persistente <code>SAAP_HIST_ADMT_ADM</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_HIST_ADMT_ADM")
public class SaapHistAdmtAdm implements Entidade {

	/** Primary key. */
	
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_HIST_ADMT")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_HIST_ADMT", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsHistAdmt;

	@javax.persistence.Column(name = "DESC_ADMT_ATUAL", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String descAdmtAtual;

	@javax.persistence.Column(name = "NOME_ACAO_HIST_ADMT", 
							  nullable = false, 
							  length = 15, 
							  unique = false)
	private java.lang.String nomeAcaoHistAdmt;

	@javax.persistence.Column(name = "SEQ_USUARIO", 
							  nullable = false, 
							  length = 10, 
							  unique = false)
	private java.lang.Integer seqUsuario;

	/** Association. */
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumns({
		@javax.persistence.JoinColumn(
			name = "SEQ_ADMT_ADM"		),
		@javax.persistence.JoinColumn(
			name = "IND_TIPO_ADMT_ADM"		),
	})
	private br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia saapAdmtAdmPresidencia;

	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqHistAdmt atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqHistAdmt) {
		this.id = seqHistAdmt;
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
	 * @return O valor do atributo tsHistAdmt
	 */
	public java.util.Date getTsHistAdmt() {
		return tsHistAdmt;
	}
	
	/**
	 * @param _tsHistAdmt atribui um valor 
	 * 		ao atributo tsHistAdmt
	 */
	public void setTsHistAdmt(
			java.util.Date _tsHistAdmt) {
		this.tsHistAdmt = _tsHistAdmt;
	}
	
	/**
	 * @return O valor do atributo descAdmtAtual
	 */
	public java.lang.String getDescAdmtAtual() {
		return descAdmtAtual;
	}

	/**
	 * @param _descAdmtAtual atribui um valor 
	 * 		ao atributo descAdmtAtual
	 */
	public void setDescAdmtAtual(
			java.lang.String _descAdmtAtual) {
		this.descAdmtAtual = _descAdmtAtual;
	}
	
	/**
	 * @return O valor do atributo nomeAcaoHistAdmt
	 */
	public java.lang.String getNomeAcaoHistAdmt() {
		return nomeAcaoHistAdmt;
	}

	/**
	 * @param _nomeAcaoHistAdmt atribui um valor 
	 * 		ao atributo nomeAcaoHistAdmt
	 */
	public void setNomeAcaoHistAdmt(
			java.lang.String _nomeAcaoHistAdmt) {
		this.nomeAcaoHistAdmt = _nomeAcaoHistAdmt;
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
	 * @return O valor do atributo saapAdmtAdmPresidencia;
	 */
	public br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia getSaapAdmtAdmPresidencia() {
		return saapAdmtAdmPresidencia;
	}

	/**
	 * @param _saapAdmtAdmPresidencia atribui um valor 
	 * 		ao atributo this.saapAdmtAdmPresidencia
	 */
	public void setSaapAdmtAdmPresidencia(
			br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia _saapAdmtAdmPresidencia) {
		this.saapAdmtAdmPresidencia = _saapAdmtAdmPresidencia;
	}
	
}
 