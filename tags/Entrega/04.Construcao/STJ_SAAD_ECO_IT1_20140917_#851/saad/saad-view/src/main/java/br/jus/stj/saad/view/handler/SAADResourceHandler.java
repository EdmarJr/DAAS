package br.jus.stj.saad.view.handler;

import java.io.IOException;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;

public class SAADResourceHandler extends ResourceHandler {
	
	private ResourceHandler resourceHandler;
	
	public SAADResourceHandler(ResourceHandler resourceHandler) {
		this.resourceHandler = resourceHandler;
	}

	@Override
	public Resource createResource(String resourceName) {
		return this.resourceHandler.createResource(resourceName);
	}

	@Override
	public Resource createResource(String resourceName, String libraryName) {
		return this.resourceHandler.createResource(resourceName, libraryName);
	}

	@Override
	public Resource createResource(String resourceName, String libraryName,
			String contentType) {
		return this.resourceHandler.createResource(resourceName, libraryName, contentType);
	}

	@Override
	public boolean libraryExists(String libraryName) {
		return this.resourceHandler.libraryExists(libraryName);
	}

	@Override
	public void handleResourceRequest(FacesContext context) throws IOException {
		this.resourceHandler.handleResourceRequest(context);
	}

	@Override
	public boolean isResourceRequest(FacesContext context) {
		return this.resourceHandler.isResourceRequest(context);
	}

	@Override
	public String getRendererTypeForResourceName(String resourceName) {
		return this.resourceHandler.getRendererTypeForResourceName(resourceName);
	}

}
