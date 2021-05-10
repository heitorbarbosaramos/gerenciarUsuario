package com.heitor.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTest {
	
	private final Logger LOG = LoggerFactory.getLogger(Profile.class);
	
	@Bean
	public void TesteConfiguration() throws ParseException {
		
		LOG.info("PROFILE DE TESTE CARREGADO");
		
	}
}
