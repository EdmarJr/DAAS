package br.jus.stj.saad.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
/**
 * Classe criada somente para criar o protótipo de tela.
 * @author edmar.junior
 *
 */
public class PrototypeController extends GenericController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer quantidadeReservas;

	private String tipoDocumento;
	private String proximoNumeroDisponivel;
	private List<Expedicao> expedicoes;
	
	@PostConstruct
	private void inicializar() {
		setExpedicoes(new ArrayList<Expedicao>());
	}
	
	public String getProximoNumeroDisponivel() {
		return proximoNumeroDisponivel;
	}

	public void setProximoNumeroDisponivel(String proximoNumeroDisponivel) {
		this.proximoNumeroDisponivel = proximoNumeroDisponivel;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getQuantidadeReservas() {
		return quantidadeReservas;
	}

	public void setQuantidadeReservas(Integer quantidadeReservas) {
		this.quantidadeReservas = quantidadeReservas;
	}

	public String comandoReservar() {
		return "";
	}

	public List<Expedicao> getExpedicoes() {
		return expedicoes;
	}

	public void setExpedicoes(List<Expedicao> expedicoes) {
		this.expedicoes = expedicoes;
	}

	class Expedicao {
		private String numero;
		private String identificador;

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

	}

}
