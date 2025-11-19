package com.example.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "US_CITIZEN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class USCitizenApplicationRegistrationEntity {

    @Id
    @SequenceGenerator(name = "genSeq", sequenceName = "App_ID", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "genSeq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 40)
    private String fullName;

    @Column(length = 40)
    private String email;

    @Column(length = 6)
    private String gender;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "ssn")
    private Long ssn;

    @Column(length = 40)
    private String usState; // now works with setUsState(String)

    private LocalDateTime dob;

    @Column(length = 40)
    private String created;

    @Column(length = 40)
    private String updated;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateDateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getUsState() {
		return usState;
	}

	public void setUsState(String usState) {
		this.usState = usState;
	}
    

    // **Remove all manually written getters/setters** — Lombok @Data handles them
}
