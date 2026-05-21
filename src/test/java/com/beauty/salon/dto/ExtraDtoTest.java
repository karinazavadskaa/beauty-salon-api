package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ExtraDtoTest {

    @Test
    void testClientCreateUpdateDTO_AllFields() {
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Тестов");
        dto.setFirstName("Тест");
        dto.setPhone("79991112233");
        dto.setEmail("test@test.com");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        assertEquals("Тестов", dto.getLastName());
        assertEquals("Тест", dto.getFirstName());
        assertEquals("test@test.com", dto.getEmail());
    }

    @Test
    void testAppointmentCreateUpdateDTO_AllFields() {
        AppointmentCreateUpdateDTO dto = new AppointmentCreateUpdateDTO();
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(14, 0));
        dto.setStatus("Confirmed");
        dto.setClientId(1);
        dto.setEmployeeId(2);
        dto.setProcedureId(3);

        assertEquals("Confirmed", dto.getStatus());
        assertEquals(1, dto.getClientId());
        assertEquals(2, dto.getEmployeeId());
        assertEquals(3, dto.getProcedureId());
    }

    @Test
    void testClientDTO_AllFields() {
        ClientDTO dto = new ClientDTO();
        dto.setId(10);
        dto.setLastName("Клиентов");
        dto.setFirstName("Клиент");
        dto.setEmail("client@test.com");

        assertEquals(10, dto.getId());
        assertEquals("Клиентов", dto.getLastName());
        assertEquals("client@test.com", dto.getEmail());
    }

    @Test
    void testEmployeeDTO_AllFields() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(5);
        dto.setLastName("Сотрудников");
        dto.setFirstName("Сотрудник");
        dto.setSpecialization("Косметолог");

        assertEquals(5, dto.getId());
        assertEquals("Сотрудников", dto.getLastName());
        assertEquals("Косметолог", dto.getSpecialization());
    }

    @Test
    void testProcedureDTO_AllFields() {
        ProcedureDTO dto = new ProcedureDTO();
        dto.setId(3);
        dto.setName("Маникюр");
        dto.setCost("1500");
        dto.setDuration("60");

        assertEquals(3, dto.getId());
        assertEquals("Маникюр", dto.getName());
    }
}