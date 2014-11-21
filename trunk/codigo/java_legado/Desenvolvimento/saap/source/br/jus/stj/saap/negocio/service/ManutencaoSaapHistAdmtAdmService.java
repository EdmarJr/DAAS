/*
 * ManutencaoSaapHistAdmtAdmService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.jus.stj.saap.negocio.bo.SaapHistAdmtAdmBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapHistAdmtAdm.
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapHistAdmtAdmService extends 
		ManutencaoServiceImpl<SaapHistAdmtAdm, SaapHistAdmtAdmBO> {
	
	private SaapHistAdmtAdmBO bo;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapHistAdmtAdmService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapHistAdmtAdmBO")
	protected void setBo(SaapHistAdmtAdmBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapHistAdmtAdmBO getBo() {
		return bo;
	}
	
}
