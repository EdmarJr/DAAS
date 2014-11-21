//package br.jus.stj.saad.view.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//
//import org.primefaces.event.data.PageEvent;
//
//import br.jus.stj.saad.business.AuditBusiness;
//import br.jus.stj.saad.entity.local.Audit;
//import br.jus.stj.saad.util.ActionOperation;
//import br.jus.stj.saad.util.YieldDate;
//
//@ManagedBean(name = "auditController")
//@RequestScoped
//public class AuditController extends GenericController {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4285716463343197209L;
//
//	StringBuffer messageError;
//	StringBuffer messageSuccess;
//
//	private List<Audit> auditList;
//
//	private Audit audit;
//
//	@EJB
//	private AuditBusiness auditBusiness;
//
//	public AuditController() throws Exception {
//
//	}
//
//	@PostConstruct
//	private void init() {
//
//		this.audit = new Audit();
//		this.audit.setIdDocumento(null);
//		this.audit.setIdUsuario(null);
//		this.audit.setYieldDate(new YieldDate());
//		this.auditList = new ArrayList<Audit>();
//
//	}
//
//	public List<Audit> getAuditList() {
//		return auditList;
//	}
//
//	public void setAuditList(List<Audit> auditList) {
//		this.auditList = auditList;
//	}
//
//	public Audit getAudit() {
//		return audit;
//	}
//
//	public void setAudit(Audit audit) {
//		this.audit = audit;
//	}
//
//	public ActionOperation[] getActionOperation() {
//		return ActionOperation.values();
//	}
//
//	public void dataTableChange(PageEvent event) throws Exception {
//
//		System.out.println("dataTableChange()............");
//
//		int fromIndex = (10 * event.getPage());
//		int toIndex = (fromIndex + 10);
//
//		Map<Long, List<Audit>> dataPage = this.auditBusiness.getDataPage(
//				fromIndex, toIndex, this.audit);
//
//		Long fullSize = dataPage.keySet().iterator().next();
//
//		List<Audit> data = dataPage.get(fullSize);
//
//		while (this.auditList.size() < fullSize) {
//			this.auditList.add(null);
//		}
//
//		int iSubList = 0;
//		while (iSubList < data.size()) {
//
//			Audit audit = data.get(iSubList);
//			this.auditList.set(iSubList + fromIndex, audit);
//
//			iSubList++;
//		}
//
//		System.out.println("this.auditList ::: " + this.auditList);
//	}
//
//	public void search() throws Exception {
//
//		System.out.println("search()........");
//
//		Map<Long, List<Audit>> dataPage = this.auditBusiness.getDataPage(0, 10,
//				this.audit);
//
//		Long fullSize = dataPage.keySet().iterator().next();
//
//		this.auditList = dataPage.get(fullSize);
//
//		while (this.auditList.size() < fullSize) {
//			this.auditList.add(null);
//		}
//
//	}
//
//}
