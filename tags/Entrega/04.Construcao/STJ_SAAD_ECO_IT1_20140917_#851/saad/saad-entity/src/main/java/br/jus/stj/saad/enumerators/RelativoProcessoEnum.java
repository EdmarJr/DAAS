package br.jus.stj.saad.enumerators;

public enum RelativoProcessoEnum {
	NAO('N',""), JUDICIAL('J',"relacionamento.processo.judicial"), ADMINISTRATIVO('A',"relacionamento.processo.administrativo"), TODOS('T',"");
	
	private Character valor;
	private String codLabel;

	private RelativoProcessoEnum(Character valor,String codLabel) {
		this.valor = valor;
		this.codLabel = codLabel;
	}

	public Character getValor() {
		return valor;
	}

	public String getCodLabel() {
		return codLabel;
	}
	
	public static RelativoProcessoEnum[] getEnumsPrincipais() {
		RelativoProcessoEnum[] enums = {RelativoProcessoEnum.JUDICIAL,RelativoProcessoEnum.ADMINISTRATIVO};
		return enums;
	}

	
	
	
}
