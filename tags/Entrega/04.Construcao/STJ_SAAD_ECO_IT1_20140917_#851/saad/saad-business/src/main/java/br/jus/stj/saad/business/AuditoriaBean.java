package br.jus.stj.saad.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.persistence.AuditoriaDAO;
import br.jus.stj.saad.persistence.GenericDAO;
import br.jus.stj.saad.persistence.UsuarioDAO;
	
@Stateless
@javax.ejb.LocalBean
public class AuditoriaBean<T> extends Bean<T>{
	
	@Inject
	private AuditoriaDAO<T> auditoriaDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Override
	public GenericDAO<T> getDao() {
		
		return auditoriaDAO;
		
	}
	
	public void incluir(T entidade) {
		
		getDao().incluir(entidade);
		
	}
	
	
//	public List<AuditoriaDocumento> consultaHistoricoDocumento(
//			Documento documento) {
//
//		List<AuditoriaDocumento> auditoriaDocumentos = auditoriaDocumentoDAO
//				.consultaHistoricoDocumento(documento);
//		for (AuditoriaDocumento auditoriaDocumento : auditoriaDocumentos) {
//			
//			auditoriaDocumento.setNomeUsuario(usuarioDAO.obterPorId(
//					auditoriaDocumento.getId()).getNome());
//
//		}
//		
//		return auditoriaDocumentos;
//	}

}
