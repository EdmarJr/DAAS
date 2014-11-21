package br.jus.stj.saad.persistence;

import java.util.List;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.local.TipoDocumento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Usuario;
import br.jus.stj.saad.vo.FiltroDocumentoConsultaAndamentoVO;

public interface DocumentoDAO extends GenericDAO<Documento>{

	public List<Documento> listarDocumento(Integer primeiraLinha, 
			Integer maximoPorPagina ,FiltroDocumentoConsultaAndamentoVO filtro);
	
	public Integer listarDocumentoCount(
			FiltroDocumentoConsultaAndamentoVO filtro);
	
	public Documento obterPorId(Long id, TipoDocumento tipoDocumento);
	
	public List<Documento> obterTarefasDocumentoPendente(Usuario usuario);
	
	public List<Documento> obterDocumentosPendentes(Local local);
	
	public Documento buscarDocumentoPorIdentificador(String sigla, String identificacaoDocumentoSTJ, String ano, Local local);

	public Documento buscarDocumento(String numero, String sigla, String identificacaoDocumentoSTJ, String ano, Local local);
	
	public List<Documento> buscarDocumentoPorDocumento(Documento documento);

	public List<Documento> documentosPendentesLocalLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local);
	
	public int documentosPendentesLocalLazyCount(Local local);
	
	public List<Documento> documentosTarefasPendentesLocalLazy(Integer primeiraLinha,
			Integer maximoPorPagina, Local local, Usuario usuario);
	
	public int documentosTarefasPendentesLocalLazyCount(Local local, Usuario usuario);
	
	public int totalDocumentosPendentesPorDia(Integer dias, Local local, Usuario usuario, Boolean maior);
	
	public int totalDocumentosPendentesEntreDias(Integer diaInicial, Integer diaFinal, Local local, Usuario usuario);
	
	public int totalDocumentosSemTarefas(Local local);
		
}
