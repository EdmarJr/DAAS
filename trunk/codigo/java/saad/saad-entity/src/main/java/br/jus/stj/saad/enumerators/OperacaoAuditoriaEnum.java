package br.jus.stj.saad.enumerators;

public enum OperacaoAuditoriaEnum {
	
	EXCLUIR('E', "excluir"), INCLUIR('I',
			"incluir"), ALTERAR('A', "alterar");

	private String codLabel;
	private Character valor;

	private OperacaoAuditoriaEnum(Character valor, String codLabel) {
		this.valor = valor;
		this.codLabel = codLabel;
	}

	public Character getValor() {
		return valor;
	}

	public String getCodLabel() {
		return codLabel;
	}
}
