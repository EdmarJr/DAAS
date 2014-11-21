/*
 * ManutencaoSaapAcompPendenciaAdmService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAcompPendenciaAdmBO;
import br.jus.stj.saap.negocio.bo.SaapPendenciaAdmPresidenciaBO;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapAcompPendenciaAdm.
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapAcompPendenciaAdmService extends 
		ManutencaoServiceImpl<SaapAcompPendenciaAdm, SaapAcompPendenciaAdmBO> {
	
	private SaapAcompPendenciaAdmBO bo;
	private SaapPendenciaAdmPresidenciaBO saapPendenciaAdmPresidenciaBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapAcompPendenciaAdmService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapAcompPendenciaAdmBO")
	protected void setBo(SaapAcompPendenciaAdmBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapAcompPendenciaAdmBO getBo() {
		return bo;
	}
	
	
	/**
	 * Retorna saapPendenciaAdmPresidenciaBO.
	 * 
	 * @return SaapPendenciaAdmPresidenciaBO
	 */
	public SaapPendenciaAdmPresidenciaBO getSaapPendenciaAdmPresidenciaBO() {
		return saapPendenciaAdmPresidenciaBO;
	}

	
	/**
	 * Atribui saapPendenciaAdmPresidenciaBO.
	 * 
	 * @param saapPendenciaAdmPresidenciaBO saapPendenciaAdmPresidenciaBO
	 */
	@Resource(name = "saapPendenciaAdmPresidenciaBO")	
	public void setSaapPendenciaAdmPresidenciaBO(
			SaapPendenciaAdmPresidenciaBO saapPendenciaAdmPresidenciaBO) {
		this.saapPendenciaAdmPresidenciaBO = saapPendenciaAdmPresidenciaBO;
	}
	
	/**
	 * Retorna aaUsuarioSaapBO.
	 * 
	 * @return AaUsuarioSaapBO
	 */
	public AaUsuarioSaapBO getAaUsuarioSaapBO() {
		return aaUsuarioSaapBO;
	}

	/**
	 * Atribui aaUsuarioSaapBO.
	 * 
	 * @param aaUsuarioSaapBO aaUsuarioSaapBO
	 */
	@Resource(name = "aaUsuarioSaapBO")	
	public void setAaUsuarioSaapBO(AaUsuarioSaapBO aaUsuarioSaapBO) {
		this.aaUsuarioSaapBO = aaUsuarioSaapBO;
	}

	/**
	 * 
	 * Obter o Acompanhamento de uma pendência específica
	 * @param entidade entidade
	 * @return SaapAcompPendenciaAdm
	 */
	@Transactional(readOnly = true)
	public SaapAcompPendenciaAdm consultarAcompanhamento(
			SaapAcompPendenciaAdm entidade){
		
		Collection<SaapAcompPendenciaAdm> col = getBo().consultarAcompanhamentos(entidade);
		Iterator it = col.iterator();
		if (it.hasNext()) {
			entidade = (SaapAcompPendenciaAdm)it.next();
			
			SaapPendenciaAdmPresidencia saapPendencia = getSaapPendenciaAdmPresidenciaBO().obter(
					entidade.getSaapPendenciaAdmPresidencia().getId());
			entidade.setSaapPendenciaAdmPresidencia(saapPendencia);
		}else {
			entidade.setTxtObsAcompPendencia(null);
			entidade.setId(null);
			SaapPendenciaAdmPresidencia saapPendencia = getSaapPendenciaAdmPresidenciaBO().obter(
					entidade.getSaapPendenciaAdmPresidencia().getId());
			entidade.setSaapPendenciaAdmPresidencia(saapPendencia);
		}
			
		
		return entidade;
		
	}

	/**
	 * 
	 * Consultar Usuários
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<AaUsuario>
	 */
	@Transactional(readOnly = true)
	public Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade) {
		AaUsuario usu = getAaUsuarioSaapBO().obter(entidade.getSeqUsuario());
		Collection<SaapAcompPendenciaAdm> usuariosAcomp = getBo().
				consultarUsuariosPendencia(entidade);
		
		Collection<AaUsuario> listaUsuarios = new ArrayList<AaUsuario>();
		
		Iterator<SaapAcompPendenciaAdm> iterator = usuariosAcomp.iterator();
		AaUsuario usuario = null;
		while (iterator.hasNext()) {
			SaapAcompPendenciaAdm acompanhamento = iterator.next();
			usuario = getAaUsuarioSaapBO().obter(acompanhamento.getSeqUsuario());
			listaUsuarios.add(usuario);
		}
		
		listaUsuarios.add(usu);

		//Ordenar a coleção novamente com o usuário logado incluído
		UtilColecao.ordenar(listaUsuarios, 
				new Comparator<AaUsuario>(){
			public int compare(AaUsuario a1, AaUsuario a2) {
				return a1.getNomeUsuario().compareTo(
						a2.getNomeUsuario());
			}
		});
		
		return listaUsuarios;
	}

	/**
	 * 
	 * Inclui ou altera um acompanhamento da pendência
	 * 
	 * @param entidade SaapAcompPendenciaAdm
	 */
	@Override
	@Transactional
	public void salvar(SaapAcompPendenciaAdm entidade) {
		if (UtilObjeto.isReferencia(entidade.getId())) {
			getBo().salvar(entidade);
		}else {
			getBo().inserir(entidade);	
		}
	}
}
