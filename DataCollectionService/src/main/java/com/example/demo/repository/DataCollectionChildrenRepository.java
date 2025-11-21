package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DataCollectionChildrenEntity;

public interface DataCollectionChildrenRepository extends JpaRepository<DataCollectionChildrenEntity, Integer> {

	List<DataCollectionChildrenEntity> findByCaseNo(Integer caseNo);
    // custom queries
}
