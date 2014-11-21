/*
 * SaapArqDocAdm.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.entidade;

import java.io.Serializable;
import java.util.ResourceBundle;

import org.hibernate.annotations.GenericGenerator;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe que representa a entidade persistente <code>SAAP_ARQ_DOC_ADM</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SAAP_ARQ_DOC_ADM")
public class SaapArqDocAdm implements Entidade {

	/** Primary key. */
	@javax.persistence.Id
	@GenericGenerator(name="max", strategy="increment")
	@javax.persistence.GeneratedValue(generator="max")
	@javax.persistence.Column(name = "SEQ_ARQ_DOC")
	private java.lang.Integer id;

	/** Regular field. */
	@javax.persistence.Column(name = "NOME_ARQ_DOC", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String nomeArqDoc;

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
	 * @param seqArqDoc atribui um valor ao atributo id
	 */
	public void setId(java.lang.Integer seqArqDoc) {
		this.id = seqArqDoc;
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
	 * @return O valor do atributo nomeArqDoc
	 */
	public java.lang.String getNomeArqDoc() {
		return nomeArqDoc;
	}

	/**
	 * Retorna o nome do arquivo com o path.
	 * 
	 * @return String
	 */
	public java.lang.String getNomeArqDocComPath() {
		String caminho = getUrlServidor() + Constante.PREFIXO_DIRETORIO + 
				getSaapDocAdmPresidencia().getId()+ "/" + nomeArqDoc.trim();
		return caminho;
	}

	/**
	 * Obtem a URL do servidor de aquivos
	 * @return URL
	 */
	private String getUrlServidor() {
		
		ResourceBundle properties = ResourceBundle.getBundle("multimidia");
		
		String protocolo = properties.getObject("share.protocolo").toString();
		String host = properties.getObject("host").toString();
		String usuario = properties.getObject("share.usuario").toString();
		String senha = properties.getObject("share.senha").toString();
		String servidor = properties.getObject("servidor").toString();
		String diretorio = properties.getObject("share.diretorio").toString();
		
		return protocolo + host + ";" + usuario + ":" + senha + "@" + servidor + diretorio;
	}	
	

	/**
	 * @param _nomeArqDoc atribui um valor 
	 * 		ao atributo nomeArqDoc
	 */
	public void setNomeArqDoc(
			java.lang.String _nomeArqDoc) {
		this.nomeArqDoc = _nomeArqDoc;
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
