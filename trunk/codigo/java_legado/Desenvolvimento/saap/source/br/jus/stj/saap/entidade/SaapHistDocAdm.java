/*
 * SaapHistDocAdm.java
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
 * Classe que representa a entidade persistente <code>SAAP_HIST_DOC_ADM</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_HIST_DOC_ADM")
public class SaapHistDocAdm implements Entidade {

	/** Primary key. */
	
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_HIST_DOC")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_HIST_DOC", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsHistDoc;

	@javax.persistence.Column(name = "NOME_ACAO_HIST_DOC", 
							  nullable = false, 
							  length = 15, 
							  unique = false)
	private java.lang.String nomeAcaoHistDoc;

	@javax.persistence.Column(name = "SEQ_USUARIO", 
							  nullable = false, 
							  length = 10, 
							  unique = false)
	private java.lang.Integer seqUsuario;

	/** Association. */
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumns({
		@javax.persistence.JoinColumn(
			name = "SEQ_DOC_ADM"		),
	})
	private br.jus.stj.saap.entidade.SaapDocAdmPresidencia saapDocAdmPresidencia;

	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqHistDoc atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqHistDoc) {
		this.id = seqHistDoc;
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
	 * @return O valor do atributo tsHistDoc
	 */
	public java.util.Date getTsHistDoc() {
		return tsHistDoc;
	}

	/**
	 * @param _tsHistDoc atribui um valor 
	 * 		ao atributo tsHistDoc
	 */
	public void setTsHistDoc(
			java.util.Date _tsHistDoc) {
		this.tsHistDoc = _tsHistDoc;
	}
	
	/**
	 * @return O valor do atributo nomeAcaoHistDoc
	 */
	public java.lang.String getNomeAcaoHistDoc() {
		return nomeAcaoHistDoc;
	}

	/**
	 * @param _nomeAcaoHistDoc atribui um valor 
	 * 		ao atributo nomeAcaoHistDoc
	 */
	public void setNomeAcaoHistDoc(
			java.lang.String _nomeAcaoHistDoc) {
		this.nomeAcaoHistDoc = _nomeAcaoHistDoc;
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
	 * @return O valor do atributo saapDocAdmPresidencia;
	 */
	public br.jus.stj.saap.entidade.SaapDocAdmPresidencia getSaapDocAdmPresidencia() {
		return saapDocAdmPresidencia;
	}

	/**
	 * @param _saapDocAdmPresidencia atribui um valor 
	 * 		ao atributo this.saapDocAdmPresidencia
	 */
	public void setSaapDocAdmPresidencia(
			br.jus.stj.saap.entidade.SaapDocAdmPresidencia _saapDocAdmPresidencia) {
		this.saapDocAdmPresidencia = _saapDocAdmPresidencia;
	}
	
}
 