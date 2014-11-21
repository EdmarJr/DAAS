/*
 * ManutencaoSaapHistPendenciaAdmService.java
 * 
 * Data de cria��o: 24/04/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.negocio.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.saap.negocio.bo.SaapHistPendenciaAdmBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapHistPendenciaAdm.
 *
 * @author Politec Inform�tica S/A
 */
@Service
public class ManutencaoSaapHistPendenciaAdmService extends 
		ManutencaoServiceImpl<SaapHistPendenciaAdm, SaapHistPendenciaAdmBO> {
	
	private SaapHistPendenciaAdmBO bo;
	
	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	ManutencaoSaapHistPendenciaAdmService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapHistPendenciaAdmBO")
	protected void setBo(SaapHistPendenciaAdmBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapHistPendenciaAdmBO getBo() {
		return bo;
	}

	/**
	 * Retorna hist�rico da pend�ncia pela pk e tsHistPendencia.
	 * 
	 * @param entidade SaapPendenciaAdmPresidencia
	 * @return SaapHistPendenciaAdm
	 */
	@Transactional(readOnly = true)
	public SaapHistPendenciaAdm obtemPelaPkETsHistPendencia(SaapPendenciaAdmPresidencia entidade) {
		return getBo().obtemPelaPkETsHistPendencia(entidade);
	}
}
