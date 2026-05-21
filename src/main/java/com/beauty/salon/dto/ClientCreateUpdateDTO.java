package com.beauty.salon.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateUpdateDTO {

    @NotBlank(message = "Фамилия обязательна")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "Имя обязательно")
    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String middleName;

    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^7\\d{10}$", message = "Телефон должен быть в формате 7XXXXXXXXXX")
    private String phone;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotNull(message = "Дата рождения обязательна")
    private LocalDate birthDate;
}