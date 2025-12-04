package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_CHILDREN")
@Data
public class DataCollectionChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 @Column
	 private Integer caseNo; 
	 private Integer childId; 
	 private LocalDate childDOB;
	 private Long   childSSN;
 
}
