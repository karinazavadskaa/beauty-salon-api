package com.beauty.salon.repository;

import com.beauty.salon.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindByEmail_ShouldReturnClient() {
        Optional<Client> client = clientRepository.findByEmail("anna.iv@email.ru");
        assertThat(client).isPresent();
        assertThat(client.get().getFirstName()).isEqualTo("Анна");
    }

    @Test
    void testFindByPhone_ShouldReturnClient() {
        Optional<Client> client = clientRepository.findByPhone("79101234567");
        assertThat(client).isPresent();
    }

    @Test
    void testSaveClient_ShouldGenerateId() {
        Client client = new Client();
        client.setLastName("Тестовый");
        client.setFirstName("Клиент");
        client.setPhone("79991112299");
        client.setEmail("test_repo@test.com");
        client.setBirthDate(LocalDate.of(1990, 1, 1));
        client.setRegistrationDate(LocalDate.now());

        Client saved = clientRepository.save(client);
        assertThat(saved.getId()).isNotNull();
    }
}