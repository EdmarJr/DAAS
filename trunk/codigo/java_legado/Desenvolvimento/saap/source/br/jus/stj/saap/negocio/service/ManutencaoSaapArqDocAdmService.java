/*
 * ManutencaoSaapArqDocAdmService.java
 * 
 * Data de cria��o: 24/04/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.negocio.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.jus.stj.saap.negocio.bo.SaapArqDocAdmBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.SaapArqDocAdm;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapArqDocAdm.
 *
 * @author Politec Inform�tica S/A
 */
@Service
public class ManutencaoSaapArqDocAdmService extends 
		ManutencaoServiceImpl<SaapArqDocAdm, SaapArqDocAdmBO> {
	
	private SaapArqDocAdmBO bo;
	
	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	ManutencaoSaapArqDocAdmService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapArqDocAdmBO")
	protected void setBo(SaapArqDocAdmBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapArqDocAdmBO getBo() {
		return bo;
	}
	
}
