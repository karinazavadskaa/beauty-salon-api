package com.beauty.salon.integration;

import com.beauty.salon.dto.ProcedureDTO;
import com.beauty.salon.service.ProcedureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProcedureServiceIntegrationTest {

    @Autowired
    private ProcedureService procedureService;

    @Test
    void getAllProcedures_Integration() {
        var procedures = procedureService.getAllProcedures();
        assertThat(procedures).isNotNull();
        assertThat(procedures.size()).isGreaterThan(0);
    }

    @Test
    void getProcedureById_Integration() {
        var procedures = procedureService.getAllProcedures();
        if (!procedures.isEmpty()) {
            Integer id = procedures.get(0).getId();
            ProcedureDTO procedure = procedureService.getProcedureById(id);
            assertThat(procedure).isNotNull();
            assertThat(procedure.getId()).isEqualTo(id);
        }
    }

    @Test
    void getProcedureById_WithInvalidId_ThrowsException() {
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            procedureService.getProcedureById(99999);
        });
    }
}