/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para formatação de dados.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *		UtilData		(util-data.js)
 *		UtilEvento		(util-evento.js)
 *		UtilFormulario	(util-formulario.js)
 *		UtilValidador	(util-validador.js)
 *		UtilBrowser		(util-browser.js)
*******************************************************************************
 * métodos do objeto:
 *		formatarNumericoDoCampo(evento, objeto)
 *		formatarInteiroDoCampo(evento, objeto)
 *		formatarRealDoCampo(evento, objeto)
 *		formatarDinheiroDoCampo(evento, objeto)
 *		formatarTelefoneDoCampo(evento, objeto)
 *		formatarCepDoCampo(evento, objeto)
 *		formatarCNPJDoCampo(evento, objeto)
 *		formatarCPFDoCampo(evento, objeto)
 *		formatarCPF_CNPJDoCampo(evento, objeto)
 *		formatarDataDoCampo(evento, objeto)
 *		formatarData_ddMMDoCampo(evento, objeto)
 *		formatarData_MMyyyyDoCampo(evento, objeto)
 *		formatarData_ddMMyyyyEspacohhmmDoCampo(evento, objeto)
 *		formatarData_ddMMyyyyEspacohhmmssDoCampo(evento, objeto)
 *		formatarHoraDoCampo(evento, objeto)
 *		formatarHora_hhmmssDoCampo(evento, objeto)
 *		formatarRemoverAcentuacaoDoCampo(evento, objeto)
 *		formatarRemoverCaracteresEspeciaisDoCampo(evento, objeto)
 *		formatarQuantidadeDeCaracteres(evento, objeto, quantidade)
 *		formatarLetrasENumerosDoCampo(evento, objeto)
 *		formatarCartaoDoCampo(evento, objeto)
 *		formatarAlfanumerico(evento, objeto)
 *		formatarLimparCampoAoPressionarUmaTela(evento, objeto)
 *
 *		formatarInteiro(string)
 *		formatarReal(string)
 *		formatarDinheiro(string)
 *		formatarTelefone(string)
 *		formatarCep(string)
 *		formatarCNPJ(string)
 *		formatarCPF(string)
 *		formatarCPF_CNPJ(string)
 *		formatarData(string)
 *		formatarData_ddMM(string)
 *		formatarData_MMyyyy(string)
 *		formatarData_ddMMyyyyEspacohhmm(string)
 *		formatarData_ddMMyyyyEspacohhmmss(string)
 *		formatarHora(string)
 *		formatarHora_hhmmss(string)
 *		formatarLetrasENumeros(string)
 *		formatarCartao(string)
 *		formatar(string, pattern, completar)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilFormatador();
 *		alert(objeto.formatarInteiro("150099"));
 *******************************************************************************
 */
 
UtilFormatador.prototype = new UtilValidador();

