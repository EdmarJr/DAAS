package br.jus.stj.saad.entity.local;

public enum TipoSituacaoAviso {

	L("label.read"), // Lido
	N("label.unread"), // Não Lido
	P("label.pending"), // Pendente
	E("label.progress"), // Em Andamento
	R("label.solved"); // Resolvido
	
	private String descricao;
	
	private TipoSituacaoAviso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
