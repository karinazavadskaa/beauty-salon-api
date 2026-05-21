package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ResponseDtoTest {

    @Test
    void testClientDTO_AllFields() {
        ClientDTO dto = new ClientDTO();
        dto.setId(1);
        dto.setLastName("Иванов");
        dto.setFirstName("Иван");
        dto.setMiddleName("Иванович");
        dto.setPhone("79991112233");
        dto.setEmail("ivan@test.ru");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));
        dto.setRegistrationDate(LocalDate.now());

        assertEquals(1, dto.getId());
        assertEquals("Иванов", dto.getLastName());
        assertEquals("ivan@test.ru", dto.getEmail());
    }

    @Test
    void testEmployeeDTO_AllFields() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(1);
        dto.setLastName("Петрова");
        dto.setFirstName("Анна");
        dto.setMiddleName("Сергеевна");
        dto.setSpecialization("Косметолог");
        dto.setPhone("79992223344");
        dto.setEmail("anna@beauty.ru");

        assertEquals(1, dto.getId());
        assertEquals("Петрова", dto.getLastName());
        assertEquals("anna@beauty.ru", dto.getEmail());
    }

    @Test
    void testProcedureDTO_AllFields() {
        ProcedureDTO dto = new ProcedureDTO();
        dto.setId(1);
        dto.setName("Чистка лица");
        dto.setCost("2500");
        dto.setDuration("60");

        assertEquals(1, dto.getId());
        assertEquals("Чистка лица", dto.getName());
        assertEquals("2500", dto.getCost());
    }

    @Test
    void testScheduleDTO_AllFields() {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(1);
        dto.setDate(LocalDate.now());
        dto.setStartTime(LocalTime.of(9, 0));
        dto.setEndTime(LocalTime.of(18, 0));
        dto.setEmployeeId(1);
        dto.setEmployeeName("Иванов Иван");

        assertEquals(1, dto.getId());
        assertEquals(LocalTime.of(9, 0), dto.getStartTime());
        assertEquals("Иванов Иван", dto.getEmployeeName());
    }

    @Test
    void testAppointmentDTO_AllFields() {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(1);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(10, 0));
        dto.setStatus("Confirmed");
        dto.setNote("Тест");
        dto.setClientId(1);
        dto.setClientName("Иванов Иван");
        dto.setEmployeeId(2);
        dto.setEmployeeName("Петрова Анна");
        dto.setProcedureId(3);
        dto.setProcedureName("Маникюр");
        dto.setCost("1500");

        assertEquals(1, dto.getId());
        assertEquals("Confirmed", dto.getStatus());
        assertEquals("Иванов Иван", dto.getClientName());
        assertEquals("Маникюр", dto.getProcedureName());
    }
}