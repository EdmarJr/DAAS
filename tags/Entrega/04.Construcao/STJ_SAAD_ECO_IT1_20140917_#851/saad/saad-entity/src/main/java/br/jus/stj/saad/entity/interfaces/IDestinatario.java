package br.jus.stj.saad.entity.interfaces;

import java.util.Date;

import br.jus.stj.saad.entity.related.Tratamento;

public interface IDestinatario {
	public Long getId();

	public String getNomeDestinatario();

	public void setNomeDestinatario(String nomeDestinatario);

	public String getNomeEndereco();

	public void setNomeEndereco(String nomeEndereco);

	public String getNomeComplementoEndereco();

	public void setNomeComplementoEndereco(String nomeComplementoEndereco);

	public String getNomeBairro();

	public void setNomeBairro(String nomeBairro);

	public String getNumeroCep();

	public void setNumeroCep(String numeroCep);

	public String getNomeCidade();

	public void setNomeCidade(String nomeCidade);

	public String getSiglaUf();

	public void setSiglaUf(String siglaUf);

	public String getNumeroTelefone();

	public void setNumeroTelefone(String numeroTelefone);

	public Date getDataAniversario();

	public void setDataAniversario(Date dataAniversario);


	public String getEmail();

	public void setEmail(String email);

	public String getFax();

	public void setFax(String fax);

	public String getTitulo();

	public void setTitulo(String titulo);

	public Tratamento getTratamento();

	public void setTratamento(Tratamento tratamento);

	public Character getSexo();

	public void setSexo(Character sexo);
}
