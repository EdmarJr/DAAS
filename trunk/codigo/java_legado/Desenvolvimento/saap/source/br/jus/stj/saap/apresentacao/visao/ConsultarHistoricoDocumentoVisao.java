/*
 * ConsultarHistoricoDocumentoVisao.java
 * 
 * Data de criação: 23/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.apresentacao.visao;

import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapHistDocAdm;


/**
 * Responsável pela representação da visão do caso de uso Consultar Histórico do Documento.
 * @author jesler.santos
 */
public class ConsultarHistoricoDocumentoVisao extends ManutencaoVisao<SaapHistDocAdm> {

	private String seqDocAdm;

	/**
	 * Retorna seqDocAdm.
	 * 
	 * @return String
	 */
	public String getSeqDocAdm() {
		return seqDocAdm;
	}

	/**
	 * Atribui seqDocAdm.
	 * 
	 * @param seqDocAdm seqDocAdm
	 */
	public void setSeqDocAdm(String seqDocAdm) {
		this.seqDocAdm = seqDocAdm;
	}
}
