package com.beauty.salon.service;

import com.beauty.salon.dto.ScheduleCreateUpdateDTO;
import com.beauty.salon.dto.ScheduleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    void testGetAllSchedules_ShouldReturnList() {
        var schedules = scheduleService.getAllSchedules();
        assertThat(schedules).isNotNull();
    }

    @Test
    void testGetScheduleById_WithValidId_ShouldReturnSchedule() {
        var schedules = scheduleService.getAllSchedules();
        if (!schedules.isEmpty()) {
            Integer id = schedules.get(0).getId();
            ScheduleDTO schedule = scheduleService.getScheduleById(id);
            assertThat(schedule).isNotNull();
            assertThat(schedule.getId()).isEqualTo(id);
        }
    }

    @Test
    void testGetScheduleById_WithInvalidId_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            scheduleService.getScheduleById(99999);
        });
    }

    @Test
    void testCreateSchedule_ShouldCreate() {
        ScheduleCreateUpdateDTO dto = new ScheduleCreateUpdateDTO();
        dto.setDate(LocalDate.now().plusDays(10));
        dto.setStartTime(LocalTime.of(9, 0));
        dto.setEndTime(LocalTime.of(18, 0));
        dto.setEmployeeId(1);

        ScheduleDTO result = scheduleService.createSchedule(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void testDeleteSchedule_ShouldDelete() {
        // Create first
        ScheduleCreateUpdateDTO createDto = new ScheduleCreateUpdateDTO();
        createDto.setDate(LocalDate.now().plusDays(20));
        createDto.setStartTime(LocalTime.of(10, 0));
        createDto.setEndTime(LocalTime.of(19, 0));
        createDto.setEmployeeId(1);

        ScheduleDTO created = scheduleService.createSchedule(createDto);

        // Delete
        scheduleService.deleteSchedule(created.getId());

        // Verify
        assertThrows(RuntimeException.class, () -> {
            scheduleService.getScheduleById(created.getId());
        });
    }
}