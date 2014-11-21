package br.jus.stj.saad.enumerators;

public enum DocumentTypeEnum {
	INTERNAL(1L), EXTERNAL(2L), ISSUED(3L);
	
	private Long id;

	private DocumentTypeEnum(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
