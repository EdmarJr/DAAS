/*
 * ConsultarHistoricoPendencia.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.apresentacao.controle;

import java.util.Collection;

import javax.faces.event.ActionEvent;

import org.richfaces.event.DataScrollerEvent;

import br.jus.stj.alp01.arquitetura.integracao.PaginacaoConsultaHolder;
import br.jus.stj.alp01.comum.conversor.UtilConversorDeString;
import br.jus.stj.alp01.jsf.util.ContextoExternoUtil;
import br.jus.stj.saap.apresentacao.visao.ConsultarHistoricoPendenciaVisao;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarHistoricoPendenciasTO;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe para controle das acoes do caso de uso de ConsultarHistoricoDocumento.
 *
 * @author Politec Informática
 */
public class ConsultarHistoricoPendenciaAction extends SaapActionAbstrato<SaapHistPendenciaAdm> {

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
	public ConsultarHistoricoPendenciaVisao getVisao() {
		return getObjetoDoContexto(ConsultarHistoricoPendenciaVisao.class);
	}

	/**
	 * Consulta o histórico do documento.
	 */
	public void consultarHistoricoPendencia() {
		ConsultarHistoricoPendenciaVisao visao = getVisao();
		
		SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia = getEntidadeFactory().
			novoSaapPendenciaAdmPresidencia();

		saapPendenciaAdmPresidencia.setId(UtilConversorDeString.converterParaInteger(
				visao.getSeqPendenciaAdm()));

		visao.getConsulta().setSaapPendenciaAdmPresidencia(saapPendenciaAdmPresidencia);
		ContextoExternoUtil.carregarParametros(this, getEntidadeClazz());
		removerValoresDePaginacao();
		Collection<ConsultarHistoricoPendenciasTO> col = getFachada().consultar(visao.getConsulta());
/*		PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS_POPUP);
		col = configurarPaginacao(col);
		getVisao().setListaHistorico(col);
*/		
		if(!isVazio(col)) {
			PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS_POPUP);
			col = configurarPaginacao(col);
			getVisao().setListaHistorico(col);
		}else {
			addMensagemInformativaChave("framework.mensagem.nenhumregistro");
		}
		
		
		
	}
	
	/**
	 * Limpa lista histórico.
	 */
	public void limpaListaHistorico() {
		getVisao().setListaHistorico(null);
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		consultarHistoricoPendencia();
	}	
}
