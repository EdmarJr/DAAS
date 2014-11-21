package br.jus.stj.saad.util;

import java.io.Serializable;
import java.util.Date;

public class YieldDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4207450240025532828L;

	private Date yield;
	private Date begin;
	private Date end;
	private RangeDate range;

	public Date getYield() {
		return yield;
	}

	public void setYield(Date yield) {
		this.yield = yield;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public RangeDate getRange() {
		return range;
	}

	public void setRange(RangeDate range) {
		this.range = range;
	}

}
