/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para manipulação de janelas.
 *******************************************************************************
 * Dependências: 
 *		UtilObjeto	(util-objeto.js)
 *		UtilBrowser	(util-browser.js)
 *******************************************************************************
 * métodos do objeto:
 *		abrirPopUp(url, largura, altura, opcoes, nome)
 *		abrirPopUpModal(url, largura, altura, opcoes, nome)
 *		isPopUp()
 *		fechar()
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilJanela();
 *		alert(objeto.abrirPopUp("/index.jsp", 500, 500, "status=yes", "minhaJanela"));
 *******************************************************************************
 */
 
UtilJanela.prototype = new UtilBrowser();

function UtilJanela() {
	
	/**
	 * Abre uma janela pop-up.
	 *
	 * @url
	 * @largura 
	 * @altura
	 * @opcoes Opções do window.open.
	 * 		channelmode = { yes | no | 1 | 0 }
	 * 		directories = { yes | no | 1 | 0 }
	 * 		fullscreen = { yes | no | 1 | 0 }
	 * 		height = number
	 * 		left = number
	 * 		location = { yes | no | 1 | 0 }
	 * 		menubar = { yes | no | 1 | 0 }
	 * 		resizable = { yes | no | 1 | 0 }
	 * 		scrollbars = { yes | no | 1 | 0 }
	 * 		status = { yes | no | 1 | 0 }
	 * 		titlebar = { yes | no | 1 | 0 }
	 * 		toolbar = { yes | no | 1 | 0 }
	 * 		top = number
	 * 		width = number
	 * @nome
	 */
	this.abrirPopUp = function(url, largura, altura, opcoes, nome) {
		var left 	= (parseInt(screen.width,10) - parseInt(largura,10)) / 2;
		var top 	= (parseInt(screen.height,10) - parseInt(altura,10)) / 2;
	
		opcoes		= (opcoes == null? "": opcoes);
		opcoes		= "width="+ largura +"px, height="+ altura +"px," +" top="+ top +", left="+ left + ", "+ opcoes;
		return window.open (url, nome, opcoes);
	}

	/**
	 * Abre uma janela pop-up modal.
	 *
	 * @url
	 * @largura
	 * @altura
	 * @opcoes Opções do window.showModalDialog.
	 * 		dialogHeight:sHeight
	 * 		dialogLeft:sXPos
	 * 		dialogTop:sYPos
	 * 		dialogWidth:sWidth
	 * 		center:{ yes | no | 1 | 0 | on | off }
	 * 		dialogHide:{ yes | no | 1 | 0 | on | off }
	 * 		edge:{ sunken | raised }
	 * 		resizable:{ yes | no | 1 | 0 | on | off }
	 * 		scroll:{ yes | no | 1 | 0 | on | off }
	 * 		status:{ yes | no | 1 | 0 | on | off }
	 * 		unadorned:{ yes | no | 1 | 0 | on | off }
	 * @nome
	 */
	this.abrirPopUpModal = function(url, largura, altura, opcoes, nome) {
		if (this.isIE()) {
			var left 	= (parseInt(screen.width,10) - parseInt(largura,10)) / 2;
			var top 	= (parseInt(screen.height,10) - parseInt(altura,10)) / 2;
		
			opcoes		= (opcoes == null? "": opcoes);
			opcoes		= "dialogWidth="+ largura +"px, dialogHeight="+ altura +"px," +" dialogTop="+ top +", dialogLeft="+ left + ", "+ opcoes;
			return window.showModalDialog(url, nome, opcoes);		
		}
	}
	
	/**
	 * Retorna true se a janela atual for uma pop-up.
	 */
	this.isPopUp = function() {
		return this.isReferencia(window.opener);
	}
	
	/**
	 * Fecha a janela atual.
	 */
	this.fechar = function() {
		if (this.isReferencia(window)) {
			window.close();
		}
	}
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/
	/**
	 * @veja UtilObjeto.isReferencia(objeto)
	 */
	this.isReferencia = function(objeto) {
		var util = new UtilObjeto();
		return util.isReferencia(objeto);
	}
}