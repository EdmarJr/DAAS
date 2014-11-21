/*
 * PendenciaDetalhadaTO.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.to;

import java.util.ArrayList;
import java.util.Collection;

import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;

/**
 * Respons�vel pela representa��o das informa��es do detalhe da pend�ncia.
 * @author Politec Inform�tica
 */
public class PendenciaDetalhadaTO extends SaapTOAbstrato{

	private SaapPendenciaAdmPresidencia pendenciaBasica;
	private Collection<SaapAcompPendenciaAdmTO> acompanhamentosPendencia;
	private Collection<SaapAdmtAdmPresidenciaTO> andamentosPendencia;

	/**
	 * Retorna Collection<SaapAdmtAdmPresidenciaTO>.
	 * 
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public Collection<SaapAdmtAdmPresidenciaTO> getAndamentosPendencia() {
		if (andamentosPendencia == null) {
			andamentosPendencia = new ArrayList<SaapAdmtAdmPresidenciaTO>();
		}
		return andamentosPendencia;
	}

	/**
	 * Atribui andamentosPendencia.
	 * 
	 * @param andamentosPendencia Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public void setAndamentosPendencia(
			Collection<SaapAdmtAdmPresidenciaTO> andamentosPendencia) {
		this.andamentosPendencia = andamentosPendencia;
	}

	/**
	 * Retorna SaapPendenciaAdmPresidencia.
	 * 
	 * @return SaapPendenciaAdmPresidencia
	 */
	public SaapPendenciaAdmPresidencia getPendenciaBasica() {
		if (pendenciaBasica == null){
			pendenciaBasica = new SaapPendenciaAdmPresidencia();
		}
		return pendenciaBasica;
	}

	/**
	 * Atribui pendenciaBasica.
	 * 
	 * @param pendenciaBasica SaapPendenciaAdmPresidencia
	 */ 
	public void setPendenciaBasica(SaapPendenciaAdmPresidencia pendenciaBasica) {
		this.pendenciaBasica = pendenciaBasica;
	}

	/**
	 * Retorna Collection<SaapAcompPendenciaAdmTO>.
	 * 
	 * @return Collection<SaapAcompPendenciaAdmTO>
	 */
	public Collection<SaapAcompPendenciaAdmTO> getAcompanhamentosPendencia() {
		return acompanhamentosPendencia;
	}

	/**
	 * Atribui acompanhamentosPendencia.
	 * 
	 * @param acompanhamentosPendencia Collection<SaapAcompPendenciaAdmTO>
	 */
	public void setAcompanhamentosPendencia(
			Collection<SaapAcompPendenciaAdmTO> acompanhamentosPendencia) {
		this.acompanhamentosPendencia = acompanhamentosPendencia;
	}
}
