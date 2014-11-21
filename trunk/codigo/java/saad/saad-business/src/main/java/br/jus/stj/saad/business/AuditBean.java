//package br.jus.stj.saad.business;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//
//import br.jus.stj.saad.entity.local.Audit;
//import br.jus.stj.saad.persistence.AuditoriaDAO;
//
///**
// * Session Bean implementation class Audit
// */
//@Stateless
//public class AuditBean implements AuditBusiness {
//
//	@Inject
//	private AuditoriaDAO auditoriaDAO;
//
//	@Override
//	public Map<Long, List<Audit>> getDataPage(int fromIndex, int toIndex,
//			Audit audit) throws Exception {
//
//		return null;//auditoriaDAO.list(fromIndex, toIndex, audit);
//	}
//
//}
