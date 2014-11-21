package br.jus.stj.saad.entity.local;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.jus.stj.saad.entity.EntidadeBase;

@Entity
@Table(schema = "saad", name = "EVENTO")
public class Evento extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "SQ_EVENTO", referencedColumnName = "SQ_DOCUMENTO_UNIDADE")
	private Documento documento;

	@Column(name = "NM_EVENTO")
	private String nomeEvento;

	@Column(name = "DT_INICIO_EVENTO")
	@Temporal(TemporalType.DATE)
	private Date dataInicioEvento;

	@Column(name = "DT_TERMINO_EVENTO")
	@Temporal(TemporalType.DATE)
	private Date dataTerminoEvento;

	@Column(name = "TX_OBSERVACAO_EVENTO")
	private String observacaoEvento;
	
	@Column(name = "NR_CARGA_HORARIA_TOTAL")
	private Long cargaHorariaTotal;  
	
	@Column(name = "HR_INICIO_EVENTO")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Column(name = "HR_TERMINO_EVENTO")
	@Temporal(TemporalType.TIME)
	private Date horaTermino;
	
	@Transient
	private String responsavel;
	
	@Override
	public Long getId() {
		return getDocumento().getId();
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Date getDataInicioEvento() {
		return dataInicioEvento;
	}

	public void setDataInicioEvento(Date dataInicioEvento) {
		this.dataInicioEvento = dataInicioEvento;
	}

	public Date getDataTerminoEvento() {
		return dataTerminoEvento;
	}

	public void setDataTerminoEvento(Date dataTerminoEvento) {
		this.dataTerminoEvento = dataTerminoEvento;
	}

	public String getObservacaoEvento() {
		return observacaoEvento;
	}

	public void setObservacaoEvento(String observacaoEvento) {
		this.observacaoEvento = observacaoEvento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public Long getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Long cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
}
