package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;

/**
 * Entity implementation class for Entity: Enderecamento
 * 
 */
@Entity
@Table(schema="saad",name = "ENDERECAMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Enderecamento extends EntidadeBase implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327445825981276805L;

	@Id
	@Column(name = "SQ_ENDERECAMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NM_ENDERECADO", columnDefinition = "VARCHAR(150)")
	private String nomeEnderecado;

	@Column(name = "TP_ENDERECAMENTO")
	private String tipoEnderecamento;
	
	
	
	public Enderecamento() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEnderecado() {
		return nomeEnderecado;
	}

	public void setNomeEnderecado(String nomeEnderecado) {
		this.nomeEnderecado = nomeEnderecado;
	}

	
	public String getTipoEnderecamento() {
		return tipoEnderecamento;
	}

	public void setTipoEnderecamento(String tipoEnderecamento) {
		this.tipoEnderecamento = tipoEnderecamento;
	}

	public Destinatario getDestinatario() {
		return ((EnderecamentoExterno) this).getDestinatario();
	}

	public void setDestinatario(Destinatario destinatario) {
		((EnderecamentoExterno) this).setDestinatario(destinatario);
	}

	public Local getLocal() {
		return ((EnderecamentoInterno) this).getLocal();
	}

	public void setLocal(Local local) {
		((EnderecamentoInterno) this).setLocal(local);
	}
	
	public Usuario getUsuario() {
		return ((EnderecamentoInterno) this).getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		((EnderecamentoInterno) this).setUsuario(usuario);
	}	
}
