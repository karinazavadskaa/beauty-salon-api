package com.beauty.salon.service;

import com.beauty.salon.dto.ClientCreateUpdateDTO;
import com.beauty.salon.dto.ClientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void testGetAllClients_ShouldReturnList() {
        List<ClientDTO> clients = clientService.getAllClients();
        assertThat(clients).isNotNull();
        System.out.println("Clients count: " + clients.size());
    }

    @Test
    void testGetClientById_WithValidId_ShouldReturnClient() {
        List<ClientDTO> clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            Integer id = clients.get(0).getId();
            ClientDTO client = clientService.getClientById(id);
            assertThat(client).isNotNull();
            assertThat(client.getId()).isEqualTo(id);
        }
    }

    @Test
    void testGetClientById_WithInvalidId_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> {
            clientService.getClientById(99999);
        });
    }

    @Test
    void testCreateClient_ShouldCreate() {
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Тестов");
        dto.setFirstName("Тест");
        dto.setMiddleName("Тестович");
        dto.setPhone("79991112299");
        dto.setEmail("test_new@example.com");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO result = clientService.createClient(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test_new@example.com");
    }

    @Test
    void testCreateClient_DuplicateEmail_ShouldThrowException() {
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Тестов");
        dto.setFirstName("Тест");
        dto.setPhone("79991112288");
        dto.setEmail("anna.iv@email.ru"); // существующий email
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        assertThrows(RuntimeException.class, () -> {
            clientService.createClient(dto);
        });
    }

    @Test
    void testUpdateClient_ShouldUpdate() {
        // Create first
        ClientCreateUpdateDTO createDto = new ClientCreateUpdateDTO();
        createDto.setLastName("ДляОбновления");
        createDto.setFirstName("Тест");
        createDto.setPhone("79991112277");
        createDto.setEmail("update_test@example.com");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO created = clientService.createClient(createDto);

        // Update
        ClientCreateUpdateDTO updateDto = new ClientCreateUpdateDTO();
        updateDto.setLastName("Обновлен");
        updateDto.setFirstName(created.getFirstName());
        updateDto.setPhone(created.getPhone());
        updateDto.setEmail(created.getEmail());
        updateDto.setBirthDate(created.getBirthDate());

        ClientDTO updated = clientService.updateClient(created.getId(), updateDto);

        assertThat(updated.getLastName()).isEqualTo("Обновлен");
    }

    @Test
    void testDeleteClient_ShouldDelete() {
        // Create first
        ClientCreateUpdateDTO createDto = new ClientCreateUpdateDTO();
        createDto.setLastName("ДляУдаления");
        createDto.setFirstName("Тест");
        createDto.setPhone("79991112266");
        createDto.setEmail("delete_test@example.com");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO created = clientService.createClient(createDto);

        // Delete
        clientService.deleteClient(created.getId());

        // Verify
        assertThrows(RuntimeException.class, () -> {
            clientService.getClientById(created.getId());
        });
    }
    @Test
    void testGetAllClients_ReturnsData() {
        var clients = clientService.getAllClients();
        assertThat(clients).isNotNull();
        System.out.println("Total clients: " + clients.size());
    }

    @Test
    void testGetClientById_WithValidId_ReturnsCorrectClient() {
        var clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            Integer firstId = clients.get(0).getId();
            ClientDTO client = clientService.getClientById(firstId);
            assertThat(client).isNotNull();
            assertThat(client.getId()).isEqualTo(firstId);
        }
    }
}