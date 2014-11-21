/*
 * ConsultarHistoricoDocumentoService.java
 * 
 * Data de criação: 24/04/2009
 *
 * Desenvolvido por Politec Informática S/A.
 */
package br.jus.stj.saap.negocio.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stj.alp01.comum.colecao.UtilColecao;
import br.jus.stj.alp01.comum.objeto.UtilObjeto;
import br.jus.stj.saap.entidade.AaUsuario;
import br.jus.stj.saap.entidade.SaapDocAdmPresidencia;
import br.jus.stj.saap.entidade.SaapHistDocAdm;
import br.jus.stj.saap.negocio.bo.AaUsuarioSaapBO;
import br.jus.stj.saap.negocio.bo.SaapHistDocAdmBO;
import br.jus.stj.saap.util.fabrica.EntidadeFactory;

/**
 * Classe para encapsulamento da regra de negocio do caso de uso de ConsultarHistoricoDocumento
 *
 * @author Politec Informática S/A
 */
@Service
public class ConsultarHistoricoDocumentoService {

	private SaapHistDocAdmBO saapHistDocAdmBO;
	private AaUsuarioSaapBO aaUsuarioSaapBO;

	/**
	 * Construtor default para rentringir a criação desta classe somente a este pacote.
	 */
	ConsultarHistoricoDocumentoService() {
		// Construtor default
	}

	/**
	 * Retorna saapHistDocAdmBO.
	 * 
	 * @return SaapHistDocAdmBO
	 */
	public SaapHistDocAdmBO getSaapHistDocAdmBO() {
		return saapHistDocAdmBO;
	}

	/**
	 * Atribui saapHistDocAdmBO.
	 * 
	 * @param saapHistDocAdmBO saapHistDocAdmBO
	 */
	@Resource(name = "saapHistDocAdmBO")
	public void setSaapHistDocAdmBO(SaapHistDocAdmBO saapHistDocAdmBO) {
		this.saapHistDocAdmBO = saapHistDocAdmBO;
	}

	/**
	 * Retorna aaUsuarioSaapBO.
	 * 
	 * @return AaUsuarioSaapBO
	 */
	public AaUsuarioSaapBO getAaUsuarioSaapBO() {
		return aaUsuarioSaapBO;
	}

	/**
	 * Atribui aaUsuarioSaapBO.
	 * 
	 * @param aaUsuarioSaapBO aaUsuarioSaapBO
	 */
	@Resource(name = "aaUsuarioSaapBO")
	public void setAaUsuarioSaapBO(AaUsuarioSaapBO aaUsuarioSaapBO) {
		this.aaUsuarioSaapBO = aaUsuarioSaapBO;
	}

	/**
	 * Consulta o histórico do documento.
	 * 
	 * @param entidade SaapHistDocAdm
	 * @return Collection<SaapHistDocAdm>
	 */
	@Transactional(readOnly = true)
	public Collection<SaapHistDocAdm> consultar(SaapHistDocAdm entidade){
		Collection<SaapHistDocAdm> col = getSaapHistDocAdmBO().consultar(entidade);
		if(!UtilColecao.isVazio(col)) {
			for(SaapHistDocAdm saapHistDocAdm : col) {
				AaUsuario aaUsuario = getAaUsuarioSaapBO().obter(saapHistDocAdm.getSeqUsuario());
				SaapDocAdmPresidencia saapDocAdmPresidencia = EntidadeFactory.getInstancia().
						novoSaapDocAdmPresidencia();
				saapHistDocAdm.setSaapDocAdmPresidencia(saapDocAdmPresidencia);
				if(UtilObjeto.isReferencia(aaUsuario)) {
					saapDocAdmPresidencia.setNomeRespConvite(aaUsuario.getNomeUsuario());
				}
			}
		}
		return col;
	}
}
