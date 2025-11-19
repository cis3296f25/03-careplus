package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DataCollectionEducationEntity;

@Repository
public interface DataCollectionEducationRepository 
        extends JpaRepository<DataCollectionEducationEntity, Integer> {

    Optional<DataCollectionEducationEntity> findByCaseNo(Integer caseNo);
}

