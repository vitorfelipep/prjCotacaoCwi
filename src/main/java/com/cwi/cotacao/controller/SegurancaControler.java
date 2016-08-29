package com.cwi.cotacao.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SegurancaControler {
	
	@RequestMapping("/Login")
	public String login(@AuthenticationPrincipal User user) {
		
		if(user != null) { 
			return "redirect:/cotacoes/cotarMoedas";
		}
		return "Login"; 
	}
}
