package br.jus.stj.saad.entity.interfaces;

import java.util.Date;

import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.entity.related.Local;
import br.jus.stj.saad.entity.related.Tratamento;

public interface IDestinatario {
	public Long getId();
	
	public Date getDataAniversario();

	public void setDataAniversario(Date dataAniversario);

	public Documento getDocumento();

	public void setDocumento(Documento documento);

	public String getEmail();

	public void setEmail(String email);

	public String getFax();

	public void setFax(String fax);

	public String getTitulo();

	public void setTitulo(String titulo);

	public Local getLocal();

	public void setLocal(Local local);

	public Tratamento getTratamento();

	public void setTratamento(Tratamento tratamento);

	public Character getSexo();

	public void setSexo(Character sexo);
	
	public String getNome();

	public void setNome(String nome);

	public String getEndereco();

	public void setEndereco(String endereco);

	public String getComplementoEndereco();

	public void setComplementoEndereco(String complementoEndereco);

	public String getBairro();

	public void setBairro(String bairro);

	public String getCep();

	public void setCep(String cep);

	public String getCidade();

	public void setCidade(String cidade);

	public String getUf();

	public void setUf(String uf);

	public String getTelefone();

	public void setTelefone(String telefone);

	public void setDocumentoId(Long documentoId);
}
