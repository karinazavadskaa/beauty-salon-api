package com.beauty.salon.repository;

import com.beauty.salon.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByEmployeeId(Integer employeeId);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByEmployeeIdAndStatus(Integer employeeId, String status);
}