/*
 * ConsultarPendenciasTO.java
 * 
 * Data de criação: 22/01/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.to;

import java.util.Date;

import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;

public class ConsultarPendenciasTO extends SaapTOAbstrato{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataInicial;
    private Date dataFinal;
    private SaapPendenciaAdmPresidencia entidadePendencia;
    private String descAcompanhamentoPendencia;

    
    
    
    public String getDescAcompanhamentoPendencia() {
		return descAcompanhamentoPendencia;
	}

	public void setDescAcompanhamentoPendencia(String descAcompanhamentoPendencia) {
		this.descAcompanhamentoPendencia = descAcompanhamentoPendencia;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
    
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public SaapPendenciaAdmPresidencia getEntidadePendencia() {
		if (entidadePendencia == null) {
			entidadePendencia = new SaapPendenciaAdmPresidencia();
		}
		return entidadePendencia;
	}

	
	public void setEntidadePendencia(
			SaapPendenciaAdmPresidencia entidadePendencia) {
		this.entidadePendencia = entidadePendencia;
	}
    

	
	
}
