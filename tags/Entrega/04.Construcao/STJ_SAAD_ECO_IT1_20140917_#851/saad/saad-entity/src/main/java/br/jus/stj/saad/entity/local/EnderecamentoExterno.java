package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.jus.stj.saad.entity.related.Destinatario;

/**
 * Entity implementation class for Entity: EnderecamentoExterno
 * 
 */
@Entity
@Table(schema="saad",name = "ENDERECAMENTO_EXTERNO")
@PrimaryKeyJoinColumn(name = "SQ_ENDERECAMENTO", referencedColumnName = "SQ_ENDERECAMENTO")
public class EnderecamentoExterno extends Enderecamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -30525540839827870L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQ_DESTINATARIO")
	private Destinatario destinatario;

	public EnderecamentoExterno() {
		super();
	}

	public Destinatario getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

}
