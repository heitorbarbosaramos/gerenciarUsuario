package com.heitor.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityAdpter extends WebSecurityConfigurerAdapter {

	private final String[] PUBLIC_ACCES = {"/h2-gerenciarUsuario/**"};
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(env.getActiveProfiles().toString().equals("test")) != null) {
			http.headers().frameOptions().disable();
		}
		
		http.csrf().disable()
		.authorizeRequests().antMatchers(PUBLIC_ACCES).permitAll()
		.anyRequest().authenticated();
	}
}
