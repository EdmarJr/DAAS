/*
 * SaapPendenciaAdmPresidencia.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe que representa a entidade persistente <code>SAAP_PENDENCIA_ADM_PRESIDENCIA</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_PENDENCIA_ADM_PRESIDENCIA")
public class SaapPendenciaAdmPresidencia implements Entidade {

	/** Primary key. */
	
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_PENDENCIA_ADM")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_ENTRADA_PENDENCIA", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsEntradaPendencia;

	@javax.persistence.Column(name = "DESC_ENDERECO_PENDENCIA", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String descEnderecoPendencia;

	@javax.persistence.Column(name = "TXT_ASSUNTO_PENDENCIA", 
							  nullable = false, 
							  length = 500, 
							  unique = false)
	private java.lang.String txtAssuntoPendencia;

	@javax.persistence.Column(name = "IND_SITUACAO_PENDENCIA", 
							  nullable = true, 
							  length = 1, 
							  unique = false)
	private java.lang.Character indSituacaoPendencia;

	/** Association. */
	@javax.persistence.OneToMany(mappedBy = "saapPendenciaAdmPresidencia")
	private java.util.Collection<SaapHistPendenciaAdm> saapHistPendenciaAdms;

	@javax.persistence.OneToMany(mappedBy = "saapPendenciaAdmPresidencia")
	private java.util.Collection<SaapAdmtAdmPresidencia> saapAdmtAdmPresidencias;

	@javax.persistence.OneToMany(mappedBy = "saapPendenciaAdmPresidencia")
	private java.util.Collection<SaapAcompPendenciaAdm> saapAcompPendenciaAdms;

	@Transient
	private String registradoPor;

	/**
	 * Retorna true se indSituacaoPendencia for igual a 'D' senão retorna false.
	 * 
	 * @return boolean
	 */
	public boolean isInativo(){
		boolean retorno = false;
		if (getIndSituacaoPendencia().equals(Constante.DOCUMENTO_INATIVO)){
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Retorna cssAtivoInativo.
	 * 
	 * @return String
	 */
	public java.lang.String getCssAtivoInativo(){
		String retorno = "";
		if (getIndSituacaoPendencia().equals(Constante.DOCUMENTO_INATIVO)){
			retorno = "color:#b5b5b5;";
		}
		return retorno;
	}

	/**
	 * Retorna cssLinkAtivoInativo.
	 * 
	 * @return String
	 */
	public java.lang.String getCssLinkAtivoInativo(){
		String retorno = "";
		if (getIndSituacaoPendencia().equals(Constante.DOCUMENTO_INATIVO)){
			retorno = "color:#b5b5b5;font-weight:bold;text-decoration:underline;";
		}
		return retorno;
	}

	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqPendenciaAdm atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqPendenciaAdm) {
		this.id = seqPendenciaAdm;
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
	 * @return O valor do atributo tsEntradaPendencia
	 */
	public java.util.Date getTsEntradaPendencia() {
		return tsEntradaPendencia;
	}

	/**
	 * @param _tsEntradaPendencia atribui um valor 
	 * 		ao atributo tsEntradaPendencia
	 */
	public void setTsEntradaPendencia(
			java.util.Date _tsEntradaPendencia) {
		this.tsEntradaPendencia = _tsEntradaPendencia;
	}
	
	/**
	 * @return O valor do atributo descEnderecoPendencia
	 */
	public java.lang.String getDescEnderecoPendencia() {
		return descEnderecoPendencia;
	}

	/**
	 * @param _descEnderecoPendencia atribui um valor 
	 * 		ao atributo descEnderecoPendencia
	 */
	public void setDescEnderecoPendencia(
			java.lang.String _descEnderecoPendencia) {
		this.descEnderecoPendencia = _descEnderecoPendencia;
	}
	
	/**
	 * @return O valor do atributo txtAssuntoPendencia
	 */
	public java.lang.String getTxtAssuntoPendencia() {
		return txtAssuntoPendencia;
	}

	/**
	 * @param _txtAssuntoPendencia atribui um valor 
	 * 		ao atributo txtAssuntoPendencia
	 */
	public void setTxtAssuntoPendencia(
			java.lang.String _txtAssuntoPendencia) {
		this.txtAssuntoPendencia = _txtAssuntoPendencia;
	}
	
	/**
	 * @return O valor do atributo indSituacaoPendencia
	 */
	public java.lang.Character getIndSituacaoPendencia() {
		return indSituacaoPendencia;
	}

	/**
	 * Retorna indSituacaoPendenciaFormatado.
	 * 
	 * @return String
	 */
	public java.lang.String getIndSituacaoPendenciaFormatado() {
		String retorno = "";
		if(UtilObjeto.isReferencia(getIndSituacaoPendencia()) && 
				getIndSituacaoPendencia().charValue() == Constante.DOCUMENTO_ATIVO){
			retorno = "Ativo";
		}else if(UtilObjeto.isReferencia(getIndSituacaoPendencia()) && 
				getIndSituacaoPendencia().charValue() == Constante.DOCUMENTO_INATIVO) {
			retorno = "Inativo";
		}
		return retorno;
	}

	/**
	 * @param _indSituacaoPendencia atribui um valor 
	 * 		ao atributo indSituacaoPendencia
	 */
	public void setIndSituacaoPendencia(
			java.lang.Character _indSituacaoPendencia) {
		this.indSituacaoPendencia = _indSituacaoPendencia;
	}
	
	/**
	 * @return O valor do atributo saapHistPendenciaAdms;
	 */
	public java.util.Collection<SaapHistPendenciaAdm> getSaapHistPendenciaAdms() {
		return saapHistPendenciaAdms;
	}

	/**
	 * @param _saapHistPendenciaAdms atribui um valor 
	 * 		ao atributo this.saapHistPendenciaAdms
	 */
	public void setSaapHistPendenciaAdms(
			java.util.Collection<SaapHistPendenciaAdm> _saapHistPendenciaAdms) {
		this.saapHistPendenciaAdms = _saapHistPendenciaAdms;
	}
	
	/**
	 * @return O valor do atributo saapAdmtAdmPresidencias;
	 */
	public java.util.Collection<SaapAdmtAdmPresidencia> getSaapAdmtAdmPresidencias() {
		return saapAdmtAdmPresidencias;
	}

	/**
	 * @param _saapAdmtAdmPresidencias atribui um valor 
	 * 		ao atributo this.saapAdmtAdmPresidencias
	 */
	public void setSaapAdmtAdmPresidencias(
			java.util.Collection<SaapAdmtAdmPresidencia> _saapAdmtAdmPresidencias) {
		this.saapAdmtAdmPresidencias = _saapAdmtAdmPresidencias;
	}
	
	/**
	 * @return O valor do atributo saapAcompPendenciaAdms;
	 */
	public java.util.Collection<SaapAcompPendenciaAdm> getSaapAcompPendenciaAdms() {
		return saapAcompPendenciaAdms;
	}

	/**
	 * @param _saapAcompPendenciaAdms atribui um valor 
	 * 		ao atributo this.saapAcompPendenciaAdms
	 */
	public void setSaapAcompPendenciaAdms(
			java.util.Collection<SaapAcompPendenciaAdm> _saapAcompPendenciaAdms) {
		this.saapAcompPendenciaAdms = _saapAcompPendenciaAdms;
	}

	/**
	 * Retorna registradoPor.
	 * 
	 * @return String
	 */
	public String getRegistradoPor() {
		return registradoPor;
	}

	/**
	 * Atribui registradoPor.
	 * 
	 * @param registradoPor registradoPor
	 */
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}
}
 