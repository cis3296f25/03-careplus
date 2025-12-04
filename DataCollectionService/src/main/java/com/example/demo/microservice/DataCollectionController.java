package com.example.demo.microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.DCFeedBackReportInputs;
import com.example.demo.bindings.DataCollectionChildrenInputs;
import com.example.demo.bindings.DataCollectionEducationInputs;
import com.example.demo.bindings.DataCollectionIncomeInputs;
import com.example.demo.bindings.PlanSelectionOptionsInputs;
import com.example.demo.service.DataCollectionInterfaceService;
import org.springframework.web.bind.annotation.CrossOrigin;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/data")
@Tag(name="DC-API", description="Data Collection Module")
public class DataCollectionController {

	@Autowired
private DataCollectionInterfaceService dcmanageService;
	@GetMapping("/planNames")
	public ResponseEntity<List <String>> displayPlanNames(){
		
		//use service
		List <String> listPlanNames=dcmanageService.showAllPlanNames();
		return new ResponseEntity<List<String>>(listPlanNames, HttpStatus.OK);
		
	}
	@PostMapping("/caseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId){
		//use service
		Integer caseNo = dcmanageService.generateCaseNo(appId);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Integer> savePlanSelection(@RequestBody PlanSelectionOptionsInputs inputs){
		//use service
		Integer caseNo = dcmanageService.savePlanSelection(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}
	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveIncomeDetails(@RequestBody DataCollectionIncomeInputs Income){
		// use service
		Integer caseNo = dcmanageService.saveIncomeDetails(Income);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	@PostMapping("/saveEducation")
	public ResponseEntity<Integer> saveIncomeEducation(@RequestBody DataCollectionEducationInputs Education){
		// use service
		Integer caseNo = dcmanageService.saveEducationDetails(Education);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
		
	}
	@PostMapping("/saveChildren")
	public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<DataCollectionChildrenInputs> children){
		//use service 
		Integer caseNo = dcmanageService.saveChildrenDetails(children);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	@GetMapping("/report-Details/{caseNo}")
	public ResponseEntity<DCFeedBackReportInputs> showSummaryReport(@PathVariable Integer caseNo){

		//use service
        DCFeedBackReportInputs report = dcmanageService.showDcSummary(caseNo);
        return new ResponseEntity<DCFeedBackReportInputs>(report,HttpStatus.OK);
        
		
	}
}
