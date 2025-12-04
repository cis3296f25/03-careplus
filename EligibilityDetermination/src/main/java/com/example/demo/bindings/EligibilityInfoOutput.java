package com.example.demo.bindings;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EligibilityInfoOutput {

	private String accountHolderName;
	private Long accountHolderSSN;
	private String insurancePlanName;
	private String insurancePlanStatus;
	private LocalDateTime policyStartDate;
	private LocalDateTime policyExpiryDate;
	private Double claimBenefitAmount;
	private String rejectionReason;

}
