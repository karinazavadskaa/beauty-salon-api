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
public class ScheduleCreateUpdateDTO {

    @NotNull(message = "Дата обязательна")
    private LocalDate date;

    @NotNull(message = "Время начала обязательно")
    private LocalTime startTime;

    @NotNull(message = "Время окончания обязательно")
    private LocalTime endTime;

    private LocalTime breakStart;
    private LocalTime breakEnd;

    @NotNull(message = "Сотрудник обязателен")
    private Integer employeeId;
}