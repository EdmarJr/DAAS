/*
 * ConsultarAndamentosTO.java
 * 
 * Data de criação: 08/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import java.util.Collection;
import java.util.Date;

import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação das informações dos dados dos andamentos.
 * @author jesler.santos
 */
public class ConsultarAndamentosTO extends SaapTOAbstrato {

	private SaapDocAdmPresidencia saapDocAdmPresidencia;
	private String descAdmtAdm;
	private Date dataInicio;
	private Date dataFim;
	private String nomeMinistroPresidente;
	private String alteradoPor;
	private Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos;

	/**
	 * Retorna alteradoPor.
	 * 
	 * @return String
	 */
	public String getAlteradoPor() {
		return alteradoPor;
	}

	/**
	 * Atribui alteradoPor.
	 * 
	 * @param alteradoPor alteradoPor
	 */
	public void setAlteradoPor(String alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	/**
	 * Retorna nomeMinistroPresidente.
	 * 
	 * @return String
	 */
	public String getNomeMinistroPresidente() {
		return nomeMinistroPresidente;
	}

	/**
	 * Atribui nomeMinistroPresidente.
	 * 
	 * @param nomeMinistroPresidente nomeMinistroPresidente
	 */
	public void setNomeMinistroPresidente(String nomeMinistroPresidente) {
		this.nomeMinistroPresidente = nomeMinistroPresidente;
	}

	/**
	 * Retorna listaAndamentos.
	 * 
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public Collection<SaapAdmtAdmPresidenciaTO> getListaAndamentos() {
		if (listaAndamentos == null) {
			listaAndamentos = getColecaoFactory().novoArrayList();
		}
		return listaAndamentos;
	}

	/**
	 * Atribui andamentos.
	 * 
	 * @param listaAndamentos andamentos
	 */
	public void setListaAndamentos(Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos) {
		this.listaAndamentos = listaAndamentos;
	}

	/**
	 * Retorna saapDocAdmPresidencia.
	 * 
	 * @return SaapDocAdmPresidencia
	 */
	public SaapDocAdmPresidencia getSaapDocAdmPresidencia() {
		if (saapDocAdmPresidencia == null) {
			saapDocAdmPresidencia = EntidadeFactory.getInstancia().novoSaapDocAdmPresidencia();
		}
		return saapDocAdmPresidencia;
	}

	/**
	 * Atribui saapDocAdmPresidencia.
	 * 
	 * @param saapDocAdmPresidencia saapDocAdmPresidencia
	 */
	public void setSaapDocAdmPresidencia(
			SaapDocAdmPresidencia saapDocAdmPresidencia) {
		this.saapDocAdmPresidencia = saapDocAdmPresidencia;
	}

	/**
	 * Retorna descAdmtAdm.
	 * 
	 * @return String
	 */
	public String getDescAdmtAdm() {
		return descAdmtAdm;
	}

	/**
	 * Atribui descAdmtAdm.
	 * 
	 * @param descAdmtAdm descAdmtAdm
	 */
	public void setDescAdmtAdm(String descAdmtAdm) {
		this.descAdmtAdm = descAdmtAdm;
	}

	/**
	 * Retorna dataInicio.
	 * 
	 * @return Date
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Atribui dataInicio.
	 * 
	 * @param dataInicio dataInicio
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Retorna dataFim.
	 * 
	 * @return Date
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Atribui dataFim.
	 * 
	 * @param dataFim dataFim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}
