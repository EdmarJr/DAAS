/*
 * GeracaoRelatorioDocumentoDataSource.java
 * 
 * Data de criação: 05/08/2009
 *
 * Desenvolvido por Politec Informática S/A.
 * Fábrica de Projetos - Brasília
 */
package br.jus.stj.saap.apresentacao.datasource;

import java.util.HashMap;
import java.util.Map;

import br.jus.stj.alp01.relatorio.datasource.ObjetoDataSourceAbstrato;
import br.jus.stj.saap.to.DocumentoDetalhadoTO;

/**
 * @author lmartins
 *
 */
public class GeracaoRelatorioDocumentoDataSource extends ObjetoDataSourceAbstrato {

	/**
	 * 
	 * @see br.jus.stj.alp01.relatorio.datasource.
	 * RelatorioDataSourceAbstrato#doCarregarMapaDeAtributos()
	 */
	@Override
	protected Map doCarregarMapaDeAtributos() {
		Map atributos = new HashMap();
		atributos.put("arquivos", ((DocumentoDetalhadoTO) getObjeto()).getArquivosDocumento());
		atributos.put("andamentos", ((DocumentoDetalhadoTO) getObjeto()).getListaAndamentosTO());
		return atributos;
	}	
}
