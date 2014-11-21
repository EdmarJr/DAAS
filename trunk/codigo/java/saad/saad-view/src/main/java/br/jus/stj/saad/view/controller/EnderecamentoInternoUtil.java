package br.jus.stj.saad.view.controller;

import java.util.List;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.saad.business.LocalBean;
import br.jus.stj.saad.business.UsuarioBean;
import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.TipoEnderecamentoEnum;

public class EnderecamentoInternoUtil {

	private UsuarioBean usuarioBean;
	private LocalBean localBean;
	private List<Usuario> listaRemetente;
	private List<Local> listaLocais;
	private Enderecamento enderecamento;
	 
	public EnderecamentoInternoUtil (UsuarioBean usuarioBean, LocalBean localBean, Enderecamento enderecamento){
		this.usuarioBean = usuarioBean;
		this.localBean = localBean;
		this.enderecamento = enderecamento;
		if(this.enderecamento instanceof EnderecamentoInterno){
			if(enderecamento.getLocal() != null){
				atualizarRemetenteInterno();
			}
		}
		this.enderecamento.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_INTERNO.getValor());
		this.listaLocais = this.localBean.obterLocaisAtivosSTj();
	}
	
	public void atualizarRemetenteInterno() {
		if(enderecamento instanceof EnderecamentoInterno){
			if (enderecamento.getLocal() != null) {
				listaRemetente = usuarioBean.obterUsuariosPorUnidades(
						ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE, enderecamento.getLocal());
			}
		}
	}
	
	public void ativarEnderecamento(){
		getEnderecamento().setUtilizado(true);
	}
	
	public List<Usuario> getListaRemetente() {
		return listaRemetente;
	}

	public void setListaRemetente(List<Usuario> listaRemetente) {
		this.listaRemetente = listaRemetente;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}

	public Enderecamento getEnderecamento() {
		return enderecamento;
	}

	public void setEnderecamento(Enderecamento enderecamento) {
		this.enderecamento = enderecamento;
	}	
}
