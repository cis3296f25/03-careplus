package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.USCitizenApplicationRegistrationEntity;

@Repository
public interface ApplicationRegistrationRepository extends JpaRepository<USCitizenApplicationRegistrationEntity, Integer> {
}
