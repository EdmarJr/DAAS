/*
 * ConsultarHistoricoDocumentoVisao.java
 * 
 * Data de cria��o: 23/04/2009
 *
 * Desenvolvido por Politec Ltda.
 * F�brica de Software - Bras�lia
 */
package br.jus.stj.saap.apresentacao.visao;

import br.jus.stj.alp01.jsf.visao.ManutencaoVisao;
import br.jus.stj.saap.entidade.SaapHistDocAdm;


/**
 * Respons�vel pela representa��o da vis�o do caso de uso Consultar Hist�rico do Documento.
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
