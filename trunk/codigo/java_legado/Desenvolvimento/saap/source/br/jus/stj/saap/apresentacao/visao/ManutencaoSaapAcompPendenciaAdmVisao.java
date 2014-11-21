/*
 * ManutencaoSaapAcompPendenciaAdmVisao.java
 * 
 * Data de criação: 08/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.model.SelectItem;

import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação da visão do caso de uso Manutenção do Acompanhamento da Pendência
 * @author lmartins
 */
public class ManutencaoSaapAcompPendenciaAdmVisao extends ManutencaoVisao<SaapAcompPendenciaAdm> {
	
	private String seqPendenciaAdm;
	private Collection<SelectItem> listaUsuario;
	private AaUsuario usuarioSelecionado;
	private SaapAcompPendenciaAdm saapAcompPendenciaAdm;
	private String caminhoRetorno;
	private Integer usuarioLogado;
	private boolean apresentarSalvar;
	
	
	/**
	 * Retorna seqPendenciaAdm.
	 * 
	 * @return String
	 */
	public String getSeqPendenciaAdm() {
		return seqPendenciaAdm;
	}

	
	/**
	 * Atribui seqPendenciaAdm.
	 * 
	 * @param seqPendenciaAdm seqPendenciaAdm
	 */
	public void setSeqPendenciaAdm(String seqPendenciaAdm) {
		this.seqPendenciaAdm = seqPendenciaAdm;
	}


	
	/**
	 * Retorna listaUsuario.
	 * 
	 * @return Collection<AaUsuario>
	 */
	public Collection<SelectItem> getListaUsuario() {
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<SelectItem>();
		}
		return listaUsuario;
	}


	
	/**
	 * Atribui listaUsuario.
	 * 
	 * @param listaUsuario listaUsuario
	 */
	public void setListaUsuario(Collection<SelectItem> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}


	
	/**
	 * Retorna saapAcompPendenciaAdm.
	 * 
	 * @return SaapAcompPendenciaAdm
	 */
	public SaapAcompPendenciaAdm getSaapAcompPendenciaAdm() {
		if (saapAcompPendenciaAdm == null) {
			saapAcompPendenciaAdm = EntidadeFactory.getInstancia().
					novoSaapAcompPendenciaAdm();
		}
		return saapAcompPendenciaAdm;
	}
	
	/**
	 * Atribui saapAcompPendenciaAdm.
	 * 
	 * @param saapAcompPendenciaAdm saapAcompPendenciaAdm
	 */
	public void setSaapAcompPendenciaAdm(
			SaapAcompPendenciaAdm saapAcompPendenciaAdm) {
		this.saapAcompPendenciaAdm = saapAcompPendenciaAdm;
	}
	
	/**
	 * Retorna usuarioSelecionado.
	 * 
	 * @return AaUsuario
	 */
	public AaUsuario getUsuarioSelecionado() {
		if (usuarioSelecionado == null) {
			usuarioSelecionado = EntidadeFactory.getInstancia().novoAaUsuario();
		}
		return usuarioSelecionado;
	}
	
	/**
	 * Atribui usuarioSelecionado.
	 * 
	 * @param usuarioSelecionado usuarioSelecionado
	 */
	public void setUsuarioSelecionado(AaUsuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}


	
	/**
	 * Retorna caminhoRetorno.
	 * 
	 * @return String
	 */
	public String getCaminhoRetorno() {
		return caminhoRetorno;
	}


	/**
	 * Atribui caminhoRetorno.
	 * 
	 * @param caminhoRetorno caminhoRetorno
	 */
	public void setCaminhoRetorno(String caminhoRetorno) {
		this.caminhoRetorno = caminhoRetorno;
	}


	
	/**
	 * Retorna apresentarSalvar.
	 * 
	 * @return boolean
	 */
	public boolean isApresentarSalvar() {
		return apresentarSalvar;
	}
	
	/**
	 * Atribui apresentarSalvar.
	 * 
	 * @param apresentarSalvar apresentarSalvar
	 */
	public void setApresentarSalvar(boolean apresentarSalvar) {
		this.apresentarSalvar = apresentarSalvar;
	}


	
	/**
	 * Retorna usuarioLogado.
	 * 
	 * @return Integer
	 */
	public Integer getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * Atribui usuarioLogado.
	 * 
	 * @param usuarioLogado usuarioLogado
	 */
	public void setUsuarioLogado(Integer usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
