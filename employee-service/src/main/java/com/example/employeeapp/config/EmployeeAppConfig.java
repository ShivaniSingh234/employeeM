package com.example.employeeapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.client.RestTemplate;
import org.modelmapper.ModelMapper;

@Configuration
public class EmployeeAppConfig {

	@Value("${addressservice.base.url}")
	private String addressBaseURL;
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
//	@Bean
//	public RestTemplate resttemplate()
//	{
//		return new RestTemplate();
//	}
	
	@Bean
	public WebClient webClient()
	{
		return WebClient
					.builder()
					.baseUrl(addressBaseURL)
					.build();
	}
	
}
