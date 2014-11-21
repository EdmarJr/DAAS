/*
 * SaapAcompPendenciaAdm.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;

/**
 * Classe que representa a entidade persistente <code>SAAP_ACOMP_PENDENCIA_ADM</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_ACOMP_PENDENCIA_ADM")
public class SaapAcompPendenciaAdm implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
    @GenericGenerator(name="seq_id", strategy="increment")	
	@GeneratedValue(generator="seq_id")
	@javax.persistence.Column(name = "SEQ_ACOMP_PENDENCIA")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "SEQ_USUARIO", 
							  nullable = false, 
							  length = 10, 
							  unique = false)
	private java.lang.Integer seqUsuario;

	@javax.persistence.Column(name = "TXT_OBS_ACOMP_PENDENCIA", 
							  nullable = true, 
							  length = 500, 
							  unique = false)
	private java.lang.String txtObsAcompPendencia;

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
	 * @param seqAcompPendencia atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqAcompPendencia) {
		this.id = seqAcompPendencia;
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
	 * @return O valor do atributo txtObsAcompPendencia
	 */
	public java.lang.String getTxtObsAcompPendencia() {
		return txtObsAcompPendencia;
	}

	/**
	 * @param _txtObsAcompPendencia atribui um valor 
	 * 		ao atributo txtObsAcompPendencia
	 */
	public void setTxtObsAcompPendencia(
			java.lang.String _txtObsAcompPendencia) {
		this.txtObsAcompPendencia = _txtObsAcompPendencia;
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
 