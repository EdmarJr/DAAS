package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.DestinatarioDocumento;

public class DestinatarioDocumentoDAOImpl extends GenericDAOMssqlImpl<DestinatarioDocumento> implements DestinatarioDocumentoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DestinatarioDocumento> listarPorNome(String query) {
		Criteria criteria = obterCriteria(DestinatarioDocumento.class);
		criteria.add(Restrictions.ilike("nome", query, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
}
