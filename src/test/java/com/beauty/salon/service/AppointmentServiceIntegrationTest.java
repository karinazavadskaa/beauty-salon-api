package com.beauty.salon.service;

import com.beauty.salon.dto.AppointmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AppointmentServiceIntegrationTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    void testGetAllAppointments() {
        var appointments = appointmentService.getAllAppointments();
        assertThat(appointments).isNotNull();

        if (!appointments.isEmpty()) {
            AppointmentDTO first = appointments.get(0);
            assertThat(first.getId()).isNotNull();
            assertThat(first.getClientName()).isNotNull();
            assertThat(first.getEmployeeName()).isNotNull();
            assertThat(first.getProcedureName()).isNotNull();
        }
    }
}