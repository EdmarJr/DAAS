package br.jus.stj.saad.entity.related;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.local.DestinatarioAviso;

@Entity
@Table(schema="DB2SA",name="USUARIO")
public class Usuario extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SEQ_USUARIO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private List<LocalUsuario> locaisUsuarios;
	
	@Column(name="NOME_USUARIO")
	private String nome;
	
	@Column(name="DESC_USERNAME")
	private String login;
	
	@OneToMany(mappedBy="usuario")
	private List<DestinatarioAviso> destinatariosAviso;
	
	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Usuario setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Usuario setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public List<DestinatarioAviso> getDestinatariosAviso() {
		return destinatariosAviso;
	}

	public Usuario setDestinatariosAviso(List<DestinatarioAviso> destinatariosAviso) {
		this.destinatariosAviso = destinatariosAviso;
		return this;
	}

	public List<LocalUsuario> getLocaisUsuarios() {
		return locaisUsuarios;
	}

	public Usuario setLocaisUsuarios(List<LocalUsuario> locaisUsuarios) {
		this.locaisUsuarios = locaisUsuarios;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	

}
