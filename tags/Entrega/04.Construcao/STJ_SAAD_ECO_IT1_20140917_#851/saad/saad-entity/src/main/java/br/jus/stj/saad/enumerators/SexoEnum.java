package br.jus.stj.saad.enumerators;

public enum SexoEnum {
	MASCULINO("M", "sexo.masculino"), FEMININO("F","sexo.feminino");

	private String valor;
	private String codLabel;

	private SexoEnum(String valor, String codLabel) {
		this.valor = valor;
		this.codLabel = codLabel;
	}

	public String getValor() {
		return valor;
	}

	public String getCodLabel() {
		return codLabel;
	}

}
