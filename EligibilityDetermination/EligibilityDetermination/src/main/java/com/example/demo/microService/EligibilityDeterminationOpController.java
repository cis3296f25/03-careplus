package com.example.demo.microService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.EligibilityInfoOutput;
import com.example.demo.service.EligibilityDeterminationManagementService;

@RestController
@RequestMapping("/Eligibility-api")
public class EligibilityDeterminationOpController {
	@Autowired
	private EligibilityDeterminationManagementService isService;

	@GetMapping("/Determine/{caseNo}")
	public ResponseEntity<EligibilityInfoOutput> validatePlanEligibility(@PathVariable Integer caseNo) {

		EligibilityInfoOutput getOutput = isService.eligibilityDetermine(caseNo);
		return new ResponseEntity<EligibilityInfoOutput>(getOutput, HttpStatus.CREATED);
	}
}
