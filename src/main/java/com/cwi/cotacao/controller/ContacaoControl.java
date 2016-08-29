package com.cwi.cotacao.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cwi.cotacao.model.Cotacao;
import com.cwi.cotacao.model.CotacaoMoeda;
import com.cwi.cotacao.service.ServicoCotacao;
import com.cwi.cotacao.storage.DataCotacaoMoeda;
import com.cwi.cotacao.util.UtilWorkDates;


@Controller
@RequestMapping("/cotacoes")
public class ContacaoControl { 
 
	@Autowired
	private DataCotacaoMoeda cotacaoMoeda;
	
	@Autowired
	private ServicoCotacao cotacaoService;

	private List<Cotacao> cotacoes;

	@RequestMapping("/cotarMoedas")
	public ModelAndView novo(CotacaoMoeda cotacaoMoeda) {
		ModelAndView mv = new ModelAndView("/cotacao/CurrencyQuotation");
		mv.addObject("cotacoesList", getListOfCotacaoCoin());
		return mv;
	}
	
	@RequestMapping(value = "/cotarMoedas", method = RequestMethod.POST)
	public ModelAndView cotarMoeda(@Valid CotacaoMoeda cotacaoMoeda, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(cotacaoMoeda);
		}
		
		BigDecimal resultado = this.cotacaoService.currencyQuotation(cotacaoMoeda.getFrom(), cotacaoMoeda.getTo(), cotacaoMoeda.getValue(), cotacaoMoeda.getQuotation());
		
		System.out.println("O valor cotado é: " + resultado);
		
		attributes.addFlashAttribute("mensagem", "Cotacao efetuada com sucesso!");
		attributes.addFlashAttribute("initMessage", "O valor cotado em " + cotacaoMoeda.getFrom() + " é de ");
		attributes.addFlashAttribute("resultado", resultado + " " + cotacaoMoeda.getTo());
		return new ModelAndView("redirect:/cotacoes/cotarMoedas");
	}

	public List<Cotacao> getListOfCotacaoCoin() {
		try {
			
			String data = new UtilWorkDates().dateToDayToString();
			cotacoes = cotacaoMoeda.getGetData().getCsvData(data);

			return cotacoes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
