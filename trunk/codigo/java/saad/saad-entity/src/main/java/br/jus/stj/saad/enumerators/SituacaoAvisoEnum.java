package br.jus.stj.saad.enumerators;

public enum SituacaoAvisoEnum {
     PENDENTE('P', "aviso.situacao.pendente"), RESOLVIDO('R',
	    "aviso.situacao.resolvido"), EM_ANDAMENTO('E',
	    "aviso.situacao.emandamento");

    private Character valor;
    private String codLabel;

    private SituacaoAvisoEnum(Character valor, String codLabel) {
	this.valor = valor;
	this.codLabel = codLabel;
    }

    public Character getValor() {
	return valor;
    }

    public String getCodLabel() {
	return codLabel;
    }
    
    public static SituacaoAvisoEnum obterSituacaoAvisoEnumPorCharacter(Character character) {
	SituacaoAvisoEnum[] situacoes = SituacaoAvisoEnum.values();
	for(SituacaoAvisoEnum situacao : situacoes) {
	    if(situacao.getValor().equals(character)) {
		return situacao;
	    }
	}
	return null;
    }
    
    public static SituacaoAvisoEnum getEnumPorChave(Character chave){
    	switch (chave) {
		case 'P':
			return PENDENTE;
		case 'R':
			return RESOLVIDO;
		case 'E':
			return EM_ANDAMENTO;
		default:
			return PENDENTE;
		}
    }

}
