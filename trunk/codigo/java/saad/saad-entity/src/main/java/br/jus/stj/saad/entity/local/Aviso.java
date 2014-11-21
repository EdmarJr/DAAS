package br.jus.stj.saad.entity.local;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;

@Entity
@Table(schema="saad",name="AVISO_UNIDADE")
@EntityListeners(EntidadeComDataInclusaoListener.class)
public class Aviso extends EntidadeBase implements EntidadeComDataInclusao {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SQ_AVISO_UNIDADE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TP_ENDERECAMENTO_AVISO")
	private Character tipoEnderecamento;
	
	@Column(name="DS_AVISO_UNIDADE")
	private String descricao;
	
	@Column(name="ST_AVISO_UNIDADE")
	private Character situacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEQ_LOCAL")
	private Local local;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEQ_USUARIO")
	private Usuario usuario;
	
	@OneToMany(mappedBy="aviso", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<DestinatarioAviso> destinatariosAviso;
	
	/** Join para obter número do documento e tipo de documento, quando existentes */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SQ_DOCUMENTO_UNIDADE")
	private Documento documento;
	
	@Column(name="DH_INCLUSAO")
	private Date dataInclusao;
	
	@Column(name="DH_RESOLUCAO")
	private Date dataResolucao;
	
	@Transient
	private String destinatariosToString;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public Character getTipoEnderecamento() {
		return tipoEnderecamento;
	}




	public void setTipoEnderecamento(Character tipoEnderecamento) {
		this.tipoEnderecamento = tipoEnderecamento;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public Character getSituacao() {
		return situacao;
	}




	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}




	public Local getLocal() {
		return local;
	}




	public void setLocal(Local local) {
		this.local = local;
	}




	public List<DestinatarioAviso> getDestinatariosAviso() {
		return destinatariosAviso;
	}




	public void setDestinatariosAviso(List<DestinatarioAviso> destinatariosAviso) {
		this.destinatariosAviso = destinatariosAviso;
	}




	public Documento getDocumento() {
		return documento;
	}




	public void setDocumento(Documento documento) {
		this.documento = documento;
	}




	public Date getDataInclusao() {
		return dataInclusao;
	}




	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}




	public Date getDataResolucao() {
		return dataResolucao;
	}




	public void setDataResolucao(Date dataResolucao) {
		this.dataResolucao = dataResolucao;
	}


	public String getDestinatariosToString() {
		return destinatariosToString;
	}




	public void setDestinatariosToString(String destinatariosToString) {
		this.destinatariosToString = destinatariosToString;
	}




	@Transient
	public String getNomesDestinatarios() {
		StringBuilder sb = new StringBuilder();
		if(destinatariosAviso != null && !destinatariosAviso.isEmpty()) {
			for(int i=0; i<destinatariosAviso.size(); i++) {
				DestinatarioAviso destinatarioAviso = destinatariosAviso.get(i);
				sb.append(destinatarioAviso.getUsuario().getNome()).append(i < (destinatariosAviso.size() - 1) ? ", " : ".");
			}
		}
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result
				+ ((dataResolucao == null) ? 0 : dataResolucao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((destinatariosAviso == null) ? 0 : destinatariosAviso
						.hashCode());
		result = prime * result
				+ ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime
				* result
				+ ((tipoEnderecamento == null) ? 0 : tipoEnderecamento
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
		Aviso other = (Aviso) obj;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataResolucao == null) {
			if (other.dataResolucao != null)
				return false;
		} else if (!dataResolucao.equals(other.dataResolucao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (destinatariosAviso == null) {
			if (other.destinatariosAviso != null)
				return false;
		} else if (!destinatariosAviso.equals(other.destinatariosAviso))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tipoEnderecamento == null) {
			if (other.tipoEnderecamento != null)
				return false;
		} else if (!tipoEnderecamento.equals(other.tipoEnderecamento))
			return false;
		return true;
	}
	
	public String obterListaDestinatariosFormatado() {
		String usuarios = "";
		
		if(this.getDestinatariosAviso() != null && this.getDestinatariosAviso().size() > 0){
			for (DestinatarioAviso destAviso : this.getDestinatariosAviso()) {
				if (this.getDestinatariosAviso().indexOf(destAviso) + 1 < this.getDestinatariosAviso().size()) {
					usuarios = usuarios.concat(destAviso.getUsuario().getNome()	+ ", ");
				} else {
					usuarios = usuarios.concat(destAviso.getUsuario().getNome() + " ");
				}
			}
		}else{
			usuarios = usuarios + "Todos";
		}

		return usuarios;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	
	
}
