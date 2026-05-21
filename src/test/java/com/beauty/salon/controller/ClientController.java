package com.beauty.salon.controller;

import com.beauty.salon.dto.ClientCreateUpdateDTO;
import com.beauty.salon.dto.ClientDTO;
import com.beauty.salon.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private final ClientDTO clientDTO = new ClientDTO(1, "Иванов", "Иван", "Петрович", "79161234567", "ivan@mail.com", LocalDate.of(1990, 1, 1), LocalDate.now());
    private final ClientCreateUpdateDTO createDTO = new ClientCreateUpdateDTO("Сидорова", "Мария", "Алексеевна", "79169998877", "maria@mail.com", LocalDate.of(1995, 5, 15));

    @Test
    void getAllClients_ShouldReturnList() {
        List<ClientDTO> clients = Arrays.asList(clientDTO);
        when(clientService.getAllClients()).thenReturn(clients);

        ResponseEntity<List<ClientDTO>> response = clientController.getAllClients();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void getAllClients_WhenEmpty_ShouldReturnEmptyList() {
        when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ClientDTO>> response = clientController.getAllClients();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void getClientById_ShouldReturnClient() {
        when(clientService.getClientById(1)).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.getClientById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getFirstName()).isEqualTo("Иван");
        verify(clientService, times(1)).getClientById(1);
    }

    @Test
    void createClient_ShouldReturnCreated() {
        when(clientService.createClient(any(ClientCreateUpdateDTO.class))).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.createClient(createDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
        verify(clientService, times(1)).createClient(any());
    }

    @Test
    void updateClient_ShouldReturnOk() {
        when(clientService.updateClient(eq(1), any(ClientCreateUpdateDTO.class))).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.updateClient(1, createDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        verify(clientService, times(1)).updateClient(eq(1), any());
    }

    @Test
    void deleteClient_ShouldReturnNoContent() {
        doNothing().when(clientService).deleteClient(1);

        ResponseEntity<Void> response = clientController.deleteClient(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(clientService, times(1)).deleteClient(1);
    }
}