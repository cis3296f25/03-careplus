package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DataCollectionCaseEntity;

public interface DataCollectionCaseRepository extends JpaRepository<DataCollectionCaseEntity,Integer>{

public Optional<DataCollectionCaseEntity> findByCaseNo(Integer caseNo);


}
