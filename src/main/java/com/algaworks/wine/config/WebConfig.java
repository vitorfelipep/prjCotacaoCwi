package com.algaworks.wine.config;

/**
 * @author vitor
 */


import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		//Utiliza expressão lambda do java 8 para encurtar o método customize da interface EmbeddedServletContainerCustomizer
		return (container ->
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404")));
	}
	
	/**
	 * Serve para converter o objeto passado via parametro no método de visualizar o vinho,
	 * Com isso ele automaticamente pega o código passado via url, chama o método do jpa findOne(Long codigo) 
	 * e converte os dados em um objeto. isso diminui o código, deixando mais elegante e muda o estado do objeto passado.
	 * 
	 * @param conversionService
	 * @return DomainClassConverter<FormattingConversionService>
	 */
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
}
