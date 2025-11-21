package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DataCollectionIncomeEntity;

public interface DataCollectionIncomeRepository extends JpaRepository<DataCollectionIncomeEntity, Integer>{

	 Optional<DataCollectionIncomeEntity> findByCaseNo(Integer caseNo);

}
