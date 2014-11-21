/*
 * SaapFacade.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import br.jus.stj.alp01.arquitetura.negocio.Facade;
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
import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.ConsultarDocumentosTO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.to.ConsultarHistoricoPendenciasTO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.to.PendenciaDetalhadaTO;

/**
 * Interface para acesso aos metodos de negocio.
 *
 * @author Politec Informática S/A
 */
public interface SaapFacade extends Facade {

	/**
	 * Consulta os objetos referente aos parametros encapsulados no objeto informado como parametro.
	 * 
	 * @param entidade Objeto com os parametros de consulta
	 * 
	 * @return Colecao com o resultado da consulta
	 */
	Collection<SaapDocAdmPresidencia> consultar(SaapDocAdmPresidencia entidade);

	/**
	 * Consulta todos os objetos da entidade SaapDocAdmPresidencia.
	 * 
	 * @return Colecao com todos os objetos da entidade
	 */
	Collection<SaapDocAdmPresidencia> consultarSaapDocAdmPresidencia();

	/**
	 * Obtem o objeto da entidade SaapDocAdmPresidencia pelo seu identificador.
	 * 
	 * @param identificador O identificador da entidade
	 * 
	 * @return Entidade obtida
	 */
	SaapDocAdmPresidencia obterSaapDocAdmPresidencia(Serializable identificador);

	/**
	 * Inclui a entidade informada como parametro na base de dados.
	 * 
	 * @param entidade Entidade a ser inserida
	 * 
	 * @return Chave da entidade inserida na base de dados
	 */
	Serializable inserir(SaapDocAdmPresidencia entidade);

	/**
	 * inclui a entidade caso ele não exista, senão, atualiza a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser salva
	 */
	void salvar(SaapDocAdmPresidencia entidade);

	/**
	 * Altera a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser alterada
	 */
	void alterar(SaapDocAdmPresidencia entidade);

	/**
	 * Remove a entidade da base de dados.
	 * 
	 * @param entidade Entidade a ser removida com o identificador encapsulado
	 */
	void remover(SaapDocAdmPresidencia entidade);

	/**
	 * Remove todas as entidades da base de dados.
	 * 
	 * @param entidades Entidades a serem removidas
	 */
	void removerTodosSaapDocAdmPresidencia(Collection<SaapDocAdmPresidencia> entidades);
	
	/**
	 * Consulta os objetos referente aos parametros encapsulados no objeto informado como parametro.
	 * 
	 * @param entidade Objeto com os parametros de consulta
	 * 
	 * @return Colecao com o resultado da consulta
	 */
	Collection<SaapGestaoPresidencia> consultar(SaapGestaoPresidencia entidade);
	
	/**
	 * Consulta documentos similares.
	 * 
	 * @param documento
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	Collection<SaapDocAdmPresidencia> consultarDocumentosSimilares(SaapDocAdmPresidencia documento);
	
	/**
	 * Obtem próximo documento.
	 * 
	 * @param identificador
	 * @return SaapDocAdmPresidencia
	 */
	SaapDocAdmPresidencia obterProximoDocumento(Integer identificador);
	
	/**
	 * Obtem documento anterior.
	 * 
	 * @param identificador
	 * @return SaapDocAdmPresidencia
	 */
	SaapDocAdmPresidencia obterDocumentoAnterior(Integer identificador);
	
	/**
	 * Inclui documento.
	 * 
	 * @param to
	 * @return Integer
	 */
	Integer incluirDocumento(DocumentoDetalhadoTO to);
	
	/**
	 * Altera documento.
	 * 
	 * @param to
	 */
	void alterarDocumento(DocumentoDetalhadoTO to);
	
