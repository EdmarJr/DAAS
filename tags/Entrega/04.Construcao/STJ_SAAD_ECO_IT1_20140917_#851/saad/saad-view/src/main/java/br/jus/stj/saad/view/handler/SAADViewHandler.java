package br.jus.stj.saad.view.handler;

import java.io.IOException;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class SAADViewHandler extends ViewHandler {
	
	private ViewHandler viewHandler;
	
	public SAADViewHandler (ViewHandler viewHandler) {
		super();
		this.viewHandler = viewHandler;
	}

	@Override
	public Locale calculateLocale(FacesContext arg0) {
		return this.viewHandler.calculateLocale(arg0);
	}

	@Override
	public String calculateRenderKitId(FacesContext arg0) {
		return this.viewHandler.calculateRenderKitId(arg0);
	}

	@Override
	public UIViewRoot createView(FacesContext arg0, String arg1) {
		return this.viewHandler.createView(arg0, arg1);
	}

	@Override
	public String getActionURL(FacesContext arg0, String arg1) {
		return this.viewHandler.getActionURL(arg0, arg1);
	}

	@Override
	public String getResourceURL(FacesContext arg0, String arg1) {
		return this.viewHandler.getResourceURL(arg0, arg1);
	}

	@Override
	public void renderView(FacesContext arg0, UIViewRoot arg1)
			throws IOException, FacesException {
		this.viewHandler.renderView(arg0, arg1);
	}

	@Override
	public UIViewRoot restoreView(FacesContext arg0, String arg1) {
		return this.viewHandler.restoreView(arg0, arg1);
	}

	@Override
	public void writeState(FacesContext arg0) throws IOException {
		this.viewHandler.writeState(arg0);
	}

}
