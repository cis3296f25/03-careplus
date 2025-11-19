package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CarePlusApplicationRegistrationApplication {
	//This is a method that creates and returns an object of type RestTemplate.
	@Bean 
	public RestTemplate createTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CarePlusApplicationRegistrationApplication.class, args);
	}

}
