/*
 * ConsultarAndamentosPendenciaTO.java
 * 
 * Data de criação: 20/05/2009
 *
 * Desenvolvido por Politec Ltda.
 * Fábrica de Software - Brasília
 */
package br.jus.stj.saap.to;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapAdmtAdmPresidencia;
import br.jus.stj.saap.entidade.SaapPendenciaAdmPresidencia;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;


/**
 * Responsável pela representação das informações dos dados dos andamentos da pendência
 * @author Politec
 */
public class ConsultarAndamentosPendenciaTO  extends SaapTOAbstrato {
	
	private Date dataInicio;
	private Date dataFim;
	private SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia;
	private SaapAdmtAdmPresidencia saapAdmtAdmPresidencia;
	private AaUsuario aaUsuario;
	private Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos;
	private Collection<SaapAcompPendenciaAdmTO> listaAcompanhamentos;
	
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
	
	/**
	 * Retorna saapPendenciaAdmPresidencia.
	 * 
	 * @return SaapPendenciaAdmPresidencia
	 */
	public SaapPendenciaAdmPresidencia getSaapPendenciaAdmPresidencia() {
		
		if (saapPendenciaAdmPresidencia == null) {
			saapPendenciaAdmPresidencia = EntidadeFactory.
				getInstancia().novoSaapPendenciaAdmPresidencia();
		}
		
		return saapPendenciaAdmPresidencia;
	}
	
	/**
	 * Atribui saapPendenciaAdmPresidencia.
	 * 
	 * @param saapPendenciaAdmPresidencia saapPendenciaAdmPresidencia
	 */
	public void setSaapPendenciaAdmPresidencia(
			SaapPendenciaAdmPresidencia saapPendenciaAdmPresidencia) {
		this.saapPendenciaAdmPresidencia = saapPendenciaAdmPresidencia;
	}

	
	/**
	 * Retorna saapAdmtAdmPresidencia.
	 * 
	 * @return SaapAdmtAdmPresidencia
	 */
	public SaapAdmtAdmPresidencia getSaapAdmtAdmPresidencia() {
		if (saapAdmtAdmPresidencia == null) {
			saapAdmtAdmPresidencia = 
				EntidadeFactory.getInstancia().novoSaapAdmtAdmPresidencia();
		}
		
		return saapAdmtAdmPresidencia;
	}

	
	/**
	 * Atribui saapAdmtAdmPresidencia.
	 * 
	 * @param saapAdmtAdmPresidencia saapAdmtAdmPresidencia
	 */
	public void setSaapAdmtAdmPresidencia(
			SaapAdmtAdmPresidencia saapAdmtAdmPresidencia) {
		this.saapAdmtAdmPresidencia = saapAdmtAdmPresidencia;
	}

	
	/**
	 * Retorna aaUsuario.
	 * 
	 * @return AaUsuario
	 */
	public AaUsuario getAaUsuario() {
		
		if (aaUsuario == null) {
			aaUsuario = EntidadeFactory.getInstancia().novoAaUsuario();
		}
		return aaUsuario;

	}

	
	/**
	 * Atribui aaUsuario.
	 * 
	 * @param aaUsuario aaUsuario
	 */
	public void setAaUsuario(AaUsuario aaUsuario) {
		this.aaUsuario = aaUsuario;
	}

	
	/**
	 * Retorna listaAndamentos.
	 * 
	 * @return Collection<SaapAdmtAdmPresidenciaTO>
	 */
	public Collection<SaapAdmtAdmPresidenciaTO> getListaAndamentos() {
		if (listaAndamentos == null) {
			listaAndamentos = new ArrayList<SaapAdmtAdmPresidenciaTO>();
		}
		return listaAndamentos;
	}

	
	/**
	 * Atribui listaAndamentos.
	 * 
	 * @param listaAndamentos listaAndamentos
	 */
	public void setListaAndamentos(
			Collection<SaapAdmtAdmPresidenciaTO> listaAndamentos) {
		this.listaAndamentos = listaAndamentos;
	}

	
	/**
	 * Retorna listaAcompanhamentos.
	 * 
	 * @return Collection<SaapAcompPendenciaAdmTO>
	 */
	public Collection<SaapAcompPendenciaAdmTO> getListaAcompanhamentos() {
		if (listaAcompanhamentos == null) {
			listaAcompanhamentos = new ArrayList<SaapAcompPendenciaAdmTO>();
		}
		
		return listaAcompanhamentos;
	}

	
	/**
	 * Atribui listaAcompanhamentos.
	 * 
	 * @param listaAcompanhamentos listaAcompanhamentos
	 */
	public void setListaAcompanhamentos(
			Collection<SaapAcompPendenciaAdmTO> listaAcompanhamentos) {
		this.listaAcompanhamentos = listaAcompanhamentos;
	}

	

	


}
