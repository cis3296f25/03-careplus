package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_PlanSelection")
@Data
public class PlanOptionsEntity {


    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer planId;
    @Column(length =30)
    private String planName;
    private LocalDate startDate;
    
    private LocalDate endDate;
    @Column(length = 50)
    private String description;
    private String activeSwitch;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;
    @CreationTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedDate;
    @Column(length = 30)
    private String createdBy;
    @Column(length = 30)
    private String updateedby;
    
    
    
}