function UtilFormatador() {
	var NUMERO 				= "0123456789";
	var LETRA				= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	var LETRA_SEM_ESPACO	= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var MASCARA 			= "#";
	var PREFIXO_DINHEIRO 	= "R$ ";
	var SEPARADOR_MILHAR	= ".";
	var SEPARADOR_DECIMAL	= ",";

	/**
	 * Bloqueia teclas que não sejam numéricas. Use no evento onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarNumerico(event, this)" onkeypress="return new UtilFormatador().formatarNumerico(event, this)" onkeyup="new UtilFormatador().formatarNumerico(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarNumericoDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento);
			}
			
			if (this.isEventoKeyPress(evento)) {
				this.removerLetrasDoCampo(objeto);
				
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento);				
			}
			
			if (this.isEventoKeyUp(evento)) {
				this.removerLetrasDoCampo(objeto);
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo para inteiro. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarInteiroDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarInteiroDoCampo(event, this)" onkeyup="new UtilFormatador().formatarInteiroDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarInteiroDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarInteiro(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo para inteiro. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarInteiroDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarInteiroDoCampo(event, this)" onkeyup="new UtilFormatador().formatarInteiroDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarNumeroAno = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var valores = string.split('/');
			if(valores.length == 2){
			var parte1 = this.formatar(this.formatarInteiro(valores[0]),"#########",null);
			var parte2 = this.formatar(this.formatarInteiro(valores[1]),"####",null);
			var formatado = parte1+'/'+parte2;
			}else{
			string = this.formatarInteiro(string);
			var formatado = this.formatar(string,"#########/####",'0');
			}
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	

	/**
	 * Formata o conteúdo do campo para real. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarRealDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarRealDoCampo(event, this)" onkeyup="new UtilFormatador().formatarRealDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarRealDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarReal(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}

		}
		return res;
	};

	/**
	 * Formata o conteúdo do campo para real. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarDinheiroDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarDinheiroDoCampo(event, this)" onkeyup="new UtilFormatador().formatarDinheiroDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarDinheiroDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarDinheiro(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo para real. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarTelefoneDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarTelefoneDoCampo(event, this)" onkeyup="new UtilFormatador().formatarTelefoneDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarTelefoneDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarTelefone(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo para CEP. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCepDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCepDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCepDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarCepDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarCep(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo para CNPJ. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCNPJDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCNPJDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCNPJDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarCNPJDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarCNPJ(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para CPF. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCPFDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCPFDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCPFDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarCPFDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarCPF(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para CPF ou CNPJ. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCPF_CNPJDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCPF_CNPJDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCPF_CNPJDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarCPF_CNPJDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarCPF_CNPJ(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para data no formato dd/MM/yyyy. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="new UtilFormatador().formatarDataDoCampo(event, this)" onkeypress="new UtilFormatador().formatarDataDoCampo(event, this)" onkeyup="new UtilFormatador().formatarDataDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarDataDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarData(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para data no formato dd/MM. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarData_ddMMDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarData_ddMMDoCampo(event, this)" onkeyup="new UtilFormatador().formatarData_ddMMDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarData_ddMMDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarData_ddMM(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para data no formato MM/yyyy. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarData_MMyyyyDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarData_MMyyyyDoCampo(event, this)" onkeyup="new UtilFormatador().formatarData_MMyyyyDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarData_MMyyyyDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarData_MMyyyy(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para data no formato dd/MM/yyyy hh:mm. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarData_ddMMyyyyEspacohhmmDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarData_ddMMyyyyEspacohhmmDoCampo(event, this)" onkeyup="new UtilFormatador().formatarData_ddMMyyyyEspacohhmmDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarData_ddMMyyyyEspacohhmmDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarData_ddMMyyyyEspacohhmm(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para data no formato dd/MM/yyyy hh:mm:ss. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarData_ddMMyyyyEspacohhmmssDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarData_ddMMyyyyEspacohhmmssDoCampo(event, this)" onkeyup="new UtilFormatador().formatarData_ddMMyyyyEspacohhmmssDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarData_ddMMyyyyEspacohhmmssDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarData_ddMMyyyyEspacohhmmss(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para hora no formato hh:mm. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarHoraDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarHoraDoCampo(event, this)" onkeyup="new UtilFormatador().formatarHoraDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarHoraDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarHora(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};

	/**
	 * Formata o conteúdo do campo para hora no formato hh:mm:ss. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarHora_hhmmssDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarHora_hhmmssDoCampo(event, this)" onkeyup="new UtilFormatador().formatarHora_hhmmssDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarHora_hhmmssDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarHora_hhmmss(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo removendo as acentuações. Use no evento onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeyup="new UtilFormatador().formatarRemoverAcentuacaoDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarRemoverAcentuacaoDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = this.removerAcentuacao(string);
			}
		}
		return res;
	};

	/**
	 * Formata o conteúdo do campo removendo os caracteres especiais. Use no evento onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeyup="new UtilFormatador().formatarRemoverCaracteresEspeciaisDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarRemoverCaracteresEspeciaisDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = this.removerCaracteresEspeciais(string);
			}
		}
		return res;
	};

	 /**
	  * Constantes usadas na função funcaoContarCaracteres 
	  */
	  var TECLA_BACKSPACE = 8;

	  var TECLA_TAB = 9;

	  var TECLA_DEL = 46;

	  var TECLA_ENTER = 13;

	  var TECLA_ESQUERDA = 37

	  var TECLA_DIREITA = 39;

	  var TECLA_BAIXO = 40;

	  var TECLA_CIMA  = 38;
	  
	/**
	 * Formata a quantidade de caracteres de um campo, geralmente usado no textarea. Use nos eventos onkeypress.
	 * Exemplo:
	 *		<input type="text" onkeypress="return new UtilFormatador().formatarQuantidadeDeCaracteres(event, this, 20)" onkeyup="new UtilFormatador().formatarQuantidadeDeCaracteres(event, this, 20)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 * @quantidade Quantidade máxima de caracteres.
	 */

	  this.funcaoContarCaracteres = function(campo, evt, limite) {
		var retorno = true;

		if (evt.keyCode != TECLA_BAIXO) {
			evt = evt ? evt : event;
			var tecla = evt.keyCode;
			var qtdeAtual = campo.value.length;
			var objeto = new UtilEvento();
			if (campo.value == '') {
				qtdeAtual = 0; 
			}
			// Se teclar enter no firefox, tem que adicionar mais um caracter.
			var enter = /[\n]/g;
			if (campo.value.match(enter) != null && navigator.appName != "Microsoft Internet Explorer") {
				qtdeAtual = qtdeAtual + campo.value.match(enter).length;
			}
			if (qtdeAtual > limite) {
				truncarCaracteres(campo, limite, idContador);
				qtdeAtual = limite;
				retorno = false;
			}else if (qtdeAtual >= limite && (tecla != TECLA_BACKSPACE 
					&& tecla != TECLA_DEL && tecla != TECLA_TAB)) {
				retorno = false;
			}
			if (!retorno && (evt.type == "keyup") && (evt.keyCode != TECLA_ENTER)
					&& (evt.keyCode != TECLA_CIMA) && (evt.keyCode != TECLA_BAIXO)
					&& (evt.keyCode != TECLA_DIREITA) && (evt.keyCode != TECLA_ESQUERDA)) {
				
			}
		}
		return retorno;
	}

	
	/**
	 * Formata a quantidade de caracteres de um campo, geralmente usado no textarea. Use nos eventos onkeypress.
	 * Exemplo:
	 *		<input type="text" onkeypress="return new UtilFormatador().formatarQuantidadeDeCaracteres(event, this, 20)" onkeyup="new UtilFormatador().formatarQuantidadeDeCaracteres(event, this, 20)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 * @quantidade Quantidade máxima de caracteres.
	 */
	this.formatarQuantidadeDeCaracteres = function(evento, objeto, quantidade) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento) && this.isNumero(quantidade)) {
			var string = objeto.value;
			
			if (this.isEventoKeyPress(evento)) {
				tamanho = this.getTamanho(string);
				res = 	(this.isTeclaDeMovimentacaoPressionada(evento) || 
						this.isTeclaDelecaoPressionada(evento)) ||
						(tamanho < quantidade)
			}
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = string.substring(0, quantidade);
			}
		}
		
		return res;

		
	};

	/**
	 * Formata os caracteres de um campo, permitindo que sejam digitados somente números e letras (sem acento). Use nos eventos onkeypress e onkeyup.
	 * Exemplo:
	 * 		<input type="text" onkeypress="return new UtilFormatador().formatarLetrasENumerosDoCampo(event, this)" onkeyup="new UtilFormatador().formatarLetrasENumerosDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarLetrasENumerosDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(evento) && this.isReferencia(objeto)) {
			var string = objeto.value;
			
			if (this.isEventoKeyPress(evento)) {
				res = 	this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento) ||
						this.isEspacoPressionado(evento) ||
						this.isNumeroPressionado(evento) ||
						this.isLetraPressionada(evento);
				objeto.value = this.formatarLetrasENumeros(string);
			}
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = this.formatarLetrasENumeros(string);
			}
		}
		return res;
	};
	
	
	/**
	 * Formata os caracteres de um campo, permitindo que sejam digitados somente letras (sem acento). Use nos eventos onkeypress e onkeyup.
	 * Exemplo:
	 * 		<input type="text" onkeypress="return new UtilFormatador().formatarLetrasDoCampo(event, this)" onkeyup="new UtilFormatador().formatarLetrasDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarLetrasDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(evento) && this.isReferencia(objeto)) {
			var string = objeto.value;
			
			if (this.isEventoKeyPress(evento)) {
				res = 	this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento) ||
						this.isLetraPressionada(evento);
				objeto.value = this.formatarLetras(string);
			}
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = this.formatarLetras(string);
			}
		}
		return res;
	};
	
	
	/**
	 * Formata os caracteres de um campo, permitindo que sejam digitados somente letras (sem acento) e sem espaços. Use nos eventos onkeypress e onkeyup.
	 * Exemplo:
	 * 		<input type="text" onkeypress="return new UtilFormatador().formatarLetrasSemEspacoDoCampo(event, this)" onkeyup="new UtilFormatador().formatarLetrasSemEspacoDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarLetrasSemEspacoDoCampo = function(evento, objeto) {
		var res = false;
		
		if (this.isReferencia(evento) && this.isReferencia(objeto)) {
			var string = objeto.value;
			
			if (this.isEventoKeyPress(evento)) {
				res = 	this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento) ||
						this.isLetraPressionada(evento);
				objeto.value = this.formatarLetras(string);
			}
			
			if (this.isEventoKeyUp(evento)) {
				objeto.value = this.formatarLetrasSemEspaco(string);
			}
		}
		return res;
	};
	
	/**
	 * Formata o conteúdo do campo no formato de cartão. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCartaoDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCartaoDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCartaoDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarCartaoDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarCartao(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
	
	/**
	 * Formata os caracteres de um campo, permitindo que sejam digitados qualquer letra, exceto números. Use no evento onkeypress.
	 * Exemplo:
	 * 		<input type="text" onkeypress="return new UtilFormatador().formatarAlfanumerico(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarAlfanumerico = function (evento, objeto) {
		var res = false;
		
		if (this.isReferencia(evento) && this.isReferencia(objeto)) {
			if (this.isEventoKeyPress(evento)) {
				res = !this.isNumeroPressionado(evento);
			}
		}
		return res;
	};
	
	/**
	 * Limpa as informações do campo ao pressionar uma tecla.
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarLimparAoPressionarUmaTeclaDoCampo = function (evento, objeto) {
		var res = false;
		
		if (this.isReferencia(evento) && this.isReferencia(objeto)) {
			objeto.value = "";
		}
		return false;
	};

	/**
	 * Retorna a string formatada para inteiro.
	 *
	 * @string
	 */
	this.formatarInteiro = function(string) {
		string = this.removerZerosAEsquerda(string);
		string = this.removerLetras(string);
		var pattern = this.getPatternDeInteiro(string);
		
		return this.formatar(string, pattern, null);
	};
	
	/**
	 * Retorna a string formatada para real.
	 *
	 * @string
	 */
	this.formatarReal = function(string) {
		string = this.removerLetras(string);
		string = this.removerStringsDaEsquerda(string, "0");
		if (this.getTamanho(string) > 0) {
			string = this.inserirStringNaEsquerda(string, "0", null, 3);
		}
		var pattern = this.getPatternDeReal(string);
		
		return this.formatar(string, pattern, null);
	};
	
	/**
	 * Retorna a string formatada para dinheiro.
	 *
	 * @string
	 */
	this.formatarDinheiro = function(string) {
		string = this.removerLetras(string);
		string = this.removerStringsDaEsquerda(string, "0");
		if (this.getTamanho(string) > 0) {
			string = this.inserirStringNaEsquerda(string, "0", null, 3);
		}
		var pattern = this.getPatternDeReal(string);

		string = this.formatar(string, pattern, null);
		if (!this.isStringVazia(string)) {
			string = PREFIXO_DINHEIRO + string;
		}

		return string;
	};
	
	/**
	 * Retorna a string formatada para telefone.
	 *
	 * @string
	 */
	this.formatarTelefone = function(string) {
		string = this.removerLetras(string);
		var pattern = this.getPatternDeTelefone(string);

		return this.formatar(string, pattern, null);
	};
	
	/**
	 * Retorna a string formatada para CEP.
	 *
	 * @string
	 */
	this.formatarCep = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##.###-###";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Retorna a string formatada para CNPJ.
	 *
	 * @string
	 */
	this.formatarCNPJ = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##.###.###/####-##";

		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Retorna a string formatada para CPF.
	 *
	 * @string
	 */
	this.formatarCPF = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "###.###.###-##";
		
		return this.formatar(string, PATTERN, null);
	};

	/**
	 * Retorna a string formatada para CPF ou CNPJ.
	 *
	 * @string
	 */
	this.formatarCPF_CNPJ = function(string) {
		string = this.removerLetras(string);
		if (this.getTamanho(string) <= 11) {
			string = this.formatarCPF(string);
		} else {
			string = this.formatarCNPJ(string);
		}
		
		return string;
	};
	
	
	
	/**
	 * Retorna a string formatada para data no formato dd/MM/yyyy.
	 *
	 * @string
	 */
	this.formatarData = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##/##/####";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Retorna a string formatada para data no formato dd/MM.
	 *
	 * @string
	 */
	this.formatarData_ddMM = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##/##";
		
		return this.formatar(string, PATTERN, null);
	};

	/**
	 * Retorna a string formatada para data no formato MM/yyyy.
	 *
	 * @string
	 */
	this.formatarData_MMyyyy = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##/####";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Retorna a string formatada para data no formato dd/MM/yyyy hh:mm.
	 *
	 * @string
	 */
	this.formatarData_ddMMyyyyEspacohhmm = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##/##/#### ##:##";
		
		return this.formatar(string, PATTERN, null);
	};

	/**
	 * Retorna a string formatada para data no formato dd/MM/yyyy hh:mm:ss.
	 *
	 * @string
	 */
	this.formatarData_ddMMyyyyEspacohhmmss = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##/##/#### ##:##:##";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Retorna a string formatada para hora.
	 *
	 * @string
	 */
	this.formatarHora = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##:##";
		
		return this.formatar(string, PATTERN, null);
	};

	/**
	 * Retorna a string formatada para hora no formato hh:mm:ss.
	 *
	 * @string
	 */
	this.formatarHora_hhmmss = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "##:##:##";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Função para formatar uma string baseada no pattern passado por parâmetro.
	 *
 	 * Exemplo: 
 	 *		formatar("23101978", "##/##/####", null) => 23/10/1978
 	 *		formatar("15", "##:##", "0") => 15:00
 	 *
 	 * @string String que será formatada.
 	 * @pattern Pattern que será aplicado à string.
 	 * @completar String que será usada para completar a formatação,
 	 *		caso a string que será mascarada não possuia quantidade suficiente
	 */
	this.formatar = function(string, pattern, completar) {
		if (!this.isStringVazia(string) && !this.isStringVazia(pattern)) {
			var caracteres = this.substituirString(pattern, MASCARA, "");
			if (!this.isStringVazia(caracteres)) {
				caracteres = "["+ caracteres +"]";
				string = this.substituirString(string, caracteres, "");
			}

			if (!this.isStringVazia(string)) {
				var resposta = pattern;

				for (var indice = 0; indice < pattern.length; indice++) {
					var caracterDoPattern 	= pattern.charAt(indice);
					var caracter			= string.charAt(0);
					if (caracterDoPattern == MASCARA) {
						if (string.length > 0) {
							string			= string.substring(1);
						} else {
							if (!this.isStringVazia(completar)) {
								caracter = completar;
							}
						}
						if (!this.isStringVazia(caracter)) {
							resposta = this.substituirStringNoIndice(resposta, indice, caracter);
						}
					}
					
					if (this.isStringVazia(string)) {
						resposta = resposta.substring(0, indice+1);
						indice = pattern.length;
					}
				}
				string = resposta;
			}
		} else {
			string = "";
		}
			
		return string;
	};
	
	/**
	 * Formata os caracteres de um campo, permitindo somente números e letras.
	 * 
	 * @string String que será alterada
	 */
	this.formatarLetrasENumeros = function(string) {
		var pattern = LETRA + NUMERO;
		return this.removerLetrasComExcecao(string, pattern);
	};

	/**
	 * Formata os caracteres de um campo, permitindo somente letras.
	 * 
	 * @string String que será alterada
	 */
	this.formatarLetras = function(string) {
		var pattern = LETRA;
		return this.removerLetrasComExcecao(string, pattern);
	};
	
	/**
	 * Formata os caracteres de um campo, permitindo somente letras sem espaços em branco.
	 * 
	 * @string String que será alterada
	 */
	this.formatarLetrasSemEspaco = function(string) {
		var pattern = LETRA_SEM_ESPACO;
		return this.removerLetrasComExcecao(string, pattern);
	};

	/**
	 * Formata os caracteres no padrão de cartão de crédito.
	 * 
	 * @string String que será alterada
	 */
	this.formatarCartao = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "####.####.####.####";
		
		return this.formatar(string, PATTERN, null);
	};
	
	
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

	/**
	 * Monta o pattern de telefone.
	 *
	 * Exemplo:
	 *		getPatternDeTelefone("3555039") => 355-5039
	 */
	this.getPatternDeTelefone = function (string) {
		string = this.substituirString(string, "[-() ]", "");
		var tamanho = this.getTamanho(string);
		var pattern = "";
		switch (tamanho) {
			case 7:
				pattern = "###-####";
				break;
			case 8:
				pattern = "####-####";
				break;
			case 9:
				pattern = "(##) ###-####";
				break;
			case 10:
				pattern = "(##) ####-####";
				break;
			default:
				if (tamanho > 10) {
					pattern = "(##) ####-####";
				} else {
					pattern = "###-####";
				}
		}

		return pattern;
	};

	/**
	 * Monta um pattern de um número inteiro a partir do número passado por 
	 * parâmetro.
	 *
	 * Exemplo:
	 *		getPatternDeInteiro("123456") => 123.456
	 * @string Número que será usado para criar o pattern.
	 */
	this.getPatternDeInteiro = function (string) {
		var pattern = "";

		string = this.removerLetras(string);
		string = this.removerStringsDaEsquerda(string, "0");
		if (!this.isStringVazia(string)) {
			var tamanho 	= string.length;
			var separador 	= 1;
			
			for (var indice = tamanho; indice > 0; indice--) {
				pattern = "#"+ pattern;
				if (separador == 3 && indice != 1) {
					pattern = "."+ pattern;
					separador = 0;
				}
				
				separador++;
			}
		}

		return pattern;
	};
	
	/**
	 * Monta um pattern de um número real a partir do número passado por 
	 * parâmetro.
	 *
	 * Exemplo:
	 *		getPatternDeReal("123456") => 1.234,56
	 * @string Número que será usado para criar o pattern.
	 */
	this.getPatternDeReal = function (string) {
		var pattern = "";
		string = this.removerLetras(string);
		
		if (!this.isStringVazia(string) && this.getTamanho(string) > 3) {
			var tamanho 			= string.length;
			var separador_milhar 	= 1;
			var separador_decimal 	= 0;
			

			for (var indice = tamanho; indice > 0; indice--) {
				if (separador_decimal == 2) {
					pattern = SEPARADOR_DECIMAL + pattern;
					separador_milhar = 0;
				}
				if (separador_milhar == 3) {
					pattern = SEPARADOR_MILHAR + pattern;
					separador_milhar = 0;
				}
				pattern = "#"+ pattern;

				separador_decimal++;
				separador_milhar++;
			}
		} else {
			pattern = "#,##";
		}

		return pattern;
	};
	
		
	/**
	 * Remove as letras do valor do objeto, caso existam.
	 *
	 * @objeto Objeto validado.
	 */
	this.removerLetrasDoCampo = function(objeto) {
		var string = objeto.value;
		
		if (this.isLetraContidaEm(string)) {			
			string = this.removerLetras(string);
			objeto.value = string;
		}
	};
	
	/**
	 * Remove os zeros da esquerda.
	 *
	 * @string String que será alterada.
	 */
	this.removerZerosAEsquerda = function(string) {
		return this.removerStringsDaEsquerda(string, "0");
	}
	
	/**
	 * Verifica se existe seleção no campo.
	 *
	 */
	this.isConteudoSelecionado = function() {
		var resultado = false;
		
		if (this.novoUtilBrowser().isIE()) {
			var textoSelecionado = document.selection.createRange().text;
			resultado = !this.isStringVazia(textoSelecionado);
		}
		
		return resultado;
	};
	
	/**
	 * Retorna UtilBrowser.
	 */
	this.novoUtilBrowser = function() {
		return new UtilBrowser();
	};
	
		/**
	 * Retorna a string formatada para hora.
	 *
	 * @string
	 */
	this.formatarNumeroProcesso = function(string) {
		string = this.removerLetras(string);
		var PATTERN = "###.###";
		
		return this.formatar(string, PATTERN, null);
	};
	
	/**
	 * Formata o conteúdo do campo para CPF. Use nos eventos onkeydown, onkeypress e onkeyup.
	 * Exemplo:
	 *		<input type="text" onkeydown="return new UtilFormatador().formatarCPFDoCampo(event, this)" onkeypress="return new UtilFormatador().formatarCPFDoCampo(event, this)" onkeyup="new UtilFormatador().formatarCPFDoCampo(event, this)"/>
	 *
	 * @evento Objeto event
	 * @objeto Objeto que terá o conteúdo formatado.
	 */
	this.formatarNumeroProcessoDoCampo = function(evento, objeto) {
		var res = false;

		if (this.isReferencia(objeto) && this.isReferencia(evento)) {
			var string = objeto.value;
			var formatado = this.formatarNumeroProcesso(string);
			
			if (this.isEventoKeyDown(evento)) {
				res = 	this.isNumeroPressionado(evento) ||
						this.isTeclaDeMovimentacaoPressionada(evento) ||
						this.isTeclaDelecaoPressionada(evento)
			}
			
			if (this.isEventoKeyPress(evento)) {
				if (this.isConteudoSelecionado()) {
					formatado = "";
				}
				objeto.value = formatado;
				res = true;
			}
			
			if (this.isEventoKeyUp(evento) && string != formatado) {
				objeto.value = formatado;
			}
		}
		
		return res;
	};
}