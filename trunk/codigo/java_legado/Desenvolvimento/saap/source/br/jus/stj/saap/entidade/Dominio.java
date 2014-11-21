/*
 * Dominio.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade;

import java.io.Serializable;

import br.jus.stj.alp01.arquitetura.entidade.Entidade;

import br.jus.stj.saap.entidade.chave.DominioPk;

/**
 * Classe que representa a entidade persistente <code>DOMINIO</code>.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "DOMINIO")
public class Dominio implements Entidade {

	/** Primary key. */
	@javax.persistence.EmbeddedId
	private DominioPk id;
	
	/** Regular field. */
	@javax.persistence.Column(name = "desc_estado", 
							  nullable = false, 
							  length = 100, 
							  unique = false)
	private java.lang.String descEstado;

	@javax.persistence.Column(name = "ind_st_dominio", 
							  nullable = false, 
							  length = 5, 
							  unique = false)
	private java.lang.String indStDominio;

	/** Association. */
	/** 
	 *Constroi o objeto e inicializa a chave primaria.
	 */
	public Dominio() {
		id = new DominioPk();
	}

	/**
	 * @return O valor do atributo id
	 */
	public DominioPk getId() {
		return id;
	}

	/**
	 * @param primaryKey atribui um valor ao atributo id
	 */
	public void setId(DominioPk primaryKey) {
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
	 * @return O valor do atributo descEstado
	 */
	public java.lang.String getDescEstado() {
		return descEstado;
	}

	/**
	 * @param _descEstado atribui um valor 
	 * 		ao atributo descEstado
	 */
	public void setDescEstado(
			java.lang.String _descEstado) {
		this.descEstado = _descEstado;
	}
	
	/**
	 * @return O valor do atributo indStDominio
	 */
	public java.lang.String getIndStDominio() {
		return indStDominio;
	}

	/**
	 * @param _indStDominio atribui um valor 
	 * 		ao atributo indStDominio
	 */
	public void setIndStDominio(
			java.lang.String _indStDominio) {
		this.indStDominio = _indStDominio;
	}
	
}
 