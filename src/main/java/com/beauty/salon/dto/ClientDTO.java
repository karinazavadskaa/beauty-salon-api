package com.beauty.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private LocalDate registrationDate;
}