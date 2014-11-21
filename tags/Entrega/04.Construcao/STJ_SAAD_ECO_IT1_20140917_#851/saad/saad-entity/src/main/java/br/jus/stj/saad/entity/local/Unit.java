package br.jus.stj.saad.entity.local;

import br.jus.stj.saad.entity.EntidadeBase;


public class Unit extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6820463738230733072L;

	private Long id;
	private String name;
	private boolean selected;

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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
