package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EligibilityInfoEntity;

public interface EligibilityDeterminationRepository extends JpaRepository<EligibilityInfoEntity,Integer>{

}
