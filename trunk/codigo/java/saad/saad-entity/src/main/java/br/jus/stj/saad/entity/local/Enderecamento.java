package br.jus.stj.saad.entity.local;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.entity.related.Usuario;

/**
 * Entity implementation class for Entity: Enderecamento
 * 
 */
@Entity
@Table(schema="saad",name = "ENDERECAMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
public class Enderecamento extends EntidadeBase implements
		Serializable {

	private static final long serialVersionUID = 1327445825981276805L;

	@Id
	@Column(name = "SQ_ENDERECAMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TP_ENDERECAMENTO")
	private String tipoEnderecamento;
	
	@OneToOne(mappedBy = "enderecamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private EnderecamentoDestinatarioDocumento enderecamentoDestinatarioDocumento;
	
	@Transient
	private Boolean utilizado = false;
	
	public Enderecamento() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoEnderecamento() {
		return tipoEnderecamento;
	}

	public void setTipoEnderecamento(String tipoEnderecamento) {
		this.tipoEnderecamento = tipoEnderecamento;
	}

		public Local getLocal() {
		return ((EnderecamentoInterno) this).getLocal();
	}

	public void setLocal(Local local) {
		((EnderecamentoInterno) this).setLocal(local);
	}
	
	public Usuario getUsuario() {
		return ((EnderecamentoInterno) this).getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		this.utilizado = true;
		((EnderecamentoInterno) this).setUsuario(usuario);
	}
	
	public DestinatarioOcupacao getOcupacao() {
		return ((EnderecamentoExterno) this).getOcupacao();
	}

	public void setOcupacao(DestinatarioOcupacao ocupacao) {
		((EnderecamentoExterno) this).setOcupacao(ocupacao);
	}

	public Orgao getOrgao() {
		return ((EnderecamentoExterno) this).getOrgao();
	}

	public void setOrgao(Orgao orgao) {
		((EnderecamentoExterno) this).setOrgao(orgao);
	}

	public Cargo getCargo() {
		return ((EnderecamentoExterno) this).getCargo();
	}

	public void setCargo(Cargo cargo) {
		((EnderecamentoExterno) this).setCargo(cargo);
	}

	public Destinatario getDestinatario() {
		this.utilizado = true;
		return ((EnderecamentoExterno) this).getDestinatario();
	}

	public void setDestinatario(Destinatario destinatario) {
		((EnderecamentoExterno) this).setDestinatario(destinatario);
	}

	public EnderecamentoDestinatarioDocumento getEnderecamentoDestinatarioDocumento() {
		return enderecamentoDestinatarioDocumento;
	}

	public void setEnderecamentoDestinatarioDocumento(
			EnderecamentoDestinatarioDocumento enderecamentoDestinatarioDocumento) {
		this.enderecamentoDestinatarioDocumento = enderecamentoDestinatarioDocumento;
	}

	public Boolean getUtilizado() {
		return utilizado;
	}

	public void setUtilizado(Boolean utilizado) {
		this.utilizado = utilizado;
	}
	
	public String getNomeEnderecado(){
		if(this instanceof EnderecamentoExterno){
			return getDestinatario().getNome();
		}else if( this instanceof EnderecamentoInterno){
			return getUsuario().getNome(); 
		}else{
			return getEnderecamentoDestinatarioDocumento().getDestinatarioDocumento().getNome();
		}
	}
}
