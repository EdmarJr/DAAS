/*
 * AcordaoTOAbstrato.java
 * 
 * Data de criação: 15/01/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;


import br.jus.stj.saap.util.fabrica.TOFactory;
import br.jus.stj.alp01.comum.to.TOAbstrato;

/**
 * Responsável pela infraestrutura das TO's.
 * 
 * @author adrianop
 */
public abstract class SaapTOAbstrato extends TOAbstrato {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return fábrica de TO
	 */
	protected TOFactory getTOFactory() {
		return TOFactory.getInstancia();
	}

}
