package br.jus.stj.saad.entity.related;

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
import br.jus.stj.saad.entity.local.Documento;
import br.jus.stj.saad.enumerators.SexoEnum;

@Entity
@Table(schema = "MALADIR", name = "DESTINATARIO")
public class Destinatario extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_DESTINATARIO")
	private Long id;

	@OneToMany(mappedBy = "destinatario")
	private List<DestinatarioOcupacao> destinatariosOcupacao;

	@Column(name = "NOME_DESTINATARIO")
	private String nome;

	@Column(name = "DESC_ENDERECO")
	private String endereco;

	@Column(name = "DESC_CMPLT_ENDER")
	private String complementoEndereco;

	@Column(name = "DESC_BAIRRO")
	private String bairro;

	@Column(name = "COD_CEP")
	private String cep;

	@Column(name = "DESC_CIDADE")
	private String cidade;

	@Column(name = "SG_UF")
	private String uf;

	@Column(name = "NUM_TELEFONE")
	private String telefone;

	@Column(name = "DT_ANIVERSARIO")
	private Date dataAniversario;

	@Column(name = "DT_ANIV_CONJUGE")
	private Date dataAniversarioConjuge;

	@Column(name = "DT_EVENTO")
	private Date dataEvento;

	@Column(name = "DESC_EMAIL")
	private String nomeEmail;

	@Column(name = "DESC_TITULO")
	private String titulo;

//	@ManyToOne
//	@JoinColumn(name = "SEQ_LOCAL")
//	private Local local;

//	@ManyToOne
//	@JoinColumn(name = "SEQ_TRATAMENTO")
//	private Tratamento tratamento;

	public Long getId() {
		return id;
	}

	public List<DestinatarioOcupacao> getDestinatariosOcupacao() {
		return destinatariosOcupacao;
	}

	public void setDestinatariosOcupacao(
			List<DestinatarioOcupacao> destinatariosOcupacao) {
		this.destinatariosOcupacao = destinatariosOcupacao;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public Date getDataAniversarioConjuge() {
		return dataAniversarioConjuge;
	}

	public void setDataAniversarioConjuge(Date dataAniversarioConjuge) {
		this.dataAniversarioConjuge = dataAniversarioConjuge;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getNomeEmail() {
		return nomeEmail;
	}

	public void setNomeEmail(String nomeEmail) {
		this.nomeEmail = nomeEmail;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

//	public Local getLocal() {
//		return local;
//	}
//
//	public void setLocal(Local local) {
//		this.local = local;
//	}

//	public Tratamento getTratamento() {
//		return tratamento;
//	}
//
//	public void setTratamento(Tratamento tratamento) {
//		this.tratamento = tratamento;
//	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub

	}

	public String getFax() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFax(String fax) {
		// TODO Auto-generated method stub

	}

	public Character getSexo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSexo(Character sexo) {
		// TODO Auto-generated method stub

	}
	
	public Documento getDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDocumento(Documento documento) {
		// TODO Auto-generated method stub
		
	}

	public void setDocumentoId(Long documentoId) {
		// TODO Auto-generated method stub
		
	}

	public DestinatarioOcupacao getDestinatarioOcupacao() {
		if (getDestinatariosOcupacao() != null
				&& getDestinatariosOcupacao().size() > 0) {
			return getDestinatariosOcupacao().get(0);
		}
		return null;
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
}
