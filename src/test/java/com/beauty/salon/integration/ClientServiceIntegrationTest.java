package com.beauty.salon.integration;

import com.beauty.salon.dto.ClientCreateUpdateDTO;
import com.beauty.salon.dto.ClientDTO;
import com.beauty.salon.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ClientServiceIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Test
    void createAndFindClient_Integration() {
        // Create
        ClientCreateUpdateDTO dto = new ClientCreateUpdateDTO();
        dto.setLastName("Интеграция");
        dto.setFirstName("Тест");
        dto.setPhone("79991112244");
        dto.setEmail("integration@test.com");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO created = clientService.createClient(dto);

        // Find
        ClientDTO found = clientService.getClientById(created.getId());

        // Verify
        assertThat(found.getEmail()).isEqualTo("integration@test.com");
        assertThat(found.getLastName()).isEqualTo("Интеграция");
    }

    @Test
    void updateClient_Integration() {
        // Create
        ClientCreateUpdateDTO createDto = new ClientCreateUpdateDTO();
        createDto.setLastName("ДляОбновленияИнтегр");
        createDto.setFirstName("Тест");
        createDto.setPhone("79991112255");
        createDto.setEmail("update_integration@test.com");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO created = clientService.createClient(createDto);

        // Update
        ClientCreateUpdateDTO updateDto = new ClientCreateUpdateDTO();
        updateDto.setLastName("ОбновленИнтегр");
        updateDto.setFirstName(created.getFirstName());
        updateDto.setPhone(created.getPhone());
        updateDto.setEmail(created.getEmail());
        updateDto.setBirthDate(created.getBirthDate());

        ClientDTO updated = clientService.updateClient(created.getId(), updateDto);

        assertThat(updated.getLastName()).isEqualTo("ОбновленИнтегр");
    }

    @Test
    void deleteClient_Integration() {
        // Create
        ClientCreateUpdateDTO createDto = new ClientCreateUpdateDTO();
        createDto.setLastName("ДляУдаленияИнтегр");
        createDto.setFirstName("Тест");
        createDto.setPhone("79991112266");
        createDto.setEmail("delete_integration@test.com");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));

        ClientDTO created = clientService.createClient(createDto);

        // Delete
        clientService.deleteClient(created.getId());

        // Verify
        assertThrows(RuntimeException.class, () -> {
            clientService.getClientById(created.getId());
        });
    }
}