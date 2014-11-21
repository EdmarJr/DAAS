package br.jus.stj.saad.entity.local.auditoria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Usuario;

@Entity
@Table(schema = "saad", name = "AUDIT_DOCUMENTO_UNIDADE")
@EntityListeners(EntidadeComDataInclusaoListener.class)
public class AuditoriaDocumento extends EntidadeBase implements
		EntidadeComDataInclusao {

	private static final long serialVersionUID = 1L;

	public AuditoriaDocumento() {

	}

	public AuditoriaDocumento(Documento documento, Usuario usuario,
			Character tipoOperacao) {
		super();
		this.idUsuario = usuario.getId();
		this.idDocumento = documento.getId();
		this.idLocal = usuario.getLocaisUsuarios().get(0).getId();
		this.idEnderecado = documento.getEnderecamentoRemetente() != null ? documento
				.getEnderecamentoRemetente().getId() : null;
		this.idTipoDocumento = documento.getTipo().getId();
		this.tipoOperacao = tipoOperacao;
		this.numero = documento.getNumeroDocumentoOrigem();
		this.numeroRegistro = documento.getNumeroRegistro();
		this.tipoClassificacaoDocumento = documento
				.getTipoClassificacaoDocumento();
		this.tipoOrigem = documento.getTipoOrigem();
		this.identificacaoDocumentoSTJ = documento
				.getNumeroControleIdentificador();
		this.dataOrigemDocumento = documento.getDataOrigemDocumento();
		this.dataRecebimentoExpedicao = documento.getDataRecebimentoExpedicao();
		this.dataInclusaoDocumento = documento.getDataInclusao();
		this.numeroProcessoAdministrativo = documento
				.getNumeroProcessoAdministrativo();
		this.assuntoDocumento = documento.getAssuntoDocumento();
		this.situacaoDocumentoEvento = documento.getSituacaoDocumentoEvento();
		this.situacao = documento.getSituacao();
		this.dataOperacao = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SQ_AUDIT_DOCUMENTO_UNIDADE")
	private Long id;

	@Column(name = "SEQ_USUARIO")
	private Long idUsuario;

	@Column(name = "SQ_DOCUMENTO_UNIDADE")
	private Long idDocumento;

	@Column(name = "SEQ_LOCAL")
	private Long idLocal;

	@Column(name = "SQ_ENDERECAMENTO")
	private Long idEnderecado;

	@Column(name = "SQ_TIPO_DOCUMENTO_UNIDADE")
	private Long idTipoDocumento;

	@Column(name = "SEQ_DESTINATARIO")
	private Long idDestinatario;

	@Column(name = "DH_OPERACAO")
	private Date dataOperacao;

	@Column(name = "TP_OPERACAO")
	private Character tipoOperacao;

	@Column(name = "NR_DOCUMENTO_ORIGEM")
	private String numero;

	@Column(name = "NUM_REGISTRO", nullable = true)
	private String numeroRegistro;

	@Column(name = "TP_CLASSIFICACAO_DOCUMENTO", nullable = true)
	private Character tipoClassificacaoDocumento;

	@Column(name = "TP_ORIGEM", nullable = true)
	private Character tipoOrigem;

	@Column(name = "ID_DOCUMENTO_STJ", nullable = true)
	private String identificacaoDocumentoSTJ;

	@Column(name = "DH_DOCUMENTO_ORIGEM", nullable = true)
	private Date dataOrigemDocumento;

	@Column(name = "DH_RECEBIMENTO_EXPEDICAO", nullable = true)
	private Date dataRecebimentoExpedicao = new Date();

	@Column(name = "DH_INCLUSAO")
	private Date dataInclusaoDocumento;

	@Column(name = "NR_PROCESSO_ADMINISTRATIVO", nullable = true)
	private String numeroProcessoAdministrativo;

	@Column(name = "TX_ASSUNTO_DOCUMENTO", nullable = true)
	private String assuntoDocumento;

	@Column(name = "ST_DOCUMENTO_EVENTO", nullable = true)
	private Character situacaoDocumentoEvento;

	@Column(name = "ST_SITUACAO_DOCUMENTO")
	private Character situacao;

	@Transient
	private String nomeUsuario;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Character getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Character tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataInclusaoDocumento() {
		return dataInclusaoDocumento;
	}

	public void setDataInclusaoDocumento(Date dataInclusaoDocumento) {
		this.dataInclusaoDocumento = dataInclusaoDocumento;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Character getTipoClassificacaoDocumento() {
		return tipoClassificacaoDocumento;
	}

	public void setTipoClassificacaoDocumento(
			Character tipoClassificacaoDocumento) {
		this.tipoClassificacaoDocumento = tipoClassificacaoDocumento;
	}

	public Character getTipoOrigem() {
		return tipoOrigem;
	}

	public void setTipoOrigem(Character tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	public String getIdentificacaoDocumentoSTJ() {
		return identificacaoDocumentoSTJ;
	}

	public void setIdentificacaoDocumentoSTJ(String identificacaoDocumentoSTJ) {
		this.identificacaoDocumentoSTJ = identificacaoDocumentoSTJ;
	}

	public Date getDataOrigemDocumento() {
		return dataOrigemDocumento;
	}

	public void setDataOrigemDocumento(Date dataOrigemDocumento) {
		this.dataOrigemDocumento = dataOrigemDocumento;
	}

	public Date getDataRecebimentoExpedicao() {
		return dataRecebimentoExpedicao;
	}

	public void setDataRecebimentoExpedicao(Date dataRecebimentoExpedicao) {
		this.dataRecebimentoExpedicao = dataRecebimentoExpedicao;
	}

	public String getNumeroProcessoAdministrativo() {
		return numeroProcessoAdministrativo;
	}

	public void setNumeroProcessoAdministrativo(
			String numeroProcessoAdministrativo) {
		this.numeroProcessoAdministrativo = numeroProcessoAdministrativo;
	}

	public String getAssuntoDocumento() {
		return assuntoDocumento;
	}

	public void setAssuntoDocumento(String assuntoDocumento) {
		this.assuntoDocumento = assuntoDocumento;
	}

	public Character getSituacaoDocumentoEvento() {
		return situacaoDocumentoEvento;
	}

	public void setSituacaoDocumentoEvento(Character situacaoDocumentoEvento) {
		this.situacaoDocumentoEvento = situacaoDocumentoEvento;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	@Override
	public void setDataInclusao(Date data) {
		setDataOperacao(data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((assuntoDocumento == null) ? 0 : assuntoDocumento.hashCode());
		result = prime
				* result
				+ ((dataInclusaoDocumento == null) ? 0 : dataInclusaoDocumento
						.hashCode());
		result = prime * result
				+ ((dataOperacao == null) ? 0 : dataOperacao.hashCode());
		result = prime
				* result
				+ ((dataOrigemDocumento == null) ? 0 : dataOrigemDocumento
						.hashCode());
		result = prime
				* result
				+ ((dataRecebimentoExpedicao == null) ? 0
						: dataRecebimentoExpedicao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime
				* result
				+ ((identificacaoDocumentoSTJ == null) ? 0
						: identificacaoDocumentoSTJ.hashCode());
		result = prime * result
				+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime
				* result
				+ ((numeroProcessoAdministrativo == null) ? 0
						: numeroProcessoAdministrativo.hashCode());
		result = prime * result
				+ ((numeroRegistro == null) ? 0 : numeroRegistro.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime
				* result
				+ ((situacaoDocumentoEvento == null) ? 0
						: situacaoDocumentoEvento.hashCode());
		result = prime
				* result
				+ ((tipoClassificacaoDocumento == null) ? 0
						: tipoClassificacaoDocumento.hashCode());
		result = prime * result
				+ ((tipoOperacao == null) ? 0 : tipoOperacao.hashCode());
		result = prime * result
				+ ((tipoOrigem == null) ? 0 : tipoOrigem.hashCode());
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
		AuditoriaDocumento other = (AuditoriaDocumento) obj;
		if (assuntoDocumento == null) {
			if (other.assuntoDocumento != null)
				return false;
		} else if (!assuntoDocumento.equals(other.assuntoDocumento))
			return false;
		if (dataInclusaoDocumento == null) {
			if (other.dataInclusaoDocumento != null)
				return false;
		} else if (!dataInclusaoDocumento.equals(other.dataInclusaoDocumento))
			return false;
		if (dataOperacao == null) {
			if (other.dataOperacao != null)
				return false;
		} else if (!dataOperacao.equals(other.dataOperacao))
			return false;
		if (dataOrigemDocumento == null) {
			if (other.dataOrigemDocumento != null)
				return false;
		} else if (!dataOrigemDocumento.equals(other.dataOrigemDocumento))
			return false;
		if (dataRecebimentoExpedicao == null) {
			if (other.dataRecebimentoExpedicao != null)
				return false;
		} else if (!dataRecebimentoExpedicao
				.equals(other.dataRecebimentoExpedicao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (identificacaoDocumentoSTJ == null) {
			if (other.identificacaoDocumentoSTJ != null)
				return false;
		} else if (!identificacaoDocumentoSTJ
				.equals(other.identificacaoDocumentoSTJ))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (numeroProcessoAdministrativo == null) {
			if (other.numeroProcessoAdministrativo != null)
				return false;
		} else if (!numeroProcessoAdministrativo
				.equals(other.numeroProcessoAdministrativo))
			return false;
		if (numeroRegistro == null) {
			if (other.numeroRegistro != null)
				return false;
		} else if (!numeroRegistro.equals(other.numeroRegistro))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (situacaoDocumentoEvento == null) {
			if (other.situacaoDocumentoEvento != null)
				return false;
		} else if (!situacaoDocumentoEvento
				.equals(other.situacaoDocumentoEvento))
			return false;
		if (tipoClassificacaoDocumento == null) {
			if (other.tipoClassificacaoDocumento != null)
				return false;
		} else if (!tipoClassificacaoDocumento
				.equals(other.tipoClassificacaoDocumento))
			return false;
		if (tipoOperacao == null) {
			if (other.tipoOperacao != null)
				return false;
		} else if (!tipoOperacao.equals(other.tipoOperacao))
			return false;
		if (tipoOrigem == null) {
			if (other.tipoOrigem != null)
				return false;
		} else if (!tipoOrigem.equals(other.tipoOrigem))
			return false;
		return true;
	}

}
