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

@ManagedBean(name="taskController")
@RequestScoped
public class TaskController {

	private static final int SIZE = 432;
	private static final String[] TIPOS_DOCUMENTO = {"Ofício", "Memorando", "Petição", "", "", "", "", ""};
	private static final String[] SITUACOES = {"Pendente", "Em Andamento", "Resolvido/Lido"};
	
	private List<Task> tasks;
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
		if(tasks == null) {
			tasks = new ArrayList<Task>();
		}
		if(tasks.isEmpty()) {
			for(int i=0; i<SIZE; i++) {
				String numero = format.format(i+1);
				int indiceSituacao = new Random().nextInt(SITUACOES.length);
				String situacao = SITUACOES[indiceSituacao];
				if(indiceSituacao != 2) {
					pending++;
				}
				tasks.add(
						new Task()
						.setDocumento("Doc " + numero)
						.setTipoDocumento(TIPOS_DOCUMENTO[new Random().nextInt(TIPOS_DOCUMENTO.length)])
						.setTarefa("Tarefa " + numero)
						.setSituacao(situacao)
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

	public List<Task> listAll() {
		return tasks;
	}
	
	public int countPending() {
		return pending;
	}
	
	public static class Task {
		private String documento;
		private String tipoDocumento;
		private String tarefa;
		private String situacao;
		private Date resolverAte;
		
		public Task() {
			super();
		}

		public String getDocumento() {
			return documento;
		}

		public Task setDocumento(String documento) {
			this.documento = documento;
			return this;
		}

		public String getTipoDocumento() {
			return tipoDocumento;
		}

		public Task setTipoDocumento(String tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
			return this;
		}

		public String getTarefa() {
			return tarefa;
		}

		public Task setTarefa(String tarefa) {
			this.tarefa = tarefa;
			return this;
		}

		public String getSituacao() {
			return situacao;
		}

		public Task setSituacao(String situacao) {
			this.situacao = situacao;
			return this;
		}

		public Date getResolverAte() {
			return resolverAte;
		}

		public Task setResolverAte(Date resolverAte) {
			this.resolverAte = resolverAte;
			return this;
		}
		
	}
	
}
