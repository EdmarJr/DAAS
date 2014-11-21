package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;

/**
 * Entity implementation class for Entity: EnderecamentoInterno
 * 
 */
@Entity
@Table(schema="saad",name="ENDERECAMENTO_INTERNO")
@PrimaryKeyJoinColumn(name = "SQ_ENDERECAMENTO", referencedColumnName = "SQ_ENDERECAMENTO")
public class EnderecamentoInterno extends Enderecamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6555873939491666420L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_LOCAL")
	private Local local;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_USUARIO")
	private Usuario usuario;

	public EnderecamentoInterno() {
		super();
		super.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_INTERNO.getValor());
	}

	public Local getLocal() {
		return this.local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
