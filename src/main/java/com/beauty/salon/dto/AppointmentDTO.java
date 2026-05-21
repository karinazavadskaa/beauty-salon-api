package com.beauty.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String status;
    private String note;
    private Integer clientId;
    private String clientName;
    private Integer employeeId;
    private String employeeName;
    private Integer procedureId;
    private String procedureName;
    private String cost;
}