package br.jus.stj.saad.login;


import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;

import br.jus.stj.commons.ldap.exception.LdapException;
import br.jus.stj.commons.ldap.vo.UsuarioVO;
import br.jus.stj.commons.util.HashUtils;

public class loginController implements LoginModule {

	private final static String SUFIXO_EMAIL_STJ = "@stj.jus.br";
	private AutenticadorLdap autenticadorLdap = null;

	private boolean commitSucceeded = false;
	private boolean succeeded = false;
	private User user;
	private Set<Role> roles = new HashSet();

	protected Subject subject;
	protected CallbackHandler callbackHandler;
	protected Map sharedState;
	private String dataSourceName;
	private String sqlUser;
	private String sqlRoles;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
		autenticadorLdap = new AutenticadorLdap();
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		dataSourceName = (String) options.get("dataSourceName");
		sqlUser = (String) options.get("sqlUser");
		sqlRoles = (String) options.get("sqlRoles");
	}

	@SuppressWarnings("unchecked")
	public boolean login() throws LoginException {
		// recupera o login e senha informados no form
		getUsernamePassword();

		Connection conn = null;
		try {
			// obtem a conexão
			try {
				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext
						.lookup("java:jboss/datasources/saad_DB2");
				conn = ds.getConnection();
			} catch (NamingException e) {
				succeeded = false;
				throw new LoginException("Erro ao recuperar DataSource: "
						+ e.getClass().getName() + ": " + e.getMessage());
			} catch (SQLException e) {
				succeeded = false;
				throw new LoginException("Erro ao obter conexão: "
						+ e.getClass().getName() + ": " + e.getMessage());
			}
			// valida o usuario
			validarUsuario(conn);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		sharedState.put("javax.security.auth.principal", sqlUser);
		sharedState.put("javax.security.auth.roles", roles);

		return true;
	}

	public boolean commit() throws LoginException {
		// adiciona o usuario no principals
		if (user != null && !subject.getPrincipals().contains(user)) {
			subject.getPrincipals().add(user);
		}

		Group grupo = new GroupImpl("Roles");
		grupo.addMember(new Role("ADMINISTRADOR"));

		subject.getPrincipals().add(grupo);

		commitSucceeded = true;

		return true;
	}

	public boolean abort() throws LoginException {
		if (!succeeded) {
			return false;
		} else if (succeeded && !commitSucceeded) {
			succeeded = false;
		} else {
			succeeded = false;
			logout();
		}

		this.subject = null;
		this.callbackHandler = null;
		this.sharedState = null;
		this.roles = new HashSet();

		return succeeded;
	}

	public boolean logout() throws LoginException {
		subject.getPrincipals().removeAll(roles);
		subject.getPrincipals().remove(user);
		return true;
	}

	/**
	 * Valida login e senha no banco
	 * 
	 * @param conn	
	 */
	private void validarUsuario(Connection conn) throws LoginException {
		if (!isUsuarioInterno(loginInformado)) {
			senhaInformado = HashUtils.gerarHashPorMD5(senhaInformado);
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			if (isUsuarioInterno(loginInformado)) {
				String nomeUsuario = efetuarLoginUsuarioInterno(loginInformado,
						senhaInformado);
				if (nomeUsuario != null) {
					user = new User(sqlUser);
					recuperaRoles(conn);
					user.setRoles(roles);
					return;
				} else {
					throw new LoginException(
							"Usuário ou senha estão incorretos");
				}
			} else {
				sqlUser = "select * from SISOUV.USUARIO_EXTERNO where TX_EMAIL=? AND TX_SENHA_MANIFESTANTE=?";
				statement = conn.prepareStatement(sqlUser);
				statement.setString(1, loginInformado);
				statement.setString(2, senhaInformado);
				rs = statement.executeQuery();
				if (rs.next()) {
					user = new User(loginInformado);
					recuperaRoles(conn);
					user.setRoles(roles);
				} else {
					succeeded = false;
					throw new LoginException("Usuário não localizado.");
				}
			}
		} catch (SQLException e) {
			succeeded = false;
			throw new LoginException("Erro ao abrir sessão: "
					+ e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
		}

	}

	/**
	 * Login do usuário.
	 */
	protected String loginInformado;

	/**
	 * Senha do usuário.
	 */
	protected String senhaInformado;

	/**
	 * Obtem o login e senha digitados
	 */
	protected void getUsernamePassword() throws LoginException {
		if (callbackHandler == null)
			throw new LoginException(
					"Error: no CallbackHandler available to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Login");
		callbacks[1] = new PasswordCallback("Senha", false);
		try {
			callbackHandler.handle(callbacks);
			loginInformado = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1])
					.getPassword();
			senhaInformado = new String(tmpPassword);
			((PasswordCallback) callbacks[1]).clearPassword();
		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException(
					"Error: "
							+ uce.getCallback().toString()
							+ " not available to garner authentication information from the user");
		}
	}

	public void recuperaRoles(Connection conn) throws LoginException {
		roles.add(new Role("ADMINISTRADOR"));
	}

	private String efetuarLoginUsuarioInterno(String usuario,
			String senha) {
		if (usuario.indexOf("@") != -1) {
			usuario = removerSufixoEmailStj(usuario);
		}
		UsuarioVO usuarioVO = new UsuarioVO(usuario, senha);
		boolean autenticado = autenticadorLdap.autenticarUsuario(usuarioVO);
		if (!autenticado) {
			return null;
		} else {
			sqlUser = usuarioVO.getNomeLogin();
			roles.add(new Role("ADMINISTRADOR"));
		}
		return usuario;
	}

	private static boolean isUsuarioInterno(String usuario) {
		if (usuario.endsWith(SUFIXO_EMAIL_STJ) || usuario.indexOf("@") == -1)
			return true;
		else
			return false;
	}

	private static String removerSufixoEmailStj(String usuario) {
		return usuario.substring(0,
				usuario.length() - SUFIXO_EMAIL_STJ.length());
	}
	
	
	
	
	public class AutenticadorLdap {
		
		private final static String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
		
		private final static String SECURITY_AUTHENTICATION = "simple";
		
		private final static String REFERRAL = "follow";
		
		private final static String URL_SERVIDOR = "URL_SERVIDOR";
		
		private final static String DOMINIO = "DOMINIO";
		
		private final static String MSG_ERRO_URL_SERVIDOR = "ParÃ¢metros URL_SERVIDOR nÃ£o informado no arquivo properties.";
		
		private final static String MSG_ERRO_DOMINIO = "ParÃ¢metros DOMINIO nÃ£o informado no arquivo properties.";
		
		private final static String MSG_ERRO_USUARIO_NOME_NAO_INFORMADO = "Nome do usuÃ¡rio nÃ£o informado.";
		
		private final static String MSG_ERRO_USUARIO_SENHA_NAO_INFORMADA = "Senha do usuÃ¡rio nÃ£o informada.";
		
		private final static String MSG_ERRO_CARGA_PROPERTIES = "Erro na carga do arquivo properties de configuraÃ§Ã£o.";
		
		
		private final static  String DOMINIO_TEMP="STJ";
		private  final static String URL_SERVIDOR_TEMP="ldap://stj.gov.br/";
		
		public boolean autenticarUsuario(UsuarioVO usuarioVO) {
			validarConfig();
			validarUsuario(usuarioVO);
			
			String dominio_user = DOMINIO_TEMP + '\\' + usuarioVO.getNomeLogin();
			
			Hashtable<String, String> env = new Hashtable<String, String>(11);
			env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(javax.naming.Context.PROVIDER_URL, URL_SERVIDOR_TEMP);
			
			env.put(javax.naming.Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
			env.put(javax.naming.Context.SECURITY_PRINCIPAL, dominio_user);
			env.put(javax.naming.Context.SECURITY_CREDENTIALS, usuarioVO.getSenha());
			env.put(javax.naming.Context.REFERRAL, REFERRAL);
			
			try {
				@SuppressWarnings("unused")
				DirContext ctx = new InitialDirContext(env);
				return true;
			} catch (AuthenticationException ae) {
				return false;
			} catch (Exception ne) {
				throw new LdapException(ne);
			}
			
		}
		
		private void validarConfig() throws LdapException {
			if (URL_SERVIDOR_TEMP == null || 
					URL_SERVIDOR_TEMP.trim().length() == 0)
				throw new LdapException(MSG_ERRO_URL_SERVIDOR);
			
			if (DOMINIO_TEMP == null || 
					DOMINIO_TEMP.trim().length() == 0)
				throw new LdapException(MSG_ERRO_DOMINIO);
		}
		
		private void validarUsuario(UsuarioVO usuarioVO) throws LdapException {
			if (usuarioVO.getNomeLogin() == null || 
					usuarioVO.getNomeLogin().trim().length() == 0)
				throw new LdapException(MSG_ERRO_USUARIO_NOME_NAO_INFORMADO);
			
			if (usuarioVO.getSenha() == null || 
					usuarioVO.getSenha().trim().length() == 0)
				throw new LdapException(MSG_ERRO_USUARIO_SENHA_NAO_INFORMADA);
		}
	}
}
