package br.jus.stj.saad.entity.related;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;

/**
 * @author zainer.silva
 *
 */
@Entity
@Table(schema = "MALADIR", name = "TRATAMENTO")
public class Tratamento extends EntidadeBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4089765789325916128L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_TRATAMENTO")
	private Long id;//PK
	
	@Column(name = "DESC_TRATAMENTO")
	private String nome;
	
	@Column(name = "IND_PUBLICO")
	private String indPublico;
	
	@Column(name = "SEQ_LOCAL")
	private Integer seqLocal;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the indPublico
	 */
	public String getIndPublico() {
		return indPublico;
	}

	/**
	 * @param indPublico the indPublico to set
	 */
	public void setIndPublico(String indPublico) {
		this.indPublico = indPublico;
	}

	/**
	 * @return the seqLocal
	 */
	public Integer getSeqLocal() {
		return seqLocal;
	}

	/**
	 * @param seqLocal the seqLocal to set
	 */
	public void setSeqLocal(Integer seqLocal) {
		this.seqLocal = seqLocal;
	}

	
}
