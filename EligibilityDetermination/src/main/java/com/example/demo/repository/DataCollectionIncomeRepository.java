package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DataCollectionIncomeEntity;

public interface DataCollectionIncomeRepository extends JpaRepository<DataCollectionIncomeEntity, Integer> {

	DataCollectionIncomeEntity findByCaseNo(Integer caseNo);

}
