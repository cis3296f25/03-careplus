package com.example.bindings;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class USACitizenApplication {
	private String fullName;
	private String email;
	private String gender;
	private String phoneNumber;
	private Long ssn;
	private String stateName;
	private LocalDateTime BOD;
	

}
