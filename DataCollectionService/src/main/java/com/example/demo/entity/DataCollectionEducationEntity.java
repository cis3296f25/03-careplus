package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_EDUCATION")
public class DataCollectionEducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer educationId;
	private Long caseNo;
	@Column(length = 40)
	private String highestDegree;
	private Integer completionYear;

}
