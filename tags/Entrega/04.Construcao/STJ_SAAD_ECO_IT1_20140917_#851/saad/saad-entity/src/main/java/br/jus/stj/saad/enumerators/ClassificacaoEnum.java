package br.jus.stj.saad.enumerators;

public enum ClassificacaoEnum {
	EXPEDIDO('E', "classificacao.expedido"), RECEBIDO('R',
			"classificacao.recebido");

	private String codLabel;
	private Character valor;

	private ClassificacaoEnum(Character valor, String codLabel) {
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
