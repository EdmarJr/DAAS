package br.jus.stj.saad.entity.related;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema = "DB2SA", name = "LOCAL")
public class Local extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEQ_LOCAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "local")
	private List<LocalUsuario> locaisUsuarios;

	@Column(name = "NOME_LOCAL", columnDefinition = "VARCHAR(150)")
	private String nome;
	
	@Column(name="IND_LOCAL_ATIVO")
	private Character ativo;
	
	@Column(name="IND_ESCANINHO")
	private Character escaninho;
	
	@ManyToOne
	@JoinColumn(name="SEQ_LOCAL_SUPERIOR")
	private Local localSuperior;
	
	@Column(name="IND_ORGANOGRAMA")
	private Character organograma;

	public Local() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Local setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Local setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public List<LocalUsuario> getLocaisUsuarios() {
		return locaisUsuarios;
	}

	public Local setLocaisUsuarios(List<LocalUsuario> locaisUsuarios) {
		this.locaisUsuarios = locaisUsuarios;
		return this;
	}

	public Character getAtivo() {
		return ativo;
	}

	public void setAtivo(Character ativo) {
		this.ativo = ativo;
	}

	public Character getEscaninho() {
		return escaninho;
	}

	public void setEscaninho(Character escaninho) {
		this.escaninho = escaninho;
	}

	public Local getLocalSuperior() {
		return localSuperior;
	}

	public void setLocalSuperior(Local localSuperior) {
		this.localSuperior = localSuperior;
	}

	public Character getOrganograma() {
		return organograma;
	}

	public void setOrganograma(Character organograma) {
		this.organograma = organograma;
	}
	
	

}
