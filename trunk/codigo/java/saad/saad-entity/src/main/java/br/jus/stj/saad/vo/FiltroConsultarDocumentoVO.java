package br.jus.stj.saad.vo;

import java.util.Date;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.entity.related.Usuario;


public class FiltroConsultarDocumentoVO {
	
	private Documento documento;	
	private Date dataOrigemInicial;
	private Date dataOrigemFinal;
	private Date dataRecebimentoInicial;
	private Date dataRecebimentoFinal;
	private Date dataEventoInicial;
	private Date dataEventoFinal;
	private Usuario usuario;	
	private Local local;
	private Orgao orgao;
	private String descricaoAndamento;
	private Boolean andamento = false;
	
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public Date getDataOrigemInicial() {
		return dataOrigemInicial;
	}
	public void setDataOrigemInicial(Date dataOrigemInicial) {
		this.dataOrigemInicial = dataOrigemInicial;
	}
	public Date getDataOrigemFinal() {
		return dataOrigemFinal;
	}
	public void setDataOrigemFinal(Date dataOrigemFinal) {
		this.dataOrigemFinal = dataOrigemFinal;
	}
	public Date getDataRecebimentoInicial() {
		return dataRecebimentoInicial;
	}
	public void setDataRecebimentoInicial(Date dataRecebimentoInicial) {
		this.dataRecebimentoInicial = dataRecebimentoInicial;
	}
	public Date getDataRecebimentoFinal() {
		return dataRecebimentoFinal;
	}
	public void setDataRecebimentoFinal(Date dataRecebimentoFinal) {
		this.dataRecebimentoFinal = dataRecebimentoFinal;
	}
	public Date getDataEventoInicial() {
		return dataEventoInicial;
	}
	public void setDataEventoInicial(Date dataEventoInicial) {
		this.dataEventoInicial = dataEventoInicial;
	}
	public Date getDataEventoFinal() {
		return dataEventoFinal;
	}
	public void setDataEventoFinal(Date dataEventoFinal) {
		this.dataEventoFinal = dataEventoFinal;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Orgao getOrgao() {
		return orgao;
	}
	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	public String getDescricaoAndamento() {
		return descricaoAndamento;
	}
	public void setDescricaoAndamento(String descricaoAndamento) {
		this.descricaoAndamento = descricaoAndamento;
	}
	public Boolean getAndamento() {
		return andamento;
	}
	public void setAndamento(Boolean andamento) {
		this.andamento = andamento;
	}
}
