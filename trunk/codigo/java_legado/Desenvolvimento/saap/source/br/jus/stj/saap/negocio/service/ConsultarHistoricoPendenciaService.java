/*
 * ConsultarHistoricoPendenciaService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapHistPendenciaAdmBO;
import br.jus.stj.saap.to.ConsultarHistoricoPendenciasTO;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de ConsultarHistoricoPendencia
 *
 * @author Politec Informática S/A
 */
@Service
public class ConsultarHistoricoPendenciaService  extends 
	ManutencaoServiceImpl<SaapHistPendenciaAdm, SaapHistPendenciaAdmBO>{

	
	private SaapHistPendenciaAdmBO saapHistPendenciaAdmBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ConsultarHistoricoPendenciaService() {
		// Construtor default
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
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapHistPendenciaAdmBO getBo() {
		return saapHistPendenciaAdmBO;
	}

	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * @param saapHistPendenciaAdmBO saapHistPendenciaAdmBO
	 */
	@Resource(name = "saapHistPendenciaAdmBO")
	public void setBo(SaapHistPendenciaAdmBO saapHistPendenciaAdmBO) {
		this.saapHistPendenciaAdmBO = saapHistPendenciaAdmBO;
	}	
	

	/**
	 * 
	 * Consultar o Histórico de Pendências
	 * @param entidade SaapHistPendenciaAdm
	 * @return Collection<ConsultarHistoricoPendenciasTO>
	 */
	@Transactional(readOnly = true)
	public Collection<ConsultarHistoricoPendenciasTO> consultarHistPendencia(
			SaapHistPendenciaAdm entidade){
		
		Collection<SaapHistPendenciaAdm> col = getBo().consultar(entidade);
		
		ConsultarHistoricoPendenciasTO consultarHistoricoPendenciasTO = null; 
		
		Collection<ConsultarHistoricoPendenciasTO> listaHistorico = 
			new ArrayList<ConsultarHistoricoPendenciasTO>();
		
		if(!UtilColecao.isVazio(col)) {
			for(SaapHistPendenciaAdm saapHistPendenciaAdm : col) {
				AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(
						saapHistPendenciaAdm.getSeqUsuario());
				consultarHistoricoPendenciasTO = new ConsultarHistoricoPendenciasTO(); 
				consultarHistoricoPendenciasTO.setSaapHistPendenciaAdm(saapHistPendenciaAdm);
				consultarHistoricoPendenciasTO.setNomeUsuario(aaUsuario.getNomeUsuario());
				listaHistorico.add(consultarHistoricoPendenciasTO);
			}
		}
		return listaHistorico;
		
	}	
	
}
