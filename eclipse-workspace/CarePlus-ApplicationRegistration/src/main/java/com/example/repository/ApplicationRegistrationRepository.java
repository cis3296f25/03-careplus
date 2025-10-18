package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.USCitizenApplicationRegistrationEntity;

public interface ApplicationRegistrationRepository extends JpaRepository<USCitizenApplicationRegistrationEntity,Integer>{
	

}
