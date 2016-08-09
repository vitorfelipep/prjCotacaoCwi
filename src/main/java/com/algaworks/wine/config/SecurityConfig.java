package com.algaworks.wine.config;

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
			.withUser("vitor.felipe").password("vitor").roles("CADASTRAR_VINHO","LISTAR_VINHO")
			.and()
			.withUser("maria").password("1234").roles("CADASTRAR_VINHO");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**"); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/vinhos/novo").hasRole("CADASTRAR_VINHO")
				.antMatchers("/vinhos/**").hasRole("LISTAR_VINHO")
				.anyRequest()
					.authenticated()
					.and().formLogin()
					.loginPage("/Login")
					.permitAll()
					.and().logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}
