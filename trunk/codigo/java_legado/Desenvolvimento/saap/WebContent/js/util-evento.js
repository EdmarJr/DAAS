/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação de eventos.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto		(util-objeto.js)
 *		UtilNumero		(util-numero.js)
 *		UtilString		(util-string.js)
 *		UtilData		(util-data.js)
 *******************************************************************************
 * métodos do objeto:
 *		getCodigoTecla(evento)
 *		getLetraPressionada(evento)
 *		isNumeroPressionado(evento)
 *		isLetraPressionada(evento)
 *		isEspacoPressionado(evento)
 *		isTeclaDeMovimentacaoPressionada(evento)
 *		isTeclaEspecialPressionada(evento)
 *		isTeclaEnterPressionada(evento)
 *		isTeclaTabPressionada(evento)
 *		isTeclaDelecaoPressionada(evento)
 *		isEventoAbort(evento)
 *		isEventoBlur(evento)
 *		isEventoChange(evento)
 *		isEventoClick(evento)
 *		isEventoDblClick(evento)
 *		isEventoDragDrop(evento)
 *		isEventoError(evento)
 *		isEventoFocus(evento)
 *		isEventoKeyDown(evento)
 *		isEventoKeyPress(evento)
 *		isEventoKeyUp(evento)
 *		isEventoLoad(evento)
 *		isEventoMouseDown(evento)
 *		isEventoMouseMove(evento)
 *		isEventoMouseOut(evento)
 *		isEventoMouseOver(evento)
 *		isEventoMouseUp(evento)
 *		isEventoMove(evento)
 *		isEventoReset(evento)
 *		isEventoResize(evento)
 *		isEventoSelect(evento)
 *		isEventoSubmit(evento)
 *		isEventoUnload(evento)
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilEvento();
 *		alert(objeto.getCodigoTecla(window.event));
 *******************************************************************************
 */
 
UtilEvento.prototype = new UtilData();

