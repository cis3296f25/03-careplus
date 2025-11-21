package com.example.demo.bindings;

import java.util.List;

import lombok.Data;
@Data
public class DCFeedBackReportInputs {
	    private String planName;
	    private DataCollectionIncomeInputs incomeDetails;
	    private DataCollectionEducationInputs educationDetails;
	    private CitizenAppRegistrationInputs citizenDetails;
	    private List<DataCollectionChildrenInputs> childrenDetails;
	    //private PlanSelectionOptionsInputs planDetails; 

}
