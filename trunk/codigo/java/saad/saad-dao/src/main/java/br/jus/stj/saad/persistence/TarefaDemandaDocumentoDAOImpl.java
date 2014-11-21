package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.TarefaDemandaDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.enumerators.SituacaoTarefaEnum;

public class TarefaDemandaDocumentoDAOImpl extends GenericDAOMssqlImpl<TarefaDemandaDocumento> implements
		TarefaDemandaDocumentoDAO {
	
	@SuppressWarnings("unchecked")
	public List<TarefaDemandaDocumento> tarefasPendentesLocalUsuarioLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local, Usuario usuario){
		
		Criteria criteria = obterCriteria(TarefaDemandaDocumento.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("situacaoTarefa", SituacaoTarefaEnum.PENDENTE.getValor()));
		
		Criteria criteriaDocumento = criteria.createCriteria("documento");
		criteriaDocumento.add(Restrictions.eq("local", local));

		criteria.setFirstResult(primeiraLinha);
		criteria.setMaxResults(maximoPorPagina);
		
		return criteria.list();
	}
	
	public int tarefasPendentesLocalUsuarioLazyCount(Local local, Usuario usuario){
		Criteria criteria = obterCriteria(TarefaDemandaDocumento.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("situacaoTarefa", SituacaoTarefaEnum.PENDENTE.getValor()));		
		criteria.setFetchMode("documento", FetchMode.JOIN)
				.createAlias("documento", "d")
				.add(Restrictions.eq("d.local", local));
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		
		return resultCount.intValue();
	}

}
