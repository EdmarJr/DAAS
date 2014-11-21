package br.jus.stj.saad.persistence;

import br.jus.stj.saad.entity.EntidadeBase;

public class AuditoriaDAOImpl<T extends EntidadeBase> extends
		GenericDAOMssqlImpl<T> implements AuditoriaDAO<T> {

	// @SuppressWarnings("unchecked")
	// public Map<Long, List<Audit>> list(int fromIndex, int toIndex, T audit) {
	//
	// Query query = manager.createNamedQuery("findAllAuditByFilter");
	// query.setParameter("idDocumento", audit.getIdDocumento());
	// query.setParameter("idUsuario", audit.getIdUsuario());
	// query.setParameter("tipoOperacao", audit.getTipoOperacao());
	//
	// query.setFirstResult(fromIndex);
	// query.setMaxResults(toIndex);
	//
	// List<Audit> listAudit = query.getResultList();
	//
	// manager.clear();
	// query = manager.createNamedQuery("countAllAuditByFilter");
	// query.setParameter("idDocumento", audit.getIdDocumento());
	// query.setParameter("idUsuario", audit.getIdUsuario());
	// query.setParameter("tipoOperacao", audit.getTipoOperacao());
	//
	// Long count = new Long(query.getSingleResult().toString());
	//
	// Map<Long, List<Audit>> mapAudit = new HashMap<Long, List<Audit>>();
	// mapAudit.put(count, listAudit);
	//
	// return mapAudit;
	// }

	// public Audit find(Audit audit) {
	// Query query = manager.createNamedQuery("findAudit");
	// query.setParameter("audit", audit);
	// return (Audit) query.getSingleResult();
	// }

}
