package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bindings.CitizenAppRegistrationInputs;
import com.example.demo.service.ICitizenApplicationRegistrationService;

@RestController
@RequestMapping("/citizen") // <-- ADD THIS LINE
public class CitizenApplicationOperationalController {

    @Autowired
    private ICitizenApplicationRegistrationService registrationService;
    
    @GetMapping("/register")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller is working!");
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> saveCitizenApplication(
            @RequestBody CitizenAppRegistrationInputs inputs) throws Exception {
        int appId = registrationService.registerCitizenApplication(inputs);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("appId", appId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

	    

