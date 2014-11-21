package br.jus.stj.saad.entity.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Local;

@Entity
@Table(schema = "saad", name = "CONTROLE_NUMERACAO")
@IdClass(ControleNumeracaoId.class)
public class ControleNumeracao extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "SQ_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;

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
	

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public Long getId() {
		return new Long(hashCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		result = prime * result
				+ ((ultimoNumero == null) ? 0 : ultimoNumero.hashCode());
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
		ControleNumeracao other = (ControleNumeracao) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		if (ultimoNumero == null) {
			if (other.ultimoNumero != null)
				return false;
		} else if (!ultimoNumero.equals(other.ultimoNumero))
			return false;
		return true;
	}
	
	


}
