package com.beauty.salon.service;

import com.beauty.salon.dto.EmployeeCreateUpdateDTO;
import com.beauty.salon.dto.EmployeeDTO;
import com.beauty.salon.entity.Employee;
import com.beauty.salon.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return convertToDTO(employee);
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeCreateUpdateDTO dto) {
        if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Employee with email " + dto.getEmail() + " already exists");
        }
        if (employeeRepository.findByPhone(dto.getPhone()).isPresent()) {
            throw new RuntimeException("Employee with phone " + dto.getPhone() + " already exists");
        }

        Employee employee = new Employee();
        employee.setLastName(dto.getLastName());
        employee.setFirstName(dto.getFirstName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setSpecialization(dto.getSpecialization());
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());

        Employee saved = employeeRepository.save(employee);
        return convertToDTO(saved);
    }

    @Transactional
    public EmployeeDTO updateEmployee(Integer id, EmployeeCreateUpdateDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        if (!employee.getEmail().equals(dto.getEmail()) &&
                employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Employee with email " + dto.getEmail() + " already exists");
        }

        employee.setLastName(dto.getLastName());
        employee.setFirstName(dto.getFirstName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setSpecialization(dto.getSpecialization());
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());

        return convertToDTO(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getSpecialization(),
                employee.getPhone(),
                employee.getEmail()
        );
    }
}