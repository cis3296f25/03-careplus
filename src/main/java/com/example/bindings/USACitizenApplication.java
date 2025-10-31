package com.example.bindings;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class USACitizenApplication {
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNumber;
	private Long ssn;
	private LocalDateTime BOD;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public Object getssn() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
