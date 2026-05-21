package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class AllDtoTest {

    @Test
    void testClientCreateUpdateDTO_AllFields() {
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Тестов");
        dto.setFirstName("Тест");
        dto.setMiddleName("Тестович");
        dto.setPhone("79991112233");
        dto.setEmail("test@test.com");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        assertEquals("Тестов", dto.getLastName());
        assertEquals("Тест", dto.getFirstName());
        assertEquals("Тестович", dto.getMiddleName());
        assertEquals("79991112233", dto.getPhone());
        assertEquals("test@test.com", dto.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getBirthDate());
    }

    @Test
    void testEmployeeCreateUpdateDTO_AllFields() {
        EmployeeCreateUpdateDTO dto = new EmployeeCreateUpdateDTO();
        dto.setLastName("Смирнов");
        dto.setFirstName("Алексей");
        dto.setMiddleName("Иванович");
        dto.setSpecialization("Косметолог");
        dto.setPhone("79992223344");
        dto.setEmail("alex@beauty.ru");

        assertEquals("Смирнов", dto.getLastName());
        assertEquals("Алексей", dto.getFirstName());
        assertEquals("Косметолог", dto.getSpecialization());
        assertEquals("alex@beauty.ru", dto.getEmail());
    }

    @Test
    void testProcedureCreateUpdateDTO_AllFields() {
        ProcedureCreateUpdateDTO dto = new ProcedureCreateUpdateDTO();
        dto.setName("Маникюр");
        dto.setCost("1500");
        dto.setDuration("60");

        assertEquals("Маникюр", dto.getName());
        assertEquals("1500", dto.getCost());
        assertEquals("60", dto.getDuration());
    }

    @Test
    void testScheduleCreateUpdateDTO_AllFields() {
        ScheduleCreateUpdateDTO dto = new ScheduleCreateUpdateDTO();
        dto.setDate(LocalDate.now());
        dto.setStartTime(LocalTime.of(9, 0));
        dto.setEndTime(LocalTime.of(18, 0));
        dto.setBreakStart(LocalTime.of(13, 0));
        dto.setBreakEnd(LocalTime.of(14, 0));
        dto.setEmployeeId(1);

        assertEquals(LocalTime.of(9, 0), dto.getStartTime());
        assertEquals(LocalTime.of(18, 0), dto.getEndTime());
        assertEquals(1, dto.getEmployeeId());
    }

    @Test
    void testAppointmentCreateUpdateDTO_AllFields() {
        AppointmentCreateUpdateDTO dto = new AppointmentCreateUpdateDTO();
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(10, 0));
        dto.setStatus("Scheduled");
        dto.setNote("Тест");
        dto.setClientId(1);
        dto.setEmployeeId(2);
        dto.setProcedureId(3);

        assertEquals(LocalTime.of(10, 0), dto.getTime());
        assertEquals("Scheduled", dto.getStatus());
        assertEquals(1, dto.getClientId());
        assertEquals(2, dto.getEmployeeId());
        assertEquals(3, dto.getProcedureId());
    }
}