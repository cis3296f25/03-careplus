package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CitizenAppRegistrationEntity;

public interface CitizenAppRegistrationRepository extends JpaRepository<CitizenAppRegistrationEntity, Integer> {
    // Custom repository methods can be defined here if needed
}
