/*
 * ConsultarHistoricoAndamentoPendencia.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import javax.faces.event.ActionEvent;

import org.richfaces.event.DataScrollerEvent;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.saap.apresentacao.visao.ConsultarHistoricoAndamentoPendenciaVisao;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentoPendenciaTO;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe para controle das acoes do caso de uso de ConsultarHistoricoAndamentoPendencia.
 *
 * @author Politec Informática
 */
public class ConsultarHistoricoAndamentoPendenciaAction extends 
		SaapActionAbstrato<SaapHistAdmtAdm> {

	private SaapFacade facade;

	/**
	 * @return A fachada para acesso aos metodos de negocio
	 */
	@Override
	protected SaapFacade getFachada() {
		if (facade == null) {
			facade = getSaapFacade();
		}
		return facade;
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.Action#getVisao()
	 */
	@Override
	public ConsultarHistoricoAndamentoPendenciaVisao getVisao() {
		return getObjetoDoContexto(ConsultarHistoricoAndamentoPendenciaVisao.class);
	}

	/**
	 * Consulta o histórico do andamento da pendência.
	 */
	public void consultarHistoricoAndamentoPendencia() {
		ConsultarHistoricoAndamentoPendenciaTO to = null;
		ConsultarHistoricoAndamentoPendenciaVisao visao = getVisao();
		removerValoresDePaginacao();
		to = getFachada().consultarHistoricoAndamentoPendencia(visao.getConsultaTO());
		if(isReferencia(to) && !isVazio(to.getListaAndamentos())) {
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS_POPUP);
			configurarPaginacao(to.getListaAndamentos());
			visao.setResultadoTO(to);
		}else {
			addMensagemInformativaChave("framework.mensagem.nenhumregistro");
		}
	}
	
	/**
	 * Limpa lista histórico.
	 */
	public void limpaListaHistorico() {
		getVisao().getResultadoTO().setListaAndamentos(null);
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		consultarHistoricoAndamentoPendencia();
	}
}

