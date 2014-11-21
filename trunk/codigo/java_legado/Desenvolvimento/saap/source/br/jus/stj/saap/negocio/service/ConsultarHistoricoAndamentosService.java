/*
 * ConsultarHistoricoAndamentosService.java
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
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapDocAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapGestaoPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapHistDocAdmBO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de ConsultarHistoricoAndamentos
 *
 * @author Politec Informática S/A
 */
@Service
public class ConsultarHistoricoAndamentosService  extends 
ManutencaoServiceImpl<SaapHistAdmtAdm, SaapHistAdmtAdmBO>{
	
	private SaapHistAdmtAdmBO saapHistAdmtAdmBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	private SaapDocAdmPresidenciaBO saapDocAdmPresidenciaBO;
	private SaapGestaoPresidenciaBO saapGestaoPresidenciaBO;
	private SaapHistDocAdmBO saapHistDocAdmBO;
	private SaapAdmtAdmPresidenciaBO saapAdmtAdmPresidenciaBO;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ConsultarHistoricoAndamentosService() {
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
	protected SaapHistAdmtAdmBO getBo() {
		return saapHistAdmtAdmBO;
	}

	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * @param saapHistAdmtAdmBO saapHistAdmtAdmBO
	 */
	@Resource(name = "saapHistAdmtAdmBO")
	public void setBo(SaapHistAdmtAdmBO saapHistAdmtAdmBO) {
		this.saapHistAdmtAdmBO = saapHistAdmtAdmBO;
	}		
	
	
	/**
	 * 
	 * Consultar o Histórico de Andamentos
	 * @param entidade entidade
	 * @return DocumentoDetalhadoTO
	 */
	@Transactional(readOnly = true)
	public DocumentoDetalhadoTO consultarHistAndamento(
			SaapAdmtAdmPresidencia entidade){
		
		SaapDocAdmPresidencia doc = getSaapDocAdmPresidenciaBO().obterDocumento(
				entidade.getSaapDocAdmPresidencia().getId());
		
		return criaListaHistorico(doc, entidade);
	}
	
	/**
	 * 
	 * @param doc SaapDocAdmPresidencia
	 * @param saapAdmtAdmPresidencia SaapAdmtAdmPresidencia
	 * @return DocumentoDetalhadoTO
	 */
	private DocumentoDetalhadoTO criaListaHistorico(SaapDocAdmPresidencia doc,
			SaapAdmtAdmPresidencia saapAdmtAdmPresidencia){
		
		Collection<SaapHistAdmtAdm> col = getBo().consultar(saapAdmtAdmPresidencia);
		
		Collection<ConsultarHistoricoAndamentosTO> listaHistorico = new 
			ArrayList<ConsultarHistoricoAndamentosTO>();
		
		Integer sequencial = 1;
		Integer seqAdmtAdmAnt = null;
		
		if(!UtilColecao.isVazio(col)) {
			for(SaapHistAdmtAdm saapHistAdmtAdm : col) {
				
				ConsultarHistoricoAndamentosTO consultarHistoricoAndamentosTO = 
					addHistoricoAndamentosTO(saapHistAdmtAdm);
				
				listaHistorico.add(consultarHistoricoAndamentosTO);
				
				if ((seqAdmtAdmAnt != null) && (!saapHistAdmtAdm.getSaapAdmtAdmPresidencia().
						getId().getSeqAdmtAdm().equals(seqAdmtAdmAnt))) {
					sequencial++;
				}
				
				saapHistAdmtAdm.setId(sequencial);
				seqAdmtAdmAnt = saapHistAdmtAdm.getSaapAdmtAdmPresidencia().getId().getSeqAdmtAdm();
			}
		}
		return obterDocumentoDetalhado(doc, listaHistorico);
	}	
	
	/**
	 * @param saapHistAdmtAdm SaapHistAdmtAdm
	 * @return ConsultarHistoricoAndamentosTO
	 */
	private ConsultarHistoricoAndamentosTO addHistoricoAndamentosTO(
				SaapHistAdmtAdm saapHistAdmtAdm) {
		
		ConsultarHistoricoAndamentosTO consultarHistoricoAndamentosTO = null;
		
		AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(saapHistAdmtAdm.getSeqUsuario());
		
		consultarHistoricoAndamentosTO = TOFactory.getInstancia().
			novoConsultarHistoricoAndamentosTO();
		consultarHistoricoAndamentosTO.setSaapHistAdmtAdm(saapHistAdmtAdm);
		consultarHistoricoAndamentosTO.setNomeUsuario(aaUsuario.getNomeUsuario());
		
		return consultarHistoricoAndamentosTO;
	}
	
	/**
	 * Procedimento retorna um DocumentoDetalhadoTO preenchido
	 * @param SaapDocAdmPresidencia doc
	 * @return DocumentoDetalhadoTO
	 */
	private DocumentoDetalhadoTO obterDocumentoDetalhado(SaapDocAdmPresidencia doc, 
			Collection<ConsultarHistoricoAndamentosTO> listaHistorico ) {

		SaapGestaoPresidencia gestao = getSaapGestaoPresidenciaBO().
		obterGestaoPeloPeriodo(doc.getTsEntradaDoc());

		SaapHistDocAdm histDocAdm = getSaapHistDocAdmBO().
				obterUltimoHistorico(doc.getId());

		DocumentoDetalhadoTO documentoDetalhadoTO = TOFactory.getInstancia().
					novoDocumentoDetalhadoTO();
		documentoDetalhadoTO.setDocumentoBasico(doc);
		documentoDetalhadoTO.setGestaoDocumento(gestao);
		if(UtilObjeto.isReferencia(histDocAdm)) {
			AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(histDocAdm.getSeqUsuario());
			documentoDetalhadoTO.setNomeUsuarioAlteracao(aaUsuario.getNomeUsuario());
		}

		documentoDetalhadoTO.setListaHistAndamentos(listaHistorico);

		return documentoDetalhadoTO;
	}

	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * @return saapDocAdmPresidenciaBO saapDocAdmPresidenciaBO
	 */
	public SaapDocAdmPresidenciaBO getSaapDocAdmPresidenciaBO() {
		return saapDocAdmPresidenciaBO;
	}

	
	/**
	 * Atribui saapDocAdmPresidenciaBO.
	 * 
	 * @param saapDocAdmPresidenciaBO saapDocAdmPresidenciaBO
	 */
	@Resource(name = "saapDocAdmPresidenciaBO")	
	public void setSaapDocAdmPresidenciaBO(
			SaapDocAdmPresidenciaBO saapDocAdmPresidenciaBO) {
		this.saapDocAdmPresidenciaBO = saapDocAdmPresidenciaBO;
	}

	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * @return saapGestaoPresidenciaBO saapGestaoPresidenciaBO
	 */
	public SaapGestaoPresidenciaBO getSaapGestaoPresidenciaBO() {
		return saapGestaoPresidenciaBO;
	}

	/**
	 * Atribui saapGestaoPresidenciaBO.
	 * 
	 * @param saapGestaoPresidenciaBO saapGestaoPresidenciaBO
	 */
	@Resource(name = "saapGestaoPresidenciaBO")	
	public void setSaapGestaoPresidenciaBO(
			SaapGestaoPresidenciaBO saapGestaoPresidenciaBO) {
		this.saapGestaoPresidenciaBO = saapGestaoPresidenciaBO;
	}

	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * @return saapHistDocAdmBO saapHistDocAdmBO
	 */
	public SaapHistDocAdmBO getSaapHistDocAdmBO() {
		return saapHistDocAdmBO;
	}

	/**
	 * Atribui saapHistDocAdmBO.
	 * 
	 * @param saapHistDocAdmBO saapHistDocAdmBO
	 */
	@Resource(name = "saapHistDocAdmBO")	
	public void setSaapHistDocAdmBO(SaapHistDocAdmBO saapHistDocAdmBO) {
		this.saapHistDocAdmBO = saapHistDocAdmBO;
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
	
	
	
}
