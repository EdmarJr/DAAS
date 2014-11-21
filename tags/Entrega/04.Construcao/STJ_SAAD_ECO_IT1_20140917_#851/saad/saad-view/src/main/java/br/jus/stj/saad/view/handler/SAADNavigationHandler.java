package br.jus.stj.saad.view.handler;

import java.util.Map;
import java.util.Set;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.context.FacesContext;

public class SAADNavigationHandler extends ConfigurableNavigationHandler {

	private ConfigurableNavigationHandler configurableNavigationHandler;

	public SAADNavigationHandler(
			ConfigurableNavigationHandler configurableNavigationHandler) {
		this.configurableNavigationHandler = configurableNavigationHandler;
	}

	@Override
	public void handleNavigation(FacesContext arg0, String arg1, String arg2) {
		this.configurableNavigationHandler.handleNavigation(arg0, arg1, arg2);
	}

	@Override
	public NavigationCase getNavigationCase(FacesContext context,
			String fromAction, String outcome) {
		return this.configurableNavigationHandler.getNavigationCase(context,
				fromAction, outcome);
	}

	@Override
	public Map<String, Set<NavigationCase>> getNavigationCases() {
		return this.configurableNavigationHandler.getNavigationCases();
	}

}
