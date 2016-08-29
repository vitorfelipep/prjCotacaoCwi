package com.cwi.cotacao.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cwi.cotacao.model.Cotacao;
import com.cwi.cotacao.model.CotacaoMoeda;
import com.cwi.cotacao.storage.DataCotacaoMoeda;
import com.cwi.cotacao.util.UtilWorkDates;

@Service
public class ServicoCotacao implements IServiceQuotation {
	
	@Autowired 
	private DataCotacaoMoeda cotacaoMoeda;
	
	@Autowired
	@Qualifier("workDate")
	private UtilWorkDates workDate;
	
	private List<Cotacao> listaCotacao;
	private CotacaoMoeda resultado;

	@Override
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation) {
		
		Cotacao currency = new Cotacao();
		
		try {
			
			String data = workDate.stringFormatForString(quotation);
			listaCotacao = cotacaoMoeda.getGetData().getCsvData(data);
			
			if(listaCotacao != null) {
				for(Cotacao c : listaCotacao) {
					if(to.equals(c.getMoeda())) {
						currency = c;
					}
				}
				if(currency.getMoeda() != null) {
					this.resultado = new CotacaoMoeda(from, to, value.doubleValue() * currency.getTaxaCompra().doubleValue(), quotation);
				}
			}
			
			BigDecimal result = BigDecimal.valueOf(resultado.getValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Cotacao> getListaCotacao() {
		return listaCotacao;
	}

	public void setListaCotacao(List<Cotacao> listaCotacao) {
		this.listaCotacao = listaCotacao;
	}

}
