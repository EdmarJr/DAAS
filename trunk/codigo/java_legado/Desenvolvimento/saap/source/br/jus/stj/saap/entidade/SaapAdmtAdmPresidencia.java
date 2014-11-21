/*
 * SaapAdmtAdmPresidencia.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;

import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;

/**
 * Classe que representa a entidade persistente <code>SAAP_ADMT_ADM_PRESIDENCIA</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_ADMT_ADM_PRESIDENCIA")
public class SaapAdmtAdmPresidencia implements Entidade {

	/** Primary key. */
	@javax.persistence.EmbeddedId
	private SaapAdmtAdmPresidenciaPk id;
	
	/** Regular field. */
	@javax.persistence.Column(name = "DESC_ADMT_ADM", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String descAdmtAdm;

	
	/** Association. */
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumns({
		@javax.persistence.JoinColumn(
			name = "SEQ_PENDENCIA_ADM"		),
	})
	private br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia;

	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumns({
		@javax.persistence.JoinColumn(
			name = "SEQ_DOC_ADM"		),
	})
	private br.jus.stj.saap.entidade.SaapDocAdmPresidencia saapDocAdmPresidencia;

	@javax.persistence.OneToMany(mappedBy = "saapAdmtAdmPresidencia")
	private java.util.Collection<SaapHistAdmtAdm> saapHistAdmtAdms;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_ENTRADA_ADMT", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsEntradaAdmt;

	/**
	 * @return O valor do atributo tsEntradaAdmt
	 */
	public java.util.Date getTsEntradaAdmt() {
		return tsEntradaAdmt;
	}

	/**
	 * @param _tsEntradaAdmt atribui um valor 
	 * 		ao atributo tsEntradaAdmt
	 */
	public void setTsEntradaAdmt(
			java.util.Date _tsEntradaAdmt) {
		this.tsEntradaAdmt = _tsEntradaAdmt;
	}

	/** 
	 *Constroi o objeto e inicializa a chave primaria.
	 */
	public SaapAdmtAdmPresidencia() {
		id = new SaapAdmtAdmPresidenciaPk();
	}

	/**
	 * @return O valor do atributo id
	 */
	public SaapAdmtAdmPresidenciaPk getId() {
		return id;
	}

	
	
	/**
	 * @param primaryKey atribui um valor ao atributo id
	 */
	public void setId(SaapAdmtAdmPresidenciaPk primaryKey) {
		this.id = primaryKey;
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
	 * @return O valor do atributo descAdmtAdm
	 */
	public java.lang.String getDescAdmtAdm() {
		return descAdmtAdm;
	}

	/**
	 * @param _descAdmtAdm atribui um valor 
	 * 		ao atributo descAdmtAdm
	 */
	public void setDescAdmtAdm(
			java.lang.String _descAdmtAdm) {
		this.descAdmtAdm = _descAdmtAdm;
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
	
	/**
	 * @return O valor do atributo saapHistAdmtAdms;
	 */
	public java.util.Collection<SaapHistAdmtAdm> getSaapHistAdmtAdms() {
		return saapHistAdmtAdms;
	}

	/**
	 * @param _saapHistAdmtAdms atribui um valor 
	 * 		ao atributo this.saapHistAdmtAdms
	 */
	public void setSaapHistAdmtAdms(
			java.util.Collection<SaapHistAdmtAdm> _saapHistAdmtAdms) {
		this.saapHistAdmtAdms = _saapHistAdmtAdms;
	}

	/**
	 * Retorna a descrição do documento.
	 * 
	 * @return String
	 */
	public String getDescDoc() {
		String retorno = "";
		if(UtilObjeto.isReferencia(getSaapDocAdmPresidencia())) {
			retorno = getSaapDocAdmPresidencia().getDescDoc();
		}
		return retorno;
	}

	/**
	 * Retorna o tipo do documento.
	 * 
	 * @return String
	 */
	public String getIndTipoDocFormatado() {
		String retorno = "";
		if(UtilObjeto.isReferencia(getSaapDocAdmPresidencia())) {
			retorno = getSaapDocAdmPresidencia().getIndTipoDocFormatado();
		}
		return retorno;
	}
	
	/**
	 * Retorna o assunto da pendência
	 * 
	 * @return String
	 */
	public String getTxtAssuntoPendencia() {
		String retorno = "";
		if(UtilObjeto.isReferencia(getSaapPendenciaAdmPresidencia())) {
			retorno = getSaapPendenciaAdmPresidencia().getTxtAssuntoPendencia();
		}
		return retorno;
	}
	
	
	
}
