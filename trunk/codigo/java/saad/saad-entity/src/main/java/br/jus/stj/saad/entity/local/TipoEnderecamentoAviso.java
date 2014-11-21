package br.jus.stj.saad.entity.local;

public enum TipoEnderecamentoAviso {
	I("label.individual"), // Individual
	M("label.multiple"), // Múltiplo
	U("label.unit"); // Unidade
	
	private String descricao;
	
	private TipoEnderecamentoAviso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
