package br.jus.stj.saad.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.ControleNumeracao;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;

public class ControleNumeracaoDAOImpl extends GenericDAOMssqlImpl<ControleNumeracao> implements ControleNumeracaoDAO {

	@Override
	public ControleNumeracao obterPorId(TipoDocumento tipoDocumento,
			Local local, Long ano) {
		Criteria criteria = obterCriteria(ControleNumeracao.class);
		criteria.add(Restrictions.eq("tipoDocumento", tipoDocumento));
		criteria.add(Restrictions.eq("local", local));
		criteria.add(Restrictions.eq("ano", ano));
		return (ControleNumeracao) criteria.uniqueResult();
	}
	
}
