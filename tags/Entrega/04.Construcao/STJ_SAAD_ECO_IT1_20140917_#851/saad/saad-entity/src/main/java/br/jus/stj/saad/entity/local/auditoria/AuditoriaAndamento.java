package br.jus.stj.saad.entity.local.auditoria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.local.AndamentoDocumento;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Usuario;

@Entity
@Table(schema = "saad", name = "AUDIT_ANDAMENTO_DOCUMENTO")
@EntityListeners(EntidadeComDataInclusaoListener.class)
public class AuditoriaAndamento extends EntidadeBase implements
		EntidadeComDataInclusao {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUDIT_ANDAMENTO_DOCUMENTO")
	private Long sqAuditoriaAndamento;
	
	@Column(name="SEQ_USUARIO")
	private Long sqUsuario;
	
	@Column(name="DH_OPERACAO")
	private Date dhOperacao;
	
	@Column(name="TP_OPERACAO")
	private Character tpOperacao;

	@Column(name="SQ_ANDAMENTO_DOCUMENTO")
	private Long sqAndamento;
	
	@Column(name="SQ_DOCUMENTO_UNIDADE")
	private Long sqDocumento;
	
	@Column(name="DS_ANDAMENTO_DOCUMENTO")
	private String descricaoAndamentoDocumento;
	
	@Column(name="DH_ANDAMENTO_DOCUMENTO")
	private Date dhAndamentoDocumento;

	public AuditoriaAndamento() {

	}

	public AuditoriaAndamento(Usuario usuario, Character tpOperacao,
			AndamentoDocumento andamentoDocumento, Documento documento) {

		this.sqUsuario = usuario.getId();
		this.tpOperacao = tpOperacao;
		this.sqAndamento = andamentoDocumento.getId();
		this.sqDocumento = documento.getId();
		this.descricaoAndamentoDocumento = andamentoDocumento
				.getDescricaoAndamentoDocumento();
		this.dhAndamentoDocumento = andamentoDocumento.getAndamentoDocumento();

	}

	@Override
	public Long getId() {
		return sqAuditoriaAndamento;
	}

	@Override
	public void setDataInclusao(Date data) {
		dhOperacao = data;
	}
	
	public Long getSqAuditoriaAndamento() {
		return sqAuditoriaAndamento;
	}

	public void setSqAuditoriaAndamento(Long sqAuditoriaAndamento) {
		this.sqAuditoriaAndamento = sqAuditoriaAndamento;
	}

	public Long getSqUsuario() {
		return sqUsuario;
	}

	public void setSqUsuario(Long sqUsuario) {
		this.sqUsuario = sqUsuario;
	}

	public Date getDhOperacao() {
		return dhOperacao;
	}

	public void setDhOperacao(Date dhOperacao) {
		this.dhOperacao = dhOperacao;
	}

	public Character getTpOperacao() {
		return tpOperacao;
	}

	public void setTpOperacao(Character tpOperacao) {
		this.tpOperacao = tpOperacao;
	}

	public Long getSqAndamento() {
		return sqAndamento;
	}

	public void setSqAndamento(Long sqAndamento) {
		this.sqAndamento = sqAndamento;
	}

	public Long getSqDocumento() {
		return sqDocumento;
	}

	public void setSqDocumento(Long sqDocumento) {
		this.sqDocumento = sqDocumento;
	}

	public String getDescricaoAndamentoDocumento() {
		return descricaoAndamentoDocumento;
	}

	public void setDescricaoAndamentoDocumento(String descricaoAndamentoDocumento) {
		this.descricaoAndamentoDocumento = descricaoAndamentoDocumento;
	}

	public Date getDhAndamentoDocumento() {
		return dhAndamentoDocumento;
	}

	public void setDhAndamentoDocumento(Date dhAndamentoDocumento) {
		this.dhAndamentoDocumento = dhAndamentoDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((sqAuditoriaAndamento == null) ? 0 : sqAuditoriaAndamento
						.hashCode());
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
		AuditoriaAndamento other = (AuditoriaAndamento) obj;
		if (sqAuditoriaAndamento == null) {
			if (other.sqAuditoriaAndamento != null)
				return false;
		} else if (!sqAuditoriaAndamento.equals(other.sqAuditoriaAndamento))
			return false;
		return true;
	}

}
