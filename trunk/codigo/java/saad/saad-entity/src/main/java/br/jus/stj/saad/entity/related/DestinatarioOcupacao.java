package br.jus.stj.saad.entity.related;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema = "MALADIR", name = "DEST_OCUPACAO")
public class DestinatarioOcupacao extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_OCUPACAO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_ORGAO_MALA", nullable = true)
	private Orgao orgao;

	@ManyToOne
	@JoinColumn(name = "SEQ_CARGO_MALA", nullable = true)
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "SEQ_DESTINATARIO")
	private Destinatario destinatario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

}
