/*
 * SaapDocAdmPresidencia.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe que representa a entidade persistente <code>SAAP_DOC_ADM_PRESIDENCIA</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_DOC_ADM_PRESIDENCIA")
public class SaapDocAdmPresidencia implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_DOC_ADM")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "TS_ENTRADA_DOC", 
							  nullable = false, 
							  length = 8, 
							  unique = false)
	private java.util.Date tsEntradaDoc;

	@javax.persistence.Column(name = "NOME_LOCAL_FISICO_DOC", 
							  nullable = true, 
							  length = 20, 
							  unique = false)
	private java.lang.String nomeLocalFisicoDoc;

	@javax.persistence.Column(name = "DESC_DOC", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String descDoc;

	@javax.persistence.Column(name = "TXT_ASSUNTO_DOC", 
							  nullable = false, 
							  length = 500, 
							  unique = false)
	private java.lang.String txtAssuntoDoc;

	@javax.persistence.Column(name = "TXT_OBS_DOC", 
							  nullable = true, 
							  length = 500, 
							  unique = false)
	private java.lang.String txtObsDoc;

	@javax.persistence.Column(name = "NOME_RESP_CONVITE", 
							  nullable = true, 
							  length = 50, 
							  unique = false)
	private java.lang.String nomeRespConvite;

	@javax.persistence.Column(name = "DT_HR_EVENTO_CONVITE", 
							  nullable = true, 
							  length = 23, 
							  unique = false)
	private java.util.Date dtHrEventoConvite;

	@javax.persistence.Column(name = "IND_SITUACAO_DOC", 
							  nullable = false, 
							  length = 1, 
							  unique = false)
	private java.lang.Character indSituacaoDoc;

	@javax.persistence.Column(name = "IND_TIPO_DOC", 
							  nullable = false, 
							  length = 5, 
							  unique = false)
	private java.lang.Short indTipoDoc;
	

	
	/** Association. */
	@javax.persistence.OneToMany(mappedBy = "saapDocAdmPresidencia")
	private java.util.Collection<SaapAdmtAdmPresidencia> saapAdmtAdmPresidencias;

	@javax.persistence.OneToMany(mappedBy = "saapDocAdmPresidencia")
	private java.util.Collection<SaapHistDocAdm> saapHistDocAdms;

	@javax.persistence.OneToMany(mappedBy = "saapDocAdmPresidencia")
	private java.util.Collection<SaapArqDocAdm> saapArqDocAdms;

	/**
	 * @return O valor do atributo id
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param seqDocAdm atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqDocAdm) {
		this.id = seqDocAdm;
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
	 * @return O valor do atributo tsEntradaDoc
	 */
	public java.util.Date getTsEntradaDoc() {
		return tsEntradaDoc;
	}

	/**
	 * @param _tsEntradaDoc atribui um valor 
	 * 		ao atributo tsEntradaDoc
	 */
	public void setTsEntradaDoc(
			java.util.Date _tsEntradaDoc) {
		this.tsEntradaDoc = _tsEntradaDoc;
	}
	
	/**
	 * @return O valor do atributo nomeLocalFisicoDoc
	 */
	public java.lang.String getNomeLocalFisicoDoc() {
		return nomeLocalFisicoDoc;
	}

	/**
	 * @param _nomeLocalFisicoDoc atribui um valor 
	 * 		ao atributo nomeLocalFisicoDoc
	 */
	public void setNomeLocalFisicoDoc(
			java.lang.String _nomeLocalFisicoDoc) {
		this.nomeLocalFisicoDoc = _nomeLocalFisicoDoc;
	}
	
	/**
	 * @return O valor do atributo descDoc
	 */
	public java.lang.String getDescDoc() {
		return descDoc;
	}

	/**
	 * @param _descDoc atribui um valor 
	 * 		ao atributo descDoc
	 */
	public void setDescDoc(
			java.lang.String _descDoc) {
		this.descDoc = _descDoc;
	}
	
	/**
	 * @return O valor do atributo txtAssuntoDoc
	 */
	public java.lang.String getTxtAssuntoDoc() {
		return txtAssuntoDoc;
	}

	/**
	 * @param _txtAssuntoDoc atribui um valor 
	 * 		ao atributo txtAssuntoDoc
	 */
	public void setTxtAssuntoDoc(
			java.lang.String _txtAssuntoDoc) {
		this.txtAssuntoDoc = _txtAssuntoDoc;
	}
	
	/**
	 * @return O valor do atributo txtObsDoc
	 */
	public java.lang.String getTxtObsDoc() {
		return txtObsDoc;
	}

	/**
	 * @param _txtObsDoc atribui um valor 
	 * 		ao atributo txtObsDoc
	 */
	public void setTxtObsDoc(
			java.lang.String _txtObsDoc) {
		this.txtObsDoc = _txtObsDoc;
	}
	
	/**
	 * @return O valor do atributo nomeRespConvite
	 */
	public java.lang.String getNomeRespConvite() {
		return nomeRespConvite;
	}

	/**
	 * @param _nomeRespConvite atribui um valor 
	 * 		ao atributo nomeRespConvite
	 */
	public void setNomeRespConvite(
			java.lang.String _nomeRespConvite) {
		this.nomeRespConvite = _nomeRespConvite;
	}
	
	/**
	 * @return O valor do atributo dtEventoConvite
	 */
	public java.util.Date getDtHrEventoConvite() {
		return dtHrEventoConvite;
	}

	/**
	 * atribui um valor 
	 * 		ao atributo dtEventoConvite
	 * @param _dtHrEventoConvite java.util.Date
	 */
	public void setDtHrEventoConvite(
			java.util.Date _dtHrEventoConvite) {
		this.dtHrEventoConvite = _dtHrEventoConvite;
	}
	
	
	/**
	 * @return O valor do atributo indSituacaoDoc
	 */
	public java.lang.Character getIndSituacaoDoc() {
		return indSituacaoDoc;
	}

	/**
	 * @return String
	 */
	public java.lang.String getIndSituacaoDocFormatado() {
		
		String retorno = "";
		
		if (getIndSituacaoDoc() == null) {
			setIndSituacaoDoc(' ');
		}
		
		switch (getIndSituacaoDoc()){
		case 'A':
			retorno = "Ativo";
			break;
		case 'D':
			retorno = "Inativo";
			break;
		default:
			retorno = "";
		}
		
		return retorno;
	}

	/**
	 * @return java.lang.String 
	 */
	public java.lang.String getCssAtivoInativo(){
		
		String retorno = "";
		
		if (indSituacaoDoc.equals(Constante.DOCUMENTO_INATIVO)){
			retorno = "color:#b5b5b5;";
		}
		return retorno;
	}

	/**
	 * @return java.lang.String
	 */
	public java.lang.String getCssLinkAtivoInativo(){
		
		String retorno = "";	
		
		if (indSituacaoDoc.equals(Constante.DOCUMENTO_INATIVO)){
			retorno = "color:#b5b5b5;font-weight:bold;text-decoration:underline;";
		}
		return retorno;
	}

	/**
	 * @return boolean
	 */
	public boolean isInativo(){
		boolean retorno = false;
		if (indSituacaoDoc.equals(Constante.DOCUMENTO_INATIVO)){
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * @return boolean
	 */
	public boolean isDocumentoConvite(){
		boolean retorno = false;
		if (getIndTipoDoc()!=null && getIndTipoDoc()==Constante.TIPO_DOCUMENTO_CONVITE){
			retorno = true;
		}
		return retorno;
	}
	
	
	/**
	 * @param _indSituacaoDoc atribui um valor 
	 * 		ao atributo indSituacaoDoc
	 */
	public void setIndSituacaoDoc(
			java.lang.Character _indSituacaoDoc) {
		this.indSituacaoDoc = _indSituacaoDoc;
	}
	
	/**
	 * @return O valor do atributo indTipoDoc
	 */
	public java.lang.Short getIndTipoDoc() {
		return indTipoDoc;
	}

	/**
	 * @return String
	 */
	public java.lang.String getIndTipoDocFormatado() {
		String retorno = "";
		if (getIndTipoDoc() == null) {
			setIndTipoDoc(new java.lang.Short("0"));
		}
		switch (getIndTipoDoc()) {
		case Constante.TIPO_DOCUMENTO_OFICIO:
			retorno = "Ofício";	break;
		case Constante.TIPO_DOCUMENTO_AVISO:
			retorno = "Aviso";break;
		case Constante.TIPO_DOCUMENTO_CARTA:
			retorno = "Carta";break;
		case Constante.TIPO_DOCUMENTO_CONVITE:
			retorno = "Convite";break;
		case Constante.TIPO_DOCUMENTO_EMAIL:
			retorno = "Email";break;
		case Constante.TIPO_DOCUMENTO_MEMORANDO:
			retorno = "Memorando";break;
		case Constante.TIPO_DOCUMENTO_PROCESSO:
			retorno = "Processo";break;
		default:
			retorno = "";
		}
		
		return retorno;
	}
	
	
	/**
	 * @param _indTipoDoc atribui um valor 
	 * 		ao atributo indTipoDoc
	 */
	public void setIndTipoDoc(
			java.lang.Short _indTipoDoc) {
		this.indTipoDoc = _indTipoDoc;
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
	 * @return O valor do atributo saapHistDocAdms;
	 */
	public java.util.Collection<SaapHistDocAdm> getSaapHistDocAdms() {
		return saapHistDocAdms;
	}

	/**
	 * @param _saapHistDocAdms atribui um valor 
	 * 		ao atributo this.saapHistDocAdms
	 */
	public void setSaapHistDocAdms(
			java.util.Collection<SaapHistDocAdm> _saapHistDocAdms) {
		this.saapHistDocAdms = _saapHistDocAdms;
	}
	
	/**
	 * @return O valor do atributo saapArqDocAdms;
	 */
	public java.util.Collection<SaapArqDocAdm> getSaapArqDocAdms() {
		return saapArqDocAdms;
	}

	/**
	 * @param _saapArqDocAdms atribui um valor 
	 * 		ao atributo this.saapArqDocAdms
	 */
	public void setSaapArqDocAdms(
			java.util.Collection<SaapArqDocAdm> _saapArqDocAdms) {
		this.saapArqDocAdms = _saapArqDocAdms;
	}


	
	
	
}
 