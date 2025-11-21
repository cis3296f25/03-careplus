package com.example.demo.bindings;

import lombok.Data;

@Data
public class PlanSelectionOptionsInputs {
	  private Integer caseNo; // Used to load the case to update
	  private Integer planId; // Used to update the case with the plan selection
	  private Integer appId;
	  private String planName;
	  
}
