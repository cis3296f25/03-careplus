package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration 
public class ApplicationConfig {
	@Bean(name="temp")
	public RestTemplate isTemplate() {
		return new RestTemplate() ;
		
	}

}
