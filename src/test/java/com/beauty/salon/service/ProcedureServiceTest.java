package com.beauty.salon.service;

import com.beauty.salon.dto.ProcedureCreateUpdateDTO;
import com.beauty.salon.dto.ProcedureDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ProcedureServiceTest {

    @Autowired
    private ProcedureService procedureService;

    @Test
    void testGetAllProcedures_ShouldReturnList() {
        var procedures = procedureService.getAllProcedures();
        assertThat(procedures).isNotNull();
    }

    @Test
    void testGetProcedureById_WithValidId_ShouldReturnProcedure() {
        var procedures = procedureService.getAllProcedures();
        if (!procedures.isEmpty()) {
            Integer id = procedures.get(0).getId();
            ProcedureDTO procedure = procedureService.getProcedureById(id);
            assertThat(procedure).isNotNull();
            assertThat(procedure.getId()).isEqualTo(id);
        }
    }

    @Test
    void testGetProcedureById_WithInvalidId_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            procedureService.getProcedureById(99999);
        });
    }
}