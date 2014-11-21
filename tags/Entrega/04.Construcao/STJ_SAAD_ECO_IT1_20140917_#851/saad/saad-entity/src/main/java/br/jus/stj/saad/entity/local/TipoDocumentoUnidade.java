package br.jus.stj.saad.entity.local;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.entity.related.Local;

@Entity
@Table(schema = "saad", name = "TIPO_DOCUMENTO_UNIDADE")
public class TipoDocumentoUnidade extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TipoDocumentoUnidadeId id;
	
	@ManyToOne
	@MapsId("idTipoDocumento")
	@JoinColumn(name = "SQ_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;
	@ManyToOne
	@MapsId("idLocal")
	@JoinColumn(name = "SEQ_LOCAL")
	private Local local;

	@Column(name = "NM_CAMINHO_PASTA_FISICA")
	private String nmCaminhoPastaFisica;

	@Column(name = "ST_GESTAO_MINISTRO")
	private Character stGestaoMinistro;

	

	@Override
	public Long getId() {
		return null;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getNmCaminhoPastaFisica() {
		return nmCaminhoPastaFisica;
	}

	public void setNmCaminhoPastaFisica(String nmCaminhoPastaFisica) {
		this.nmCaminhoPastaFisica = nmCaminhoPastaFisica;
	}

	public Character getStGestaoMinistro() {
		return stGestaoMinistro;
	}

	public void setStGestaoMinistro(Character stGestaoMinistro) {
		this.stGestaoMinistro = stGestaoMinistro;
	}

	
	
	

}
