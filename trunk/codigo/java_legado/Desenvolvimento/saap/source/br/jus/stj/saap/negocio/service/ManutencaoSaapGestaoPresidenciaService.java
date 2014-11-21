/*
 * ManutencaoSaapGestaoPresidenciaService.java
 * 
 * Data de cria��o: 22/01/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.arquitetura.excecao.NegocioException;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.SaapGestaoPresidencia;
import br.jus.stj.saap.negocio.bo.SaapGestaoPresidenciaBO;
import br.jus.stj.saap.util.UtilDate;
import br.jus.stj.saap.util.constante.Mensagem;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de 
 * Manter SaapGestaoPresidencia.
 *
 * @author Politec Inform�tica S/A
 */
@Service
public class ManutencaoSaapGestaoPresidenciaService 
		extends ManutencaoServiceImpl<SaapGestaoPresidencia, SaapGestaoPresidenciaBO> {

	private SaapGestaoPresidenciaBO bo;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	ManutencaoSaapGestaoPresidenciaService() {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapGestaoPresidenciaBO")
	protected void setBo(SaapGestaoPresidenciaBO _bo) {
		this.bo = _bo;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapGestaoPresidenciaBO getBo() {
		return bo;
	}

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		consultar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<SaapGestaoPresidencia> consultar(SaapGestaoPresidencia entidade) {
		return getBo().consultar(entidade);
	}

	/**
	 * Obt�m gest�o pelo per�odo.
	 * 
	 * @param dataPeriodo Date
	 * @return SaapGestaoPresidencia
	 */
	@Transactional(readOnly = true)
	public SaapGestaoPresidencia obterGestaoPeloPeriodo(Date dataPeriodo) {
		return getBo().obterGestaoPeloPeriodo(dataPeriodo);
	}

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		inserir(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional
	public Serializable inserir(SaapGestaoPresidencia entidade) {
		Serializable id = null;

		if (UtilObjeto.isReferencia(entidade)) {
			SaapGestaoPresidencia ultimaGestao = getBo().consultarUltimaGestao();

			//Valida a data inicial
			if (UtilObjeto.isReferencia(ultimaGestao)){
				validarDataInicialNovaGestao(entidade,ultimaGestao);
			}

			//Insere a nova gest�o
			id = getBo().inserir(entidade);

			//Altera a data final da gest�o anterior, se houver, com D-1
			if (UtilObjeto.isReferencia(ultimaGestao)){
				Date dataInicialGestaoCorrente = entidade.getDtInicialVigGestao();
				ultimaGestao.setDtFinalVigGestao(UtilDate.adicionaDias(
						dataInicialGestaoCorrente, -1));
				getBo().alterar(ultimaGestao);
			}
		}
		return id;
	}

	/**
	 * @see br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl#
	 * 		alterar(br.jus.stj.alp01.arquitetura.entidade.Entidade)
	 */
	@Override
	@Transactional
	public void alterar(SaapGestaoPresidencia entidade) {
		if (UtilObjeto.isReferencia(entidade)) {

			//Se a altera��o for da gest�o corrente (n�o possui data final)
			if (entidade.getDtFinalVigGestao()==null){
				SaapGestaoPresidencia ultimaGestao = getBo().consultarUltimaGestaoParaAlteracao();

				//Valida a data inicial
				if (UtilObjeto.isReferencia(ultimaGestao)){
					validarDataInicialNovaGestao(entidade,ultimaGestao);
				}

				getBo().alterar(entidade);

				//Altera a data final da gest�o anterior, se houver, com D-1
				if (UtilObjeto.isReferencia(ultimaGestao)){
					Date dataInicialGestaoCorrente = entidade.getDtInicialVigGestao();
					ultimaGestao.setDtFinalVigGestao(UtilDate.adicionaDias(
							dataInicialGestaoCorrente, -1));
					getBo().alterar(ultimaGestao);
				}
				//Se a altera��o for de uma gest�o anterior
			}else{
				getBo().alterar(entidade);
			}
		}
	}

	/**
	 * Valida a data inicial da nova gest�o a ser inserida.
	 * 
	 * @param gestaoCorrente SaapGestaoPresidencia
	 * @param gestaoAnterior SaapGestaoPresidencia
	 */
	protected void validarDataInicialNovaGestao(SaapGestaoPresidencia gestaoCorrente,
			SaapGestaoPresidencia gestaoAnterior) {

		if (UtilObjeto.isReferencia(gestaoAnterior)) {
			Date dataInicialGestaoAnterior = gestaoAnterior.getDtInicialVigGestao();
			Date dataInicialGestaoCorrente = gestaoCorrente.getDtInicialVigGestao();

			//Cr�tica de Data inicial inconsistente
			if (dataInicialGestaoCorrente.compareTo(dataInicialGestaoAnterior)<=0){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = formatter.format(dataInicialGestaoAnterior); 
				throw new NegocioException(Mensagem.getMA026(),dataFormatada);
			}
		}
	}
}
