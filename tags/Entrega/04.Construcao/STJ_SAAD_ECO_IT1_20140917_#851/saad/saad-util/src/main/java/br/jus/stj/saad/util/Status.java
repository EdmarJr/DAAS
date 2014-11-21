package br.jus.stj.saad.util;

import java.io.Serializable;

public enum Status implements Serializable {

	P("Pendente"), R("Resolvido");

	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
