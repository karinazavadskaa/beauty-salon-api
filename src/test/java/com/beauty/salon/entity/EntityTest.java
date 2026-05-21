package com.beauty.salon.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    void testClientEntity() {
        Client client = new Client();
        client.setId(1);
        client.setFirstName("Иван");
        client.setLastName("Иванов");
        client.setEmail("ivan@test.ru");

        assertEquals(1, client.getId());
        assertEquals("Иван", client.getFirstName());
        assertEquals("Иванов", client.getLastName());
    }

    @Test
    void testEmployeeEntity() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setFirstName("Екатерина");
        emp.setLastName("Медведева");
        emp.setSpecialization("Косметолог");

        assertEquals(1, emp.getId());
        assertEquals("Екатерина", emp.getFirstName());
        assertEquals("Косметолог", emp.getSpecialization());
    }

    @Test
    void testProcedureEntity() {
        Procedure proc = new Procedure();
        proc.setId(1);
        proc.setName("Чистка лица");
        proc.setCost("2500 ₽");
        proc.setDuration("60 минут");

        assertEquals(1, proc.getId());
        assertEquals("Чистка лица", proc.getName());
    }

    @Test
    void testAppointmentEntity() {
        Appointment app = new Appointment();
        app.setId(1);
        app.setDate(LocalDate.now());
        app.setStatus("Scheduled");
        app.setNote("Тестовая запись");

        assertEquals(1, app.getId());
        assertEquals("Scheduled", app.getStatus());
    }
}