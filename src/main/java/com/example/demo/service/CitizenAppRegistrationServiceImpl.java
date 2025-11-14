package com.example.demo.service;


import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.bindings.CitizenAppRegistrationInputs;
import com.example.demo.entity.CitizenAppRegistrationEntity;
import com.example.demo.exceptions.SsnNotFoundException;
import com.example.demo.repository.CitizenAppRegistrationRepository;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class CitizenAppRegistrationServiceImpl implements ICitizenApplicationRegistrationService {

    private final CitizenAppRegistrationRepository citizenRepo;
    private final WebClient webClient;

    @Value("${ar.ssa-web.url}")
    private String apiEndpointUrl;

    // Multiple valid states as a comma-separated string
    @Value("${ar.valid-states}")
    private String validatedStates; // e.g. "NY,CA,TX,FL,WA"

    public CitizenAppRegistrationServiceImpl(CitizenAppRegistrationRepository citizenRepo, 
                                             WebClient.Builder webClientBuilder) {
        this.citizenRepo = citizenRepo;
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws SsnNotFoundException {

        // Call external API to get citizen info
        Mono<JsonNode> response = webClient.get()
                .uri(apiEndpointUrl, inputs.getSsn())
                .retrieve()
                .bodyToMono(JsonNode.class);

        JsonNode jsonResponse = response.block();
        if (jsonResponse == null || jsonResponse.get("state") == null) {
            throw new SsnNotFoundException("Invalid Social Security Number or API error");
        }

        String citizenState = jsonResponse.get("state").asText();

        // Convert comma-separated string to list
        java.util.List<String> validStatesList = Arrays.stream(validatedStates.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        // Check if the citizen state is valid
        if (validStatesList.stream().anyMatch(state -> state.equalsIgnoreCase(citizenState))) {
            CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
            BeanUtils.copyProperties(inputs, entity);
            entity.setStateName(citizenState);
            int appId = citizenRepo.save(entity).getAppId();
            return appId;
        }

        throw new SsnNotFoundException("Invalid Social Security Number or state not allowed");
    }

}
