package br.jus.stj.saad.entity.local;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.stj.commons.file.FileUtil;
import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.triagem.ArquivoUtils;

@Entity
@Table(schema = "saad", name = "ANEXO")
public class Anexo extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SQ_ANEXO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SQ_DOCUMENTO_UNIDADE")
	private Documento documento;

	@Column(name = "NM_ARQUIVO")
	private String nomeArquivo;

	@Transient
	private InputStream inputStream;

	@Override
	public Long getId() {
		return id;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String caminhoUnidade(){
		if(documento != null)
			return ArquivoUtils.obterEnderecoPastaSaad() + "/" + documento.getLocal().getNomePasta();
		else
			return null;
	}
	
	public String caminhoPasta(){
		if(documento != null)
			return caminhoUnidade() + "/" + ArquivoUtils.retornoAmigavel(documento.getTipo().getNome());
		else
			return null;
	}
	
	public String caminhoArquivo(){
		if(documento != null)
			return caminhoPasta() + "/" + this.documento.getId() + "_" + this.getNomeArquivo();
		else
			return null;
	}

	@PrePersist
	public void gravarArquivos() {
		if(inputStream != null){
			FileUtil.salvarEmDisco(inputStream, caminhoArquivo());
		}
	}
}
