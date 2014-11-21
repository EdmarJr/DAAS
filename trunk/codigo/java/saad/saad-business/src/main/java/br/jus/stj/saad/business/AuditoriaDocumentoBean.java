package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.auditoria.AuditoriaDocumento;
import br.jus.stj.saad.persistence.AuditoriaDocumentoDAO;
import br.jus.stj.saad.persistence.UsuarioDAO;
	
@Stateless
@javax.ejb.LocalBean
public class AuditoriaDocumentoBean extends Bean<AuditoriaDocumento>{
	
	@Inject
	private AuditoriaDocumentoDAO auditoriaDocumentoDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Override
	public AuditoriaDocumentoDAO getDao() {
		
		return auditoriaDocumentoDAO;
	}
	
	
	public List<AuditoriaDocumento> consultaHistoricoDocumento(
			Documento documento) {

		List<AuditoriaDocumento> auditoriaDocumentos = auditoriaDocumentoDAO
				.consultaHistoricoDocumento(documento);
		for (AuditoriaDocumento auditoriaDocumento : auditoriaDocumentos) {
			
			auditoriaDocumento.setNomeUsuario(usuarioDAO.obterPorId(
					auditoriaDocumento.getId()).getNome());

		}
		
		return auditoriaDocumentos;
	}

}
