package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bindings.USACitizenApplication;
import com.example.entity.USCitizenApplicationRegistrationEntity;
import com.example.repository.ApplicationRegistrationRepository;

@Service
public class USAApplicationFormServiceImpl implements ApplicationRegistrationService {

    @Autowired
    private ApplicationRegistrationRepository isRepo;

    @Autowired
    private RestTemplate temp;

    @Value("${ar.apiUrl.url}")
    private String apiURL;

    @Value("${state}")
    private String requiredState;

@Override
public Integer USRegistrationForm(USACitizenApplication inputs) {

    // TEMP FIX: skip calling SSA service
    // String usState = temp.exchange(apiURL, HttpMethod.GET, null, String.class, inputs.getSsn()).getBody();

    // Accept whatever state frontend sends for now
    String usState = inputs.getUsState();

    // Check if user belongs to required state
    if (usState != null && usState.equalsIgnoreCase(requiredState)) {

        USCitizenApplicationRegistrationEntity entity = new USCitizenApplicationRegistrationEntity();

        // Copy properties
        BeanUtils.copyProperties(inputs, entity);

        // Set state
        entity.setUsState(usState);

        // Convert dob String → LocalDateTime
        try {
            entity.setDob(java.time.LocalDateTime.parse(inputs.getDob() + "T00:00:00"));
        } catch (Exception e) {
            System.out.println("DOB conversion failed: " + e.getMessage());
        }

        // Save to DB
        USCitizenApplicationRegistrationEntity saved = isRepo.save(entity);
        return saved.getId();
    }

    return 0;
}

}
