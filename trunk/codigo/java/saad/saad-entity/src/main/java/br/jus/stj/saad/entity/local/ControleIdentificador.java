package br.jus.stj.saad.entity.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Local;

@Entity
@Table(schema = "saad", name = "CONTROLE_IDENTIFICADOR")
@IdClass(ControleIdentificadorId.class)
public class ControleIdentificador extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="SEQ_LOCAL")
	private Local local;
	
	@Id
	@Column(name = "NR_ANO")
	private Long ano;

	@Column(name = "VR_ULTIMO_NUMERO")
	private Long ultimoNumero;


	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Long getUltimoNumero() {
		return ultimoNumero;
	}

	public void setUltimoNumero(Long ultimoNumero) {
		this.ultimoNumero = ultimoNumero;
	}

	@Override
	public Long getId() {
		return new Long(hashCode());
	}
}
