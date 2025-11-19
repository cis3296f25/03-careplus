package com.example.demo.bindings;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DataCollectionEducationInputs {
	
    private Integer caseNo;
    private Integer educationId;
    @Column(length = 40)
    private String qualification;
    private Integer yearOfGraduation;
}
