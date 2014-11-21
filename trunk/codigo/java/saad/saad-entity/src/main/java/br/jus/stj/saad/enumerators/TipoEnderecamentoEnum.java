package br.jus.stj.saad.enumerators;


public enum TipoEnderecamentoEnum {

	ENDERECAMENTO_INTERNO("EIN"), ENDERECAMENTO_MALA_DIRETA(
			"EMD"), ENDERECAMENTO_OUTRO_DESTINATARIO(
			"EOD");


	private String valor;

	private TipoEnderecamentoEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	


}
