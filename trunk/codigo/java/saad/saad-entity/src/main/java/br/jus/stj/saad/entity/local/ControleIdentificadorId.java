package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ControleIdentificadorId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long local;
	private Long ano;

	public Long getLocal() {
		return local;
	}

	public void setLocal(Long local) {
		this.local = local;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
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
		ControleIdentificadorId other = (ControleIdentificadorId) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		return true;
	}

}
