package br.jus.stj.saad.entity.local;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Usuario;

@Entity
@Table(schema="saad",name="DESTINATARIO_AVISO")
public class DestinatarioAviso extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DestinatarioAvisoId compositeId;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@ManyToOne
	@MapsId("idAviso")
	@JoinColumn(name="SQ_AVISO_UNIDADE")
	private Aviso aviso;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@ManyToOne(fetch=FetchType.EAGER)
	@MapsId("idUsuario")
	@JoinColumn(name="SEQ_USUARIO")
	private Usuario usuario;
	
	@Column(name="ST_AVISO_DESTINATARIO")
	private Character situacao;
	
	@Transient
	public Boolean ativo = false;
	
	@Transient
	public Boolean removido = false;
	
	public Character getSituacao() {
		return situacao;
	}


	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}


	public DestinatarioAviso() {
		super();
		this.compositeId = new DestinatarioAvisoId();
	}


	public Aviso getAviso() {
		return aviso;
	}

	public DestinatarioAviso setAviso(Aviso aviso) {
		this.aviso = aviso;
		return this;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public DestinatarioAviso setUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public DestinatarioAvisoId getCompositeId() {
		return compositeId;
	}

	public DestinatarioAviso setCompositeId(DestinatarioAvisoId compositeId) {
		this.compositeId = compositeId;
		return this;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((compositeId == null) ? 0 : compositeId.hashCode());
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
		DestinatarioAviso other = (DestinatarioAviso) obj;
		if (compositeId == null) {
			if (other.compositeId != null)
				return false;
		} else if (!compositeId.equals(other.compositeId))
			return false;
		return true;
	}


	@Transient
	@Override
	public Long getId() {
		return null;
	}

}
