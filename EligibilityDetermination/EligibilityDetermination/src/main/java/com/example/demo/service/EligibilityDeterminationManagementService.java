package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bindings.EligibilityInfoOutput;
import com.example.demo.entity.CitizenAppRegistrationEntity;
import com.example.demo.entity.CorrespondenceTriggerEntity;
import com.example.demo.entity.DataCollectionCaseEntity;
import com.example.demo.entity.DataCollectionChildrenEntity;
import com.example.demo.entity.DataCollectionEducationEntity;
import com.example.demo.entity.DataCollectionIncomeEntity;
import com.example.demo.entity.EligibilityInfoEntity;
import com.example.demo.entity.PlanOptionsEntity;
import com.example.demo.repository.CitizenAppRegistrationRepository;
import com.example.demo.repository.CorrespondenceTriggerRepository;
import com.example.demo.repository.DataCollectionCaseRepository;
import com.example.demo.repository.DataCollectionChildrenRepository;
import com.example.demo.repository.DataCollectionEducationRepository;
import com.example.demo.repository.DataCollectionIncomeRepository;
import com.example.demo.repository.EligibilityDeterminationRepository;
import com.example.demo.repository.PlanOptionsRepository;

@Service
public class EligibilityDeterminationManagementService implements EligibilityDeterminationService {
	@Autowired
	private DataCollectionCaseRepository DCcaseRepo;
	@Autowired
	private PlanOptionsRepository myPlanRepo;
	@Autowired
	private DataCollectionIncomeRepository myIncomeRepo;
	@Autowired
	private DataCollectionChildrenRepository isChildRepo;
	@Autowired
	private CitizenAppRegistrationRepository isCitizenRepo;
	@Autowired
	private DataCollectionEducationRepository isEducationRepo;
	@Autowired
	private EligibilityDeterminationRepository isEligibilityRepo;
	@Autowired
	private CorrespondenceTriggerRepository isCorrespondenceRepo;

	@Override
	public EligibilityInfoOutput eligibilityDetermine(Integer caseNo) {
		Integer appId = null;
		Integer planId = null;

		// get planId and appId based on case No
		Optional<DataCollectionCaseEntity> isOptionalCaseEntity = DCcaseRepo.findById(caseNo);
		if (isOptionalCaseEntity.isPresent()) {
			DataCollectionCaseEntity isCase = isOptionalCaseEntity.get();
			planId = isCase.getPlanId();
			appId = isCase.getAppId();
		}

		// choose your plan name
		String planName = null;
		Optional<PlanOptionsEntity> isPlan = myPlanRepo.findById(planId);
		if (isPlan.isPresent()) {
			PlanOptionsEntity isPlanEntity = isPlan.get();
			planName = isPlanEntity.getPlanName();
		}

		// calculate citizen age according to their date of birth with appId
		Optional<CitizenAppRegistrationEntity> resident = isCitizenRepo.findById(appId);
		int citizenAge = 0;
		String citizenName = null;
		if (resident.isPresent()) {
			CitizenAppRegistrationEntity isCitizen = resident.get();
			LocalDate residentAge = isCitizen.getDob();
			citizenName = isCitizen.getFullName();
			LocalDate currentDate = LocalDate.now();
			citizenAge = Period.between(residentAge, currentDate).getYears();
		}

		// call helper method to apply conditions
		EligibilityInfoOutput isEligibilityInfo = applyPlanConditions(caseNo, citizenAge, planName);
		// set resident name
		isEligibilityInfo.setAccountHolderName(citizenName);

		// save Eligibility object
		EligibilityInfoEntity isEntity = new EligibilityInfoEntity();
		BeanUtils.copyProperties(isEligibilityInfo, isEntity);
		isEligibilityRepo.save(isEntity);

		// save correspondence trigger object
		CorrespondenceTriggerEntity isCorrespondence = new CorrespondenceTriggerEntity();
		isCorrespondence.setCaseNo(caseNo);
		isCorrespondence.setTriggerStatus("pending");
		isCorrespondenceRepo.save(isCorrespondence);

		return isEligibilityInfo;
	}

