package br.jus.stj.saad.enumerators;

public enum SituacaoDocumentoEnum {
	PENDENTE('P',"situacao.documento.pendente"), RESOLVIDO('R',"situacao.documento.resolvido"),SUSPENSO('S',"situacao.documento.suspenso"), CANCELADO('C',"situacao.documento.cancelado");
	
	private Character valor;
	private String codLabel;

	private SituacaoDocumentoEnum(Character valor,String codLabel) {
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
