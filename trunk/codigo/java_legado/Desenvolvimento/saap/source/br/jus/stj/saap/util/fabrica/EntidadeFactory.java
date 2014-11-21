/*
 * FabricaEntidade.java
 * 
 * Data de cria��o: 18/11/2008
 *
 * Desenvolvido por Politec Ltda.
 * F�brica de Software - Bras�lia
 */
package br.jus.stj.saap.util.fabrica;

import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAcompPendenciaAdm;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistAdmtAdm;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.entidade.SaapHistPendenciaAdm;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.entidade.chave.SaapAdmtAdmPresidenciaPk;


/**
 * Respons�vel pela constru��o de entidade.
 * 
 * @author adrianop
 */
public final class EntidadeFactory {

	private static EntidadeFactory	instancia	= new EntidadeFactory();

	/**
	 * Retorna inst�ncia �nica da f�brica.
	 * 
	 * @return inst�ncia �nica da f�brica.
	 */
	public static EntidadeFactory getInstancia() {
		return instancia;
	}

	/**
	 * Construtor
	 */
	private EntidadeFactory() {
		// Classe f�brica
	}

	/**
	 * @return novo AaUsuario
	 */
	public AaUsuario novoAaUsuario() {
		return new AaUsuario();
	}

	/**
	 * @return novo SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia novoSaapDocAdmPresidencia() {
		return new SaapDocAdmPresidencia();
	}
	
	/**
	 * @return novo SaapHistDocAdm
	 */
	public SaapHistDocAdm novoSaapHistDocAdm() {
		return new SaapHistDocAdm();
	}
	

	/**
	 * @return novo SaapDocAdmPresidencia
	 */
	public SaapPendenciaAdmPresidencia novoSaapPendenciaAdmPresidencia() {
		return new SaapPendenciaAdmPresidencia();
	}

	/**
	 * @return novo SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia novoSaapAdmtAdmPresidencia() {
		return new SaapAdmtAdmPresidencia();
	}

	/**
	 * @return novo SaapHistAdmtAdm
	 */
	public SaapHistAdmtAdm novoSaapHistAdmtAdm() {
		return new SaapHistAdmtAdm();
	}

	/**
	 * @return novo SaapAdmtAdmPresidenciaPk
	 */
	public SaapAdmtAdmPresidenciaPk novoSaapAdmtAdmPresidenciaPk() {
		return new SaapAdmtAdmPresidenciaPk();
	}
	/**
	 * @return novo SaapAcompPendenciaAdm
	 */
	public SaapAcompPendenciaAdm novoSaapAcompPendenciaAdm() {
		return new SaapAcompPendenciaAdm();
	}

	/**
	 * @return novo SaapHistPendenciaAdm
	 */
	public SaapHistPendenciaAdm novoSaapHistPendenciaAdm() {
		return new SaapHistPendenciaAdm();
	}
}
