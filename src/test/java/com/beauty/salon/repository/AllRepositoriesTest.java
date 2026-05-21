package com.beauty.salon.repository;

import com.beauty.salon.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AllRepositoriesTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void clientRepository_ShouldFindByEmail() {
        var client = clientRepository.findByEmail("anna.iv@email.ru");
        assertThat(client).isPresent();
        assertThat(client.get().getFirstName()).isEqualTo("Анна");
    }

    @Test
    void clientRepository_ShouldFindByPhone() {
        var client = clientRepository.findByPhone("79101234567");
        assertThat(client).isPresent();
    }

    @Test
    void employeeRepository_ShouldFindByEmail() {
        var employee = employeeRepository.findByEmail("e.medvedeva@beauty.ru");
        assertThat(employee).isPresent();
        assertThat(employee.get().getFirstName()).isEqualTo("Екатерина");
    }

    @Test
    void employeeRepository_ShouldFindByPhone() {
        var employee = employeeRepository.findByPhone("79201112233");
        assertThat(employee).isPresent();
    }

    @Test
    void procedureRepository_ShouldFindAll() {
        var procedures = procedureRepository.findAll();
        assertThat(procedures).isNotEmpty();
    }

    @Test
    void appointmentRepository_ShouldFindByEmployeeId() {
        var appointments = appointmentRepository.findByEmployeeId(1);
        assertThat(appointments).isNotNull();
    }

    @Test
    void appointmentRepository_ShouldFindByStatus() {
        var appointments = appointmentRepository.findByStatus("Scheduled");
        assertThat(appointments).isNotNull();
    }

    @Test
    void scheduleRepository_ShouldFindByEmployeeId() {
        var schedules = scheduleRepository.findByEmployeeId(1);
        assertThat(schedules).isNotNull();
    }

    @Test
    void roleRepository_ShouldFindByName() {
        var role = roleRepository.findByName("Cosmetologist");
        assertThat(role).isPresent();
        assertThat(role.get().getName()).isEqualTo("Cosmetologist");
    }
}