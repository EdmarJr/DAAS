/*
 * AcordaoTOAbstrato.java
 * 
 * Data de cria��o: 15/01/2009
 *
 * Desenvolvido por Politec Ltda.
 * F�brica de Software - Bras�lia
 */
package br.jus.stj.saap.to;


import br.jus.stj.saap.util.fabrica.TOFactory;
import br.jus.stj.alp01.comum.to.TOAbstrato;

/**
 * Respons�vel pela infraestrutura das TO's.
 * 
 * @author adrianop
 */
public abstract class SaapTOAbstrato extends TOAbstrato {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return f�brica de TO
	 */
	protected TOFactory getTOFactory() {
		return TOFactory.getInstancia();
	}

}
