package com.example.demo.bindings;

import java.time.LocalDate;


import lombok.Data;
@Data
public class DataCollectionChildrenInputs {
	
	private Integer caseNo;
    private Integer childId;
    private LocalDate childDOB;
    private Long childSSN;
   
    
}
