package br.jus.stj.saad.entity.local;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.interfaces.IDestinatario;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Tratamento;

@Entity
@Table(schema = "saad", name = "DESTINATARIO_DOCUMENTO")
public class DestinatarioDocumento extends EntidadeBase implements IDestinatario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "SQ_DESTINATARIO_DOCUMENTO")
	private Documento documento;

	@Column(name = "NM_DESTINATARIO")
	private String nomeDestinatario;

	@Column(name = "NM_ENDERECO")
	private String nomeEndereco;

	@Column(name = "NM_CMPLT_ENDER")
	private String nomeComplementoEndereco;

	@Column(name = "NM_BAIRRO")
	private String nomeBairro;

	@Column(name = "NR_CEP")
	private String numeroCep;

	@Column(name = "NM_CIDADE")
	private String nomeCidade;

	@Column(name = "SG_UF")
	private String siglaUf;

	@Column(name = "NR_TELEFONE")
	private String numeroTelefone;

	@Column(name = "DT_ANIVERSARIO")
	private Date dataAniversario;

	@Column(name = "NM_EMAIL")
	private String email;

	@Column(name = "NM_TITULO")
	private String titulo;

	@Transient
	private String fax;

	@Transient
	private Local local;

	@Transient
	private Tratamento tratamento;

	@Transient
	private Character sexo;

	@Override
	public Long getId() {
		return documento.getId();
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public String getNomeComplementoEndereco() {
		return nomeComplementoEndereco;
	}

	public void setNomeComplementoEndereco(String nomeComplementoEndereco) {
		this.nomeComplementoEndereco = nomeComplementoEndereco;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNumeroCep() {
		return numeroCep;
	}

	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}


	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataAniversario == null) ? 0 : dataAniversario.hashCode());
		result = prime * result
				+ ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((nomeBairro == null) ? 0 : nomeBairro.hashCode());
		result = prime * result
				+ ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
		result = prime
				* result
				+ ((nomeComplementoEndereco == null) ? 0
						: nomeComplementoEndereco.hashCode());
		result = prime
				* result
				+ ((nomeDestinatario == null) ? 0 : nomeDestinatario.hashCode());
		result = prime * result
				+ ((nomeEndereco == null) ? 0 : nomeEndereco.hashCode());
		result = prime * result
				+ ((numeroCep == null) ? 0 : numeroCep.hashCode());
		result = prime * result
				+ ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((siglaUf == null) ? 0 : siglaUf.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result
				+ ((tratamento == null) ? 0 : tratamento.hashCode());
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
		DestinatarioDocumento other = (DestinatarioDocumento) obj;
		if (dataAniversario == null) {
			if (other.dataAniversario != null)
				return false;
		} else if (!dataAniversario.equals(other.dataAniversario))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (nomeBairro == null) {
			if (other.nomeBairro != null)
				return false;
		} else if (!nomeBairro.equals(other.nomeBairro))
			return false;
		if (nomeCidade == null) {
			if (other.nomeCidade != null)
				return false;
		} else if (!nomeCidade.equals(other.nomeCidade))
			return false;
		if (nomeComplementoEndereco == null) {
			if (other.nomeComplementoEndereco != null)
				return false;
		} else if (!nomeComplementoEndereco
				.equals(other.nomeComplementoEndereco))
			return false;
		if (nomeDestinatario == null) {
			if (other.nomeDestinatario != null)
				return false;
		} else if (!nomeDestinatario.equals(other.nomeDestinatario))
			return false;
		if (nomeEndereco == null) {
			if (other.nomeEndereco != null)
				return false;
		} else if (!nomeEndereco.equals(other.nomeEndereco))
			return false;
		if (numeroCep == null) {
			if (other.numeroCep != null)
				return false;
		} else if (!numeroCep.equals(other.numeroCep))
			return false;
		if (numeroTelefone == null) {
			if (other.numeroTelefone != null)
				return false;
		} else if (!numeroTelefone.equals(other.numeroTelefone))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (siglaUf == null) {
			if (other.siglaUf != null)
				return false;
		} else if (!siglaUf.equals(other.siglaUf))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (tratamento == null) {
			if (other.tratamento != null)
				return false;
		} else if (!tratamento.equals(other.tratamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DestinatarioDocumento [documento=" + documento
				+ ", nomeDestinatario=" + nomeDestinatario + ", nomeEndereco="
				+ nomeEndereco + ", nomeComplementoEndereco="
				+ nomeComplementoEndereco + ", nomeBairro=" + nomeBairro
				+ ", numeroCep=" + numeroCep + ", nomeCidade=" + nomeCidade
				+ ", siglaUf=" + siglaUf + ", numeroTelefone=" + numeroTelefone
				+ ", dataAniversario=" + dataAniversario + ", email=" + email
				+ ", titulo=" + titulo + ", fax=" + fax + ", local=" + local
				+ ", tratamento=" + tratamento + ", sexo=" + sexo + "]";
	}
	
	
	
	
	



}
