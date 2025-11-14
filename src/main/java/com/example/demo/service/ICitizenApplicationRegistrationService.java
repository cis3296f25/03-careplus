package com.example.demo.service;

import com.example.demo.bindings.CitizenAppRegistrationInputs;
import com.example.demo.entity.CitizenAppRegistrationEntity;
import com.example.demo.exceptions.SsnNotFoundException;

public interface ICitizenApplicationRegistrationService {
    
    public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs)throws SsnNotFoundException ;

	
}