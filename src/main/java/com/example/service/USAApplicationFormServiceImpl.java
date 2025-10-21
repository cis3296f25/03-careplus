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
    private String Url;

    @Value("${ar.state}")
    private String SearchForState;

    @Override
    public Integer USRegistrationForm(USACitizenApplication inputs) {

        String apiUrl = "http://localhost:9091/SsaWebOperation/find/{ssn}";
        ResponseEntity<String> isResponse = temp.exchange(apiUrl, HttpMethod.GET, null, String.class, inputs.getssn());
        String usState = isResponse.getBody();

        if (usState.equalsIgnoreCase(SearchForState)) {
            USCitizenApplicationRegistrationEntity isEntity = new USCitizenApplicationRegistrationEntity();
            BeanUtils.copyProperties(inputs, isEntity);
            isEntity.setUsState(usState);

            USCitizenApplicationRegistrationEntity savedEntity = isRepo.save(isEntity);
            return savedEntity.getId();
        }
        return 0;
    }
}
