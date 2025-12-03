package com.example.bindings;

import lombok.Data;

@Data
public class USACitizenApplication {
    private String fullName;
    private String email;
    private String gender;
    private Long phoneNumber;
    private Long ssn;
    private String dob;     
    private String usState; 
}