	/**
	 * Obtem gestão pelo período.
	 * 
	 * @param dataPeriodo
	 * @return SaapGestaoPresidencia
	 */
	SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo);
	
	/**
	 * Inclui arquivos documento.
	 * 
	 * @param arquivosDocumento
	 */
	void incluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento);

	/**
	 * Exclui arquivos documento.
	 * 
	 * @param arquivosDocumento
	 */
	void excluirArquivosDocumento(Collection<SaapArqDocAdm> arquivosDocumento);

	/**
	 * Desativar todos documentos.
	 * 
	 * @param entidades
	 */
	void desativarTodas(Collection<SaapDocAdmPresidencia> entidades);
	
	/**
	 * Desativar todas as pendências.
	 * 
	 * @param entidades
	 */
	void desativarTodasPendencias(Collection<SaapPendenciaAdmPresidencia> entidades);
	
	/**
	 * Desativar documento.
	 * 
	 * @param entidade
	 */
	void desativar(SaapDocAdmPresidencia entidade);
	
	/**
	 * Obtem o primeiro documento similar.
	 * 
	 * @param documento
	 * @return SaapDocAdmPresidencia
	 */
	SaapDocAdmPresidencia obterPrimeiroDocumentoSimilar(SaapDocAdmPresidencia documento);
	
	/**
	 * Obtem o próximo documento similar.
	 * 
	 * @param documento
	 * @return SaapDocAdmPresidencia
	 */
	SaapDocAdmPresidencia obterProximoDocumentoSimilar(SaapDocAdmPresidencia documento);
	
	/**
	 * Obtem o documento anterior similar.
	 * 
	 * @param documento
	 * @return SaapDocAdmPresidencia
	 */
	SaapDocAdmPresidencia obterAnteriorDocumentoSimilar(SaapDocAdmPresidencia documento);
	
	/**
	 * Consulta pendências.
	 * 
	 * @param consultarendenciasTO
	 * @return Collection<SaapPendenciaAdmPresidencia>
	 */
	Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO);
	
	/**
	 * Obtem pendência detalhada.
	 * 
	 * @param identificador
	 * @return PendenciaDetalhadaTO
	 */
	PendenciaDetalhadaTO obterPendenciaDetalhada(Integer identificador);
	
	/**
	 * Obtem documento detalhado.
	 * @param identificador
	 * @return DocumentoDetalhadoTO
	 */
	DocumentoDetalhadoTO obterDocumentoDetalhado(Integer identificador);
	
	
	/**
	 * Consulta documento(s).
	 * @param consultarDocumentosTO
	 * @return Collection<SaapDocAdmPresidencia>
	 */
	Collection<SaapDocAdmPresidencia> consultarDocumentos(
			ConsultarDocumentosTO consultarDocumentosTO);

	/**
	 * Consulta todos os objetos da entidade SaapGestaoPresidencia.
	 * 
	 * @return Colecao com todos os objetos da entidade
	 */
	Collection<SaapGestaoPresidencia> consultarSaapGestaoPresidencia();

	/**
	 * Obtem o objeto da entidade SaapGestaoPresidencia pelo seu identificador.
	 * 
	 * @param identificador O identificador da entidade
	 * 
	 * @return Entidade obtida
	 */
	SaapGestaoPresidencia obterSaapGestaoPresidencia(Serializable identificador);

	
	/**
	 * Carrega pdf.
	 * 
	 * @param path
	 * @return byte[]
	 */
	byte[] carregarPDF(final SaapArqDocAdm path);
	
	/**
	 * Inclui a entidade informada como parametro na base de dados.
	 * 
	 * @param entidade Entidade a ser inserida
	 * 
	 * @return Chave da entidade inserida na base de dados
	 */
	Serializable inserir(SaapGestaoPresidencia entidade);

	/**
	 * Inclui a entidade informada como parametro na base de dados.
	 * 
	 * @param entidade Entidade a ser inserida
	 * 
	 * @return Chave da entidade inserida na base de dados
	 */
	Serializable inserir(SaapPendenciaAdmPresidencia entidade);

	/**
	 * inclui a entidade caso ele não exista, senão, atualiza a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser salva
	 */
	void salvar(SaapGestaoPresidencia entidade);

	/**
	 * Altera a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser alterada
	 */
	void alterar(SaapGestaoPresidencia entidade);

	/**
	 * Remove a entidade da base de dados.
	 * 
	 * @param entidade Entidade a ser removida com o identificador encapsulado
	 */
	void remover(SaapGestaoPresidencia entidade);

	/**
	 * Remove todas as entidades da base de dados.
	 * 
	 * @param entidades Entidades a serem removidas
	 */
	void removerTodosSaapGestaoPresidencia(Collection<SaapGestaoPresidencia> entidades);

	/**
	 * Consulta os objetos referente aos parametros encapsulados no objeto informado como parametro.
	 * 
	 * @param entidade Objeto com os parametros de consulta
	 * 
	 * @return Colecao com o resultado da consulta
	 */
	Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade);

	/**
	 * Consulta os objetos referente aos parametros encapsulados no objeto informado como parametro.
	 * 
	 * @param entidade Objeto com os parametros de consulta
	 * 
	 * @return Colecao com o resultado da consulta
	 */
	Collection<ConsultarHistoricoPendenciasTO> consultar(SaapHistPendenciaAdm entidade);

	/**
	 * Consulta os objetos referente aos parametros encapsulados no objeto informado como parametro.
	 * 
	 * @param entidade Objeto com os parametros de consulta
	 * 
	 * @return Colecao com o resultado da consulta
	 */
	DocumentoDetalhadoTO consultar(SaapAdmtAdmPresidencia entidade);	

	/**
	 * Consulta o histórico do andamento da pendência.
	 * 
	 * @param to ConsultarHistoricoAndamentoPendenciaTO
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	ConsultarHistoricoAndamentoPendenciaTO consultarHistoricoAndamentoPendencia(
			ConsultarHistoricoAndamentoPendenciaTO to);

	/**
	 * @return Collection<AaUsuario>
	 * @param entidade SaapAcompPendenciaAdm
	 */
	Collection<AaUsuario> consultarUsuariosPendencia(SaapAcompPendenciaAdm entidade);

    /**
     * Consulta o(s) andamento(s) pelo filtro.
     * 
     * @param to ConsultarAndamentosTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(ConsultarAndamentosTO to);

    /**
     * Consulta o(s) andamento(s) pelo documento.
     * 
     * @param entidade SaapDocAdmPresidencia
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    ConsultarAndamentosTO consultarAndamentosPeloDocumento(SaapDocAdmPresidencia entidade);
    
    /**
     * Consulta o(s) andamento(s) pela pendência
     * 
     * @param entidade SaapPendenciaAdmPresidencia
     * @return ConsultarAndamentosPendenciaTO
     */
    ConsultarAndamentosPendenciaTO consultarAndamentosPelaPendencia
    			(SaapPendenciaAdmPresidencia entidade);    

    /**
     * Consulta o(s) acompanhamento(s).
     * 
     * @param entidade SaapAcompPendenciaAdm
     * @return SaapAcompPendenciaAdm
     */
    SaapAcompPendenciaAdm consultarAcompanhamento(SaapAcompPendenciaAdm entidade);

    /**
     * Consulta os tipos de documentos.
     * 
     * @return Collection<Dominio>
     */
    Collection<Dominio> consultarTipoDocumento();

    /**
     * 
     * Salvar Acompanhamento de Pendência
     * 
     * @param entidade SaapAcompPendenciaAdm
     */
    void salvar(SaapAcompPendenciaAdm entidade);

	/**
	 * Inclui a entidade informada como parametro na base de dados.
	 * 
	 * @param entidade Entidade a ser inserida
	 * 
	 * @return Chave da entidade inserida na base de dados
	 */
    Serializable inserir(SaapAdmtAdmPresidencia entidade);

	/**
	 * Altera a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser alterada
	 */
    void alterar(SaapAdmtAdmPresidencia entidade);

	/**
	 * Altera a entidade na base de dados.
	 * 
	 * @param entidade Entidade a ser alterada
	 */
    void alterar(SaapPendenciaAdmPresidencia entidade);

    /**
     * Consulta o(s) andamento(s) pendência pelo filtro.
     * 
     * @param to ConsultarAndamentosPendenciaTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
    Collection<SaapAdmtAdmPresidencia> consultarAndamentosPendenciaPeloFiltro(
    		ConsultarAndamentosPendenciaTO to);

    /**
     * Consulta a(s) pendência(s) ativa(s) em ordem crescente (tsEntradaPendencia).
     * 
     * @return Collection<SaapPendenciaAdmPresidencia>
     */
    Collection<SaapPendenciaAdmPresidencia> consultar();

	/**
	 * Registra o acompanhamento da(s) pendência(s). 
	 * 
	 * @param entidades Collection<SaapAcompPendenciaAdm>
	 */
    void acompanharPendencias(Collection<SaapAcompPendenciaAdm> entidades);

	/**
	 * Consulta o(s) acompanhamento(s) pelo id da pendência e seqUsuario.
	 * 
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
    Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(SaapAcompPendenciaAdm entidade);

	/**
	 * Retorna o usuário pela chave.
	 * 
	 * @param identificador Serializable
	 * @return AaUsuario
	 */
    AaUsuario obterUsuario(Serializable identificador);

	/**
	 * Retorna a pendência pela chave.
	 * 
	 * @param identificador Serializable
	 * @return SaapPendenciaAdmPresidencia
	 */
    SaapPendenciaAdmPresidencia obterPendencia(Serializable identificador);

    /**
     * Retorna o objeto AaUsuario do acessoa a areia meio
     * @param matricula String
     * @return  br.jus.stj.alp01.acessoareameio.entidade.AaUsuario 
     */
    br.jus.stj.alp01.acessoareameio.entidade.AaUsuario 
    		obterUsuarioPorMatricula(String matricula); 

	/**
	 * Retorna histórico da pendência pela pk e tsHistPendencia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @return SaapHistPendenciaAdm
	 */
    SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade);
}
