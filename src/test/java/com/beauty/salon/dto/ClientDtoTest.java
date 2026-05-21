package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ClientDtoTest {

    @Test
    void testClientCreateUpdateDTO() {
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Иванов");
        dto.setFirstName("Иван");
        dto.setPhone("79991112233");
        dto.setEmail("ivan@test.ru");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        assertEquals("Иванов", dto.getLastName());
        assertEquals("Иван", dto.getFirstName());
        assertEquals("79991112233", dto.getPhone());
        assertEquals("ivan@test.ru", dto.getEmail());
    }

    @Test
    void testClientDTO() {
        ClientDTO dto = new ClientDTO();
        dto.setId(1);
        dto.setLastName("Петров");
        dto.setFirstName("Петр");
        dto.setPhone("79992223344");
        dto.setEmail("petr@test.ru");

        assertEquals(1, dto.getId());
        assertEquals("Петров", dto.getLastName());
        assertEquals("Петр", dto.getFirstName());
    }
}