package com.cwi.cotacao.model;

import java.math.BigDecimal;

public class Cotacao {

	private String codigoMoeda;
	private String tipo; 
	private String moeda;
	private BigDecimal taxaCompra;
	private BigDecimal taxaVenda;
	private BigDecimal paridadeCompra;
	private BigDecimal paridadeVenda;
	private String dataCotacao;
	
	public Cotacao() {
	}

	public Cotacao(String codigoMoeda, String tipo, String moeda, BigDecimal taxaCompra, BigDecimal taxaVenda,
			BigDecimal paridadeCompra, BigDecimal paridadeVenda, String dataCotacao) {
		this.codigoMoeda = codigoMoeda;
		this.tipo = tipo;
		this.moeda = moeda;
		this.taxaCompra = taxaCompra;
		this.taxaVenda = taxaVenda;
		this.paridadeCompra = paridadeCompra;
		this.paridadeVenda = paridadeVenda;
		this.dataCotacao = dataCotacao;
	}

	public String getCodigoMoeda() {
		return codigoMoeda;
	}

	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public BigDecimal getTaxaCompra() {
		return taxaCompra;
	}

	public void setTaxaCompra(BigDecimal taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	public BigDecimal getTaxaVenda() {
		return taxaVenda;
	}

	public void setTaxaVenda(BigDecimal taxaVenda) {
		this.taxaVenda = taxaVenda;
	}

	public BigDecimal getParidadeCompra() {
		return paridadeCompra;
	}

	public void setParidadeCompra(BigDecimal paridadeCompra) {
		this.paridadeCompra = paridadeCompra;
	}

	public BigDecimal getParidadeVenda() {
		return paridadeVenda;
	}

	public void setParidadeVenda(BigDecimal paridadeVenda) {
		this.paridadeVenda = paridadeVenda;
	}

	public String getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(String dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	@Override
	public String toString() {
		return "Cotacao [codigoMoeda=" + codigoMoeda + ", tipo=" + tipo + ", moeda=" + moeda + ", taxaCompra="
				+ taxaCompra + ", taxaVenda=" + taxaVenda + ", paridadeCompra=" + paridadeCompra + ", paridadeVenda="
				+ paridadeVenda + ", dataCotacao=" + dataCotacao + "]";
	}

}
