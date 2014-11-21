/*******************************************************************************
 * Autor: Adriano Pamplona
 * Data de criação: 01/05/2007
 * Versão: 1.0.0
 *******************************************************************************
 * Objetivo: 
 *		Objeto para detecção do tipo de browser.
 *******************************************************************************
 * Dependências: 
 *		nenhuma
 *******************************************************************************
 * métodos do objeto:
 * 		isAOL()
 * 		isFirefox()
 * 		isIE()
 * 		isIE4()
 * 		isIE5()
 * 		isNS()
 * 		isNS4()
 * 		isNS4()
 * 		isNS5()
 * 		isOpera()
 *******************************************************************************
 * Exemplo:
 * 		var objeto = new UtilBrowser();
 *		alert(objeto.isIE());
 *******************************************************************************
 */
function UtilBrowser() {
	var AOL 	= false; //AOL
	var FIREFOX = false; //Firefox
	var IE4 	= false; //Internet explorer 4
	var IE5 	= false; //Internet explorer 5 ou superior
	var NS4 	= false; //Netscape 4
	var NS5 	= false; //Netscape 5 ou superior.
	var OPERA 	= false; //Opera

	/**
	 * Retorna true se o browser for AOL.
	 */
	this.isAOL = function() {
		this.carregarTiposDeBrowser();
		return AOL;
	};
	
	/**
	 * Retorna true se o browser for Firefox.
	 */
	this.isFirefox = function() {
		this.carregarTiposDeBrowser();
		return FIREFOX;
	};
	
	/**
	 * Retorna true se o browser for Internet Explorer.
	 */
	this.isIE = function() {
		return (this.isIE4() || this.isIE5());
	};
	
	/**
	 * Retorna true se o browser for IE4.
	 */
	this.isIE4 = function() {
		this.carregarTiposDeBrowser();
		return IE4;
	};
	
	/**
	 * Retorna true se o browser for IE5.
	 */
	this.isIE5 = function() {
		this.carregarTiposDeBrowser();
		return IE5;
	};
		
	/**
	 * Retorna true se o browser for Netscape, Firefox ou Mozila.
	 */
	this.isNS = function() {
		return (this.isNS4() || this.isNS5());
	};
	
	/**
	 * Retorna true se o browser for Netscape 4.
	 */
	this.isNS4 = function() {
		this.carregarTiposDeBrowser();
		return NS4;
	};

	/**
	 * Retorna true se o browser for Netscape 5.
	 */
	this.isNS5 = function() {
		this.carregarTiposDeBrowser();
		return NS5;
	};
	
	/**
	 * Retorna true se o browser for Opera.
	 */
	this.isOpera = function() {
		this.carregarTiposDeBrowser();
		return OPERA;
	};
	
/*******************************************************************************
 * Funções privadas do objeto.
 ******************************************************************************/

	/**
	 * Carrega as constantes que representam os diversos Browsers.
	 */
	this.carregarTiposDeBrowser = function() {
		if (document.images) {
			var userAgent 	= navigator.userAgent;
			var appName 	= navigator.appName;
			
			if (userAgent.indexOf("Opera") != -1) {
				OPERA = true;
			} else if (userAgent.indexOf("AOL") != -1) {
				AOL = true;
			} else if (userAgent.indexOf("Firefox") != -1) {
				FIREFOX = true;
			} else if (appName == "Microsoft Internet Explorer") {
				IE5 = (document.all != null && document.getElementById != null);
				IE4 = (document.all != null && !document.getElementById != null);
			} else if (appName == "Netscape") {
				NS4 = (document.layers != null);
				NS5 = (document.addEventListener != null);
			}
		} else {
			alert("Você está usando um browser de versão inferior à versão 4");
		}
	};
}