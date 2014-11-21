package br.jus.stj.saad.vo;

import java.util.List;

import br.jus.stj.saad.entity.local.Aviso;
import br.jus.stj.saad.entity.related.Usuario;

public class FiltroConsultaAvisoVO {
	
	private Aviso aviso;
	
	private String identificadorDocumento;
	
	private String ano;
	
	private String sigla;
	
	private String identificacaoDocumentoSTJ;
	
	private List<Usuario> listDestinatario;

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public List<Usuario> getListDestinatario() {
		return listDestinatario;
	}

	public void setListDestinatario(List<Usuario> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}

	public String getIdentificadorDocumento() {
		return identificadorDocumento;
	}

	public void setIdentificadorDocumento(String identificadorDocumento) {
		this.identificadorDocumento = identificadorDocumento;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
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
	
	

}
