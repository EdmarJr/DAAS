package br.jus.stj.saad.exception;

public class PropertiesException extends RuntimeException {
	
	private static final long serialVersionUID = -7078055294887313409L;
	
	public PropertiesException(String message) {
		super(message);
	}

	public PropertiesException(Throwable cause) {
		super(cause);
	}
	
}
