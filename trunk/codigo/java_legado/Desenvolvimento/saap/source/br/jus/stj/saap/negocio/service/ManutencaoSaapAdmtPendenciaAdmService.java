/*
 * ManutencaoSaapAdmtPendenciaAdmService.java
 * 
 * Data de criação: 21/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.negocio.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAcompPendenciaAdmBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapPendenciaAdmPresidenciaBO;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.SaapAcompPendenciaAdmTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;



/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter Andamentos da Pendência
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapAdmtPendenciaAdmService extends 
	ManutencaoServiceImpl<SaapAdmtAdmPresidencia, SaapAdmtAdmPresidenciaBO> {
	
	private SaapAdmtAdmPresidenciaBO bo;
	private SaapHistAdmtAdmBO saapHistAdmtAdmBO;
	private SaapAcompPendenciaAdmBO saapAcompPendenciaAdmBO;
	private SaapPendenciaAdmPresidenciaBO saapPendenciaAdmPresidenciaBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapAdmtPendenciaAdmService() {
		// Construtor default
	}	
	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapAdmtAdmPresidenciaBO")
	protected void setBo(SaapAdmtAdmPresidenciaBO _bo) {
		this.bo = _bo;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	
	protected SaapAdmtAdmPresidenciaBO getBo() {
		return bo;
	}
	
	/**
	 * @param to ConsultarAndamentosPendenciaTO
	 * @return Collection<SaapAdmtAdmPresidencia>
	 */
	@Transactional(readOnly = true)
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to) {
    	return getBo().consultarAndamentosPendenciaPeloFiltro(to);
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
	 * Retorna saapAcompPendenciaAdmBO.
	 * 
	 * @return SaapAcompPendenciaAdmBO
	 */
	public SaapAcompPendenciaAdmBO getSaapAcompPendenciaAdmBO() {
		return saapAcompPendenciaAdmBO;
	}

	/**
	 * Atribui saapAcompPendenciaAdmBO.
	 * 
	 * @param saapAcompPendenciaAdmBO saapAcompPendenciaAdmBO
	 */
	@Resource(name = "saapAcompPendenciaAdmBO")
	public void setSaapAcompPendenciaAdmBO(SaapAcompPendenciaAdmBO saapAcompPendenciaAdmBO) {
		this.saapAcompPendenciaAdmBO = saapAcompPendenciaAdmBO;
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
     * @param entidade SaapPendenciaAdmPresidencia
     * @return ConsultarAndamentosPendenciaTO
     */
	@Transactional(readOnly = true)
    public ConsultarAndamentosPendenciaTO consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade) {
		
		ConsultarAndamentosPendenciaTO to = TOFactory.getInstancia().
				novoConsultarAndamentosPendenciaTO();
		to.setSaapPendenciaAdmPresidencia(entidade);
		
    	Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setLimiteRegistro(null);
		
    	PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
		
    	carregaListaAndamentos(entidade, to);
    	to.setListaAcompanhamentos(carregaListaAcompanhamentos(entidade.getId()));
    	
    	return to;
    }
	
	
	/**
	 * Carrga lista de andamentos.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @param to ConsultarAndamentosPendenciaTO
	 */
	protected void carregaListaAndamentos(SaapPendenciaAdmPresidencia entidade,
			ConsultarAndamentosPendenciaTO to) {
    	SaapAdmtAdmPresidenciaTO saapAdmtAdmPresidenciaTO = null;
		Collection<SaapAdmtAdmPresidencia> col = getBo().consultarAndamentosPelaPendencia(entidade);
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setLimiteRegistro(null);
		if(!UtilColecao.isVazio(col)) {
			for(SaapAdmtAdmPresidencia saapAdmtAdmPresidencia : col) {
				saapAdmtAdmPresidenciaTO = TOFactory.getInstancia().novoSaapAdmtAdmPresidenciaTO();
				saapAdmtAdmPresidenciaTO.setSaapAdmtAdmPresidencia(saapAdmtAdmPresidencia);
				SaapHistAdmtAdm saapHistAdmtAdm = getSaapHistAdmtAdmBO().obtemPelaPkETsEntradaAdmt(
						saapAdmtAdmPresidencia);
				if(UtilObjeto.isReferencia(saapHistAdmtAdm)) {
					AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(
							saapHistAdmtAdm.getSeqUsuario());
					if(UtilObjeto.isReferencia(aaUsuario)) {
						saapAdmtAdmPresidenciaTO.setResponsavel(aaUsuario.getNomeUsuario());
					}
				}
				to.getListaAndamentos().add(saapAdmtAdmPresidenciaTO);
			}
		}
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}	
	
	/**
	 * @param codPendencia Integer
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
	protected Collection<SaapAcompPendenciaAdm> retornarConsultaAcompanhamentos(
						Integer codPendencia){

		SaapPendenciaAdmPresidencia pendencia = EntidadeFactory.getInstancia().
					novoSaapPendenciaAdmPresidencia();
		pendencia.setId(codPendencia);
		
		SaapPendenciaAdmPresidencia retorno = getSaapPendenciaAdmPresidenciaBO().
			obter(codPendencia);
		
		Collection<SaapAcompPendenciaAdm> lista = retorno.getSaapAcompPendenciaAdms();
		
		return lista;
		
	}
	
	/**
	 * @param codPendencia Integer
	 * @return Collection<SaapAcompPendenciaAdmTO>
	 */
	protected Collection<SaapAcompPendenciaAdmTO> carregaListaAcompanhamentos(Integer codPendencia){
		
		Collection<SaapAcompPendenciaAdm> lista = retornarConsultaAcompanhamentos(codPendencia);
		
		Collection<SaapAcompPendenciaAdmTO> listaRetorno = new ArrayList<SaapAcompPendenciaAdmTO>();
		
		if(!UtilColecao.isVazio(lista)) {
			for(SaapAcompPendenciaAdm saapAcompPendenciaAdm : lista) {
				if(UtilObjeto.isReferencia(saapAcompPendenciaAdm)) {
					AaUsuario aaUsuario = getAaUsuarioSaapBO().
							obter(saapAcompPendenciaAdm.getSeqUsuario());
					if(UtilObjeto.isReferencia(aaUsuario)) {
						SaapAcompPendenciaAdmTO to = TOFactory.getInstancia().
							novoSaapAcompPendenciaAdmTO();
						to.setSaapAcompPendenciaAdm(saapAcompPendenciaAdm);
						to.setAcompanhamento(aaUsuario.getNomeUsuario());
						listaRetorno.add(to);
					}
				}
			}
		}	
		
		return listaRetorno;
	}
	

}
