package com.example.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bindings.USACitizenApplication;
import com.example.service.ApplicationRegistrationService;

@RestController
@RequestMapping("/USCIS-api")
public class RegistrationApplicationOfpermanentResidentContrloler {
	@Autowired
	private ApplicationRegistrationService registrationService;
	@PostMapping("/save")
public ResponseEntity<String> saveResidentApplication(@RequestBody USACitizenApplication inputs){
	try {
		int applicanId = registrationService.USRegistrationForm(inputs);
		if(applicanId > 0) 
			return new ResponseEntity<String>("The citizen application has been successfully registered with the assigned ID."+applicanId,HttpStatus.CREATED);
		else 
			return new ResponseEntity<String>("Invalid SSN or the citizen must be a resident of Pennsylvania.",HttpStatus.CREATED);}
     catch (Exception e) {
	    	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
     }
	

	}

}
