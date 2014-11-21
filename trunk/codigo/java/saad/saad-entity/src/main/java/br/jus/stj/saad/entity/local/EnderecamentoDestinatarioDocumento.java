package br.jus.stj.saad.entity.local;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema="saad",name="END_DESTDOCU")
public class EnderecamentoDestinatarioDocumento extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnderecamentoDestinatarioDocumentoId compositeId;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@OneToOne
	@MapsId("idEnderecamento")
	@JoinColumn(name="SQ_ENDERECAMENTO")
	private Enderecamento enderecamento;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@ManyToOne(fetch=FetchType.EAGER)
	@MapsId("idDestinatarioDocumento")
	@JoinColumn(name="SQ_DESTINATARIO_DOCUMENTO")
	private DestinatarioDocumento destinatarioDocumento;
	
	public EnderecamentoDestinatarioDocumento() {
		super();
		this.compositeId = new EnderecamentoDestinatarioDocumentoId();
	}
	
	@Transient
	@Override
	public Long getId() {
		return null;
	}

	public Enderecamento getEnderecamento() {
		return enderecamento;
	}

	public void setEnderecamento(Enderecamento enderecamento) {
		this.enderecamento = enderecamento;
	}

	public DestinatarioDocumento getDestinatarioDocumento() {
		return destinatarioDocumento;
	}

	public void setDestinatarioDocumento(DestinatarioDocumento destinatarioDocumento) {
		this.destinatarioDocumento = destinatarioDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((compositeId == null) ? 0 : compositeId.hashCode());
		result = prime
				* result
				+ ((destinatarioDocumento == null) ? 0 : destinatarioDocumento
						.hashCode());
		result = prime * result
				+ ((enderecamento == null) ? 0 : enderecamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecamentoDestinatarioDocumento other = (EnderecamentoDestinatarioDocumento) obj;
		if (compositeId == null) {
			if (other.compositeId != null)
				return false;
		} else if (!compositeId.equals(other.compositeId))
			return false;
		if (destinatarioDocumento == null) {
			if (other.destinatarioDocumento != null)
				return false;
		} else if (!destinatarioDocumento.equals(other.destinatarioDocumento))
			return false;
		if (enderecamento == null) {
			if (other.enderecamento != null)
				return false;
		} else if (!enderecamento.equals(other.enderecamento))
			return false;
		return true;
	}

}
