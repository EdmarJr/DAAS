package br.jus.stj.saad.view.controller;

import java.util.List;

import br.jus.stj.saad.business.CargoBean;
import br.jus.stj.saad.business.DestinatarioBean;
import br.jus.stj.saad.business.DestinatarioDocumentoBean;
import br.jus.stj.saad.business.DestinatarioOcupacaoBean;
import br.jus.stj.saad.business.OrgaoBean;
import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Orgao;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;
import br.jus.stj.saad.enumerators.TipoEnderecamentoExternoEnum;

public class EnderecamentoExternoUtil {

	private OrgaoBean orgaoBean;
	
	private CargoBean cargoBean;
	
	private DestinatarioBean destinatarioBean;

	private DestinatarioDocumentoBean destinatarioDocumentoBean;
	
	private DestinatarioOcupacaoBean destinatarioOcupacaoBean;
	
	private TipoEnderecamentoExternoEnum[] listaTiposEnderacamentoExterno;
	
	private List<Cargo> listaCargos;

	private List<Destinatario> listaDestinatarios;

	private TipoEnderecamentoExternoEnum tipoEnderecamentoExternoEnum;

	private Enderecamento enderecamento;
	
	private EnderecamentoDestinatarioDocumentoUtil enderecamentoDestinatarioDocumentoUtil;
	
	public EnderecamentoExternoUtil (OrgaoBean orgaoBean, CargoBean cargoBean, DestinatarioBean destinatarioBean, DestinatarioDocumentoBean destinatarioDocumentoBean, DestinatarioOcupacaoBean destinatarioOcupacaoBean, Enderecamento enderecamento){
		this.orgaoBean = orgaoBean;
		this.cargoBean = cargoBean;
		this.destinatarioBean = destinatarioBean;
		this.destinatarioDocumentoBean = destinatarioDocumentoBean;
		this.destinatarioOcupacaoBean = destinatarioOcupacaoBean;
		this.enderecamento = enderecamento;
		
		if(enderecamento instanceof EnderecamentoExterno || enderecamento instanceof EnderecamentoInterno){
			this.enderecamentoDestinatarioDocumentoUtil = new EnderecamentoDestinatarioDocumentoUtil(this.destinatarioDocumentoBean, new Enderecamento());
			setTipoEnderecamentoExternoEnum(TipoEnderecamentoExternoEnum.ORGAO);
			if(enderecamento instanceof EnderecamentoExterno){
				if(enderecamento.getCargo() != null){
					atualizarListaDeCargos();
					atualizarListaDeDestinatarios();
				}
			}
		}else{
			this.enderecamentoDestinatarioDocumentoUtil = new EnderecamentoDestinatarioDocumentoUtil(this.destinatarioDocumentoBean, enderecamento);
			setTipoEnderecamentoExternoEnum(TipoEnderecamentoExternoEnum.PESSOA_FISICA);
		}
		
		setListaTiposEnderacamentoExterno(TipoEnderecamentoExternoEnum.values());
	}
	
	private void resetarAtributos() {
		setListaDestinatarios(null);
		setListaCargos(null);
		getEnderecamento().setCargo(null);
		getEnderecamento().setDestinatario(null);
		getEnderecamento().setOrgao(null);
		getEnderecamento().setOcupacao(null);
		enderecamentoDestinatarioDocumentoUtil.resetarAtributos();
	}
	
	public void atualizarPainel(){
		if(tipoEnderecamentoExternoEnum.equals(TipoEnderecamentoExternoEnum.ORGAO)){
			enderecamento = new EnderecamentoExterno();
			enderecamento.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_MALA_DIRETA.getValor());
		}else{
			enderecamento = new Enderecamento();
			enderecamento.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_OUTRO_DESTINATARIO.getValor());
			this.enderecamentoDestinatarioDocumentoUtil = new EnderecamentoDestinatarioDocumentoUtil(this.destinatarioDocumentoBean, this.enderecamento);
		}
	}

	public List<Orgao> autoCompleteOrgao(String query) {
		resetarAtributos();

		List<Orgao> list = orgaoBean.listarPorNome(query);

		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	public void atualizarListaDeCargos() {
		setListaCargos(cargoBean.cargosPorOrgao(enderecamento.getOrgao()));
	}

	public void atualizarListaDeDestinatarios() {
		setListaDestinatarios(destinatarioBean.destinatariosPorCargoOrgao(
				enderecamento.getCargo(), enderecamento.getOrgao()));
	}
	
	public Boolean isOrgao() {
		if (getTipoEnderecamentoExternoEnum().equals(
				TipoEnderecamentoExternoEnum.ORGAO)) {
			return true;
		}
		return false;
	}
	
	public void ativarEnderecamento(){
		if(getEnderecamento() instanceof EnderecamentoExterno){
			getEnderecamento().setOcupacao(destinatarioOcupacaoBean.buscar(getEnderecamento().getOrgao(), 
				getEnderecamento().getCargo(), getEnderecamento().getDestinatario()));
		}
		getEnderecamento().setUtilizado(true);
	}

	public List<Cargo> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<Cargo> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public List<Destinatario> getListaDestinatarios() {
		return listaDestinatarios;
	}

	public void setListaDestinatarios(List<Destinatario> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public TipoEnderecamentoExternoEnum[] getListaTiposEnderacamentoExterno() {
		return listaTiposEnderacamentoExterno;
	}

	public void setListaTiposEnderacamentoExterno(
			TipoEnderecamentoExternoEnum[] tipoEnderecamentoExternoEnums) {
		this.listaTiposEnderacamentoExterno = tipoEnderecamentoExternoEnums;
	}

	public TipoEnderecamentoExternoEnum getTipoEnderecamentoExternoEnum() {
		return tipoEnderecamentoExternoEnum;
	}

	public void setTipoEnderecamentoExternoEnum(
			TipoEnderecamentoExternoEnum tipoEnderecamentoExternoEnum) {
		this.tipoEnderecamentoExternoEnum = tipoEnderecamentoExternoEnum;
	}

	public Enderecamento getEnderecamento() {
		return enderecamento;
	}

	public void setEnderecamento(Enderecamento enderecamento) {
		this.enderecamento = enderecamento;
	}

	public EnderecamentoDestinatarioDocumentoUtil getEnderecamentoDestinatarioDocumentoUtil() {
		return enderecamentoDestinatarioDocumentoUtil;
	}

	public void setEnderecamentoDestinatarioDocumentoUtil(
			EnderecamentoDestinatarioDocumentoUtil enderecamentoDestinatarioDocumentoUtil) {
		this.enderecamentoDestinatarioDocumentoUtil = enderecamentoDestinatarioDocumentoUtil;
	}
}
