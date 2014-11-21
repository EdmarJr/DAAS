package br.jus.stj.saad.business;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import br.jus.stj.saad.teste.TesteInterceptor;

@Transactional
@Interceptors(TesteInterceptor.class)
@Stateless
public class NotificationService {
//
	public static final String MY_USER = "Maria Luíza";
//	public static final String MY_LOCAL = "Secretaria";
//	
//	@Inject
//	private AvisoDAO avisoDAO;
//	
//	@Inject
//	private UsuarioDAO usuarioDAO;
//	
//	@Inject
//	private LocalDAO localDAO;
//	
//	@Inject
//	private LocalUsuarioDAO localUsuarioDAO;
//	
//	@Inject
//	private DestinatarioAvisoDAO destinatarioAvisoDAO;
//	
//	private Usuario usrLogado;
//	
//	public NotificationService() {
//		super();
//	}
//	
//	@PostConstruct
//	private void createListNotifications() {
//		System.err.println("createListNotifications()");
//		List<Usuario> usuarioList = usuarioDAO.list();
//		if(usuarioList == null || usuarioList.isEmpty()) {
//			createMockData();
//		} else {
//			usrLogado = usuarioDAO.getByName(MY_USER);
//		}
//	}
//
//	private void createMockData() {
//		createLocais();
//		List<Usuario> usuarios = createUsuarios();
//		createAvisos(usuarios);
//	}
//	
//	private void createLocais() {
//		List<Local> locais = new ArrayList<Local>();
//		for(String nome : new String[]{"Secretaria", "Tesouraria", "Biblioteca"}) {
//			Local local = new Local();
//			local.setNome(nome);
//			localDAO.incluir(local);
//			locais.add(local);
//		}
//	}
//	
//	private List<Usuario> createUsuarios() {
//		Local local = localDAO.getByName("Secretaria");
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//		for(String nome : new String[]{"Maria", "Fulano", "Siclano", "Deltrano"}) {
//			for(String sobrenome : new String[]{"Luíza", "da Silva", "Pereira", "Antunes"}) {
//				Usuario usuario = new Usuario();
//				usuario.setNome(nome+" "+sobrenome);
//				usuarioDAO.incluir(usuario);
//				usuarios.add(usuario);
//				
//				LocalUsuarioId localUsuarioId =
//						new LocalUsuarioId()
//						.setIdLocal(local.getId())
//						.setIdUsuario(usuario.getId());
//				LocalUsuario localUsuario =
//						new LocalUsuario()
//						.setCompositeId(localUsuarioId);
//				localUsuarioDAO.incluir(localUsuario);
//			}
//		}
//		return usuarios;
//	}
//	
//	private List<Aviso> createAvisos(List<Usuario> usuarios) {
//		usrLogado = usuarioDAO.getByName(MY_USER);
//		Local local = localDAO.getByName("Secretaria");
//		List<Aviso> notificationList = new ArrayList<Aviso>();
//		Random random = new Random();
//		NumberFormat nFormat = NumberFormat.getInstance();
//		nFormat.setMinimumFractionDigits(0);
//		nFormat.setMaximumFractionDigits(0);
//		nFormat.setMinimumIntegerDigits(3);
//		nFormat.setMaximumIntegerDigits(3);
//		notificationList = new ArrayList<Aviso>();
//		for(int i=0, k=0; i<150; i++, k = (k + 1) % 5) {
//			Usuario outro1;
//			while(true) {
//				outro1 = usuarios.get(new Random().nextInt(usuarios.size()));
//				if(!usrLogado.getNome().equals(outro1.getNome())) {
//					break;
//				}
//			}
//			Usuario outro2;
//			while(true) {
//				outro2 = usuarios.get(new Random().nextInt(usuarios.size()));
//				if(!usrLogado.getNome().equals(outro2.getNome()) &&
//						!outro1.getNome().equals(outro2.getNome())) {
//					break;
//				}
//			}
//			String index = nFormat.format(i+1);
//			StringBuilder descricaoBuilder =
//					new StringBuilder()
//					.append(index)
//					.append(". ")
//					.append("Sbrubles ");
//			for(int j=0; j<10 + random.nextInt(91); j++) {
//				descricaoBuilder.append(" sbrubles");
//			}
//			Aviso aviso = new Aviso()
//					.setDescricao(descricaoBuilder.toString())
//					.setAcompanhamento(TipoSituacaoAviso.values()[new Random().nextInt(TipoSituacaoAviso.values().length)])
//					.setTipoEnderecamento(TipoEnderecamentoAviso.I)
//					.setAcompanhamento(TipoSituacaoAviso.N)
//					.setLocal(local);
//			avisoDAO.incluir(aviso.setDataInclusao(new Date()));
//			notificationList.add(aviso);
//			
//			switch(k) {
//				case 0: // 1 usuário (o usr logado)
//					aviso.setTipoEnderecamento(TipoEnderecamentoAviso.I);
//					relacionaAvisoUsuarios(aviso.getId(), new Long[]{usrLogado.getId()});
//					break;
//				case 1: // 1 usuário (outro que não o usr logado)
//					aviso.setTipoEnderecamento(TipoEnderecamentoAviso.I);
//					relacionaAvisoUsuarios(aviso.getId(), new Long[]{outro1.getId()});
//					break;
//				case 2: // N usuários, o usr logado entre eles
//					aviso.setTipoEnderecamento(TipoEnderecamentoAviso.M);
//					relacionaAvisoUsuarios(aviso.getId(), new Long[]{usrLogado.getId(), outro1.getId(), outro2.getId()});
//					break;
//				case 3: // N usuários, sem o usr logado entre eles
//					aviso.setTipoEnderecamento(TipoEnderecamentoAviso.M);
//					relacionaAvisoUsuarios(aviso.getId(), new Long[]{outro1.getId(), outro2.getId()});
//					break;
//				case 4: // Todos os usuários
//					aviso.setTipoEnderecamento(TipoEnderecamentoAviso.U);
//					break;
//			}
//			avisoDAO.atualizar(aviso);
//		}
//		return notificationList;
//	}
//
//	private void relacionaAvisoUsuarios(Long idAviso, Long[] idsUsuario) {
//		for(Long idUsuario : idsUsuario) {
//			DestinatarioAvisoId destinatarioAvisoId =
//					new DestinatarioAvisoId()
//					.setIdAviso(idAviso)
//					.setIdUsuario(idUsuario);
//			DestinatarioAviso destinatarioAviso =
//					new DestinatarioAviso()
//					.setCompositeId(destinatarioAvisoId)
//					.setSituacao(TipoSituacaoAviso.N);
//			destinatarioAvisoDAO.incluir(destinatarioAviso);
//		}
//	}
//
//	public int count(Map<String, Object> filters) {
//		return avisoDAO.count();
//	}
//	
//	public List<Aviso> listNotifications(int first, int pageSize, final String[] sortFields, boolean[] ordersAscending, Map<String, Object> filters) {
//		List<Aviso> notificationList = avisoDAO.listAvisos(first, pageSize, sortFields, ordersAscending, filters);
//		return notificationList;
//	}
//
//	public void incluir(String enderecado, String assunto, String descricao) {
////		SituacaoAndamento situacao = situacaoAvisoDAO.queryByName("Pendente");
////		avisoDAO.incluir(new Aviso().setEnderecado(enderecado).setAssunto(assunto).setAssunto(descricao).setDataInclusao(new Date()).setAcompanhamento(situacao));
//	}
//
//	public Aviso findById(long notificationId) {
//		return avisoDAO.findById(notificationId);
//	}
//
////	public List<Aviso> listAll() {
////		return avisoDAO.listAll();
////	}
//
//	public int countMine() {
//		return avisoDAO.countMine(MY_USER);
//	}
//
//	public List<DestinatarioAviso> listMyNotifications(int first, int pageSize, final String[] sortFields, boolean[] ordersAscending, Map<String, Object> filters) {
//		List<DestinatarioAviso> notificationList = avisoDAO.listarAvisosDoUsuario(first, pageSize, sortFields, ordersAscending, filters, MY_USER);
//		return notificationList;
//	}
	
}
