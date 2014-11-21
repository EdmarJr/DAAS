function RichFileUpload() {
	this.remover = function(fileupload, evento) {
		//ação do botão "Remover Todos"
		if (evento.memo.entry == null) {
			if (this.isExisteFuncao("RichFileUpload_ajaxAcaoRemoverTodos")) {
				RichFileUpload_ajaxAcaoRemoverTodos();
				fileupload.uploadedCount = 0;
			}
		} else { 
			//ação do link "Remover"
			var arquivo = evento.memo.entry.fileName;
			if (this.isExisteFuncao("RichFileUpload_ajaxAcaoRemover")) {
				RichFileUpload_ajaxAcaoRemover(arquivo);
				fileupload.uploadedCount = fileupload.uploadedCount - 1;
			}
		}
		this.redimensionar(fileupload);
	}
	
	this.redimensionar = function(fileupload) {
		var arquivos = fileupload.uploadedCount;
		var div = document.getElementById("inclusao:arquivos:fileItems");
		if (arquivos == 0) {
			div.style.height = "58px";
		} else {
			var height = 58 * arquivos;
			div.style.height = height +"px";
		}	
	}
	
	this.isExisteFuncao = function(nome) {
		var resultado = true;
		
		if (eval("window."+ nome) == null) {
			resultado = false;
			alert("Função "+ nome +" inexistente. \nEsta função é necessária para o funcionamento do rich:fileUpload.")
		}
		return resultado;
	}
}

var richfileupload = new RichFileUpload();