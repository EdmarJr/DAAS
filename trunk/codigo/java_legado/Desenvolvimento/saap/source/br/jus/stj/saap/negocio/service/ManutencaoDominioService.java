/*
 * ManutencaoDominioService.java
 * 
 * Data de cria��o: 24/04/2009
 *
 * Desenvolvido por Politec Inform�tica S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.saap.negocio.bo.DominioBO;
import br.jus.stj.alp01.negociospring.service.ManutencaoServiceImpl;
import br.jus.stj.saap.entidade.Dominio;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de Manter Dominio.
 *
 * @author Politec Inform�tica S/A
 */
@Service
public class ManutencaoDominioService extends ManutencaoServiceImpl<Dominio, DominioBO> {

	private DominioBO bo;

	/**
	 * Construtor default para rentringir a cria��o desta classe somente a este pacote.
	 */
	ManutencaoDominioService() {
		// Construtor default
	}

	/**
	 * Sobrescrita do m�todo para inje��o da implementa��o do Bo.
	 * 
	 * @param _bo Bo a ser injetado.
	 */
	@Resource(name = "dominioBO")
	protected void setBo(DominioBO _bo) {
		this.bo = _bo;
	}

	/**
	 * @see br.jus.stj.alp01.arquitetura.negocio.service.ManutencaoService#getBo()
	 */
	@Override
	protected DominioBO getBo() {
		return bo;
	}

    /**
     * Consulta os tipos de documentos.
     * 
     * @return Collection<Dominio>
     */
	@Transactional(readOnly = true)
	 public Collection<Dominio> consultarTiposDocumentos(){
		 return getBo().consultarTiposDocumentos();
	 }
}
