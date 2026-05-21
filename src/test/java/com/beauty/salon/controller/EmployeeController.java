package com.beauty.salon.controller;

import com.beauty.salon.dto.EmployeeCreateUpdateDTO;
import com.beauty.salon.dto.EmployeeDTO;
import com.beauty.salon.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private final EmployeeDTO employeeDTO = new EmployeeDTO(1, "Петрова", "Елена", "Сергеевна", "Косметолог", "79161234567", "elena@beauty.com");
    private final EmployeeCreateUpdateDTO createDTO = new EmployeeCreateUpdateDTO("Смирнова", "Ольга", "Алексеевна", "Парикмахер", "79161112233", "olga@beauty.com");

    @Test
    void getAll_ShouldReturnList() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDTO));
        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void getById_ShouldReturnEmployee() {
        when(employeeService.getEmployeeById(1)).thenReturn(employeeDTO);
        ResponseEntity<EmployeeDTO> response = employeeController.getById(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Елена");
    }

    @Test
    void create_ShouldReturnCreated() {
        when(employeeService.createEmployee(any())).thenReturn(employeeDTO);
        ResponseEntity<EmployeeDTO> response = employeeController.create(createDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void update_ShouldReturnOk() {
        when(employeeService.updateEmployee(eq(1), any())).thenReturn(employeeDTO);
        ResponseEntity<EmployeeDTO> response = employeeController.update(1, createDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(employeeService).deleteEmployee(1);
        ResponseEntity<Void> response = employeeController.delete(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}