	private EligibilityInfoOutput applyPlanConditions(Integer caseNo, int citizenAge, String planName) {
		EligibilityInfoOutput isEligibilityOutput = new EligibilityInfoOutput();
		isEligibilityOutput.setInsurancePlanName(planName);

		// Check if income data exists before accessing it
		DataCollectionIncomeEntity isIncome = myIncomeRepo.findByCaseNo(caseNo);
		if (isIncome == null) {
			isEligibilityOutput.setInsurancePlanStatus("Denied");
			isEligibilityOutput.setRejectionReason("No income information found for case number: " + caseNo);
			return isEligibilityOutput;
		}

		double empIncome = isIncome.getEmpIncome();
		double propertyIncome = isIncome.getPropertyIncome();

		if (planName.equalsIgnoreCase("SNAP")) {
			if (empIncome <= 300) {
				isEligibilityOutput.setInsurancePlanStatus("Approved");
				isEligibilityOutput.setClaimBenefitAmount(200.0);
			} else {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("High income");
			}
		} else if (planName.equalsIgnoreCase("CCAP")) {
			boolean kidsEligibilityCondition = false;
			boolean childAgeCondition = true; // FIX: Changed from false to true

			List<DataCollectionChildrenEntity> isList = isChildRepo.findByCaseNo(caseNo);

			// FIX: Changed isEmpty() to !isEmpty()
			if (!isList.isEmpty()) {
				kidsEligibilityCondition = true;
				for (DataCollectionChildrenEntity child : isList) {
					int birthDate = Period.between(child.getChildDOB(), LocalDate.now()).getYears();
					if (birthDate > 18) {
						childAgeCondition = false;
						break;
					}
				}
			}

			if (empIncome <= 300 && kidsEligibilityCondition && childAgeCondition) {
				isEligibilityOutput.setInsurancePlanStatus("Approved");
				isEligibilityOutput.setClaimBenefitAmount(300.0);
			} else {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("No eligible children found for CCAP benefits.");
			}
		} else if (planName.equalsIgnoreCase("MEDCARE")) {
			if (citizenAge >= 65) {
				isEligibilityOutput.setInsurancePlanStatus("Approved");
				isEligibilityOutput.setClaimBenefitAmount(200.0);
			} else {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("Applicant has no qualifying dependents for Medcare");
			}
		} else if (planName.equalsIgnoreCase("MEDAID")) {
			if (empIncome <= 300 && propertyIncome == 0) {
				isEligibilityOutput.setInsurancePlanStatus("Approved");
				isEligibilityOutput.setClaimBenefitAmount(400.0);
			} else {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("Applicant has no qualifying dependents for Medicaid");
			}
		} else if (planName.equalsIgnoreCase("CAJW")) {
			// Check if education data exists
			DataCollectionEducationEntity isEducation = isEducationRepo.findByCaseNo(caseNo);
			if (isEducation == null) {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("No education information found for CAJW");
			} else {
				int yearOfGraduation = isEducation.getYearOfGraduation();
				if (empIncome == 0 && yearOfGraduation < LocalDate.now().getYear()) {
					isEligibilityOutput.setInsurancePlanStatus("Approved");
					isEligibilityOutput.setClaimBenefitAmount(400.0);
				} else {
					isEligibilityOutput.setInsurancePlanStatus("Denied");
					isEligibilityOutput.setRejectionReason("Applicant has no qualifying dependents for CAJW");
				}
			}
		} else if (planName.equalsIgnoreCase("QHP")) {
			if (citizenAge >= 1) {
				isEligibilityOutput.setInsurancePlanStatus("Approved");
			} else {
				isEligibilityOutput.setInsurancePlanStatus("Denied");
				isEligibilityOutput.setRejectionReason("Applicant has no qualifying dependents for QHP");
			}
		}

		// Check if status is not null before calling equalsIgnoreCase
		if (isEligibilityOutput.getInsurancePlanStatus() != null
				&& isEligibilityOutput.getInsurancePlanStatus().equalsIgnoreCase("Approved")) {
			isEligibilityOutput.setPolicyStartDate(LocalDateTime.now());
			isEligibilityOutput.setPolicyExpiryDate(LocalDateTime.now().plusYears(1));
		}

		return isEligibilityOutput;
	}
}