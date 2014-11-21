/*
 * SaapFacadeImpl.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.jus.stj.alp01.acessoareameio.negocio.service.ManutencaoAaUsuarioService;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.Dominio;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapArqDocAdm;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.service.ConsultarHistoricoAndamentoPendenciaService;
import br.jus.stj.saap.negocio.service.ConsultarHistoricoAndamentosService;
import br.jus.stj.saap.negocio.service.ConsultarHistoricoDocumentoService;
import br.jus.stj.saap.negocio.service.ConsultarHistoricoPendenciaService;
import br.jus.stj.saap.negocio.service.ManutencaoDominioService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapAcompPendenciaAdmService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapAdmtAdmPresidenciaService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapAdmtPendenciaAdmService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapDocAdmPresidenciaService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapGestaoPresidenciaService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapHistPendenciaAdmService;
import br.jus.stj.saap.negocio.service.ManutencaoSaapPendenciaAdmPresidenciaService;
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.to.ConsultarHistoricoPendenciasTO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.to.PendenciaDetalhadaTO;

/**
 * Classe para acesso aos metodos de negocio.
 *
 * @author Politec Informática S/A
 */
@Service
public class SaapFacadeImpl implements SaapFacade {

	@Resource
	private ManutencaoSaapDocAdmPresidenciaService manutencaoSaapDocAdmPresidenciaService;

	@Resource
	private ManutencaoSaapGestaoPresidenciaService manutencaoSaapGestaoPresidenciaService;

	@Resource
	private ManutencaoSaapPendenciaAdmPresidenciaService 
			manutencaoSaapPendenciaAdmPresidenciaService;

	@Resource
	private ConsultarHistoricoDocumentoService consultarHistoricoDocumentoService;

	@Resource
	private ConsultarHistoricoPendenciaService consultarHistoricoPendenciaService;

	@Resource
	private ConsultarHistoricoAndamentosService consultarHistoricoAndamentosService;

	@Resource
	private ConsultarHistoricoAndamentoPendenciaService consultarHistoricoAndamentoPendenciaService;

	@Resource
	private ManutencaoSaapAdmtAdmPresidenciaService manutencaoSaapAdmtAdmPresidenciaService;

	@Resource
	private ManutencaoSaapAcompPendenciaAdmService manutencaoSaapAcompPendenciaAdmService;

	@Resource
	private ManutencaoDominioService manutencaoDominioService;
	
	@Resource
	private ManutencaoSaapAdmtPendenciaAdmService manutencaoSaapAdmtPendenciaAdmService;
	
	@Resource
	private ManutencaoAaUsuarioService manutencaoAaUsuarioService;

