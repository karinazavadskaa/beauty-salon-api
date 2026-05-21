package com.beauty.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String specialization;
    private String phone;
    private String email;
}