package com.beauty.salon.service;

import com.beauty.salon.dto.ScheduleCreateUpdateDTO;
import com.beauty.salon.dto.ScheduleDTO;
import com.beauty.salon.entity.Employee;
import com.beauty.salon.entity.Schedule;
import com.beauty.salon.repository.EmployeeRepository;
import com.beauty.salon.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleDTO getScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        return convertToDTO(schedule);
    }

    @Transactional
    public ScheduleDTO createSchedule(ScheduleCreateUpdateDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        Schedule schedule = new Schedule();
        schedule.setDate(dto.getDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setBreakStart(dto.getBreakStart());
        schedule.setBreakEnd(dto.getBreakEnd());
        schedule.setEmployee(employee);

        return convertToDTO(scheduleRepository.save(schedule));
    }

    @Transactional
    public ScheduleDTO updateSchedule(Integer id, ScheduleCreateUpdateDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        schedule.setDate(dto.getDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setBreakStart(dto.getBreakStart());
        schedule.setBreakEnd(dto.getBreakEnd());
        schedule.setEmployee(employee);

        return convertToDTO(scheduleRepository.save(schedule));
    }

    @Transactional
    public void deleteSchedule(Integer id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getDate(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getBreakStart(),
                schedule.getBreakEnd(),
                schedule.getEmployee().getId(),
                schedule.getEmployee().getFirstName() + " " + schedule.getEmployee().getLastName()
        );
    }
}