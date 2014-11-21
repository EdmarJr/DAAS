package br.jus.stj.saad.enumerators;


public enum TipoEnderecamentoExternoEnum {

	ORGAO("OR", "Orgão"), 
	PESSOA_FISICA("PF", "Pessoa Física");

	private String valor;
	private String descricao;

	private TipoEnderecamentoExternoEnum(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getValor() {
		return valor;
	}

}