	@Resource
	private ManutencaoSaapHistPendenciaAdmService manutencaoSaapHistPendenciaAdmService;


	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	SaapFacadeImpl() {
		// Construtor
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultar(br.jus.stj.
	 * saap.entidade.SaapDocAdmPresidencia)
	 */
	public Collection<SaapDocAdmPresidencia> consultar(SaapDocAdmPresidencia entidade) {
		return manutencaoSaapDocAdmPresidenciaService.consultar(entidade);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarDocumentosSimilares(
	 * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(
			SaapDocAdmPresidencia documento){
		return manutencaoSaapDocAdmPresidenciaService.consultarDocumentosSimilares(documento);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#incluirArquivosDocumento(java.util.Collection)
	 */
	public void incluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento){
		manutencaoSaapDocAdmPresidenciaService.incluirArquivosDocumento(arquivosDocumento);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#excluirArquivosDocumento(java.util.Collection)
	 */
	public void excluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento){
		manutencaoSaapDocAdmPresidenciaService.excluirArquivosDocumento(arquivosDocumento);
	}

	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarDocumentos(
	 * 		br.jus.stj.saap.to.ConsultarDocumentosTO)
	 */
	public Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO){
		return manutencaoSaapDocAdmPresidenciaService.consultarDocumentos(consultarDocumentosTO);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarPendencias(
	 * 		br.jus.stj.saap.to.ConsultarPendenciasTO)
	 */
	public Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO){
		return manutencaoSaapPendenciaAdmPresidenciaService.consultarPendencias(
				consultarendenciasTO);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterPendenciaDetalhada(java.lang.Integer)
	 */
	public PendenciaDetalhadaTO obterPendenciaDetalhada(Integer identificador) {
		return manutencaoSaapPendenciaAdmPresidenciaService.obterPendenciaDetalhada(identificador);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterProximoDocumento(java.lang.Integer)
	 */
	public SaapDocAdmPresidencia obterProximoDocumento(Integer identificador){
	    return manutencaoSaapDocAdmPresidenciaService.obterProximoDocumento(identificador);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterDocumentoAnterior(java.lang.Integer)
	 */
	public SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador){
	    return manutencaoSaapDocAdmPresidenciaService.obterDocumentoAnterior(identificador);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#incluirDocumento(
	 * 		br.jus.stj.saap.to.DocumentoDetalhadoTO)
	 */
	public Integer incluirDocumento(DocumentoDetalhadoTO to){
		return manutencaoSaapDocAdmPresidenciaService.incluirDocumento(to);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#alterarDocumento(
	 * 		br.jus.stj.saap.to.DocumentoDetalhadoTO)
	 */
	public void alterarDocumento(DocumentoDetalhadoTO to){
		manutencaoSaapDocAdmPresidenciaService.alterarDocumento(to);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#desativarTodas(java.util.Collection)
	 */
	public void desativarTodas(Collection<SaapDocAdmPresidencia> entidades){
		manutencaoSaapDocAdmPresidenciaService.desativarTodas(entidades);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#desativarTodasPendencias(java.util.Collection)
	 */
	public void desativarTodasPendencias(Collection<SaapPendenciaAdmPresidencia> entidades){
		manutencaoSaapPendenciaAdmPresidenciaService.desativarTodas(entidades);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#desativar(
	 * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public void desativar(SaapDocAdmPresidencia entidade){
		manutencaoSaapDocAdmPresidenciaService.desativar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterPrimeiroDocumentoSimilar(
	 * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento){
		return manutencaoSaapDocAdmPresidenciaService.obterPrimeiroDocumentoSimilar(documento);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterProximoDocumentoSimilar(
	 * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento){
		return manutencaoSaapDocAdmPresidenciaService.obterProximoDocumentoSimilar(documento);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterAnteriorDocumentoSimilar(
	 * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
	 */
	public SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento){
		return manutencaoSaapDocAdmPresidenciaService.obterAnteriorDocumentoSimilar(documento);
	}

	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarSaapDocAdmPresidencia()
	 */
	public Collection<SaapDocAdmPresidencia> consultarSaapDocAdmPresidencia() {
		return manutencaoSaapDocAdmPresidenciaService.consultar();
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterSaapDocAdmPresidencia(java.io.Serializable)
	 */
	public SaapDocAdmPresidencia obterSaapDocAdmPresidencia(Serializable identificador) {
		return manutencaoSaapDocAdmPresidenciaService.obter(identificador);		
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterGestaoPeloPeriodo(java.util.Date)
	 */
	public SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo){
		return manutencaoSaapGestaoPresidenciaService.obterGestaoPeloPeriodo(dataPeriodo);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterDocumentoDetalhado(java.lang.Integer)
	 */
	public DocumentoDetalhadoTO obterDocumentoDetalhado(Integer identificador){
		return manutencaoSaapDocAdmPresidenciaService.obterDocumentoDetalhado(identificador);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterUsuario(java.io.Serializable)
	 */
	public AaUsuario obterUsuario(Serializable identificador) {
		return manutencaoSaapPendenciaAdmPresidenciaService.obterUsuario(identificador);		
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterPendencia(java.io.Serializable)
	 */
	public SaapPendenciaAdmPresidencia obterPendencia(Serializable identificador) {
		return manutencaoSaapPendenciaAdmPresidenciaService.obter(identificador);		
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#inserir(br.jus.stj.saap.
	 * entidade.SaapDocAdmPresidencia)
	 */
	public Serializable inserir(SaapDocAdmPresidencia entidade) {
		return manutencaoSaapDocAdmPresidenciaService.inserir(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#salvar(br.jus.stj.saap.
	 * entidade.SaapDocAdmPresidencia)
	 */
	public void salvar(SaapDocAdmPresidencia entidade) {
		manutencaoSaapDocAdmPresidenciaService.salvar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#alterar(br.jus.stj.saap.
	 * entidade.SaapDocAdmPresidencia)
	 */
	public void alterar(SaapDocAdmPresidencia entidade) {
		manutencaoSaapDocAdmPresidenciaService.alterar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#remover(br.jus.stj.saap.
	 * entidade.SaapDocAdmPresidencia)
	 */
	public void remover(SaapDocAdmPresidencia entidade) {
		manutencaoSaapDocAdmPresidenciaService.remover(entidade);
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#removerTodosSaapDocAdmPresidencia(
	 * java.util.Collection)
	 */
	public void removerTodosSaapDocAdmPresidencia(Collection<SaapDocAdmPresidencia> entidades) {
		manutencaoSaapDocAdmPresidenciaService.removerTodos(entidades);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultar(br.jus.stj.saap.
	 * entidade.SaapGestaoPresidencia)
	 */
	public Collection<SaapGestaoPresidencia> consultar(SaapGestaoPresidencia entidade) {
		return manutencaoSaapGestaoPresidenciaService.consultar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarSaapGestaoPresidencia()
	 */
	public Collection<SaapGestaoPresidencia> consultarSaapGestaoPresidencia() {
		return manutencaoSaapGestaoPresidenciaService.consultar();
	}
	
	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#obterSaapGestaoPresidencia(java.io.Serializable)
	 */
	public SaapGestaoPresidencia obterSaapGestaoPresidencia(Serializable identificador) {
		return manutencaoSaapGestaoPresidenciaService.obter(identificador);		
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#inserir(br.jus.stj.saap.
	 * entidade.SaapGestaoPresidencia)
	 */
	public Serializable inserir(SaapGestaoPresidencia entidade) {
		return manutencaoSaapGestaoPresidenciaService.inserir(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#inserir(br.jus.stj.saap.
	 * entidade.SaapPendenciaAdmPresidencia)
	 */
	public Serializable inserir(SaapPendenciaAdmPresidencia entidade) {
		return manutencaoSaapPendenciaAdmPresidenciaService.inserir(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#salvar(br.jus.stj.saap.
	 * entidade.SaapGestaoPresidencia)
	 */
	public void salvar(SaapGestaoPresidencia entidade) {
		manutencaoSaapGestaoPresidenciaService.salvar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#alterar(br.jus.stj.saap.
	 * entidade.SaapGestaoPresidencia)
	 */
	public void alterar(SaapGestaoPresidencia entidade) {
		manutencaoSaapGestaoPresidenciaService.alterar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#remover(br.jus.stj.saap.
	 * entidade.SaapGestaoPresidencia)
	 */
	public void remover(SaapGestaoPresidencia entidade) {
		manutencaoSaapGestaoPresidenciaService.remover(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#removerTodosSaapGestaoPresidencia(
	 * java.util.Collection)
	 */
	public void removerTodosSaapGestaoPresidencia(Collection<SaapGestaoPresidencia> entidades) {
		manutencaoSaapGestaoPresidenciaService.removerTodos(entidades);
	}

    /**
     * @param path SaapArqDocAdm
     * @return byte[]
     */
    public byte[] carregarPDF(final SaapArqDocAdm path)   {
        return manutencaoSaapDocAdmPresidenciaService.carregarPDF(path);
    }

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultar(br.jus.stj.saap.entidade.SaapHistDocAdm)
	 */
	public Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade) {
		return consultarHistoricoDocumentoService.consultar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultar(br.jus.stj.saap.entidade.
	 * SaapHistPendenciaAdm)
	 */
	public Collection<ConsultarHistoricoPendenciasTO> consultar(SaapHistPendenciaAdm entidade) {
		return consultarHistoricoPendenciaService.consultarHistPendencia(entidade);
	}	

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultar(br.jus.stj.saap.entidade.
	 * SaapAdmtAdmPresidencia)
	 */
	public DocumentoDetalhadoTO consultar(SaapAdmtAdmPresidencia entidade) {
		return consultarHistoricoAndamentosService.consultarHistAndamento(entidade); 
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarHistoricoAndamentoPendencia(
	 * 		br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO)
	 */
	public ConsultarHistoricoAndamentoPendenciaTO consultarHistoricoAndamentoPendencia(
			ConsultarHistoricoAndamentoPendenciaTO to) {
		return consultarHistoricoAndamentoPendenciaService.consultarHistoricoAndamentoPendencia(to);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarUsuariosPendencia(SaapAcompPendenciaAdm)
	 * @return Collection<AaUsuario>
	 * @param entidade SaapAcompPendenciaAdm
	 */
	public Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade) {
		return manutencaoSaapAcompPendenciaAdmService.consultarUsuariosPendencia(entidade);
	}

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultarAndamentosPeloFiltro(
     * 		br.jus.stj.saap.to.ConsultarAndamentosTO)
     */
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(
    		ConsultarAndamentosTO to) {
    	return manutencaoSaapAdmtAdmPresidenciaService.consultarAndamentosPeloFiltro(to);
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultarAndamentosPeloDocumento(
     * 		br.jus.stj.saap.entidade.SaapDocAdmPresidencia)
     */
    public ConsultarAndamentosTO consultarAndamentosPeloDocumento(
    		SaapDocAdmPresidencia entidade) {
    	return manutencaoSaapAdmtAdmPresidenciaService.consultarAndamentosPeloDocumento(entidade);
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultarAcompanhamento(
     * 		br.jus.stj.saap.entidade.SaapAcompPendenciaAdm)
     */
    public SaapAcompPendenciaAdm consultarAcompanhamento(SaapAcompPendenciaAdm entidade) {
    	return manutencaoSaapAcompPendenciaAdmService.consultarAcompanhamento(entidade); 
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultarTipoDocumento()
     */
    public Collection<Dominio> consultarTipoDocumento() {
    	return manutencaoDominioService.consultarTiposDocumentos();
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#
     * 		salvar(br.jus.stj.saap.entidade.SaapAcompPendenciaAdm)
     */
    public void salvar(SaapAcompPendenciaAdm entidade) {
    	manutencaoSaapAcompPendenciaAdmService.salvar(entidade);
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#
     * 		inserir(br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia)
     */
    public Serializable inserir(SaapAdmtAdmPresidencia entidade) {
    	return manutencaoSaapAdmtAdmPresidenciaService.inserir(entidade);
    }

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#
	 * 		alterar(br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia)
	 */
	public void alterar(SaapAdmtAdmPresidencia entidade) {
		manutencaoSaapAdmtAdmPresidenciaService.alterar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#
	 * 		alterar(br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
	 */
	public void alterar(SaapPendenciaAdmPresidencia entidade) {
		manutencaoSaapPendenciaAdmPresidenciaService.alterar(entidade);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarAndamentosPendenciaPeloFiltro(
	 * br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO)
	 */
	public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to){
		return manutencaoSaapAdmtPendenciaAdmService.consultarAndamentosPendenciaPeloFiltro(to);
	}

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultarAndamentosPelaPendencia(
     * 		br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
     */
    public ConsultarAndamentosPendenciaTO consultarAndamentosPelaPendencia(
    		SaapPendenciaAdmPresidencia entidade) {
    	return manutencaoSaapAdmtPendenciaAdmService.consultarAndamentosPelaPendencia(entidade);
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#consultar()
     */
    public Collection<SaapPendenciaAdmPresidencia> consultar() {
    	return manutencaoSaapPendenciaAdmPresidenciaService.consultar();
    }

    /**
     * @see br.jus.stj.saap.negocio.SaapFacade#acompanharPendencias(java.util.Collection)
     */
    public void acompanharPendencias(Collection<SaapAcompPendenciaAdm> entidades) {
    	manutencaoSaapPendenciaAdmPresidenciaService.acompanharPendencias(entidades);
    }

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#consultarAcompanhamentos(
	 * 		br.jus.stj.saap.entidade.SaapAcompPendenciaAdm)
	 */
	public Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(
			SaapAcompPendenciaAdm entidade) {
		return manutencaoSaapPendenciaAdmPresidenciaService.consultarAcompanhamentos(entidade);
	}

	/**
	 * @param matricula String
	 * @return br.jus.stj.alp01.acessoareameio.entidade.AaUsuario 
	 */
	public br.jus.stj.alp01.acessoareameio.entidade.AaUsuario 
				obterUsuarioPorMatricula(String matricula) {
		return manutencaoAaUsuarioService.obterPorMatricula(matricula);
	}

	/**
	 * @see br.jus.stj.saap.negocio.SaapFacade#
	 * 		obtemPelaPkETsHistPendencia(br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia)
	 */
	public SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade) {
		return manutencaoSaapHistPendenciaAdmService.obtemPelaPkETsHistPendencia(entidade);
	}
}
