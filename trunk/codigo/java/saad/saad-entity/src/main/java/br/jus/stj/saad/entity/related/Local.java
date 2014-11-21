package br.jus.stj.saad.entity.related;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.local.ControleIdentificador;
import br.jus.stj.saad.string.StringUtils;
import br.jus.stj.saad.triagem.ArquivoUtils;

@Entity
@Table(schema = "DB2SA", name = "LOCAL")
public class Local extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEQ_LOCAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "local")
	private List<LocalUsuario> locaisUsuarios;

	@Column(name = "NOME_LOCAL", columnDefinition = "VARCHAR(150)")
	private String nome;
	
	@Column(name = "SG_LOCAL")
	private String sigla;
	
	@Column(name="IND_LOCAL_ATIVO")
	private Character ativo;
	
	@Column(name="IND_ESCANINHO")
	private Character escaninho;
	
	@ManyToOne
	@JoinColumn(name="SEQ_LOCAL_SUPERIOR")
	private Local localSuperior;
	
	@Column(name="IND_ORGANOGRAMA")
	private Character organograma;
	
	@OneToMany(mappedBy = "local",fetch=FetchType.LAZY)
	private List<ControleIdentificador> controlesIdentificadores;

	public Local() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Local setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Local setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public List<LocalUsuario> getLocaisUsuarios() {
		return locaisUsuarios;
	}

	public Local setLocaisUsuarios(List<LocalUsuario> locaisUsuarios) {
		this.locaisUsuarios = locaisUsuarios;
		return this;
	}

	public Character getAtivo() {
		return ativo;
	}

	public void setAtivo(Character ativo) {
		this.ativo = ativo;
	}

	public Character getEscaninho() {
		return escaninho;
	}

	public void setEscaninho(Character escaninho) {
		this.escaninho = escaninho;
	}

	public Local getLocalSuperior() {
		return localSuperior;
	}

	public void setLocalSuperior(Local localSuperior) {
		this.localSuperior = localSuperior;
	}

	public Character getOrganograma() {
		return organograma;
	}

	public void setOrganograma(Character organograma) {
		this.organograma = organograma;
	}
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNomePasta(){
		if(!StringUtils.isEmpty(this.sigla)){
			return ArquivoUtils.retornoAmigavel(this.sigla);
		}else{
			return ArquivoUtils.retornoAmigavel(this.nome);
		}
	}
	
	public List<ControleIdentificador> getControlesIdentificadores() {
		return controlesIdentificadores;
	}

	public void setControlesIdentificadores(
			List<ControleIdentificador> controlesIdentificadores) {
		this.controlesIdentificadores = controlesIdentificadores;
	}

	public ControleIdentificador getUltimoControle() {
		Long ano = obterMaiorAno();
		if(ano == null) {
			return null;
		}
		List<ControleIdentificador> listaPorAno = obterListaPorAno(getControlesIdentificadores(), ano);
		return obterControleComUltimoValor(listaPorAno);
	}

	private Long obterMaiorAno() {
		Long maiorNumero = null;
		for(ControleIdentificador c : getControlesIdentificadores()) {
			if(maiorNumero == null) {
				maiorNumero = c.getAno();
				continue;
			}
			if(c.getAno() > maiorNumero) {
				maiorNumero = c.getAno();
			}
		}
		return maiorNumero;
	}
	
	private ControleIdentificador obterControleComUltimoValor(List<ControleIdentificador> listaControleNumeracaos) {
		ControleIdentificador maiorNumero = null;
		for(ControleIdentificador c : listaControleNumeracaos) {
			if(maiorNumero == null) {
				maiorNumero = c;
				continue;
			}
			if(c.getUltimoNumero() > maiorNumero.getUltimoNumero()) {
				maiorNumero = c;
			}
		}
		return maiorNumero;
	}
	
	public List<ControleIdentificador> obterListaPorAno(List<ControleIdentificador> listaControleNumeracaos, Long ano) {
		List<ControleIdentificador> lista = new ArrayList<ControleIdentificador>();
		for(ControleIdentificador c : listaControleNumeracaos) {
			if(c.getAno().equals(ano)) {
				lista.add(c);
			}
		}
		return lista;
	}
}
