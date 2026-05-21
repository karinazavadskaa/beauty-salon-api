package com.beauty.salon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureCreateUpdateDTO {

    @NotBlank(message = "Название обязательно")
    private String name;

    @NotBlank(message = "Стоимость обязательна")
    private String cost;  // ← String

    @NotBlank(message = "Длительность обязательна")
    private String duration;
}