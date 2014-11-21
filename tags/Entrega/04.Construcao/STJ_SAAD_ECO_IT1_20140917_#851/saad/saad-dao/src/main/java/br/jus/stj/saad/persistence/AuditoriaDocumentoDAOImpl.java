package br.jus.stj.saad.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.auditoria.AuditoriaDocumento;

public class AuditoriaDocumentoDAOImpl extends
		GenericDAOMssqlImpl<AuditoriaDocumento> implements
		AuditoriaDocumentoDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<AuditoriaDocumento> consultaHistoricoDocumento(
			Documento documento) {

		Criteria criteria = obterCriteria(AuditoriaDocumento.class);

		criteria.add(Restrictions.eq("idDocumento", documento.getId()));

		return criteria.list();
	}

}
