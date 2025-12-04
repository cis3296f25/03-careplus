package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="Eligility_Determinations")
@Entity
@Data
public class EligibilityInfoEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer eligibilityTraceId;
	private Integer caseNo;
	@Column(length =30)
	private String accountHolderName;
	private Long accountHolderSSN;
	@Column(length =30)
	private String insurancePlanName;
	private String insurancePlanStatus;
	private LocalDateTime policyStartDate;
	private LocalDateTime policyExpiryDate;
	private Double claimBenefitAmount;
	@Column(length =30)
	private String rejectionReason;
	
	
	
}
