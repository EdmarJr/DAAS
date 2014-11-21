package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.Orgao;

public class DestinatarioDAOImpl extends GenericDAOMssqlImpl<Destinatario>
		implements DestinatarioDAO {

	@Override
	public List<Destinatario> destinatariosPorOrgao(Orgao orgao) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Destinatario> destinatariosPorCargoOrgao(Cargo cargo,
			Orgao orgao) {
		if (orgao != null) {
			Criteria criteria = obterCriteria(Destinatario.class);
			Criteria criteriaDestinatariosOcupacao = criteria
					.createCriteria("destinatariosOcupacao");
			criteriaDestinatariosOcupacao.add(Restrictions.eq("orgao", orgao));
			criteriaDestinatariosOcupacao.add(Restrictions.eq("cargo", cargo));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		}

		return null;
	}

}