function UtilEvento() {
	var TECLAS_LETRAS				= ",65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122," //letras de a-z e A-Z
	var TECLA_ESPACO				= ",32,";
	var TECLAS_NUMERO_ALFANUMERICO	= ",48,49,50,51,52,53,54,55,56,57,"; //números do teclado alfanumérico
	var TECLAS_NUMERO_NUMERICO		= ",96,97,98,99,100,101,102,103,104,105," //números do teclado numérico (use no onkeydown)
	var TECLA_ENTER					= ",13,";
	var TECLA_TAB					= ",9,";
	var TECLA_SHIFT					= ",16,";
	var TECLAS_MOVIMENTACAO			= ",35,36,37,38,39,40," + TECLA_TAB; //pg up, pg down, home, end, seta esquerda, seta cima, seta direita, seta baixo
	var TECLAS_DELECAO				= ",8,46,"; //backspace, delete
	var TECLAS_ESPECIAIS 			= TECLAS_MOVIMENTACAO + TECLA_ENTER + TECLA_TAB;
	
	/**
	 * Retorna o código da tecla pressionada.
	 *
	 * @evento 
	 */
	this.getCodigoTecla = function(evento) {
		var codigo = 0;
		
		if (this.isReferencia(evento)) {
			if (document.all != null) {
				codigo = evento.keyCode;
			} else {
				codigo = evento.charCode;
				if (codigo == 0) {
					codigo = evento.keyCode;
				}
			}
		}
		return codigo;
	}
	
	/**
	 * Retorna a letra pressionada.
	 *
	 * @evento
	 */
	this.getLetraPressionada = function(evento) {
		return String.fromCharCode(this.getCodigoTecla(evento));
	}
	
	/**
	 * Retorna true se a tecla pressionada for um número. Para maior eficiência use no evento onkeydown.
	 *
	 * @evento
	 */
	this.isNumeroPressionado = function(evento) {
		var res = false;
		var codigo = ","+ this.getCodigoTecla(evento) +",";
		
		if (evento.type == "keydown") {
			res = 	(TECLAS_NUMERO_ALFANUMERICO.indexOf(codigo) != -1) ||
					(TECLAS_NUMERO_NUMERICO.indexOf(codigo) != -1)
		} else {
			res = 	(TECLAS_NUMERO_ALFANUMERICO.indexOf(codigo) != -1)
		}
		return res;
	}
	
	/**
	 * Retorna true se a tecla pressionada for uma letra de a-z (maiúscula ou minúscula)
	 *
	 * @evento
	 */
	this.isLetraPressionada = function(evento) {
		var codigo = ","+ this.getCodigoTecla(evento) +",";
		return this.isStringContidaEm(TECLAS_LETRAS, codigo);
	}
	
	this.isEspacoPressionado = function(evento) {
		var codigo = ","+ this.getCodigoTecla(evento) +",";
		return this.isStringContidaEm(TECLA_ESPACO, codigo);
	}
		
	/**
	 * Retonra true se a tecla pressionada for uma tecla de movimentação.
	 * Use esta função no evento onkeydown ou no onkeyup.
	 *
	 * @evento
	 */
	this.isTeclaDeMovimentacaoPressionada = function(evento) {
		var tecla = ","+ this.getCodigoTecla(evento) +",";
		return (TECLAS_MOVIMENTACAO.indexOf(tecla) != -1);
	}
	
	/**
	 * Retorna true se a tecla pressionada for uma tecla especial.
	 *
	 * @evento
	 */
	this.isTeclaEspecialPressionada = function(evento) {
		var tecla = ","+ this.getCodigoTecla(evento) +",";
		return (TECLAS_ESPECIAIS.indexOf(tecla) != -1);
	}
	
	/**
	 * Retonra true se a tecla pressionada for a tecla ENTER.
	 *
	 * @evento
	 */
	this.isTeclaEnterPressionada = function(evento) {
		var tecla = ","+ this.getCodigoTecla(evento) +",";
		return (TECLA_ENTER.indexOf(tecla) != -1);
	}

	/**
	 * Retonra true se a tecla pressionada for a tecla TAB.
	 * Use esta função no evento onkeydown ou no onkeyup.
	 *
	 * @evento
	 */
	this.isTeclaTabPressionada = function(evento) {
		var tecla = ","+ this.getCodigoTecla(evento) +",";
		return (TECLA_TAB.indexOf(tecla) != -1);
	}

	/**
	 * Retonra true se a tecla pressionada for a tecla de deleção (backspace, delete).
	 * Use esta função no evento onkeydown ou no onkeyup.
	 *
	 * @evento
	 */
	this.isTeclaDelecaoPressionada = function(evento) {
		var tecla = ","+ this.getCodigoTecla(evento) +",";
		return (TECLAS_DELECAO.indexOf(tecla) != -1);
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo abort.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoAbort = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "abort");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo blur.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoBlur = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "blur");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo change.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoChange = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "change");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo click.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoClick = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "click");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo dblclick.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoDblClick = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "dblclick");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo dragdrop.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoDragDrop = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "dragdrop");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo error.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoError = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "error");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo focus.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoFocus = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "focus");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo keydown.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoKeyDown = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "keydown");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo keyupress.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoKeyPress = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "keypress");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo keyup.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoKeyUp = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "keyup");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo load.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoLoad = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "load");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo mousedown.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMouseDown = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "mousedown");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo mousemove.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMouseMove = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "mousemove");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo mouseout.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMouseOut = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "mouseout");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo mouseover.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMouseOver = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "mouseover");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo mouseup.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMouseUp = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "mouseup");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo move.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoMove = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "move");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo reset.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoReset = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "reset");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo resize.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoResize = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "resize");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo select.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoSelect = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "select");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo submit.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoSubmit = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "submit");
	}
	
	/**
	 * Retorna true se o evento invocado for do tipo unload.
	 *
	 * @evento Evento atual.
	 */
	this.isEventoUnload = function(evento) {
		return this.isReferencia(evento) && this.isStringContidaEm(evento.type, "unload");
	}

/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

}

