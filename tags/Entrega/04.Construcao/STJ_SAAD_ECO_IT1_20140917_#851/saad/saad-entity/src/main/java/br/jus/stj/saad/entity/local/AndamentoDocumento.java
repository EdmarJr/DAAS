package br.jus.stj.saad.entity.local;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Usuario;


@Entity
@Table(schema="saad",name="ANDAMENTO_DOCUMENTO")
@EntityListeners(EntidadeComDataInclusaoListener.class)
public class AndamentoDocumento extends EntidadeBase implements EntidadeComDataInclusao, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SQ_ANDAMENTO_DOCUMENTO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SQ_DOCUMENTO_UNIDADE")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name="SEQ_USUARIO")
	private Usuario usuario;
	
	@Column(name="DS_ANDAMENTO_DOCUMENTO")
	private String descricaoAndamentoDocumento;
	
	@Column(name="DH_ANDAMENTO_DOCUMENTO")
	private Date andamentoDocumento;
	

	@Override
	public Long getId() {
		return id;
	}


	public Documento getDocumento() {
		return documento;
	}


	public void setDocumento(Documento documento) {
		this.documento = documento;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getDescricaoAndamentoDocumento() {
		return descricaoAndamentoDocumento;
	}


	public void setDescricaoAndamentoDocumento(String descricaoAndamentoDocumento) {
		this.descricaoAndamentoDocumento = descricaoAndamentoDocumento;
	}


	public Date getAndamentoDocumento() {
		return andamentoDocumento;
	}


	public void setAndamentoDocumento(Date andamentoDocumento) {
		this.andamentoDocumento = andamentoDocumento;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setDataInclusao(Date data) {
		setAndamentoDocumento(data);
	}
	
	@Override
	public Object clone() {
		
		try {
			
			return super.clone();
			
		} catch (CloneNotSupportedException e) {

			return null;
	
		}
		
	}
	

}
