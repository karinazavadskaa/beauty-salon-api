package com.beauty.salon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateUpdateDTO {

    @NotNull(message = "Дата обязательна")
    private LocalDate date;

    @NotNull(message = "Время обязательно")
    private LocalTime time;

    private String status;

    private String note;

    @NotNull(message = "Клиент обязателен")
    private Integer clientId;

    @NotNull(message = "Сотрудник обязателен")
    private Integer employeeId;

    @NotNull(message = "Процедура обязательна")
    private Integer procedureId;
}