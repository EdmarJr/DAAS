/*
 * DominioPk.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
 
package br.jus.stj.saap.entidade.chave;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Chave primaria da entidade Dominio.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Embeddable
public class DominioPk implements Serializable {
	
	@javax.persistence.Column(name = "nome_coluna")
	private java.lang.String nomeColuna;
		
	@javax.persistence.Column(name = "desc_codigo")
	private java.lang.String descCodigo;
		
	/**
	 * @return O valor do atributo nomeColuna
	 */
	public java.lang.String getNomeColuna() {
		return nomeColuna;
	}

	/**
	 * @param _nomeColuna atribui um valor ao atributo nomeColuna
	 */
	public void setNomeColuna(java.lang.String _nomeColuna) {
		this.nomeColuna = _nomeColuna;
	}
	
	/**
	 * @return O valor do atributo descCodigo
	 */
	public java.lang.String getDescCodigo() {
		return descCodigo;
	}

	/**
	 * @param _descCodigo atribui um valor ao atributo descCodigo
	 */
	public void setDescCodigo(java.lang.String _descCodigo) {
		this.descCodigo = _descCodigo;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)
            .append("nomeColuna", getNomeColuna())
            .append("descCodigo", getDescCodigo())
            .toString();
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
    public boolean equals(Object other) {
        if (this == other) {
        	return true;
        }
        if (!(other instanceof DominioPk)) {
        	return false;
        }
        DominioPk castOther = (DominioPk) other;
        return new EqualsBuilder()
            .append(this.getNomeColuna(), castOther.getNomeColuna())
            .append(this.getDescCodigo(), castOther.getDescCodigo())
            .isEquals();
    }

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNomeColuna())
            .append(getDescCodigo())
            .toHashCode();
    }
	
}
