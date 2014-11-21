/*
 * SaapAdmtAdmPresidenciaPk.java
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
 * Chave primaria da entidade SaapAdmtAdmPresidencia.
 *
 * @author Politec Informática S/A
 */
@javax.persistence.Embeddable
public class SaapAdmtAdmPresidenciaPk implements Serializable {
	
	
	@javax.persistence.Column(name = "SEQ_ADMT_ADM")
	private java.lang.Integer seqAdmtAdm;
		
	@javax.persistence.Column(name = "IND_TIPO_ADMT_ADM")
	private java.lang.Character indTipoAdmtAdm;
		
	/**
	 * @return O valor do atributo seqAdmtAdm
	 */
	public java.lang.Integer getSeqAdmtAdm() {
		return seqAdmtAdm;
	}

	/**
	 * @param _seqAdmtAdm atribui um valor ao atributo seqAdmtAdm
	 */
	public void setSeqAdmtAdm(java.lang.Integer _seqAdmtAdm) {
		this.seqAdmtAdm = _seqAdmtAdm;
	}
	
	/**
	 * @return O valor do atributo indTipoAdmtAdm
	 */
	public java.lang.Character getIndTipoAdmtAdm() {
		return indTipoAdmtAdm;
	}

	/**
	 * @param _indTipoAdmtAdm atribui um valor ao atributo indTipoAdmtAdm
	 */
	public void setIndTipoAdmtAdm(java.lang.Character _indTipoAdmtAdm) {
		this.indTipoAdmtAdm = _indTipoAdmtAdm;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)
            .append("seqAdmtAdm", getSeqAdmtAdm())
            .append("indTipoAdmtAdm", getIndTipoAdmtAdm())
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
        if (!(other instanceof SaapAdmtAdmPresidenciaPk)) {
        	return false;
        }
        SaapAdmtAdmPresidenciaPk castOther = (SaapAdmtAdmPresidenciaPk) other;
        return new EqualsBuilder()
            .append(this.getSeqAdmtAdm(), castOther.getSeqAdmtAdm())
            .append(this.getIndTipoAdmtAdm(), castOther.getIndTipoAdmtAdm())
            .isEquals();
    }

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSeqAdmtAdm())
            .append(getIndTipoAdmtAdm())
            .toHashCode();
    }
	
}
