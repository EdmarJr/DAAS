package br.jus.stj.saad.entity.related;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocalUsuarioId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="SEQ_LOCAL", nullable=false)
	private Long idLocal;
	
	@Column(name="SEQ_USUARIO", nullable=false)
	private Long idUsuario;
	
	public LocalUsuarioId() {
		super();
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public LocalUsuarioId setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
		return this;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public LocalUsuarioId setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof LocalUsuarioId)) {
			return false;
		}
		LocalUsuarioId other  = (LocalUsuarioId)obj;
		return (this.hashCode() == other.hashCode());
	}
	
	@Override
	public int hashCode() {
		int hashIdLocal = (idLocal != null ? idLocal.hashCode() : 0);
		int hashIdUsuario = (idUsuario != null ? idUsuario.hashCode() : 0);
		return (hashIdLocal + hashIdUsuario);
	}
	
}
