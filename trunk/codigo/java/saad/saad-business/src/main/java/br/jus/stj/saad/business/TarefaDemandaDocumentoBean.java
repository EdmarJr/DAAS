package br.jus.stj.saad.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.persistence.GenericDAO;
import br.jus.stj.saad.persistence.TarefaDemandaDocumentoDAO;

/**
 * Session Bean implementation class Document
 */
@Stateless
@javax.ejb.LocalBean
public class TarefaDemandaDocumentoBean extends Bean<TarefaDemandaDocumento> {

	@Inject
	private TarefaDemandaDocumentoDAO tarefaDemandaDocumentoDAO;
	
	@Override
	public GenericDAO<TarefaDemandaDocumento> getDao() {
		return tarefaDemandaDocumentoDAO;
	}
	
	public List<TarefaDemandaDocumento> tarefasPendentesLocalUsuarioLazy(Integer primeiraLinha,	Integer maximoPorPagina, Local local, Usuario usuario){
		return tarefaDemandaDocumentoDAO.tarefasPendentesLocalUsuarioLazy(primeiraLinha,	maximoPorPagina, local, usuario);
	}
	
	public int tarefasPendentesLocalUsuarioLazyCount(Local local, Usuario usuario){
		return tarefaDemandaDocumentoDAO.tarefasPendentesLocalUsuarioLazyCount(local, usuario);
	}

}
