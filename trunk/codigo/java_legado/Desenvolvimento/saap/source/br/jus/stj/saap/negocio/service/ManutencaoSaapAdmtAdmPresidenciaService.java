/*
 * ManutencaoSaapAdmtAdmPresidenciaService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.io.Serializable;
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
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapAdmtAdmPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapGestaoPresidenciaBO;
import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.saap.negocio.bo.SaapHistDocAdmBO;
import br.jus.stj.saap.to.ConsultarAndamentosTO;
import br.jus.stj.saap.to.SaapAdmtAdmPresidenciaTO;
import br.jus.stj.saap.util.constante.Constante;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;
import br.jus.stj.saap.util.fabrica.TOFactory;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapAdmtAdmPresidencia.
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapAdmtAdmPresidenciaService extends 
		ManutencaoServiceImpl<SaapAdmtAdmPresidencia, SaapAdmtAdmPresidenciaBO> {

	private SaapAdmtAdmPresidenciaBO bo;
	private SaapGestaoPresidenciaBO saapGestaoPresidenciaBO;
	private SaapHistAdmtAdmBO saapHistAdmtAdmBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;
	private SaapHistDocAdmBO saapHistDocAdmBO;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapAdmtAdmPresidenciaService() {
		// Construtor default
	}

	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapAdmtAdmPresidenciaBO")
	protected void setBo(SaapAdmtAdmPresidenciaBO _bo) {
		this.bo = _bo;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapAdmtAdmPresidenciaBO getBo() {
		return bo;
	}

	/**
	 * Retorna saapGestaoPresidenciaBO.
	 * 
	 * @return SaapGestaoPresidenciaBO
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
	 * Retorna saapHistDocAdmBO.
	 * 
	 * @return SaapHistDocAdmBO
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
     * Consulta o(s) andamento(s) pelo filtro.
     * 
     * @param to ConsultarAndamentosTO
     * @return Collection<SaapAdmtAdmPresidencia>
     */
	@Transactional(readOnly = true)
    public Collection<SaapAdmtAdmPresidencia> consultarAndamentosPeloFiltro(
    		ConsultarAndamentosTO to) {
    	return getBo().consultarAndamentosPeloFiltro(to);
    }

    /**
     * Consulta o(s) andamento(s) pelo documento.
     * 
     * @param entidade SaapDocAdmPresidencia
     * @return ConsultarAndamentosTO
     */
    @Transactional(readOnly = true)
    public ConsultarAndamentosTO consultarAndamentosPeloDocumento(
    		SaapDocAdmPresidencia entidade) {
    	ConsultarAndamentosTO to = TOFactory.getInstancia().novoConsultarAndamentosTO();
    	to.setSaapDocAdmPresidencia(entidade);
    	SaapGestaoPresidencia saapGestaoPresidencia = getSaapGestaoPresidenciaBO().
    			obterGestaoPeloPeriodo(entidade.getTsEntradaDoc());
    	if(UtilObjeto.isReferencia(saapGestaoPresidencia)) {
    		to.setNomeMinistroPresidente(saapGestaoPresidencia.getNomeMinistroPresidente());
    	}
    	Integer pagina = PaginacaoConsultaHolder.getNumeroPagina();
		Integer limite = PaginacaoConsultaHolder.getLimiteRegistro();
		PaginacaoConsultaHolder.setNumeroPagina(null);
		PaginacaoConsultaHolder.setLimiteRegistro(null);

    	SaapHistDocAdm saapHistDocAdm = getSaapHistDocAdmBO().obtemPelaPkETsEntradaDoc(entidade);

    	PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
    	if(UtilObjeto.isReferencia(saapHistDocAdm)) {
    		AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(saapHistDocAdm.getSeqUsuario());
			if(UtilObjeto.isReferencia(aaUsuario)) {
				to.setAlteradoPor(aaUsuario.getNomeUsuario());
			}
    	}
    	carregaListaAndamentos(entidade, to);
    	return to;
    }

	/**
	 * Carrga lista de andamentos.
	 * 
	 * @param entidade SaapDocAdmPresidencia
	 * @param to ConsultarAndamentosTO
	 */
	protected void carregaListaAndamentos(SaapDocAdmPresidencia entidade,
			ConsultarAndamentosTO to) {
    	SaapAdmtAdmPresidenciaTO saapAdmtAdmPresidenciaTO = null;
		Collection<SaapAdmtAdmPresidencia> col = getBo().consultarAndamentosPeloDocumento(entidade);
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
				to.getListaAndamentos().add(saapAdmtAdmPresidenciaTO);
			}
		}
		PaginacaoConsultaHolder.setNumeroPagina(pagina);
		PaginacaoConsultaHolder.setLimiteRegistro(limite);
	}

    /**
     * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
     * 		inserir(br.jus.stj.alp01.arquitetura.entidade.Entidade)
     */
    @Override
    @Transactional
	public Serializable inserir(SaapAdmtAdmPresidencia entidade) {
    	int sequencial = getBo().obterProximaChave();
    	entidade.getId().setSeqAdmtAdm(sequencial);
    	entidade.setTsEntradaAdmt(new Date());
    	Serializable retorno = getBo().inserir(entidade);

    	SaapHistAdmtAdm saapHistAdmtAdm = getHistAdmtAdm(entidade, entidade.getTsEntradaAdmt(), 
    			Constante.OPERACAO_INCLUSAO);
    	getSaapHistAdmtAdmBO().inserir(saapHistAdmtAdm);

    	return retorno;
    }

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		alterar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional
	public void alterar(SaapAdmtAdmPresidencia entidade) {
		SaapAdmtAdmPresidencia saapAdmtAdmPresidencia = getBo().obter(entidade.getId());
		saapAdmtAdmPresidencia.setDescAdmtAdm(entidade.getDescAdmtAdm());
		getBo().alterar(saapAdmtAdmPresidencia);
		saapAdmtAdmPresidencia.setSaapHistAdmtAdms(entidade.getSaapHistAdmtAdms());
    	SaapHistAdmtAdm saapHistAdmtAdm = getHistAdmtAdm(saapAdmtAdmPresidencia, new Date(), 
    			Constante.OPERACAO_ALTERACAO);
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

    	Iterator<SaapHistAdmtAdm> iterator = entidade.getSaapHistAdmtAdms().iterator();
    	if (iterator.hasNext()) {
    		SaapHistAdmtAdm saapHistAdmtAdmAux = iterator.next();
    		saapHistAdmtAdm.setSeqUsuario(saapHistAdmtAdmAux.getSeqUsuario());
    	}
		return saapHistAdmtAdm;
	}
}
