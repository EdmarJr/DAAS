package br.jus.stj.saad.vo;

import java.util.Date;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.related.Local;
public class FiltroDocumentoConsultaAndamentoVO {
	private Documento documento;
	
	private Date dataOrigemDe;
	private Date dataOrigemAte;
	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private Date dataRecebimentoDe;
	private Date dataRecebimentoAte;
	private boolean relacionamentoEvento;
	private String numero;
	private String identificador;
	private Local local;
	private String sigla;
	private String identificacaoDocumentoSTJ;
	private String ano;
	

	public FiltroDocumentoConsultaAndamentoVO() {
		setDocumento(new Documento());
		getDocumento().setEnderecamentoRemetente(new EnderecamentoInterno());
	}
	public Date getDataOrigemDe() {
		return dataOrigemDe;
	}

	public void setDataOrigemDe(Date dataOrigemDe) {
		this.dataOrigemDe = dataOrigemDe;
	}

	public Date getDataOrigemAte() {
		return dataOrigemAte;
	}

	public void setDataOrigemAte(Date dataOrigemAte) {
		this.dataOrigemAte = dataOrigemAte;
	}

	public Date getDataCadastroDe() {
		return dataCadastroDe;
	}

	public void setDataCadastroDe(Date dataCadastroDe) {
		this.dataCadastroDe = dataCadastroDe;
	}

	public Date getDataCadastroAte() {
		return dataCadastroAte;
	}

	public void setDataCadastroAte(Date dataCadastroAte) {
		this.dataCadastroAte = dataCadastroAte;
	}

	public Date getDataRecebimentoDe() {
		return dataRecebimentoDe;
	}

	public void setDataRecebimentoDe(Date dataRecebimentoDe) {
		this.dataRecebimentoDe = dataRecebimentoDe;
	}

	public Date getDataRecebimentoAte() {
		return dataRecebimentoAte;
	}

	public void setDataRecebimentoAte(Date dataRecebimentoAte) {
		this.dataRecebimentoAte = dataRecebimentoAte;
	}


	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public boolean isRelacionamentoEvento() {
		return relacionamentoEvento;
	}
	public void setRelacionamentoEvento(boolean relacionamentoEvento) {
		this.relacionamentoEvento = relacionamentoEvento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getIdentificacaoDocumentoSTJ() {
		return identificacaoDocumentoSTJ;
	}
	public void setIdentificacaoDocumentoSTJ(String identificacaoDocumentoSTJ) {
		this.identificacaoDocumentoSTJ = identificacaoDocumentoSTJ;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	
	
}
