package br.jus.stj.saad.enumerators;

public enum TipoEnderecamentoAvisoEnum {
	TODOS("Todos", 'I', 1), // Todos
	DESTINATARIO_ESPECIFICO("Destinatário Específico", 'U', 2); // Destinatário Específico
	
	private String descricao;
	
	private Character chave;
	
	private int idTipoEnderecamento;
	
	private TipoEnderecamentoAvisoEnum(String descricao, Character chave, int idTipoEnderecamento) {
		this.descricao = descricao;
		this.chave = chave;
		this.idTipoEnderecamento = idTipoEnderecamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public Character getChave() {
		return chave;
	}

	public int getIdTipoEnderecamento() {
		return idTipoEnderecamento;
	}	
}
