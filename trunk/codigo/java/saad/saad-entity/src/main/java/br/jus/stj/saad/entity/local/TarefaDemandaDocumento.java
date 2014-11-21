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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoTarefaEnum;

@Entity
@Table(schema="saad",name="TAREFA_DEMANDA_DOCUMENTO")
@EntityListeners(EntidadeComDataInclusaoListener.class)
public class TarefaDemandaDocumento extends EntidadeBase implements EntidadeComDataInclusao,Cloneable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="SQ_TAREFA")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SQ_DOCUMENTO_UNIDADE",referencedColumnName="SQ_DOCUMENTO_UNIDADE")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name="SEQ_USUARIO",referencedColumnName="SEQ_USUARIO")
	private Usuario usuario;
	
	@Column(name="DS_TAREFA")
	private String descricaoTarefa;
	
	@Column(name="DT_LIMITE_CONCLUSAO")
	@Temporal(TemporalType.DATE)
	private Date dataLimiteConclusao;
	
	@Column(name="ST_TAREFA")
	private Character situacaoTarefa = SituacaoTarefaEnum.PENDENTE.getValor();
	
	@Column(name="DT_CONCLUSAO_TAREFA")
	@Temporal(TemporalType.DATE)
	private Date dataConclusaoTarefa;
	
	@Column(name="DH_INCLUSAO_TAREFA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusaoTarefa = new Date();
	
	public Date getDataInclusaoTarefa() {
		return dataInclusaoTarefa;
	}
	public void setDataInclusaoTarefa(Date dataInclusaoTarefa) {
		this.dataInclusaoTarefa = dataInclusaoTarefa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}
	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}
	public Date getDataLimiteConclusao() {
		return dataLimiteConclusao;
	}
	public void setDataLimiteConclusao(Date dataLimiteConclusao) {
		this.dataLimiteConclusao = dataLimiteConclusao;
	}
	public Character getSituacaoTarefa() {
		return situacaoTarefa;
	}
	public void setSituacaoTarefa(Character situacaoTarefa) {
		this.situacaoTarefa = situacaoTarefa;
	}
	public Date getDataConclusaoTarefa() {
		return dataConclusaoTarefa;
	}
	public void setDataConclusaoTarefa(Date dataConclusaoTarefa) {
		this.dataConclusaoTarefa = dataConclusaoTarefa;
	}
	@Override
	public void setDataInclusao(Date data) {
		setDataInclusao(data);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((dataConclusaoTarefa == null) ? 0 : dataConclusaoTarefa
						.hashCode());
		result = prime
				* result
				+ ((dataInclusaoTarefa == null) ? 0 : dataInclusaoTarefa
						.hashCode());
		result = prime
				* result
				+ ((dataLimiteConclusao == null) ? 0 : dataLimiteConclusao
						.hashCode());
		result = prime * result
				+ ((descricaoTarefa == null) ? 0 : descricaoTarefa.hashCode());
		result = prime * result
				+ ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((situacaoTarefa == null) ? 0 : situacaoTarefa.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		TarefaDemandaDocumento other = (TarefaDemandaDocumento) obj;
		if (dataConclusaoTarefa == null) {
			if (other.dataConclusaoTarefa != null)
				return false;
		} else if (!dataConclusaoTarefa.equals(other.dataConclusaoTarefa))
			return false;
		if (dataInclusaoTarefa == null) {
			if (other.dataInclusaoTarefa != null)
				return false;
		} else if (!dataInclusaoTarefa.equals(other.dataInclusaoTarefa))
			return false;
		if (dataLimiteConclusao == null) {
			if (other.dataLimiteConclusao != null)
				return false;
		} else if (!dataLimiteConclusao.equals(other.dataLimiteConclusao))
			return false;
		if (descricaoTarefa == null) {
			if (other.descricaoTarefa != null)
				return false;
		} else if (!descricaoTarefa.equals(other.descricaoTarefa))
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
		if (situacaoTarefa == null) {
			if (other.situacaoTarefa != null)
				return false;
		} else if (!situacaoTarefa.equals(other.situacaoTarefa))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
	
}
