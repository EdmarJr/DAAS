package br.jus.stj.saad.util;

import java.io.Serializable;
import java.util.Date;

public class RangeDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7952530059127989266L;

	private Date from;
	private Date to;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

}
