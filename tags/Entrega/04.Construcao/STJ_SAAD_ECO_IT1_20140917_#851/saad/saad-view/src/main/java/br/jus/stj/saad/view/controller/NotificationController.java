package br.jus.stj.saad.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="notificationController")
@RequestScoped
public class NotificationController {
//
//	public static final Pattern DATA_TABLE_NOTIFICATION_COLUMN_ID_PATTERN = Pattern.compile("^column_(.+)_aviso$");
//	
////	private LazyDataModel<Aviso> notificationList = new LazyDataModel<Aviso>() {
////		private static final long serialVersionUID = 1L;
////		@Override
////		public List<Aviso> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
////			List<Aviso> subList = notificationService.listNotifications(first, pageSize, sortField, sortOrder.ordinal(), filters);
////			setRowCount(notificationService.count(filters));
////			return subList;
////		}
////	};
////	private List<Aviso> notificationList;
//	private String enderecado;
//	private String assunto;
//	private String descricao;
//	private Date dataInclusao;
//	private int page;
//	private int pageSize;
////	private String sortBy;
////	private boolean ascending;
//	
//	private String filtroEnderecado;
//	private String filtroAssunto;
//	private String filtroDescricao;
//	
//	@EJB
//	private NotificationService notificationService;
//	
//	public NotificationController() {
//		super();
//	}
//	
//	@PostConstruct
//	public void init() {
//		page = 0;
//		pageSize = 15;
////		sortBy = "dataInclusao";
////		ascending = false;
//		listNotifications(null);
////		if(notificationList == null) {
////			notificationList = notificationService.listAll();
////		}
//	}
//	
//	public void dataTableChangeAll(PageEvent pageEvent) {
//		page = pageEvent.getPage();
//		listAllByPage();
//	}
//	
//	public void dataTableChangeMine(PageEvent pageEvent) {
//		page = pageEvent.getPage();
//		listMineByPage();
//	}
//	
////	public void dataTableSort(SortEvent sortEvent) {
////		UIColumn uiColumn = sortEvent.getSortColumn();
////		Column column = (Column)uiColumn;
////		String id = column.getId();
////		Matcher matcher = DATA_TABLE_NOTIFICATION_COLUMN_ID_PATTERN.matcher(id);
////		if(matcher.matches()) {
////			matcher.group();
////			sortBy = matcher.group(1);
////		}
////		ascending = sortEvent.isAscending();
////		page = 0;
////		listNotifications(null);
////	}
//
//	public List<DestinatarioAviso> listMineByPage() {
//		int first = page * pageSize;
//		int size = notificationService.countMine();
//		List<DestinatarioAviso> subList = notificationService.listMyNotifications(first, pageSize, new String[]{"dataInclusao", "id"}, new boolean[]{false, true}, null);
//		List<DestinatarioAviso> notificationList = new ArrayList<DestinatarioAviso>();
//		for(int i=0; i<(size - subList.size()); i++) {
//			notificationList.add(null);
//		}
//		notificationList.addAll(first, subList);
//		return notificationList;
//	}
//	
//	public List<Aviso> listAllByPage() {
//		return listNotifications(null);
//	}
//	
//	private List<Aviso> listNotifications(Map<String, Object> filters) {
//		int first = page * pageSize;
//		int size = notificationService.count(filters);
//		List<Aviso> subList = notificationService.listNotifications(first, pageSize, new String[]{"dataInclusao", "id"}, new boolean[]{false, true}, filters);
//		List<Aviso> notificationList = new ArrayList<Aviso>();
//		for(int i=0; i<(size - subList.size()); i++) {
//			notificationList.add(null);
//		}
//		notificationList.addAll(first, subList);
//		return notificationList;
//	}
//	
//	public long countAll() {
//		return notificationService.count(null);
//	}
//	
//	public long countMine() {
//		return notificationService.countMine();
//	}
//	
//	public void incluir() {
//		notificationService.incluir(enderecado, assunto, descricao);
//		enderecado = assunto = descricao = "";
//		
//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(
//						FacesMessage.SEVERITY_INFO,
//						"Registro incluído com sucesso.", ""));
//	}
//	
//	public String viewNotification(long notificationId) {
//		System.out.println("viewNotification()");
//		Aviso aviso = notificationService.findById(notificationId);
//		if(aviso != null) {
//			enderecado = aviso.getNomesDestinatarios();
//			assunto = aviso.getDescricao();
//			descricao = aviso.getDescricao();
//			dataInclusao = aviso.getDataInclusao();
//		} else {
//			enderecado = assunto = descricao = "";
//		}
//		return "showNotification";
//	}
//
//	public String getEnderecado() {
//		return enderecado;
//	}
//
//	public void setEnderecado(String enderecado) {
//		this.enderecado = enderecado;
//	}
//
//	public String getAssunto() {
//		return assunto;
//	}
//
//	public void setAssunto(String assunto) {
//		this.assunto = assunto;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public Date getDataInclusao() {
//		return dataInclusao;
//	}
//
//	public void setDataInclusao(Date dataInclusao) {
//		this.dataInclusao = dataInclusao;
//	}
//
////	public LazyDataModel<Aviso> getNotificationList() {
////		return notificationList;
////	}
////
////	public void setNotificationList(LazyDataModel<Aviso> notificationList) {
////		this.notificationList = notificationList;
////	}
//
//	public NotificationService getNotificationService() {
//		return notificationService;
//	}
//
//	public void setNotificationService(NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}
//
//	public String getFiltroEnderecado() {
//		return filtroEnderecado;
//	}
//
//	public void setFiltroEnderecado(String filtroEnderecado) {
//		this.filtroEnderecado = filtroEnderecado;
//	}
//
//	public String getFiltroAssunto() {
//		return filtroAssunto;
//	}
//
//	public void setFiltroAssunto(String filtroAssunto) {
//		this.filtroAssunto = filtroAssunto;
//	}
//
//	public String getFiltroDescricao() {
//		return filtroDescricao;
//	}
//
//	public void setFiltroDescricao(String filtroDescricao) {
//		this.filtroDescricao = filtroDescricao;
//	}
//
////	public List<Aviso> getNotificationList() {
////		return notificationList;
////	}
////
////	public void setNotificationList(List<Aviso> notificationList) {
////		this.notificationList = notificationList;
////	}

}
