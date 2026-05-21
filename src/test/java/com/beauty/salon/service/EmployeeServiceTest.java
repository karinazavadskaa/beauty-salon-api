package com.beauty.salon.service;

import com.beauty.salon.dto.EmployeeCreateUpdateDTO;
import com.beauty.salon.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees_ShouldReturnList() {
        var employees = employeeService.getAllEmployees();
        assertThat(employees).isNotNull();
    }

    @Test
    void testGetEmployeeById_WithValidId_ShouldReturnEmployee() {
        var employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            Integer id = employees.get(0).getId();
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            assertThat(employee).isNotNull();
            assertThat(employee.getId()).isEqualTo(id);
        }
    }

    @Test
    void testGetEmployeeById_WithInvalidId_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(99999);
        });
    }

    @Test
    void testCreateEmployee_ShouldCreate() {
        EmployeeCreateUpdateDTO dto = new EmployeeCreateUpdateDTO();
        dto.setLastName("Новый");
        dto.setFirstName("Сотрудник");
        dto.setSpecialization("Тестировщик");
        dto.setPhone("79991113344");
        dto.setEmail("new_employee@test.ru");

        EmployeeDTO result = employeeService.createEmployee(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getEmail()).isEqualTo("new_employee@test.ru");
    }

    @Test
    void testUpdateEmployee_ShouldUpdate() {
        // Create first
        EmployeeCreateUpdateDTO createDto = new EmployeeCreateUpdateDTO();
        createDto.setLastName("ДляОбновления");
        createDto.setFirstName("Тест");
        createDto.setSpecialization("Стажер");
        createDto.setPhone("79991115566");
        createDto.setEmail("emp_update@test.ru");

        EmployeeDTO created = employeeService.createEmployee(createDto);

        // Update
        EmployeeCreateUpdateDTO updateDto = new EmployeeCreateUpdateDTO();
        updateDto.setLastName("Обновлен");
        updateDto.setFirstName(created.getFirstName());
        updateDto.setSpecialization("Специалист");
        updateDto.setPhone(created.getPhone());
        updateDto.setEmail(created.getEmail());

        EmployeeDTO updated = employeeService.updateEmployee(created.getId(), updateDto);

        assertThat(updated.getLastName()).isEqualTo("Обновлен");
        assertThat(updated.getSpecialization()).isEqualTo("Специалист");
    }

    @Test
    void testDeleteEmployee_ShouldDelete() {
        // Create first
        EmployeeCreateUpdateDTO createDto = new EmployeeCreateUpdateDTO();
        createDto.setLastName("ДляУдаления");
        createDto.setFirstName("Тест");
        createDto.setSpecialization("Тест");
        createDto.setPhone("79991114477");
        createDto.setEmail("emp_delete@test.ru");

        EmployeeDTO created = employeeService.createEmployee(createDto);

        // Delete
        employeeService.deleteEmployee(created.getId());

        // Verify
        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(created.getId());
        });
    }
    @Test
    void testGetAllEmployees_ReturnsData() {
        var employees = employeeService.getAllEmployees();
        assertThat(employees).isNotNull();
        System.out.println("Total employees: " + employees.size());
    }
}