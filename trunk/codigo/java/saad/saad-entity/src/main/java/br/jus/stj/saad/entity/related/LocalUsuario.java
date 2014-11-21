package br.jus.stj.saad.entity.related;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema="DB2SA",name="LOCAL_USUARIO")
public class LocalUsuario extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LocalUsuarioId compositeId;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SEQ_LOCAL", insertable=false, updatable=false)
	private Local local;
	
	/** RELACIONAMENTO APENAS PARA CONSULTA */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEQ_USUARIO", insertable=false, updatable=false)
	private Usuario usuario;
	
	@Column(name="IND_GESTOR_LOCAL")
	private Character indGestorLocal;
	
	@Column(name="IND_ESTATISTICA")
	private Character indEstatistica;
	
	@Column(name="IND_ESCANINHO")
	private Character indEscaninho;
	
	@Column(name="IND_USUARIO_BLOQUEADO")
	private Character indUsuarioBloqueado;
	
	public LocalUsuario() {
		super();
	}

	@Transient
	@Override
	public Long getId() {
		return null;
	}

	public LocalUsuarioId getCompositeId() {
		return compositeId;
	}

	public LocalUsuario setCompositeId(LocalUsuarioId compositeId) {
		this.compositeId = compositeId;
		return this;
	}

	public Local getLocal() {
		return local;
	}

	public LocalUsuario setLocal(Local local) {
		this.local = local;
		return this;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalUsuario setUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public Character getIndGestorLocal() {
		return indGestorLocal;
	}

	public LocalUsuario setIndGestorLocal(Character indGestorLocal) {
		this.indGestorLocal = indGestorLocal;
		return this;
	}

	public Character getIndEstatistica() {
		return indEstatistica;
	}

	public LocalUsuario setIndEstatistica(Character indEstatistica) {
		this.indEstatistica = indEstatistica;
		return this;
	}

	public Character getIndEscaninho() {
		return indEscaninho;
	}

	public LocalUsuario setIndEscaninho(Character indEscaninho) {
		this.indEscaninho = indEscaninho;
		return this;
	}

	public Character getIndUsuarioBloqueado() {
		return indUsuarioBloqueado;
	}

	public LocalUsuario setIndUsuarioBloqueado(Character indUsuarioBloqueado) {
		this.indUsuarioBloqueado = indUsuarioBloqueado;
		return this;
	}

}
