package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DataCollectionEducationEntity;

@Repository
public interface DataCollectionEducationRepository extends JpaRepository<DataCollectionEducationEntity, Integer> {

	DataCollectionEducationEntity findByCaseNo(Integer caseNo);
}
