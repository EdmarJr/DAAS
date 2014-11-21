/*
 * ConsultarHistoricoAndamentosAction.java
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
import br.jus.stj.saap.apresentacao.visao.ConsultarHistoricoAndamentosVisao;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.negocio.SaapFacade;
import br.jus.stj.saap.to.ConsultarHistoricoAndamentosTO;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;
import br.jus.stj.saap.util.constante.Constante;

/**
 * Classe para controle das acoes do caso de uso de ConsultarHistoricoAndamentos.
 *
 * @author Politec Informática
 */
public class ConsultarHistoricoAndamentosAction extends SaapActionAbstrato<SaapHistAdmtAdm> {

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
	public ConsultarHistoricoAndamentosVisao getVisao() {
		return getObjetoDoContexto(ConsultarHistoricoAndamentosVisao.class);
	}

	/**
	 * Consulta o histórico do documento.
	 */
	public void consultarHistoricoAndamentos() {
		
		ConsultarHistoricoAndamentosVisao visao = getVisao();
		
		//monta a entidade de envio para a consulta
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = montaEntidadeEnvio();
		removerValoresDePaginacao();
		DocumentoDetalhadoTO docRetorno = getFachada().consultar(saapAdmtAdmPresidencia);
		
		Collection<ConsultarHistoricoAndamentosTO> col = 
			docRetorno.getListaHistAndamentos();
		
		visao.setDocumentoDetalhadoTO(docRetorno);
		PaginacaoConsultaHolder.setTotalRegistros(col.size());
		PaginacaoConsultaHolder.setLimiteRegistro(Constante.LIMITE_REGISTROS_POPUP);
		configurarPaginacao(col);
		getVisao().setListaHistorico(col);
		
	}

	/**
	 * 
	 * Monta a Entidade de filtro para a consulta
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	private SaapAdmtAdmPresidencia montaEntidadeEnvio() {
		
		ConsultarHistoricoAndamentosVisao visao = getVisao();
		
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = 
			getEntidadeFactory().novoSaapAdmtAdmPresidencia();
		
		SaapDocAdmPresidencia saapDocAdmPresidencia = 
			getEntidadeFactory().novoSaapDocAdmPresidencia();
		
		saapDocAdmPresidencia.setId(UtilConversorDeString.converterParaInteger(
				visao.getSeqDocAndamento()));
		
		saapAdmtAdmPresidencia.setSaapPendenciaAdmPresidencia(null);
		saapAdmtAdmPresidencia.setSaapDocAdmPresidencia(saapDocAdmPresidencia);
		return saapAdmtAdmPresidencia;
		
	}

	/**
	 * @see br.jus.stj.alp01.jsf.controle.ConsultaAction#consultar(javax.faces.event.ActionEvent)
	 */
	@Override
	public void consultar(ActionEvent event) {
		DataScrollerEvent scrollerEvent = (DataScrollerEvent) event;
		PaginacaoConsultaHolder.setNumeroPagina(new Integer(scrollerEvent.getPage()));
		consultarHistoricoAndamentos();
	}

	/**
	 * Limpa lista histórico.
	 */
	public void limpaListaHistorico() {
		getVisao().setListaHistorico(null);
	}
}
