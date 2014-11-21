package br.jus.stj.saad.entity.local;

import br.jus.stj.saad.entity.EntidadeBase;

public class DocumentTypeField extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2913436370551406423L;

	private Long id;
	private String name;
	private boolean addVisibility;
	private boolean searchVisibility;

	private String value;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAddVisibility() {
		return addVisibility;
	}

	public void setAddVisibility(boolean addVisibility) {
		this.addVisibility = addVisibility;
	}

	public boolean isSearchVisibility() {
		return searchVisibility;
	}

	public void setSearchVisibility(boolean searchVisibility) {
		this.searchVisibility = searchVisibility;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
