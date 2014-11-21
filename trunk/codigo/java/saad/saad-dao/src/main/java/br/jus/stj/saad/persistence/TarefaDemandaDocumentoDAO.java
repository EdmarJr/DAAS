package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;

public interface TarefaDemandaDocumentoDAO extends GenericDAO<TarefaDemandaDocumento>{
	public List<TarefaDemandaDocumento> tarefasPendentesLocalUsuarioLazy(Integer primeiraLinha,	Integer maximoPorPagina, Local local, Usuario usuario);
	public int tarefasPendentesLocalUsuarioLazyCount(Local local, Usuario usuario);
}
