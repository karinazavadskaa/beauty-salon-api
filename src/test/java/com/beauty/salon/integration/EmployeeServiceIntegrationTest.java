package com.beauty.salon.integration;

import com.beauty.salon.dto.EmployeeCreateUpdateDTO;
import com.beauty.salon.dto.EmployeeDTO;
import com.beauty.salon.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void createAndFindEmployee_Integration() {
        EmployeeCreateUpdateDTO dto = new EmployeeCreateUpdateDTO();
        dto.setLastName("Интеграц");
        dto.setFirstName("Сотрудник");
        dto.setSpecialization("Тестировщик");
        dto.setPhone("79991113344");
        dto.setEmail("emp_integration@test.ru");

        EmployeeDTO created = employeeService.createEmployee(dto);
        EmployeeDTO found = employeeService.getEmployeeById(created.getId());

        assertThat(found.getEmail()).isEqualTo("emp_integration@test.ru");
    }

    @Test
    void updateEmployee_Integration() {
        EmployeeCreateUpdateDTO createDto = new EmployeeCreateUpdateDTO();
        createDto.setLastName("ОбновитьИнтегр");
        createDto.setFirstName("Тест");
        createDto.setSpecialization("Стажер");
        createDto.setPhone("79991115566");
        createDto.setEmail("emp_update_integration@test.ru");

        EmployeeDTO created = employeeService.createEmployee(createDto);

        EmployeeCreateUpdateDTO updateDto = new EmployeeCreateUpdateDTO();
        updateDto.setLastName("ОбновленИнтегр");
        updateDto.setFirstName(created.getFirstName());
        updateDto.setSpecialization("Специалист");
        updateDto.setPhone(created.getPhone());
        updateDto.setEmail(created.getEmail());

        EmployeeDTO updated = employeeService.updateEmployee(created.getId(), updateDto);

        assertThat(updated.getLastName()).isEqualTo("ОбновленИнтегр");
    }

    @Test
    void deleteEmployee_Integration() {
        EmployeeCreateUpdateDTO createDto = new EmployeeCreateUpdateDTO();
        createDto.setLastName("УдалитьИнтегр");
        createDto.setFirstName("Тест");
        createDto.setSpecialization("Тест");
        createDto.setPhone("79991114477");
        createDto.setEmail("emp_delete_integration@test.ru");

        EmployeeDTO created = employeeService.createEmployee(createDto);
        employeeService.deleteEmployee(created.getId());

        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(created.getId());
        });
    }
}