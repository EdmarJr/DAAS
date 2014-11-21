package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;

@Entity
@Table(schema="saad",name = "ENDERECAMENTO_EXTERNO")
@PrimaryKeyJoinColumn(name = "SQ_ENDERECAMENTO", referencedColumnName = "SQ_ENDERECAMENTO")
public class EnderecamentoExterno extends Enderecamento implements Serializable {

	private static final long serialVersionUID = -30525540839827870L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_OCUPACAO")
	private DestinatarioOcupacao ocupacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_ORGAO_MALA")
	private Orgao orgao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_CARGO_MALA")
	private Cargo cargo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_DESTINATARIO")
	private Destinatario destinatario;

	public EnderecamentoExterno() {
		super();
		super.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_MALA_DIRETA.getValor());
	}

	public DestinatarioOcupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(DestinatarioOcupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

}
