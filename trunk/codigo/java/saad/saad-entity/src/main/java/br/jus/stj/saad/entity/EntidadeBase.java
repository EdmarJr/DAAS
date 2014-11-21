package br.jus.stj.saad.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import br.jus.stj.commons.entity.interfaces.EntidadeComId;

@MappedSuperclass
public abstract class EntidadeBase implements Serializable,EntidadeComId {
	
	private static final long serialVersionUID = 4879102937328754160L;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		EntidadeBase other = (EntidadeBase) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	
}
