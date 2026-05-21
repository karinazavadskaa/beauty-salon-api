package com.beauty.salon.controller;

import com.beauty.salon.dto.ScheduleCreateUpdateDTO;
import com.beauty.salon.dto.ScheduleDTO;
import com.beauty.salon.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private final ScheduleDTO scheduleDTO = new ScheduleDTO(1, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(18, 0), null, null, 1, "Петрова Елена");
    private final ScheduleCreateUpdateDTO createDTO = new ScheduleCreateUpdateDTO(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(19, 0), null, null, 1);

    @Test
    void getAll_ShouldReturnList() {
        List<ScheduleDTO> schedules = Arrays.asList(scheduleDTO);
        when(scheduleService.getAllSchedules()).thenReturn(schedules);

        ResponseEntity<List<ScheduleDTO>> response = scheduleController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        verify(scheduleService, times(1)).getAllSchedules();
    }

    @Test
    void getAll_WhenEmpty_ShouldReturnEmptyList() {
        when(scheduleService.getAllSchedules()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ScheduleDTO>> response = scheduleController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void getById_ShouldReturnSchedule() {
        when(scheduleService.getScheduleById(1)).thenReturn(scheduleDTO);

        ResponseEntity<ScheduleDTO> response = scheduleController.getById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        verify(scheduleService, times(1)).getScheduleById(1);
    }

    @Test
    void create_ShouldReturnCreated() {
        when(scheduleService.createSchedule(any(ScheduleCreateUpdateDTO.class))).thenReturn(scheduleDTO);

        ResponseEntity<ScheduleDTO> response = scheduleController.create(createDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        verify(scheduleService, times(1)).createSchedule(any());
    }

    @Test
    void update_ShouldReturnOk() {
        when(scheduleService.updateSchedule(eq(1), any(ScheduleCreateUpdateDTO.class))).thenReturn(scheduleDTO);

        ResponseEntity<ScheduleDTO> response = scheduleController.update(1, createDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        verify(scheduleService, times(1)).updateSchedule(eq(1), any());
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(scheduleService).deleteSchedule(1);

        ResponseEntity<Void> response = scheduleController.delete(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(scheduleService, times(1)).deleteSchedule(1);
    }
}