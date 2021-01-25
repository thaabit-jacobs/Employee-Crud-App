package com.employee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Employee {
    private Long emId;
    private String emFirstName;
    private String emLastName;
    private String emEmail;
}
