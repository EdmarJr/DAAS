package br.jus.stj.saad.enumerators;

public enum SituacaoTarefaEnum {
	PENDENTE('P', "demanda.situacao.pendente"), RESOLVIDO('R',
			"demanda.situacao.resolvido");
	private String codLabel;
	private Character valor;

	private SituacaoTarefaEnum(Character valor, String codLabel) {
		this.valor = valor;
		this.codLabel = codLabel;
	}

	public String getCodLabel() {
		return codLabel;
	}

	public void setCodLabel(String codLabel) {
		this.codLabel = codLabel;
	}

	public Character getValor() {
		return valor;
	}

	public void setValor(Character valor) {
		this.valor = valor;
	}

	public static SituacaoTarefaEnum obterSituacaoTarefaPorValor(Character situacao) {
		SituacaoTarefaEnum[] valores = SituacaoTarefaEnum.values();
		for (SituacaoTarefaEnum s : valores) {
			if(s.getValor().equals(situacao)) {
				return s;
			}
		}
		return null;
	}

}
