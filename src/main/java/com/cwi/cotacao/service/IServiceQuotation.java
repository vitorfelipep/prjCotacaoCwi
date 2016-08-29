package com.cwi.cotacao.service;

import java.math.BigDecimal;

public interface IServiceQuotation {
	
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation);
}
 