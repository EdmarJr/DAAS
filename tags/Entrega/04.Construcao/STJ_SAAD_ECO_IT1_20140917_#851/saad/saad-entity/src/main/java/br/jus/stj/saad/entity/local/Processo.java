package br.jus.stj.saad.entity.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema = "db2sa", name = "PROCESSO")
public class Processo extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NUM_REGISTRO")
	private String numeroRegistro;

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	@Override
	public Long getId() {
		return getNumeroRegistro() != null ? new Long(getNumeroRegistro().hashCode()) : null;
	}

	
	
	
}
