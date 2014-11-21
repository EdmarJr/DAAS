package br.jus.stj.saad.entity.local;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.util.ActionOperation;
import br.jus.stj.saad.util.Status;
import br.jus.stj.saad.util.YieldDate;

@Entity
@Table(schema = "saad", name = "AUDIT_DOCUMENTO_UNIDADE")
@NamedQueries({
		@NamedQuery(name = "countAllAuditByFilter", query = "SELECT COUNT(aud) FROM Audit aud WHERE aud.idDocumento = :idDocumento AND aud.idUsuario = :idUsuario AND aud.tipoOperacao = :tipoOperacao"),
		@NamedQuery(name = "findAllAuditByFilter", query = "SELECT aud FROM Audit aud WHERE aud.idDocumento = :idDocumento AND aud.idUsuario = :idUsuario AND aud.tipoOperacao = :tipoOperacao ORDER BY aud.id"),
		@NamedQuery(name = "findAudit", query = "SELECT aud FROM Audit aud WHERE aud = :audit ") })
public class Audit extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8438599291174292795L;

	@Id
	@Column(name = "SQ_AUDIT_DOCUMENTO_UNIDADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SEQ_USUARIO")
	private Long idUsuario;

	@Column(name = "DH_OPERACAO")
	private Timestamp dataHoraOperacao;

	@Column(name = "TP_OPERACAO", columnDefinition = "CHAR(1)")
	@Enumerated(EnumType.STRING)
	private ActionOperation tipoOperacao;

	@Column(name = "SQ_DOCUMENTO_UNIDADE")
	private Long idDocumento;

	@Column(name = "SQ_TIPO_DOCUMENTO_UNIDADE")
	private Long idTipoDocumento;

	@Column(name = "SEQ_LOCAL")
	private Long idLocal;

	@Column(name = "NUM_REGISTRO", columnDefinition = "CHARACTER(12)")
	private String numeroRegistro;

	@Column(name = "SQ_ENDERECAMENTO")
	private Long idEnderecamento;

	@Column(name = "TP_CLASSIFICACAO_DOCUMENTO", columnDefinition = "CHAR(1)")
	@Enumerated(EnumType.STRING)
	private ClassificacaoEnum tipoClassificacao;

	@Column(name = "TP_ORIGEM", columnDefinition = "CHAR(1)")
	private String tipoOrigem;

	@Column(name = "ID_DOCUMENTO_STJ", columnDefinition = "VARCHAR(6)")
	private String idDocumentoSTJ;

	@Column(name = "NR_DOCUMENTO_ORIGEM", columnDefinition = "VARCHAR(20)")
	private String numeroDocumentoOrigem;

	@Column(name = "DH_DOCUMENTO_ORIGEM")
	private Timestamp dataHoraDocumentoOrigem;

	@Column(name = "DH_RECEBIMENTO_EXPEDICAO")
	private Timestamp dataHoraRecebimentoExpedicao;

	@Column(name = "DH_INCLUSAO")
	private Timestamp dataHoraInclusao;

	@Column(name = "NR_PROCESSO_ADMINISTRATIVO", columnDefinition = "VARCHAR(30)")
	private String numeroProcessoAdministrativo;

	@Column(name = "TX_ASSUNTO_DOCUMENTO", columnDefinition = "CLOB(2096)")
	private String textoAssuntoDocumento;

	@Column(name = "ST_DOCUMENTO_EVENTO", columnDefinition = "CHAR(1)")
	private String situacaoDocumentoEvento;

	@Column(name = "ST_SITUACAO_DOCUMENTO", columnDefinition = "CHAR(1)")
	@Enumerated(EnumType.STRING)
	private Status situacaoDocumento;

	@Column(name = "SEQ_DESTINATARIO")
	private Long idDestinatario;

	@Transient
	private YieldDate yieldDate;

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

	public Date getDataHoraOperacao() {
		return dataHoraOperacao;
	}

	public void setDataHoraOperacao(Timestamp dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}

	public ActionOperation getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(ActionOperation tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Long getIdEnderecamento() {
		return idEnderecamento;
	}

	public void setIdEnderecamento(Long idEnderecamento) {
		this.idEnderecamento = idEnderecamento;
	}

	public ClassificacaoEnum getTipoClassificacao() {
		return tipoClassificacao;
	}

	public void setTipoClassificacao(ClassificacaoEnum tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}

	public String getTipoOrigem() {
		return tipoOrigem;
	}

	public void setTipoOrigem(String tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	public String getIdDocumentoSTJ() {
		return idDocumentoSTJ;
	}

	public void setIdDocumentoSTJ(String idDocumentoSTJ) {
		this.idDocumentoSTJ = idDocumentoSTJ;
	}

	public String getNumeroDocumentoOrigem() {
		return numeroDocumentoOrigem;
	}

	public void setNumeroDocumentoOrigem(String numeroDocumentoOrigem) {
		this.numeroDocumentoOrigem = numeroDocumentoOrigem;
	}

	public Date getDataHoraDocumentoOrigem() {
		return dataHoraDocumentoOrigem;
	}

	public void setDataHoraDocumentoOrigem(Timestamp dataHoraDocumentoOrigem) {
		this.dataHoraDocumentoOrigem = dataHoraDocumentoOrigem;
	}

	public Date getDataHoraRecebimentoExpedicao() {
		return dataHoraRecebimentoExpedicao;
	}

	public void setDataHoraRecebimentoExpedicao(
			Timestamp dataHoraRecebimentoExpedicao) {
		this.dataHoraRecebimentoExpedicao = dataHoraRecebimentoExpedicao;
	}

	public Date getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public String getNumeroProcessoAdministrativo() {
		return numeroProcessoAdministrativo;
	}

	public void setNumeroProcessoAdministrativo(
			String numeroProcessoAdministrativo) {
		this.numeroProcessoAdministrativo = numeroProcessoAdministrativo;
	}

	public String getTextoAssuntoDocumento() {
		return textoAssuntoDocumento;
	}

	public void setTextoAssuntoDocumento(String textoAssuntoDocumento) {
		this.textoAssuntoDocumento = textoAssuntoDocumento;
	}

	public String getSituacaoDocumentoEvento() {
		return situacaoDocumentoEvento;
	}

	public void setSituacaoDocumentoEvento(String situacaoDocumentoEvento) {
		this.situacaoDocumentoEvento = situacaoDocumentoEvento;
	}

	public Status getSituacaoDocumento() {
		return situacaoDocumento;
	}

	public void setSituacaoDocumento(Status situacaoDocumento) {
		this.situacaoDocumento = situacaoDocumento;
	}

	public Long getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(Long idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public YieldDate getYieldDate() {
		return yieldDate;
	}

	public void setYieldDate(YieldDate yieldDate) {
		this.yieldDate = yieldDate;
	}

}
