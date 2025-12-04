package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "us_citizen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppRegistrationEntity {

    @Id
    @Column(name = "id")
    private Integer appId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private Long mobileNum;

    @Column(name = "ssn")
    private Long ssn;

    @Column(name = "us_state")
    private String stateName;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "created")
    private String createdBy;

    @Column(name = "updated")
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "create_date_time", updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "update_date_time", insertable = false)
    private LocalDateTime updationDate;
}
