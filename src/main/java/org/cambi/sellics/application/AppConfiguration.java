/**
 * 
 */
package org.cambi.sellics.application;

import org.cambi.sellics.constant.Constants;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Spring @Configuration class for instance definition of {@link ObjectMapper}
 * and {@link RestTemplate}
 * 
 * @author luca
 *
 */
@Configuration
@ComponentScan(basePackages = { "org.cambi.sellics.service" })
public class AppConfiguration extends Constants {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
