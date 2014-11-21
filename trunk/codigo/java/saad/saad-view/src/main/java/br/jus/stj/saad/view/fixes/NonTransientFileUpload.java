package br.jus.stj.saad.view.fixes;

import org.primefaces.component.fileupload.FileUpload;

public class NonTransientFileUpload extends FileUpload {
	@Override
	public boolean isTransient() {
		return false;
	}
}
