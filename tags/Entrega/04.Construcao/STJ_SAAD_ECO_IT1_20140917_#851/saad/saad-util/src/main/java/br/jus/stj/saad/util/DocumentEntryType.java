package br.jus.stj.saad.util;

import java.io.Serializable;

public enum DocumentEntryType implements Serializable {

	I("Interno"), E("Externo");

	private String descricao;

	private DocumentEntryType(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
