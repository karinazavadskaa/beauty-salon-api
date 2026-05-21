package com.beauty.salon.controller;

import com.beauty.salon.dto.ProcedureCreateUpdateDTO;
import com.beauty.salon.dto.ProcedureDTO;
import com.beauty.salon.service.ProcedureService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcedureControllerTest {

    @Mock
    private ProcedureService procedureService;

    @InjectMocks
    private ProcedureController procedureController;

    private final ProcedureDTO procedureDTO = new ProcedureDTO(1, "Чистка лица", "2500.00", "60 мин");
    private final ProcedureCreateUpdateDTO createDTO = new ProcedureCreateUpdateDTO("Массаж", "3500.00", "90 мин");

    @Test
    void getAll_ShouldReturnList() {
        when(procedureService.getAllProcedures()).thenReturn(Arrays.asList(procedureDTO));
        ResponseEntity<List<ProcedureDTO>> response = procedureController.getAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void getById_ShouldReturnProcedure() {
        when(procedureService.getProcedureById(1)).thenReturn(procedureDTO);
        ResponseEntity<ProcedureDTO> response = procedureController.getById(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Чистка лица");
    }

    @Test
    void create_ShouldReturnCreated() {
        when(procedureService.createProcedure(any())).thenReturn(procedureDTO);
        ResponseEntity<ProcedureDTO> response = procedureController.create(createDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void update_ShouldReturnOk() {
        when(procedureService.updateProcedure(eq(1), any())).thenReturn(procedureDTO);
        ResponseEntity<ProcedureDTO> response = procedureController.update(1, createDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(procedureService).deleteProcedure(1);
        ResponseEntity<Void> response = procedureController.delete(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}