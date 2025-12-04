package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CORR_TRIGGER")
@Data
public class CorrespondenceTriggerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer triggerId;
	private Integer caseNo;
	@Lob
	private byte[] noticePdf;
	@Column(length = 30)
	private String triggerStatus = "pending";

}
