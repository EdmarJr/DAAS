package br.jus.stj.saad.entity.local;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema = "saad", name = "TIPO_DOCUMENTO")
@NamedQueries({
		@NamedQuery(name = "findAllDocumentType", query = "SELECT tipoDoc FROM TipoDocumento tipoDoc ORDER BY tipoDoc.id"),
		@NamedQuery(name = "findDocumentType", query = "SELECT tipoDoc FROM TipoDocumento tipoDoc WHERE tipoDoc = :documentType "),
//		@NamedQuery(name = "findAllDocumentTypeByFilter", query = "SELECT tipoDoc, (SELECT tipoDocUnid.nmCaminhoPastaFisica FROM TipoDocumentoUnidade tipoDocUnid WHERE tipoDoc.id = tipoDocUnid.tipoDocumento.id AND tipoDocUnid.local.id = :idLocal) FROM TipoDocumento tipoDoc ORDER BY tipoDoc.id"), 
		})
public class TipoDocumento extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SQ_TIPO_DOCUMENTO")
	private Long id;

	@Column(name = "NM_TIPO_DOCUMENTO", columnDefinition = "VARCHAR(150)")
	private String nome;

	@Column(name = "SG_TIPO_DOCUMENTO", columnDefinition = "CHAR(3)")
	private String sigla;

	@Column(name = "ST_RELATIVO_PROCESSO", columnDefinition = "CHAR(1)")
	private String stRelativoProcesso;
	
	@OneToMany(mappedBy = "tipoDocumento",fetch=FetchType.LAZY)
	private List<ControleNumeracao> controlesNumeracao;

	@Transient
	private boolean selected;

	public Long getId() {
		return id;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getStRelativoProcesso() {
		return stRelativoProcesso;
	}

	public void setStRelativoProcesso(String stRelativoProcesso) {
		this.stRelativoProcesso = stRelativoProcesso;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public List<ControleNumeracao> getControlesNumeracao() {
		return controlesNumeracao;
	}

	public void setControlesNumeracao(List<ControleNumeracao> controlesNumeracao) {
		this.controlesNumeracao = controlesNumeracao;
	}
	
	public ControleNumeracao getUltimoControle() {
		Long ano = obterMaiorAno();
		if(ano == null) {
			return null;
		}
		List<ControleNumeracao> listaPorAno = obterListaPorAno(getControlesNumeracao(), ano);
		return obterControleComUltimoValor(listaPorAno);
	}

	private Long obterMaiorAno() {
		Long maiorNumero = null;
		for(ControleNumeracao c : getControlesNumeracao()) {
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
	
	private ControleNumeracao obterControleComUltimoValor(List<ControleNumeracao> listaControleNumeracaos) {
		ControleNumeracao maiorNumero = null;
		for(ControleNumeracao c : listaControleNumeracaos) {
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
	
	public List<ControleNumeracao> obterListaPorAno(List<ControleNumeracao> listaControleNumeracaos, Long ano) {
		List<ControleNumeracao> lista = new ArrayList<ControleNumeracao>();
		for(ControleNumeracao c : listaControleNumeracaos) {
			if(c.getAno().equals(ano)) {
				lista.add(c);
			}
		}
		return lista;
	}
	

}
