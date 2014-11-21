/*
 * ConsultarHistoricoAndamentoPendenciaTO.java
 * 
 * Data de criação: 30/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import java.util.Collection;

import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;

/**
 * Responsável pela representação das informações dos dados do histórico do andamento da pendência.
 * @author jesler.santos
 */
public class ConsultarHistoricoAndamentoPendenciaTO extends SaapTOAbstrato {

	private SaapPendenciaAdmPresidencia pendencia;
	private Collection<SaapHistAdmtAdmTO> listaAndamentos;
	private SaapHistAdmtAdm historicoAndamento;
	private String alteradoPor;

	/**
	 * Retorna alteradoPor.
	 * 
	 * @return String
	 */
	public String getAlteradoPor() {
		return alteradoPor;
	}

	/**
	 * Atribui alteradoPor.
	 * 
	 * @param alteradoPor alteradoPor
	 */
	public void setAlteradoPor(String alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	/**
	 * Retorna pendencia.
	 * 
	 * @return SaapPendenciaAdmPresidencia
	 */
	public SaapPendenciaAdmPresidencia getPendencia() {
		if (pendencia == null) {
			pendencia = EntidadeFactory.getInstancia().novoSaapPendenciaAdmPresidencia();
		}
		return pendencia;
	}

	/**
	 * Atribui pendencia.
	 * 
	 * @param pendencia pendencia
	 */
	public void setPendencia(SaapPendenciaAdmPresidencia pendencia) {
		this.pendencia = pendencia;
	}

	/**
	 * Retorna listaAndamentos.
	 * 
	 * @return Collection<SaapHistAdmtAdmTO>
	 */
	public Collection<SaapHistAdmtAdmTO> getListaAndamentos() {
		if (listaAndamentos == null) {
			listaAndamentos = getColecaoFactory().novoArrayList();
		}
		return listaAndamentos;
	}

	/**
	 * Atribui listaAndamentos.
	 * 
	 * @param listaAndamentos listaAndamentos
	 */
	public void setListaAndamentos(Collection<SaapHistAdmtAdmTO> listaAndamentos) {
		this.listaAndamentos = listaAndamentos;
	}

	/**
	 * Retorna historicoAndamento.
	 * 
	 * @return SaapHistAdmtAdm
	 */
	public SaapHistAdmtAdm getHistoricoAndamento() {
		if (historicoAndamento == null) {
			historicoAndamento = EntidadeFactory.getInstancia().novoSaapHistAdmtAdm();
		}
		return historicoAndamento;
	}

	/**
	 * Atribui historicoAndamento.
	 * 
	 * @param historicoAndamento historicoAndamento
	 */
	public void setHistoricoAndamento(SaapHistAdmtAdm historicoAndamento) {
		this.historicoAndamento = historicoAndamento;
	}
}
