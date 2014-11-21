/*
 * ManutencaoSaapPendenciaAdmPresidenciaService.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAcompPendenciaAdmBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapHistPendenciaAdmBO;
import br.jus.stj.saap.negocio.bo.SaapPendenciaAdmPresidenciaBO;
import br.jus.stj.saap.to.ConsultarPendenciasTO;
import br.jus.stj.saap.to.PendenciaDetalhadaTO;
import br.jus.stj.saap.to.SaapAcompPendenciaAdmTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Responsável pela
 * @author Politec Informática
 */
@Service
public class ManutencaoSaapPendenciaAdmPresidenciaService 
		extends ManutencaoServiceImpl<SaapPendenciaAdmPresidencia, SaapPendenciaAdmPresidenciaBO >{

	private SaapPendenciaAdmPresidenciaBO bo;
	private SaapAdmtAdmPresidenciaBO boAndamento;
	private SaapHistAdmtAdmBO saapHistAdmtAdmBO;
	private SaapAcompPendenciaAdmBO saapAcompPendenciaAdmBO;
	private SaapHistPendenciaAdmBO saapHistPendenciaAdmBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;

	/**
	 * Construtor
	 */
	ManutencaoSaapPendenciaAdmPresidenciaService() {
		// Construtor default
	}
	

	/**
	 * Atribui _bo
	 * 
	 * @param _bo SaapPendenciaAdmPresidenciaBO
	 */
	@Resource(name = "saapPendenciaAdmPresidenciaBO")
	protected void setBo(SaapPendenciaAdmPresidenciaBO _bo) {
		this.bo = _bo;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapPendenciaAdmPresidenciaBO getBo() {
		return bo;
	}

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		inserir(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional
	public Serializable inserir(SaapPendenciaAdmPresidencia entidade) {
    	entidade.setTsEntradaPendencia(new Date());
    	Serializable retorno = getBo().inserir(entidade);

		getSaapHistPendenciaAdmBO().inserir(getSaapHistPendenciaAdmPreenchida(entidade, 
				entidade.getTsEntradaPendencia(), Constante.OPERACAO_INCLUSAO));

		if(!UtilColecao.isVazio(entidade.getSaapAdmtAdmPresidencias())) {
	    	Iterator<SaapAdmtAdmPresidencia> iterator = entidade.getSaapAdmtAdmPresidencias().
	    			iterator();
		   	if (iterator.hasNext()) {
		   		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = iterator.next();
		   		saapAdmtAdmPresidencia.setSaapPendenciaAdmPresidencia(entidade);
		   		inserirAndamento(saapAdmtAdmPresidencia, entidade.getTsEntradaPendencia());
		   	}
		}
		return retorno;
	}

	/**
	 * Insere o andamento e o histórico do andamento.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @param data Date
	 */
	protected void inserirAndamento(SaapAdmtAdmPresidencia entidade, Date data) {
    	int sequencial = getBoAndamento().obterProximaChave();
    	entidade.getId().setSeqAdmtAdm(sequencial);
    	entidade.setTsEntradaAdmt(data);
    	getBoAndamento().inserir(entidade);

    	SaapHistAdmtAdm saapHistAdmtAdm = getHistAdmtAdm(entidade, data, 
    			Constante.OPERACAO_INCLUSAO);
    	getSaapHistAdmtAdmBO().inserir(saapHistAdmtAdm);
    }

	/**
	 * Retorna o SaapHistAdmtAdm carregado para inclusão.
	 * 
	 * @param entidade SaapAdmtAdmPresidencia
	 * @param data Date
	 * @param operacao String
	 * @return SaapHistAdmtAdm
	 */
	protected SaapHistAdmtAdm getHistAdmtAdm(SaapAdmtAdmPresidencia entidade, Date data, 
			String operacao) {
		SaapHistAdmtAdm saapHistAdmtAdm = EntidadeFactory.getInstancia().novoSaapHistAdmtAdm();
    	saapHistAdmtAdm.setSaapAdmtAdmPresidencia(entidade);
    	saapHistAdmtAdm.setTsHistAdmt(data);
    	saapHistAdmtAdm.setDescAdmtAtual(entidade.getDescAdmtAdm());
    	saapHistAdmtAdm.setNomeAcaoHistAdmt(operacao);

		if(!UtilColecao.isVazio(entidade.getSaapHistAdmtAdms())) {
	    	Iterator<SaapHistAdmtAdm> iterator = entidade.getSaapHistAdmtAdms().iterator();
	    	if (iterator.hasNext()) {
	    		SaapHistAdmtAdm saapHistAdmtAdmAux = iterator.next();
	    		saapHistAdmtAdm.setSeqUsuario(saapHistAdmtAdmAux.getSeqUsuario());
	    	}
		}
		return saapHistAdmtAdm;
	}

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		alterar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional
	public void alterar(SaapPendenciaAdmPresidencia entidade) {
		SaapPendenciaAdmPresidencia spap = getBo().obter(entidade.getId());
		spap.setDescEnderecoPendencia(entidade.getDescEnderecoPendencia());
		spap.setTxtAssuntoPendencia(entidade.getTxtAssuntoPendencia());
    	getBo().alterar(spap);
    	spap.setSaapHistPendenciaAdms(entidade.getSaapHistPendenciaAdms());
		getSaapHistPendenciaAdmBO().inserir(getSaapHistPendenciaAdmPreenchida(spap, 
				new Date(), Constante.OPERACAO_ALTERACAO));
	}

	/**
	 * Consulta pendência(s).
	 * 
	 * @param consultarendenciasTO ConsultarPendenciasTO
	 * @return Collection<SaapPendenciaAdmPresidencia>
	 */
	@Transactional(readOnly = true)
	public Collection<SaapPendenciaAdmPresidencia> consultarPendencias(
			ConsultarPendenciasTO consultarendenciasTO) {
		Collection<SaapPendenciaAdmPresidencia> retorno = null;
		retorno = getBo().consultarPendencias(consultarendenciasTO);
		Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setLimiteRegistro(null);
		if(!UtilColecao.isVazio(retorno)) {
			for(SaapPendenciaAdmPresidencia spap : retorno) {
				SaapHistPendenciaAdm shpa = getSaapHistPendenciaAdmBO().
						obtemPelaPkETsHistPendencia(spap);
				if(UtilObjeto.isReferencia(shpa)) {
					AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(shpa.getSeqUsuario());
					if(UtilObjeto.isReferencia(aaUsuario)) {
						spap.setRegistradoPor(aaUsuario.getNomeUsuario());
					}
				}
			}
		}
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
		return retorno;
	}

	/**
	 * Obtém pendência detalhada.
	 * 
	 * @param identificador Integer
	 * @return PendenciaDetalhadaTO
	 */
	@Transactional(readOnly = true)
	public PendenciaDetalhadaTO obterPendenciaDetalhada(Integer identificador) {
		PendenciaDetalhadaTO pendenciaDetalhadaTO = new PendenciaDetalhadaTO();
		SaapPendenciaAdmPresidencia informacoesBasicasPendencia = getBo().obter(identificador);
		pendenciaDetalhadaTO.setPendenciaBasica(informacoesBasicasPendencia);
		pendenciaDetalhadaTO.setAcompanhamentosPendencia(getAcompanhamentos(
				informacoesBasicasPendencia.getSaapAcompPendenciaAdms()));
		carregaListaAndamentos(informacoesBasicasPendencia, pendenciaDetalhadaTO);
		return pendenciaDetalhadaTO;
	}

	/**
	 * Carrga lista de andamentos.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @param to ConsultarAndamentosPendenciaTO
	 */
	protected void carregaListaAndamentos(SaapPendenciaAdmPresidencia entidade,
			PendenciaDetalhadaTO to) {
    	SaapAdmtAdmPresidenciaTO saapAdmtAdmPresidenciaTO = null;
		Collection<SaapAdmtAdmPresidencia> col = getBoAndamento().
				consultarAndamentosPelaPendencia(entidade);
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
				to.getAndamentosPendencia().add(saapAdmtAdmPresidenciaTO);
			}
		}
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}

	/**
	 * Retorna lista de acompanhamento(s) já configurada. 
	 * 
	 * @param colAcomp Collection<SaapAcompPendenciaAdm>
	 * @return Collection<SaapAcompPendenciaAdmTO>
	 */
	protected Collection<SaapAcompPendenciaAdmTO> getAcompanhamentos(
			Collection<SaapAcompPendenciaAdm> colAcomp){
		Collection<SaapAcompPendenciaAdmTO> listaRetorno = new ArrayList<SaapAcompPendenciaAdmTO>();
		if(!UtilColecao.isVazio(colAcomp)) {
			for(SaapAcompPendenciaAdm saapAcompPendenciaAdm : colAcomp) {
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

	/**
	 * Desativa todas as pendência.
	 * 
	 * @param entidades SaapPendenciaAdmPresidencia
	 */
	@Transactional
	public void desativarTodas(Collection<SaapPendenciaAdmPresidencia> entidades){
		if (!UtilColecao.isVazio(entidades)) {
			Date data = new Date();
			for (SaapPendenciaAdmPresidencia entidade : entidades) {
				getBo().desativar(entidade);
				getSaapHistPendenciaAdmBO().inserir(getSaapHistPendenciaAdmPreenchida(entidade, 
						data, Constante.OPERACAO_ALTERACAO));
			}
		}
	}

	/**
	 * Registra o acompanhamento da(s) pendência(s). 
	 * 
	 * @param entidades Collection<SaapAcompPendenciaAdm>
	 */
	@Transactional
	public void acompanharPendencias(Collection<SaapAcompPendenciaAdm> entidades) {
		if (!UtilColecao.isVazio(entidades)) {
			for (SaapAcompPendenciaAdm entidade : entidades) {
				Collection<SaapAcompPendenciaAdm> col = getSaapAcompPendenciaAdmBO().
						consultarAcompanhamentos(entidade);
				if (UtilColecao.isVazio(col)) {
					getSaapAcompPendenciaAdmBO().inserir(entidade);
				}
			}
		}
	}

	/**
	 * Consulta o(s) acompanhamento(s) pelo id da pendência e seqUsuario.
	 * 
	 * @param entidade SaapAcompPendenciaAdm
	 * @return Collection<SaapAcompPendenciaAdm>
	 */
	@Transactional(readOnly = true)
	public Collection<SaapAcompPendenciaAdm> consultarAcompanhamentos(
			SaapAcompPendenciaAdm entidade) {
		return getSaapAcompPendenciaAdmBO().consultarAcompanhamentos(entidade);
	}

	/**
	 * Retorna SaapHistPendenciaAdm preenchida.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @param data Date
	 * @param operacao String
	 * @return SaapHistPendenciaAdm
	 */
	protected SaapHistPendenciaAdm getSaapHistPendenciaAdmPreenchida(
			SaapPendenciaAdmPresidencia entidade, Date data, String operacao) {
		SaapHistPendenciaAdm saapHistPendenciaAdm = EntidadeFactory.getInstancia().
				novoSaapHistPendenciaAdm();
		saapHistPendenciaAdm.setNomeAcaoHistPendencia(operacao);
		saapHistPendenciaAdm.setTsHistPendencia(data);
		saapHistPendenciaAdm.setSaapPendenciaAdmPresidencia(entidade);

		if(!UtilColecao.isVazio(entidade.getSaapHistPendenciaAdms())) {
	    	Iterator<SaapHistPendenciaAdm> iterator = entidade.getSaapHistPendenciaAdms().
	    			iterator();
	    	if (iterator.hasNext()) {
	    		SaapHistPendenciaAdm saapHistPendenciaAdmAux = iterator.next();
	    		saapHistPendenciaAdm.setSeqUsuario(saapHistPendenciaAdmAux.getSeqUsuario());
	    	}
		}
		return saapHistPendenciaAdm;
	}

	/**
	 * Retorna o usuário pela chave.
	 * 
	 * @param identificador Serializable
	 * @return AaUsuario
	 */
	@Transactional(readOnly = true)
	public AaUsuario obterUsuario(Serializable identificador) {
		return getAaUsuarioSaapBO().obter(identificador);
	}

	/**
	 * Atribui boAndamento.
	 * 
	 * @param boAndamento SaapAdmtAdmPresidenciaBO
	 */
	@Resource(name = "saapAdmtAdmPresidenciaBO")
    protected void setBoAndamento(SaapAdmtAdmPresidenciaBO boAndamento) {
        this.boAndamento = boAndamento;
    }	

	/**
	 * Retorna SaapAdmtAdmPresidenciaBO.
	 * 
	 * @return SaapAdmtAdmPresidenciaBO
	 */
	protected SaapAdmtAdmPresidenciaBO getBoAndamento() {
		return boAndamento;
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
	public void setSaapAcompPendenciaAdmBO(
			SaapAcompPendenciaAdmBO saapAcompPendenciaAdmBO) {
		this.saapAcompPendenciaAdmBO = saapAcompPendenciaAdmBO;
	}

	/**
	 * Retorna saapHistPendenciaAdmBO.
	 * 
	 * @return SaapHistPendenciaAdmBO
	 */
	public SaapHistPendenciaAdmBO getSaapHistPendenciaAdmBO() {
		return saapHistPendenciaAdmBO;
	}

	/**
	 * Atribui saapHistPendenciaAdmBO.
	 * 
	 * @param saapHistPendenciaAdmBO saapHistPendenciaAdmBO
	 */
	@Resource(name = "saapHistPendenciaAdmBO")
	public void setSaapHistPendenciaAdmBO(
			SaapHistPendenciaAdmBO saapHistPendenciaAdmBO) {
		this.saapHistPendenciaAdmBO = saapHistPendenciaAdmBO;
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
}
