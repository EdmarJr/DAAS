/*
 * ConsultarHistoricoAndamentoPendenciaService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapHistPendenciaAdmBO;
import br.jus.stj.saap.negocio.bo.SaapPendenciaAdmPresidenciaBO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.to.SaapHistAdmtAdmTO;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de 
 * 		ConsultarHistoricoAndamentoPendencia
 *
 * @author Politec Informática S/A
 */
@Service
public class ConsultarHistoricoAndamentoPendenciaService {

	private SaapHistAdmtAdmBO saapHistAdmtAdmBO;
	private SaapPendenciaAdmPresidenciaBO saapPendenciaAdmPresidenciaBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	private SaapHistPendenciaAdmBO saapHistPendenciaAdmBO;
	private SaapAdmtAdmPresidenciaBO saapAdmtAdmPresidenciaBO;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ConsultarHistoricoAndamentoPendenciaService() {
		// Construtor default
	}

	/**
	 * Retorna saapAdmtAdmPresidenciaBO.
	 * 
	 * @return SaapAdmtAdmPresidenciaBO
	 */
	public SaapAdmtAdmPresidenciaBO getSaapAdmtAdmPresidenciaBO() {
		return saapAdmtAdmPresidenciaBO;
	}

	/**
	 * Atribui saapAdmtAdmPresidenciaBO.
	 * 
	 * @param saapAdmtAdmPresidenciaBO saapAdmtAdmPresidenciaBO
	 */
	@Resource(name = "saapAdmtAdmPresidenciaBO")
	public void setSaapAdmtAdmPresidenciaBO(
			SaapAdmtAdmPresidenciaBO saapAdmtAdmPresidenciaBO) {
		this.saapAdmtAdmPresidenciaBO = saapAdmtAdmPresidenciaBO;
	}

	/**
	 * Retorna saapHistPendenciaAdmBO.
	 * 
	 * @return SaapHistPendenciaAdmBO
	 */
	public SaapHistPendenciaAdmBO getSaapHistPendenciaAdmBO() {
		return saapHistPendenciaAdmBO;
	}

	/**
	 * Atribui saapHistPendenciaAdmBO.
	 * 
	 * @param saapHistPendenciaAdmBO saapHistPendenciaAdmBO
	 */
	@Resource(name = "saapHistPendenciaAdmBO")
	public void setSaapHistPendenciaAdmBO(SaapHistPendenciaAdmBO saapHistPendenciaAdmBO) {
		this.saapHistPendenciaAdmBO = saapHistPendenciaAdmBO;
	}

	/**
	 * Retorna saapHistAdmtAdmBO.
	 * 
	 * @return SaapHistAdmtAdmBO
	 */
	public SaapHistAdmtAdmBO getSaapHistAdmtAdmBO() {
		return saapHistAdmtAdmBO;
	}

	/**
	 * Atribui saapHistAdmtAdmBO.
	 * 
	 * @param saapHistAdmtAdmBO saapHistAdmtAdmBO
	 */
	@Resource(name = "saapHistAdmtAdmBO")
	public void setSaapHistAdmtAdmBO(SaapHistAdmtAdmBO saapHistAdmtAdmBO) {
		this.saapHistAdmtAdmBO = saapHistAdmtAdmBO;
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
	 * Consulta o histórico do andamento da pendência.
	 * 
	 * @param to ConsultarHistoricoAndamentoPendenciaTO
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	@Transactional(readOnly = true)
	public ConsultarHistoricoAndamentoPendenciaTO consultarHistoricoAndamentoPendencia(
			ConsultarHistoricoAndamentoPendenciaTO to){
		SaapAdmtAdmPresidencia saapAdmtAdmPresidenciaConsulta = to.getHistoricoAndamento().
				getSaapAdmtAdmPresidencia();
		ConsultarHistoricoAndamentoPendenciaTO toRetorno = null;
		Collection<SaapHistAdmtAdm> col = getSaapHistAdmtAdmBO().consultar(
				saapAdmtAdmPresidenciaConsulta);
		
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = 
			getSaapPendenciaAdmPresidenciaBO().obter(to.getHistoricoAndamento().
					getSaapAdmtAdmPresidencia().getSaapPendenciaAdmPresidencia().getId());		
		
		if(!UtilColecao.isVazio(col)) {
			toRetorno = getConsultaHistoricoAndamentoPendenciaTO(
					saapAdmtAdmPresidenciaConsulta, col);
			
			toRetorno.setPendencia(saapPendenciaAdmPresidencia);
		}
		return toRetorno;
	}

	/**
	 * Retorna ConsultarHistoricoAndamentoPendenciaTO carregado.
	 * 
	 * @param saapAdmtAdmPresidenciaConsulta SaapAdmtAdmPresidencia
	 * @param col Collection<SaapHistAdmtAdm>
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	protected ConsultarHistoricoAndamentoPendenciaTO getConsultaHistoricoAndamentoPendenciaTO(
			SaapAdmtAdmPresidencia saapAdmtAdmPresidenciaConsulta,Collection<SaapHistAdmtAdm> col) {
		ConsultarHistoricoAndamentoPendenciaTO toRetorno = TOFactory.getInstancia().
				novoConsultarHistoricoAndamentoPendenciaTO();

		SaapHistPendenciaAdm saapHistPendenciaAdm = getSaapHistPendenciaAdmBO().
				obterUltimoHistorico(saapAdmtAdmPresidenciaConsulta.
				getSaapPendenciaAdmPresidencia().getId());

		if(UtilObjeto.isReferencia(saapHistPendenciaAdm)) {
			AaUsuario usuario = getAaUsuarioSaapBO().obter(saapHistPendenciaAdm.getSeqUsuario());
			if(UtilObjeto.isReferencia(usuario)) {
				toRetorno.setAlteradoPor(usuario.getNomeUsuario());
			}
		}
		toRetorno.setPendencia(saapAdmtAdmPresidenciaConsulta.getSaapPendenciaAdmPresidencia());
		geraListaAndamentos(toRetorno, col);
		return toRetorno;
	}
	
	
	private void geraListaAndamentos(ConsultarHistoricoAndamentoPendenciaTO toRetorno,
			Collection<SaapHistAdmtAdm> col) {
		
		Integer sequencial = 1;
		Integer seqAdmtAdmAnt = null;		
		
		for(SaapHistAdmtAdm saapHistAdmtAdm : col) {
			AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(saapHistAdmtAdm.getSeqUsuario());
			SaapHistAdmtAdmTO saapHistAdmtAdmTO = TOFactory.getInstancia().
					novoSaapHistAdmtAdmTO();
			saapHistAdmtAdmTO.setSaapHistAdmtAdm(saapHistAdmtAdm);
			if(UtilObjeto.isReferencia(aaUsuario)) {
				saapHistAdmtAdmTO.setNomeUsuario(aaUsuario.getNomeUsuario());
			}
			toRetorno.getListaAndamentos().add(saapHistAdmtAdmTO);
			
			if ((seqAdmtAdmAnt != null) && (!saapHistAdmtAdm.getSaapAdmtAdmPresidencia().
					getId().getSeqAdmtAdm().equals(seqAdmtAdmAnt))) {
				sequencial++;
			}
			
			saapHistAdmtAdm.setId(sequencial);
			seqAdmtAdmAnt = saapHistAdmtAdm.getSaapAdmtAdmPresidencia().getId().
				getSeqAdmtAdm();			
			
		}		
		
	}
}
