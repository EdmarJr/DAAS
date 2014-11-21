package br.jus.stj.saad.persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.related.Cargo;
import br.jus.stj.saad.entity.related.Destinatario;
import br.jus.stj.saad.entity.related.DestinatarioOcupacao;
import br.jus.stj.saad.entity.related.Orgao;

public class DestinatarioOcupacaoDAOImpl extends GenericDAOMssqlImpl<DestinatarioOcupacao> implements DestinatarioOcupacaoDAO {

	@Override
	public DestinatarioOcupacao buscar(Orgao orgao, Cargo cargo,
			Destinatario destinatario) {
		
		Criteria criteria = obterCriteria(DestinatarioOcupacao.class);
		criteria.add(Restrictions.eq("orgao", orgao));
		criteria.add(Restrictions.eq("cargo", cargo));
		criteria.add(Restrictions.eq("destinatario", destinatario));
		
		return (DestinatarioOcupacao) criteria.uniqueResult();
	}
	
}
