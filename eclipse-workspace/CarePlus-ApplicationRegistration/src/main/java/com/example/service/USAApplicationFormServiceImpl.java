package com.example.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bindings.USACitizenApplication;
import com.example.entity.USCitizenApplicationRegistrationEntity;
import com.example.repository.ApplicationRegistrationRepository;
@Service
public class USAApplicationFormServiceImpl implements ApplicationRegistrationService{
	@Autowired
	private ApplicationRegistrationRepository isRepo;
    @Autowired
    private RestTemplate temp;
    @Value("${ar.mywebUrl}")
    private String Url;
    @Value("${ar.state}")
    private String findState;
    
	@Override
	public Integer USRegistrationForm(USACitizenApplication inputs) {
		
		
		//Perform an API call to verify the validity of the SSN and retrieve the corresponding state name.
		
		ResponseEntity<String> response = temp.exchange(Url, HttpMethod.GET,null,String.class, inputs.getClass());
		String USState = response.getBody();
		//
		if(USState.equalsIgnoreCase(findState)) {
			
			USCitizenApplicationRegistrationEntity entity = new USCitizenApplicationRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);
			entity.getClass();
			int applicationId = isRepo.save(entity).getapplicationId();
			return applicationId;
		}
		return 0;
	}
	

}
