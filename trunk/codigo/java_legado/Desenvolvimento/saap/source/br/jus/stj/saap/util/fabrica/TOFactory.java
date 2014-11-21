/*
 * TOFactory.java
 * 
 * Data de criação: 18/11/2008
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.util.fabrica;

import br.jus.stj.saap.to.ConsultarAndamentosPendenciaTO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentosTO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.to.ListarAndamentosTO;
import br.jus.stj.saap.to.ListarPendenciasTO;
import br.jus.stj.saap.to.SaapAcompPendenciaAdmTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.to.SaapHistAdmtAdmTO;

/**
 * Responsável pela construção de TO's.
 * 
 * @author adrianop
 */
public final class TOFactory {

	private static TOFactory	instancia	= new TOFactory();

	/**
	 * Retorna instância única da fábrica.
	 * 
	 * @return instância única da fábrica.
	 */
	public static TOFactory getInstancia() {
		return instancia;
	}

	/**
	 * Construtor
	 */
	private TOFactory() {
		// Classe fábrica
	}
	
	/**
	 * @return ConsultarHistoricoAndamentosTO
	 */
	public ConsultarHistoricoAndamentosTO novoConsultarHistoricoAndamentosTO() {
		return new ConsultarHistoricoAndamentosTO();
	}

	/**
	 * @return DocumentoDetalhadoTO
	 */
	public DocumentoDetalhadoTO novoDocumentoDetalhadoTO() {
		return new DocumentoDetalhadoTO();
	}

	/**
	 * @return ConsultarHistoricoAndamentoPendenciaTO
	 */
	public ConsultarHistoricoAndamentoPendenciaTO novoConsultarHistoricoAndamentoPendenciaTO() {
		return new ConsultarHistoricoAndamentoPendenciaTO();
	}

	/**
	 * @return SaapHistAdmtAdmTO
	 */
	public SaapHistAdmtAdmTO novoSaapHistAdmtAdmTO() {
		return new SaapHistAdmtAdmTO();
	}

	/**
	 * @return ConsultarAndamentosTO
	 */
	public ConsultarAndamentosTO novoConsultarAndamentosTO() {
		return new ConsultarAndamentosTO();
	}

	/**
	 * @return SaapAdmtAdmPresidenciaTO
	 */
	public SaapAdmtAdmPresidenciaTO novoSaapAdmtAdmPresidenciaTO() {
		return new SaapAdmtAdmPresidenciaTO();
	}

	/**
	 * @return ConsultarAndamentosPendenciaTO
	 */
	public ConsultarAndamentosPendenciaTO novoConsultarAndamentosPendenciaTO() {
		return new ConsultarAndamentosPendenciaTO();
	}

	/**
	 * @return SaapAcompPendenciaAdmTO
	 */
	public SaapAcompPendenciaAdmTO novoSaapAcompPendenciaAdmTO() {
		return new SaapAcompPendenciaAdmTO();
	}

	/**
	 * @return ListarPendenciasTO
	 */
	public ListarPendenciasTO novoListarPendenciasTO() {
		return new ListarPendenciasTO();
	}
	
	/**
	 * @return ListarAndamentosTO
	 */
	public ListarAndamentosTO novoListarAndamentosTO() {
		return new ListarAndamentosTO();
	}

	/**
	 * @return ConsultarPendenciasTO
	 */
	public ConsultarPendenciasTO novoConsultarPendenciasTO() {
		return new ConsultarPendenciasTO();
	}
}
