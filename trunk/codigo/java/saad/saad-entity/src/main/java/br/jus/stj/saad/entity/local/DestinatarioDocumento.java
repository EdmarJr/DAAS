package br.jus.stj.saad.entity.local;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.enumerators.SexoEnum;

@Entity
@Table(schema = "saad", name = "DESTINATARIO_DOCUMENTO")
public class DestinatarioDocumento extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SQ_DESTINATARIO_DOCUMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @OneToMany(mappedBy="destinatarioDocumento")
    private List<EnderecamentoDestinatarioDocumento> listEnderecamentoDestinatarioDocumento;

	@Column(name = "NM_DESTINATARIO", nullable=true)
	private String nome;

	@Column(name = "NM_ENDERECO", nullable=true)
	private String endereco;

	@Column(name = "NM_COMPLEMENTO_ENDERECO", nullable=true)
	private String complementoEndereco;

	@Column(name = "NM_BAIRRO", nullable=true)
	private String bairro;

	@Column(name = "NR_CEP", nullable=true)
	private String cep;

	@Column(name = "NM_CIDADE", nullable=true)
	private String cidade;

	@Column(name = "SG_UF", nullable=true)
	private String uf;

	@Column(name = "NR_TELEFONE", nullable=true)
	private String telefone;

	@Column(name = "DT_ANIVERSARIO", nullable=true)
	private Date dataAniversario;

	@Column(name = "NM_EMAIL", nullable=true)
	private String email;

	@Column(name = "NM_TITULO", nullable=true)
	private String titulo;

	@Column(name = "NM_FAX", nullable=true)
	private String fax;

	@Column(name = "TP_SEXO", nullable=true)
	private Character sexo;

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	
	public String getSexoAmigavel(){
		if(getSexo() != null){
			if(SexoEnum.MASCULINO.getValor().equalsIgnoreCase(getSexo().toString())){
				return SexoEnum.MASCULINO.getCodLabel();
			}else{
				return SexoEnum.FEMININO.getCodLabel();
			}
		}else{
			return null;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EnderecamentoDestinatarioDocumento> getListEnderecamentoDestinatarioDocumento() {
		return listEnderecamentoDestinatarioDocumento;
	}

	public void setListEnderecamentoDestinatarioDocumento(
			List<EnderecamentoDestinatarioDocumento> listEnderecamentoDestinatarioDocumento) {
		this.listEnderecamentoDestinatarioDocumento = listEnderecamentoDestinatarioDocumento;
	}
}
