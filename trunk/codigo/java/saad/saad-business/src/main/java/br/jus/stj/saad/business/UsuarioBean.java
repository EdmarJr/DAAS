package br.jus.stj.saad.business;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.stj.commons.util.ConstantesGerais;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.LocalUsuario;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.login.LoginBean;
import br.jus.stj.saad.persistence.UsuarioDAO;

/**
 * Session Bean implementation class Audit
 */
@Stateless
public class UsuarioBean extends Bean<Usuario> {
	
	@Resource
	private SessionContext context;
	
	@EJB
	private LocalBean localBean;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private LoginBean loginBean;
	
	@Override
	public UsuarioDAO getDao() {
		return usuarioDAO;
	}
	

	
	public Usuario obterUsuarioLogado() {
		if(loginBean.getUsuarioLogado() != null) {
			return loginBean.getUsuarioLogado();
		}
		Usuario usuario = getDao().obterPorLogin(context.getCallerPrincipal().getName());
//		String login="schulz";
//		Usuario usuario = getDao().obterPorLogin(login);
		return usuario;
	}
	
	public List<Usuario> listarUsuariosPorNome(String nome){
		return usuarioDAO.listarUsuariosPorNome(nome);
	}
	
	public List<Usuario> listarUsuariosPorNomeUnidade(String nome, List<Local> locais){
		return usuarioDAO.listarUsuariosPorNomeUnidade(nome, locais);
	}
	
	public List<Usuario> listarUsuariosPorNomeUnidade(String nome, Local local){
		return usuarioDAO.listarUsuariosPorNomeUnidade(nome, local);
	}
	
	public Usuario obterPorId(Long id) {
		return usuarioDAO.obterPorId(id);
	}
	
	public Usuario buscarUsuarioPorNome(String nome) {
		return usuarioDAO.buscarUsuarioPorNome(nome);
	}
	
	public List<Usuario> obterUsuariosPorUnidades(Local... locais) {
		return getDao().obterPorLocal(locais);
	}
	
	public List<Usuario> obterUsuariosPorUnidades(
			String tipoOrdenamento, Local... locais) {

		List<Usuario> usuarios = getDao().obterPorLocal(locais);

		if (tipoOrdenamento.equals(ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE)) {

			Collections.sort(usuarios, new OrdenaUsuarioCrescente());

		} else {

			Collections.sort(usuarios, new OrdenaUsuarioDecrescente());

		}

		return usuarios;

	}
	
	public Boolean isLocalDoUsuario(Usuario usuario, Local local){
		
		List<LocalUsuario> locaisUsuarios = usuario.getLocaisUsuarios();
		
		for (LocalUsuario localUsuario : locaisUsuarios) {
			if(local.getId().longValue() == localUsuario.getLocal().getId().longValue()){
				return true;
			}
		}
		
		return false;		
	}
	
	public List<Usuario> obterUsuariosDisponiveisPorUnidadeDoUsuarioLogado() {
		Local unidade = localBean.obterPorId(Local.class, loginBean.getUnidadeUsuario().getId());
		List<Usuario> usuarios = getDao().obterPorLocal(unidade);
		return usuarios;
	}
	
	public List<Usuario> obterUsuariosDisponiveisPorUnidadeDoUsuarioLogado(
			String tipoOrdenamento) {
		Local unidade = localBean.obterPorId(Local.class, loginBean
				.getUnidadeUsuario().getId());
		List<Usuario> usuarios = getDao().obterPorLocal(unidade);
		
		if(tipoOrdenamento.equals(ConstantesGerais.TIPO_ORDENAMENTO_CRESCENTE)) {
			
			Collections.sort(usuarios, new OrdenaUsuarioCrescente());
			
		} else {
			
			Collections.sort(usuarios, new OrdenaUsuarioDecrescente());
			
		}
		
		return usuarios;
		
	}
	
	private class OrdenaUsuarioCrescente implements Comparator<Usuario> {

		@Override
		public int compare(Usuario o1, Usuario o2) {

			return obterCollator().compare(o1.getNome(), o2.getNome());
		
		}
		
	}
	
	private class OrdenaUsuarioDecrescente implements Comparator<Usuario> {
		
		@Override
		public int compare(Usuario o1, Usuario o2) {
			
			return obterCollator().compare(o2.getNome(), o1.getNome());
		
		}
		
	}
	
	private static final Collator obterCollator() {
		
        Collator collator = Collator.getInstance (new Locale ("pt", "BR"));  
        collator.setStrength(Collator.PRIMARY); // importante! 
        
        return collator;
		
	}

}
