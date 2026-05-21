package com.beauty.salon.repository;

import com.beauty.salon.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByEmployeeId(Integer employeeId);
    List<Schedule> findByDate(LocalDate date);
}