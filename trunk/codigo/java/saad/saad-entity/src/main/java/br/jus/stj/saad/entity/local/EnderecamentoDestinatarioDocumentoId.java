package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EnderecamentoDestinatarioDocumentoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEnderecamento;
	
	private Long idDestinatarioDocumento;

	public Long getIdEnderecamento() {
		return idEnderecamento;
	}

	public void setIdEnderecamento(Long idEnderecamento) {
		this.idEnderecamento = idEnderecamento;
	}

	public Long getIdDestinatarioDocumento() {
		return idDestinatarioDocumento;
	}

	public void setIdDestinatarioDocumento(Long idDestinatarioDocumento) {
		this.idDestinatarioDocumento = idDestinatarioDocumento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idDestinatarioDocumento == null) ? 0
						: idDestinatarioDocumento.hashCode());
		result = prime * result
				+ ((idEnderecamento == null) ? 0 : idEnderecamento.hashCode());
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
		EnderecamentoDestinatarioDocumentoId other = (EnderecamentoDestinatarioDocumentoId) obj;
		if (idDestinatarioDocumento == null) {
			if (other.idDestinatarioDocumento != null)
				return false;
		} else if (!idDestinatarioDocumento
				.equals(other.idDestinatarioDocumento))
			return false;
		if (idEnderecamento == null) {
			if (other.idEnderecamento != null)
				return false;
		} else if (!idEnderecamento.equals(other.idEnderecamento))
			return false;
		return true;
	}
	
}
