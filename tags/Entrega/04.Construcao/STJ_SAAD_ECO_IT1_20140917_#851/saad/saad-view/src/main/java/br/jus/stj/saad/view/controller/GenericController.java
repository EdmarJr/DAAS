package br.jus.stj.saad.view.controller;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.jus.stj.commons.util.MensagemUtil;

public abstract class GenericController implements Serializable {

	@ManagedProperty(value = "#{breadCrumbsSessionController}")
	private BreadCrumbsSessionController crumbsController;

	private static final long serialVersionUID = -2162249893778518423L;

	private String paginaDestino;
	
	protected HttpSession session = (HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(true);

	public String getMensagem(String bundleName, String key) {
		return MensagemUtil.getMensagem(bundleName, key);
	}

	public String comandoVoltar() {
		return getPaginaAnterior();
	}

	public String getPaginaDestino() {
		return paginaDestino;
	}
	
	public String obterContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	public void setPaginaDestino(String paginaDestino) {
		this.paginaDestino = paginaDestino;
	}

	public void abrirModal(String widgetVar) {
		org.primefaces.context.RequestContext.getCurrentInstance().execute(
				widgetVar + ".show();");
	}

	public void fecharModal(String widgetVar) {
		org.primefaces.context.RequestContext.getCurrentInstance().execute(
				widgetVar + ".hide();");
	}

	public void setCrumbsController(
			BreadCrumbsSessionController crumbsController) {
		this.crumbsController = crumbsController;
	}

	public String getPaginaAnterior() {
		return crumbsController.getPaginaAnterior();
	}

	public String getPaginaAtual() {
		return Paths.get(crumbsController.getPaginaAtual()).getFileName()
				.toString().replace(".jsf", ".xhtml");
	}

	public Boolean seExisteMensagemErro() {
		return FacesContext.getCurrentInstance().getMessageList().size() != 0;
	}


	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
	}

	public String redirecionar(String endereco) {
		return endereco;
	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void adicionarFacesMessage(String mensagem) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(mensagem));

	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void adicionarFacesMessage(Severity severity, String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, mensagem, null));
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void adicionarFacesMessage(Severity severity, String mensagem,
			String detalhe) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, mensagem, detalhe));

	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void limparTela() {
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    Application application = context.getApplication();
	    ViewHandler viewHandler = application.getViewHandler();
	    UIViewRoot viewRoot = viewHandler.createView(context, context
	            .getViewRoot().getViewId());
	    context.setViewRoot(viewRoot);
	    context.renderResponse();
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void salvarEstadoAtualManagedBean(Object managedBean) {
		
		if(managedBean != null) {
			
			incluirObjetoRequestMap(managedBean.getClass().getSimpleName(),
					managedBean);
			
		}

	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void recuperarUltimoEstadoManagedBean(Object beanAtual) {

		if (possuiUltimoEstadoManagedBean(beanAtual)) {

			Object estadoAnteriorBean = obterObjetoRequestMap(beanAtual
					.getClass().getSimpleName());

			try {

				preencherObjeto(beanAtual, estadoAnteriorBean);

			} catch (Exception e) {
			}

		}

	}
	
	/**
	 * @author felipe.ramalho
	 */
	private boolean possuiUltimoEstadoManagedBean(Object objeto) {
		
		return obterObjetoRequestMap(objeto.getClass().getSimpleName()) != null;
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void incluirObjetoSessionMap(String nome, Object objeto) {

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(nome, objeto);

	}
	
	public void incluirObjetoRequestMap(String nome, Object objeto) {

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(nome, objeto);

	}
	
	public Object obterObjetoRequestMap(String nome) {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(nome);

	}
	
	public <T> Object obterObjetoRequestMap(Class<T> clazz) {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(clazz.getSimpleName());

	}
	
	/**
	 * @author felipe.ramalho
	 */
	public Object obterObjetoSessionMap(String nome) {
		
		return FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().get(nome);
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	public void removerObjetoSessionMap(String nome) {
		
		FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().remove(nome);
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	private void preencherObjeto(Object objetoAPreencher,
			Object objetoPreenchido) throws IllegalArgumentException,
			IllegalAccessException {

		Field[] fieldsObjetoPreenchido = getDeclaredFieldsSemAnnotation(objetoPreenchido);

		Field field = null;

		for (Field fieldObjetoPreenchido : fieldsObjetoPreenchido) {

			fieldObjetoPreenchido.setAccessible(true);

			field = getDeclaredFieldPorNome(objetoAPreencher,
					fieldObjetoPreenchido.getName());

			if (field != null) {

				field.setAccessible(true);

				if (!Modifier.isFinal(field.getModifiers())) {

					field.set(objetoAPreencher,
							fieldObjetoPreenchido.get(objetoPreenchido));

				}

			}

		}

	}
	
	/**
	 * @author felipe.ramalho
	 */
	private Field[] getDeclaredFieldsSemAnnotation(Object object) {
		
		Field[]	fields = object.getClass().getDeclaredFields();

		List<Field> fieldsList = new ArrayList<Field>();
		
		for (Field field : fields) {
			
			if(field.getAnnotations().length == 0)
				fieldsList.add(field);
			
		}
		
		return (Field[]) fieldsList.toArray(new Field[fieldsList.size()]);
		
	}
	
	/**
	 * @author felipe.ramalho
	 */
	private Field getDeclaredFieldPorNome(Object object, String name) {
		
		Field[] fields = object.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			if(field.getName().equals(name))
				return field;
			
		}
		
		return null;
		
	}
	

}
