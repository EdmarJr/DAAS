/*
 * ManutencaoSaapHistDocAdmService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */

package br.jus.stj.saap.negocio.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.jus.stj.saap.negocio.bo.SaapHistDocAdmBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.SaapHistDocAdm;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter SaapHistDocAdm.
 *
 * @author Politec Informática S/A
 */
@Service
public class ManutencaoSaapHistDocAdmService extends 
		ManutencaoServiceImpl<SaapHistDocAdm, SaapHistDocAdmBO> {
	
	private SaapHistDocAdmBO bo;
	
	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ManutencaoSaapHistDocAdmService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do método para injeção da implementação do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "saapHistDocAdmBO")
	protected void setBo(SaapHistDocAdmBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected SaapHistDocAdmBO getBo() {
		return bo;
	}
	
}
