package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "DC_CHILDREN")
@Data
public class DataCollectionChildrenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "child_entry_id")                     
    private Integer childEntryId;

    @Column(name = "case_no")
    private Integer caseNo;

    @Column(name = "child_id")
    private Integer childId;

    @Column(name = "childDOB")
    private LocalDate childDOB;

    @Column(name = "childSSN")
    private Long childSSN;
}
