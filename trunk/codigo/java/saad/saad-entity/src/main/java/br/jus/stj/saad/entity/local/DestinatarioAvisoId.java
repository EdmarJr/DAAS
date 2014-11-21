package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DestinatarioAvisoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAviso;
	
	private Long idUsuario;

	public Long getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(Long idAviso) {
		this.idAviso = idAviso;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAviso == null) ? 0 : idAviso.hashCode());
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		DestinatarioAvisoId other = (DestinatarioAvisoId) obj;
		if (idAviso == null) {
			if (other.idAviso != null)
				return false;
		} else if (!idAviso.equals(other.idAviso))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	
	
}
