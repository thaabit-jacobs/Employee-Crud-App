package com.employee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long emId;
    private String emFirstName;
    private String emLastName;
    private String emEmail;

    public Employee(){}

    public Employee(String emFirstName, String emLastName, String emEmail) {
        this.emFirstName = emFirstName;
        this.emLastName = emLastName;
        this.emEmail = emEmail;
    }

    public Employee(Long emId, String emFirstName, String emLastName, String emEmail) {
        this.emId = emId;
        this.emFirstName = emFirstName;
        this.emLastName = emLastName;
        this.emEmail = emEmail;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }

    public String getEmFirstName() {
        return emFirstName;
    }

    public void setEmFirstName(String emFirstName) {
        this.emFirstName = emFirstName;
    }

    public String getEmLastName() {
        return emLastName;
    }

    public void setEmLastName(String emLastName) {
        this.emLastName = emLastName;
    }

    public String getEmEmail() {
        return emEmail;
    }

    public void setEmEmail(String emEmail) {
        this.emEmail = emEmail;
    }
}
