package br.jus.stj.saad.view.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.joda.time.LocalDateTime;

@ManagedBean(name="alertController")
@RequestScoped
public class AlertController {

	private static final int SIZE = 432;
	private static final String[] TIPOS_DOCUMENTO = {"Ofício", "Memorando", "Petição", "", "", "", "", ""};
	private static final String[] SITUACOES = {"Pendente", "Em Andamento", "Resolvido/Lido"};
	
	private List<Alert> alerts;
	private NumberFormat format;
	private int pending;
	
	@PostConstruct
	private void init() {
		if(format == null) {
			format = NumberFormat.getInstance();
			String number = String.valueOf(SIZE);
			format.setMinimumFractionDigits(0);
			format.setMaximumFractionDigits(0);
			format.setMinimumIntegerDigits(number.length());
			format.setMaximumIntegerDigits(number.length());
		}
		if(alerts == null) {
			alerts = new ArrayList<Alert>();
		}
		if(alerts.isEmpty()) {
			for(int i=0; i<SIZE; i++) {
				String numero = format.format(i+1);
				int indiceSituacao = new Random().nextInt(SITUACOES.length);
				String situacao = SITUACOES[indiceSituacao];
				if(indiceSituacao != 2) {
					pending++;
				}
				alerts.add(
						new Alert()
						.setDocumento("Doc " + numero)
						.setTipoDocumento(TIPOS_DOCUMENTO[new Random().nextInt(TIPOS_DOCUMENTO.length)])
						.setSituacao(situacao)
						.setDataInicio(new Date())
						.setResolverAte(generateResolverAte()));
			}
		}
	}
	
	private Date generateResolverAte() {
		Random random = new Random();
		int year = 2014 + random.nextInt(4);
		int monthOfYear = 1 + random.nextInt(12);
		int dayOfMonth = 1 + random.nextInt(28);
		int hourOfDay = random.nextInt(24);
		int minuteOfHour = random.nextInt(60);
		int secondOfMinute = random.nextInt(60);
		int millisOfSecond = random.nextInt(1000);
		LocalDateTime date = new LocalDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
		return date.toDate();
	}

	public List<Alert> listAll() {
		return alerts;
	}
	
	public int countPending() {
		return pending;
	}
	
	public static class Alert {
		private String documento;
		private String tipoDocumento;
		private String situacao;
		private Date dataInicio;
		private Date resolverAte;
		
		public Alert() {
			super();
		}

		public String getDocumento() {
			return documento;
		}

		public Alert setDocumento(String documento) {
			this.documento = documento;
			return this;
		}

		public String getTipoDocumento() {
			return tipoDocumento;
		}

		public Alert setTipoDocumento(String tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
			return this;
		}

		public String getSituacao() {
			return situacao;
		}

		public Alert setSituacao(String situacao) {
			this.situacao = situacao;
			return this;
		}

		public Date getResolverAte() {
			return resolverAte;
		}

		public Alert setResolverAte(Date resolverAte) {
			this.resolverAte = resolverAte;
			return this;
		}

		public Date getDataInicio() {
			return dataInicio;
		}

		public Alert setDataInicio(Date dataInicio) {
			this.dataInicio = dataInicio;
			return this;
		}
		
	}
	
}
