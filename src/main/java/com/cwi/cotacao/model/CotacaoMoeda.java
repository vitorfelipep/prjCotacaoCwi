package com.cwi.cotacao.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CotacaoMoeda {

	@NotBlank(message = "Value from is required!")
	private String from;

	@NotBlank(message = "Value to is required!")
	private String to; 

	@NotNull(message = "Value is required!")
	@DecimalMin("1.00")
	private Number value;

	@NotBlank(message = "Date quotation is required!")
	private String quotation;

	public CotacaoMoeda() {
	}

	public CotacaoMoeda(String from, String to, Number value, String quotation) {
		this.from = from;
		this.to = to;
		this.value = value;
		this.quotation = quotation;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public String getQuotation() {
		return quotation;
	}

	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}

	@Override
	public String toString() {
		return "CotacaoMoeda [from=" + from + ", to=" + to + ", value=" + value + ", quotation=" + quotation + "]";
	}

}
