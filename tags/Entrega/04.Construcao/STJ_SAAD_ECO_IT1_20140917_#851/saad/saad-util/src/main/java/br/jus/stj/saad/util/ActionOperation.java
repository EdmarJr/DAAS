package br.jus.stj.saad.util;

import java.io.Serializable;

public enum ActionOperation implements Serializable {

	I("Incluído"), V("Visualizado"), A("Alterado"), E("Excluído");

	private String descricao;

	private ActionOperation(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
