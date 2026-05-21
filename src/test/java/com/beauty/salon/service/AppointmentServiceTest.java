package com.beauty.salon.service;

import com.beauty.salon.dto.AppointmentCreateUpdateDTO;
import com.beauty.salon.dto.AppointmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    void testGetAllAppointments_ShouldReturnList() {
        var appointments = appointmentService.getAllAppointments();
        assertThat(appointments).isNotNull();
    }

    @Test
    void testGetAppointmentById_WithValidId_ShouldReturnAppointment() {
        var appointments = appointmentService.getAllAppointments();
        if (!appointments.isEmpty()) {
            Integer id = appointments.get(0).getId();
            AppointmentDTO appointment = appointmentService.getAppointmentById(id);
            assertThat(appointment).isNotNull();
            assertThat(appointment.getId()).isEqualTo(id);
        }
    }

    @Test
    void testGetAppointmentById_WithInvalidId_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            appointmentService.getAppointmentById(99999);
        });
    }

    @Test
    void testCreateAppointment_ShouldCreate() {
        AppointmentCreateUpdateDTO dto = new AppointmentCreateUpdateDTO();
        dto.setDate(LocalDate.now().plusDays(7));
        dto.setTime(LocalTime.of(10, 0));
        dto.setStatus("Scheduled");
        dto.setNote("Test appointment");
        dto.setClientId(1);
        dto.setEmployeeId(1);
        dto.setProcedureId(1);

        AppointmentDTO result = appointmentService.createAppointment(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo("Scheduled");
    }

    @Test
    void testUpdateAppointment_ShouldUpdate() {
        // Create first
        AppointmentCreateUpdateDTO createDto = new AppointmentCreateUpdateDTO();
        createDto.setDate(LocalDate.now().plusDays(7));
        createDto.setTime(LocalTime.of(11, 0));
        createDto.setStatus("Scheduled");
        createDto.setClientId(1);
        createDto.setEmployeeId(1);
        createDto.setProcedureId(1);

        AppointmentDTO created = appointmentService.createAppointment(createDto);

        // Update
        AppointmentCreateUpdateDTO updateDto = new AppointmentCreateUpdateDTO();
        updateDto.setDate(created.getDate());
        updateDto.setTime(created.getTime());
        updateDto.setStatus("Confirmed");
        updateDto.setClientId(created.getClientId());
        updateDto.setEmployeeId(created.getEmployeeId());
        updateDto.setProcedureId(created.getProcedureId());

        AppointmentDTO updated = appointmentService.updateAppointment(created.getId(), updateDto);

        assertThat(updated.getStatus()).isEqualTo("Confirmed");
    }

    @Test
    void testDeleteAppointment_ShouldDelete() {
        // Create first
        AppointmentCreateUpdateDTO createDto = new AppointmentCreateUpdateDTO();
        createDto.setDate(LocalDate.now().plusDays(7));
        createDto.setTime(LocalTime.of(12, 0));
        createDto.setStatus("Scheduled");
        createDto.setClientId(1);
        createDto.setEmployeeId(1);
        createDto.setProcedureId(1);

        AppointmentDTO created = appointmentService.createAppointment(createDto);

        // Delete
        appointmentService.deleteAppointment(created.getId());

        // Verify deleted
        assertThrows(RuntimeException.class, () -> {
            appointmentService.getAppointmentById(created.getId());
        });
    }

    @Test
    void testGetAppointmentsByEmployeeId_ShouldReturnList() {
        var appointments = appointmentService.getAppointmentsByEmployeeId(1);
        assertThat(appointments).isNotNull();
    }
}