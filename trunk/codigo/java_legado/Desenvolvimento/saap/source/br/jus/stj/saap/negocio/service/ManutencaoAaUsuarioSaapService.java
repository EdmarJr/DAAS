/*
 * ManutencaoAaUsuarioSaapService.java
 * 
 * Data de cria��o: 24/04/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */

package br.jus.stj.saap.negocio.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.AaUsuario;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter AaUsuario.
 *
 * @author Politec Inform�tica S/A
 */
@Service
public class ManutencaoAaUsuarioSaapService extends 
	ManutencaoServiceImpl<AaUsuario, AaUsuarioSaapBO> {
	
	private AaUsuarioSaapBO bo;
	
	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	ManutencaoAaUsuarioSaapService() {
		// Construtor default
	}
	
	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "aaUsuarioSaapBO")
	protected void setBo(AaUsuarioSaapBO _bo) {
		this.bo = _bo;
	}
	
	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected AaUsuarioSaapBO getBo() {
		return bo;
	}
	
}
