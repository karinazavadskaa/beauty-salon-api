package com.beauty.salon.service;

import com.beauty.salon.dto.AppointmentCreateUpdateDTO;
import com.beauty.salon.dto.AppointmentDTO;
import com.beauty.salon.entity.Appointment;
import com.beauty.salon.entity.Client;
import com.beauty.salon.entity.Employee;
import com.beauty.salon.entity.Procedure;
import com.beauty.salon.repository.AppointmentRepository;
import com.beauty.salon.repository.ClientRepository;
import com.beauty.salon.repository.EmployeeRepository;
import com.beauty.salon.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ProcedureRepository procedureRepository;

    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppointmentDTO getAppointmentById(Integer id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        return convertToDTO(appointment);
    }

    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAppointmentsByEmployeeId(Integer employeeId) {
        return appointmentRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AppointmentDTO createAppointment(AppointmentCreateUpdateDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));
        Procedure procedure = procedureRepository.findById(dto.getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dto.getProcedureId()));

        Appointment appointment = new Appointment();
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setStatus(dto.getStatus() != null ? dto.getStatus() : "Scheduled");
        appointment.setNote(dto.getNote());
        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setProcedure(procedure);

        return convertToDTO(appointmentRepository.save(appointment));
    }

    @Transactional
    public AppointmentDTO updateAppointment(Integer id, AppointmentCreateUpdateDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));
        Procedure procedure = procedureRepository.findById(dto.getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dto.getProcedureId()));

        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setStatus(dto.getStatus());
        appointment.setNote(dto.getNote());
        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setProcedure(procedure);

        return convertToDTO(appointmentRepository.save(appointment));
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getStatus(),
                appointment.getNote(),
                appointment.getClient().getId(),
                appointment.getClient().getFirstName() + " " + appointment.getClient().getLastName(),
                appointment.getEmployee().getId(),
                appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName(),
                appointment.getProcedure().getId(),
                appointment.getProcedure().getName(),
                appointment.getProcedure().getCost()  // ← теперь String
        );
    }
}