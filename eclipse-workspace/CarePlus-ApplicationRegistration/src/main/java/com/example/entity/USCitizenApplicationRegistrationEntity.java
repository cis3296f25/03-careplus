package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Table(name="US_CITIZEN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class USCitizenApplicationRegistrationEntity {
	#here main entity class
	@Id
	@SequenceGenerator(name="genSeq", sequenceName="App_ID", initialValue=1000, allocationSize =1)
	@GeneratedValue(generator="genSeq",strategy=GenerationType.SEQUENCE)
	private Integer Id;
	@Column(length=40)
	private String fullName;
	@Column(length=40)
	private String email;
	@Column(length=6)
	private String gender;
	private Long phone_Number;
	private Long ssn;
	@Column(length=40)
	private String State_Name;
	private LocalDateTime DOB;
	@Column(length=40)
	private String created;
	@Column(length=40)
	private String updated ;
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateDateTime;
	

}
