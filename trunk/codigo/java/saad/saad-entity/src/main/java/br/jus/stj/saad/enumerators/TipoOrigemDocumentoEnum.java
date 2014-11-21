package br.jus.stj.saad.enumerators;

import br.jus.stj.saad.entity.local.Enderecamento;
import br.jus.stj.saad.entity.local.EnderecamentoExterno;
import br.jus.stj.saad.entity.local.EnderecamentoInterno;

public enum TipoOrigemDocumentoEnum {
	
	INTERNO('I',"enderecamento.interno", EnderecamentoInterno.class), EXTERNO('E',"enderecamento.externo",EnderecamentoExterno.class);
	
	private Character valor;
	private String codLabel;
	private Class<? extends Enderecamento> clazz;

	private TipoOrigemDocumentoEnum(Character valor,String codLabel,Class<? extends Enderecamento> clazz) {
		this.valor = valor;
		this.codLabel = codLabel;
		this.clazz = clazz;
	}

	public Character getValor() {
		return valor;
	}

	public String getCodLabel() {
		return codLabel;
	}
	
	public Enderecamento getEnderecamento() {
		Enderecamento enderecamento = null;
		try {
			enderecamento = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enderecamento.setTipoEnderecamento(TipoEnderecamentoEnum.ENDERECAMENTO_INTERNO.getValor());
		return enderecamento;
	}
	

	
	
	
}
