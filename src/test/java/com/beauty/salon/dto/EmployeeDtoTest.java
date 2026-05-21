package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDtoTest {

    @Test
    void testEmployeeCreateUpdateDTO() {
        EmployeeCreateUpdateDTO dto = new EmployeeCreateUpdateDTO();
        dto.setLastName("Смирнова");
        dto.setFirstName("Дарья");
        dto.setSpecialization("Массажист");
        dto.setPhone("79202223344");
        dto.setEmail("d.smirnova@beauty.ru");

        assertEquals("Смирнова", dto.getLastName());
        assertEquals("Дарья", dto.getFirstName());
        assertEquals("Массажист", dto.getSpecialization());
    }

    @Test
    void testEmployeeDTO() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(1);
        dto.setLastName("Медведева");
        dto.setFirstName("Екатерина");
        dto.setSpecialization("Косметолог");

        assertEquals(1, dto.getId());
        assertEquals("Медведева", dto.getLastName());
    }
}