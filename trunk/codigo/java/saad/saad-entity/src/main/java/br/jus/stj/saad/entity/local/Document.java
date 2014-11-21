package br.jus.stj.saad.entity.local;

import java.util.Date;

import br.jus.stj.saad.entity.EntidadeBase;
import br.jus.stj.saad.util.DocumentEntryType;
import br.jus.stj.saad.util.Status;
import br.jus.stj.saad.util.YieldDate;

public class Document extends EntidadeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2080941247387165588L;

	private Long id;
	private String number;
	private Long registryNumber;
	private String description;
	private String physicalCabinet;
	private String subject;
	private String observation;
	private String stepDescription;
	private Date entryDate;
	private DocumentType documentType;
	private String responsible;
	private DocumentEntryType entryType;
	private Status status;
	private Status demandStatus;
	private String management;
	private String eventName;
	private YieldDate eventPeriod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getRegistryNumber() {
		return registryNumber;
	}

	public void setRegistryNumber(Long registryNumber) {
		this.registryNumber = registryNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhysicalCabinet() {
		return physicalCabinet;
	}

	public void setPhysicalCabinet(String physicalCabinet) {
		this.physicalCabinet = physicalCabinet;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public DocumentEntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(DocumentEntryType entryType) {
		this.entryType = entryType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getDemandStatus() {
		return demandStatus;
	}

	public void setDemandStatus(Status demandStatus) {
		this.demandStatus = demandStatus;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public YieldDate getEventPeriod() {
		return eventPeriod;
	}

	public void setEventPeriod(YieldDate eventPeriod) {
		this.eventPeriod = eventPeriod;
	}

}
