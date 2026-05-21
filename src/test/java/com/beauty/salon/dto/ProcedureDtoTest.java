package com.beauty.salon.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProcedureDtoTest {

    @Test
    void testProcedureCreateUpdateDTO() {
        ProcedureCreateUpdateDTO dto = new ProcedureCreateUpdateDTO();
        dto.setName("Маникюр");
        dto.setCost("1500 ₽");
        dto.setDuration("60 мин");

        assertEquals("Маникюр", dto.getName());
        assertEquals("1500 ₽", dto.getCost());
        assertEquals("60 мин", dto.getDuration());
    }

    @Test
    void testProcedureDTO() {
        ProcedureDTO dto = new ProcedureDTO();
        dto.setId(1);
        dto.setName("Педикюр");
        dto.setCost("2000 ₽");

        assertEquals(1, dto.getId());
        assertEquals("Педикюр", dto.getName());
    }
}