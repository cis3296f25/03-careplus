package com.example.demo.bindings;

import java.time.LocalDate;
import lombok.Data;


@Data
public class CitizenAppRegistrationInputs {

    private String fullName;
    private String email;
    private String gender;
    private Long mobileNum;
    private Long ssn;
    private LocalDate dob;
    private String state; // Note: 'state' in DTO vs. 'stateName' in Entity
	
}
