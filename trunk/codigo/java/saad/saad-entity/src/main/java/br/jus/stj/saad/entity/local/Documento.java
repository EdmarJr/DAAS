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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.jus.stj.commons.entity.interfaces.EntidadeComDataInclusao;
import br.jus.stj.commons.entity.listeners.EntidadeComDataInclusaoListener;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.listener.IdentificadorDocumentoListener;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.enumerators.ClassificacaoEnum;
import br.jus.stj.saad.enumerators.SituacaoDocumentoEnum;
import br.jus.stj.saad.enumerators.TipoOrigemDocumentoEnum;
import br.jus.stj.saad.util.ConstantesSaad;

@Entity
@Table(schema = "saad", name = "DOCUMENTO_UNIDADE")
@NamedQueries({
		@NamedQuery(name = "findAllDocumentByFilter", query = "SELECT doc FROM Documento doc INNER JOIN FETCH doc.tipo ORDER BY doc.id"),
		@NamedQuery(name = "findDocument", query = "SELECT doc FROM Documento doc INNER JOIN FETCH doc.tipo INNER JOIN FETCH doc.enderecamentoRemetente WHERE doc = :document ") })
@EntityListeners({ EntidadeComDataInclusaoListener.class,
		IdentificadorDocumentoListener.class })
public class Documento extends EntidadeBase implements EntidadeComDataInclusao {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SQ_DOCUMENTO_UNIDADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "documento", cascade = CascadeType.ALL)
	private Evento evento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_LOCAL")
	private Local local;

	@Column(name = "NR_DOCUMENTO_ORIGEM")
	private String numeroDocumentoOrigem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SQ_TIPO_DOCUMENTO")
	private TipoDocumento tipo;

	@Column(name = "NUM_REGISTRO", nullable = true)
	private String numeroRegistro;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SQ_ENDERECAMENTO_REMETENTE", nullable = true)
	private Enderecamento enderecamentoRemetente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SQ_ENDERECAMENTO_DESTINATARIO", nullable = true)
	private Enderecamento enderecamentoDestinatario;

	@Column(name = "TP_CLASSIFICACAO_DOCUMENTO", nullable = true)
	private Character tipoClassificacaoDocumento;

	@Column(name = "TP_ORIGEM", nullable = true)
	private Character tipoOrigem;

	@Column(name = "NR_CONTROLE_IDENTIFICADOR", nullable = true)
	private String numeroControleIdentificador;
	
	@Column(name = "NR_CONTROLE_NUMERACAO", nullable = true)
	private String numeroControleNumeracao;

	@Column(name = "DT_DOCUMENTO_ORIGEM", nullable = true)
	private Date dataOrigemDocumento;

	@Column(name = "DT_RECEBIMENTO_EXPEDICAO", nullable = true)
	private Date dataRecebimentoExpedicao = new Date();

	@Column(name = "DH_INCLUSAO_DOCUMENTO", nullable = true)
	private Date dataInclusao;

	@Column(name = "NR_PROCESSO_ADMINISTRATIVO", nullable = true)
	private String numeroProcessoAdministrativo;

	@Column(name = "TX_ASSUNTO_DOCUMENTO", nullable = true)
	private String assuntoDocumento;

	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<TarefaDemandaDocumento> tarefas;

	@Column(name = "ST_DOCUMENTO_EVENTO", nullable = true)
	private Character situacaoDocumentoEvento;
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<AndamentoDocumento> andamentosDocumento;

	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Anexo> anexos;
	
	@OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Aviso> avisos;

	@Column(name = "ST_SITUACAO_DOCUMENTO")
	private Character situacao;

	@Column(name="TX_PASTA_FISICA")
	private String pastaFisica;
	
	@Transient
	private Boolean relacionadoAEvento;

	@Transient
	private String identificadorComMascara;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TarefaDemandaDocumento> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaDemandaDocumento> tarefas) {
		this.tarefas = tarefas;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}

	public String getNumeroRegistro() {
		return numeroRegistro != null && numeroRegistro.equals("") ? null
				: numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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

	public Date getDataOrigemDocumento() {
		return dataOrigemDocumento;
	}

	public void setDataOrigemDocumento(Date dataOrigemDocumento) {
		this.dataOrigemDocumento = dataOrigemDocumento;
	}

	public Date getDataRecebimentoExpedicao() {
		return dataRecebimentoExpedicao;
	}

	public List<AndamentoDocumento> getAndamentosDocumento() {
		return andamentosDocumento;
	}

	public void setAndamentosDocumento(
			List<AndamentoDocumento> andamentosDocumento) {
		this.andamentosDocumento = andamentosDocumento;
	}

	public void setDataRecebimentoExpedicao(Date dataRecebimentoExpedicao) {
		this.dataRecebimentoExpedicao = dataRecebimentoExpedicao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public Boolean getRelacionadoAEvento() {
		return relacionadoAEvento;
	}

	public void setRelacionadoAEvento(Boolean relacionadoAEvento) {
		this.relacionadoAEvento = relacionadoAEvento;
	}

	public String getPastaFisica() {
		return pastaFisica;
	}

	public void setPastaFisica(String pastaFisica) {
		this.pastaFisica = pastaFisica;
	}

	public String getIdentificadorComMascara() {
		return identificadorComMascara;
	}

	public void setIdentificadorComMascara(String identificadorComMascara) {
		this.identificadorComMascara = identificadorComMascara;
	}

	public AndamentoDocumento getAndamentoDocumento() {
		List<AndamentoDocumento> andamentos = getAndamentosDocumento();
		AndamentoDocumento maior = null;
		for (AndamentoDocumento andamentoTemp : andamentos) {
			if (andamentoTemp.getId() == null) {
				continue;
			}
			if (maior != null) {
				if (andamentoTemp.getId() > maior.getId()) {
					maior = andamentoTemp;
				}
			} else {
				maior = andamentoTemp;
			}
		}
		return maior;
	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}
	
	public String getSituacaoAmigavel(){
		SituacaoDocumentoEnum[] situacoes = SituacaoDocumentoEnum.values();
		
		for (SituacaoDocumentoEnum situacao : situacoes) {			
			if (situacao.getValor().equals(this.situacao)) {				
				return situacao.getCodLabel();
			}
			
		}
		
		return null;
	}

	public String getNumeroDocumentoOrigem() {
		return numeroDocumentoOrigem;
	}

	public void setNumeroDocumentoOrigem(String numeroDocumentoOrigem) {
		this.numeroDocumentoOrigem = numeroDocumentoOrigem;
	}

	public Enderecamento getEnderecamentoRemetente() {
		return enderecamentoRemetente;
	}

	public void setEnderecamentoRemetente(Enderecamento enderecamentoRemetente) {
		this.enderecamentoRemetente = enderecamentoRemetente;
	}

	public Enderecamento getEnderecamentoDestinatario() {
		return enderecamentoDestinatario;
	}

	public void setEnderecamentoDestinatario(Enderecamento enderecamentoDestinatario) {
		this.enderecamentoDestinatario = enderecamentoDestinatario;
	}

	public String getNumeroControleIdentificador() {
		return numeroControleIdentificador;
	}

	public void setNumeroControleIdentificador(String numeroControleIdentificador) {
		this.numeroControleIdentificador = numeroControleIdentificador;
	}

	public String getNumeroControleNumeracao() {
		return numeroControleNumeracao;
	}

	public void setNumeroControleNumeracao(String numeroControleNumeracao) {
		this.numeroControleNumeracao = numeroControleNumeracao;
	}
	
	public boolean isDocumentoExpedido(){
		return ClassificacaoEnum.EXPEDIDO.getValor().equals(this.tipoClassificacaoDocumento);
	}
	
	public boolean isEnderecamentoRemetente(String tipoEnderecamento){
		return tipoEnderecamento.equalsIgnoreCase(ConstantesSaad.enderecamentoRemetente);
	}
	
	public boolean isEnderecamentoInterno(String tipoEnderecamento){
		if(!isEnderecamentoRemetente(tipoEnderecamento) && !isDocumentoExpedido()){
			return true;
		}else if (isEnderecamentoRemetente(tipoEnderecamento) && isDocumentoExpedido()){		
			return true;
		}else{
			return TipoOrigemDocumentoEnum.INTERNO.getValor().equals(this.tipoOrigem);
		}
	}
	
	public String classificacaoDocumentoAmigavel(){
		if(ClassificacaoEnum.EXPEDIDO.getValor().equals(this.tipoClassificacaoDocumento)){
			return ClassificacaoEnum.EXPEDIDO.getCodLabel();
		}else{
			return ClassificacaoEnum.RECEBIDO.getCodLabel();
		}
	}
}
