package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TipoDocumentoUnidadeId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idTipoDocumento;
	
	private Long idLocal;

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLocal == null) ? 0 : idLocal.hashCode());
		result = prime * result
				+ ((idTipoDocumento == null) ? 0 : idTipoDocumento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoDocumentoUnidadeId other = (TipoDocumentoUnidadeId) obj;
		if (idLocal == null) {
			if (other.idLocal != null)
				return false;
		} else if (!idLocal.equals(other.idLocal))
			return false;
		if (idTipoDocumento == null) {
			if (other.idTipoDocumento != null)
				return false;
		} else if (!idTipoDocumento.equals(other.idTipoDocumento))
			return false;
		return true;
	}




	
	


	

}
