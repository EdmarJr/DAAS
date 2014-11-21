package br.jus.stj.saad.entity.local;

public enum RelacionadoDocumentoEnum {
	S("Sim"),N("Não");
	
	private String descricao;
	
	private RelacionadoDocumentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
