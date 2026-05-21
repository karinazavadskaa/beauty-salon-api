package com.beauty.salon.controller;

import com.beauty.salon.dto.AppointmentCreateUpdateDTO;
import com.beauty.salon.dto.AppointmentDTO;
import com.beauty.salon.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    private AppointmentDTO appointmentDTO1;
    private AppointmentDTO appointmentDTO2;
    private AppointmentCreateUpdateDTO createUpdateDTO;
    private final Integer TEST_ID = 1;
    private final Integer EMPLOYEE_ID = 10;

    @BeforeEach
    void setUp() {
        appointmentDTO1 = new AppointmentDTO(
                1,
                LocalDate.of(2025, 5, 25),
                LocalTime.of(10, 0),
                "Scheduled",
                "Обычная запись",
                100,
                "Анна Иванова",
                200,
                "Елена Петрова",
                300,
                "Чистка лица",
                "2500.00"
        );

        appointmentDTO2 = new AppointmentDTO(
                2,
                LocalDate.of(2025, 5, 26),
                LocalTime.of(14, 30),
                "Completed",
                "Срочная запись",
                101,
                "Мария Сидорова",
                201,
                "Ольга Смирнова",
                301,
                "Массаж",
                "3500.00"
        );

        createUpdateDTO = new AppointmentCreateUpdateDTO(
                LocalDate.of(2025, 5, 27),
                LocalTime.of(11, 0),
                "Scheduled",
                "Тестовая запись",
                100,
                200,
                300
        );
    }

    @Test
    void getAll_ShouldReturnListOfAppointments() {
        List<AppointmentDTO> appointments = Arrays.asList(appointmentDTO1, appointmentDTO2);
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getId()).isEqualTo(1);
        assertThat(response.getBody().get(0).getStatus()).isEqualTo("Scheduled");
        assertThat(response.getBody().get(1).getId()).isEqualTo(2);
        assertThat(response.getBody().get(1).getStatus()).isEqualTo("Completed");

        verify(appointmentService, times(1)).getAllAppointments();
    }

    @Test
    void getAll_WhenNoAppointments_ShouldReturnEmptyList() {
        when(appointmentService.getAllAppointments()).thenReturn(Arrays.asList());

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
        verify(appointmentService, times(1)).getAllAppointments();
    }


    @Test
    void getById_WhenExists_ShouldReturnAppointment() {
        when(appointmentService.getAppointmentById(TEST_ID)).thenReturn(appointmentDTO1);

        ResponseEntity<AppointmentDTO> response = appointmentController.getById(TEST_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getClientId()).isEqualTo(100);
        assertThat(response.getBody().getClientName()).isEqualTo("Анна Иванова");
        assertThat(response.getBody().getProcedureName()).isEqualTo("Чистка лица");

        verify(appointmentService, times(1)).getAppointmentById(TEST_ID);
    }

    @Test
    void getById_WhenNotFound_ShouldThrowException() {
        when(appointmentService.getAppointmentById(TEST_ID))
                .thenThrow(new RuntimeException("Appointment not found with id: " + TEST_ID));

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            appointmentController.getById(TEST_ID);
        });

        verify(appointmentService, times(1)).getAppointmentById(TEST_ID);
    }


    @Test
    void getByEmployeeId_ShouldReturnEmployeeAppointments() {
        List<AppointmentDTO> employeeAppointments = Arrays.asList(appointmentDTO1);
        when(appointmentService.getAppointmentsByEmployeeId(EMPLOYEE_ID)).thenReturn(employeeAppointments);

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getByEmployeeId(EMPLOYEE_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getEmployeeId()).isEqualTo(200);
        assertThat(response.getBody().get(0).getEmployeeName()).isEqualTo("Елена Петрова");

        verify(appointmentService, times(1)).getAppointmentsByEmployeeId(EMPLOYEE_ID);
    }

    @Test
    void getByEmployeeId_WhenNoAppointments_ShouldReturnEmptyList() {
        when(appointmentService.getAppointmentsByEmployeeId(EMPLOYEE_ID)).thenReturn(Arrays.asList());

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getByEmployeeId(EMPLOYEE_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();

        verify(appointmentService, times(1)).getAppointmentsByEmployeeId(EMPLOYEE_ID);
    }


    @Test
    void create_WithValidData_ShouldReturnCreated() {
        when(appointmentService.createAppointment(any(AppointmentCreateUpdateDTO.class)))
                .thenReturn(appointmentDTO1);

        ResponseEntity<AppointmentDTO> response = appointmentController.create(createUpdateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getStatus()).isEqualTo("Scheduled");
        assertThat(response.getBody().getNote()).isEqualTo("Обычная запись");

        verify(appointmentService, times(1)).createAppointment(any(AppointmentCreateUpdateDTO.class));
    }

    @Test
    void create_WhenClientNotFound_ShouldThrowException() {
        when(appointmentService.createAppointment(any(AppointmentCreateUpdateDTO.class)))
                .thenThrow(new RuntimeException("Client not found with id: 999"));

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            appointmentController.create(createUpdateDTO);
        });

        verify(appointmentService, times(1)).createAppointment(any());
    }


    @Test
    void update_WithValidData_ShouldReturnUpdated() {
        AppointmentDTO updatedDTO = new AppointmentDTO(
                TEST_ID,
                LocalDate.of(2025, 5, 28),
                LocalTime.of(15, 0),
                "Rescheduled",
                "Измененная запись",
                100,
                "Анна Иванова",
                200,
                "Елена Петрова",
                300,
                "Чистка лица",
                "2500.00"
        );

        when(appointmentService.updateAppointment(eq(TEST_ID), any(AppointmentCreateUpdateDTO.class)))
                .thenReturn(updatedDTO);

        ResponseEntity<AppointmentDTO> response = appointmentController.update(TEST_ID, createUpdateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getStatus()).isEqualTo("Rescheduled");
        assertThat(response.getBody().getNote()).isEqualTo("Измененная запись");

        verify(appointmentService, times(1)).updateAppointment(eq(TEST_ID), any(AppointmentCreateUpdateDTO.class));
    }

    @Test
    void update_WhenNotFound_ShouldThrowException() {
        when(appointmentService.updateAppointment(eq(TEST_ID), any(AppointmentCreateUpdateDTO.class)))
                .thenThrow(new RuntimeException("Appointment not found with id: " + TEST_ID));

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            appointmentController.update(TEST_ID, createUpdateDTO);
        });

        verify(appointmentService, times(1)).updateAppointment(eq(TEST_ID), any());
    }

    @Test
    void delete_WhenExists_ShouldReturnNoContent() {
        doNothing().when(appointmentService).deleteAppointment(TEST_ID);

        ResponseEntity<Void> response = appointmentController.delete(TEST_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(appointmentService, times(1)).deleteAppointment(TEST_ID);
    }

    @Test
    void delete_WhenNotFound_ShouldThrowException() {
        doThrow(new RuntimeException("Appointment not found with id: " + TEST_ID))
                .when(appointmentService).deleteAppointment(TEST_ID);

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            appointmentController.delete(TEST_ID);
        });

        verify(appointmentService, times(1)).deleteAppointment(TEST_ID);
    }
}