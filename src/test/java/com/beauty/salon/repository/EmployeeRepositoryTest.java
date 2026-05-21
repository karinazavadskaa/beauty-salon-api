package com.beauty.salon.repository;

import com.beauty.salon.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindByEmail_ShouldReturnEmployee() {
        Optional<Employee> employee = employeeRepository.findByEmail("e.medvedeva@beauty.ru");
        assertThat(employee).isPresent();
        assertThat(employee.get().getFirstName()).isEqualTo("Екатерина");
        assertThat(employee.get().getLastName()).isEqualTo("Медведева");
    }

    @Test
    void testFindByEmail_WithInvalidEmail_ShouldReturnEmpty() {
        Optional<Employee> employee = employeeRepository.findByEmail("nonexistent@test.ru");
        assertThat(employee).isEmpty();
    }

    @Test
    void testFindByPhone_ShouldReturnEmployee() {
        Optional<Employee> employee = employeeRepository.findByPhone("79201112233");
        assertThat(employee).isPresent();
    }

    @Test
    void testFindAll_ShouldReturnList() {
        var employees = employeeRepository.findAll();
        assertThat(employees).isNotEmpty();
    }
}