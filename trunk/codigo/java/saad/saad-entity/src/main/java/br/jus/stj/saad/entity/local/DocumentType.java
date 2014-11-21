package br.jus.stj.saad.entity.local;

import br.jus.stj.saad.entity.EntidadeBase;

public class DocumentType extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8406256054009627378L;

	private Long id;
	private String identification;
	private String name;
	private String physicalCabinet;
	private String physicalNetworkDirectory;
	private boolean selected;

	private DocumentTypeField[] field;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhysicalCabinet() {
		return physicalCabinet;
	}

	public void setPhysicalCabinet(String physicalCabinet) {
		this.physicalCabinet = physicalCabinet;
	}

	public String getPhysicalNetworkDirectory() {
		return physicalNetworkDirectory;
	}

	public void setPhysicalNetworkDirectory(String physicalNetworkDirectory) {
		this.physicalNetworkDirectory = physicalNetworkDirectory;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public DocumentTypeField[] getField() {
		return field;
	}

	public void setField(DocumentTypeField[] field) {
		this.field = field;
	}

}
