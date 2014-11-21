package br.jus.stj.saad.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.jus.stj.saad.file.IOUtils;
import br.jus.stj.saad.triagem.ArquivoUtils;
import br.jus.stj.saad.vo.ArquivoTriagem;

@Stateless
@LocalBean
public class TriagemService {
	
	
	public List<ArquivoTriagem> obterListaArquivosTriagemDisponiveis() {
		String enderecoPastaTriagem = ArquivoUtils.obterEnderecoPastaTriagem();
		ArrayList<ArquivoTriagem> lista = new ArrayList<ArquivoTriagem>();
		List<String> listaCaminhoArquivos = IOUtils.obterListaNomeArquivosPorCaminho(enderecoPastaTriagem);
		for(String s : listaCaminhoArquivos) {
			lista.add(new ArquivoTriagem(s));
		}
		return lista;
	}
	
	public List<String> obterLista(String enderecoPastaTriagem) throws Exception{
		return IOUtils.obterListaNomeArquivosPorCaminhoTeste(enderecoPastaTriagem);
	}

	

	public InputStream obterInputStreamPorCaminhoDeArquivo(String caminhoArquivo) {
		return IOUtils.obterInputStreamPorCaminho(caminhoArquivo);
	}

	/**
	 * 
	 * @author edmar.junior
	 * 
	 * @param nomeArquivo
	 * @param caminhoRelativo
	 * @param mesmoNome
	 *            caso true, o parametro caminho relativo, vai ser a pasta do
	 *            arquivo, e o parametro nomeArquivo o nome do arquivo, ou seja
	 *            os dois vão ser concatenados, caso false o caminho relativo
	 *            vai ser a pasta e o nome do arquivo.
	 */
	public void moverArquivoDentroDaPastaTriagemParaPastaRelativa(
			String nomeArquivo, String caminhoRelativo, boolean arquivoComMesmoNome) {
		// TODO Auto-generated method stub

	}

}
