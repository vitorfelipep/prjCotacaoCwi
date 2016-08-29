package com.cwi.cotacao.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("COTAR_MOEDA","LISTAR_MOEDA")
			.and()
			.withUser("juvenal").password("1234").roles("COTAR_MOEDA"); 
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**"); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/cotacoes/cotarMoedas").hasRole("COTAR_MOEDA")
				.antMatchers("/vinhos/**").hasRole("LISTAR_MOEDA")
				.anyRequest()
					.authenticated()
					.and().formLogin()
					.loginPage("/Login")
					.permitAll()
					.and().logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}
