package br.jus.stj.saad.vo;

import java.io.InputStream;
import java.nio.file.Paths;

public class ArquivoTriagem {
	private String nomeArquivo;
	private String caminhoArquivo;
	private InputStream inputStream;

	public ArquivoTriagem() {
	}

	public ArquivoTriagem(String caminhoArquivo) {
		setCaminhoArquivo(caminhoArquivo);
	}

	public String getNomeArquivo() {
		return Paths.get(getCaminhoArquivo()).getFileName().toString();
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caminhoArquivo == null) ? 0 : caminhoArquivo.hashCode());
		result = prime * result
				+ ((inputStream == null) ? 0 : inputStream.hashCode());
		result = prime * result
				+ ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoTriagem other = (ArquivoTriagem) obj;
		if (caminhoArquivo == null) {
			if (other.caminhoArquivo != null)
				return false;
		} else if (!caminhoArquivo.equals(other.caminhoArquivo))
			return false;
		if (inputStream == null) {
			if (other.inputStream != null)
				return false;
		} else if (!inputStream.equals(other.inputStream))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		return true;
	}

}
