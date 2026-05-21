package com.beauty.salon.service;

import com.beauty.salon.dto.ClientCreateUpdateDTO;
import com.beauty.salon.dto.ClientDTO;
import com.beauty.salon.entity.Client;
import com.beauty.salon.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        log.debug("Fetching all clients");
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientById(Integer id) {
        log.debug("Fetching client with id: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return convertToDTO(client);
    }

    @Transactional
    public ClientDTO createClient(ClientCreateUpdateDTO createDTO) {
        log.info("Creating new client: {} {}", createDTO.getLastName(), createDTO.getFirstName());

        if (clientRepository.findByEmail(createDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Client with email " + createDTO.getEmail() + " already exists");
        }
        if (clientRepository.findByPhone(createDTO.getPhone()).isPresent()) {
            throw new RuntimeException("Client with phone " + createDTO.getPhone() + " already exists");
        }

        Client client = new Client();
        client.setLastName(createDTO.getLastName());
        client.setFirstName(createDTO.getFirstName());
        client.setMiddleName(createDTO.getMiddleName());
        client.setPhone(createDTO.getPhone());
        client.setEmail(createDTO.getEmail());
        client.setBirthDate(createDTO.getBirthDate());
        client.setRegistrationDate(LocalDate.now());

        Client saved = clientRepository.save(client);
        log.info("Client created with id: {}", saved.getId());
        return convertToDTO(saved);
    }

    @Transactional
    public ClientDTO updateClient(Integer id, ClientCreateUpdateDTO updateDTO) {
        log.info("Updating client with id: {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        if (!client.getEmail().equals(updateDTO.getEmail()) &&
                clientRepository.findByEmail(updateDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Client with email " + updateDTO.getEmail() + " already exists");
        }

        if (!client.getPhone().equals(updateDTO.getPhone()) &&
                clientRepository.findByPhone(updateDTO.getPhone()).isPresent()) {
            throw new RuntimeException("Client with phone " + updateDTO.getPhone() + " already exists");
        }

        client.setLastName(updateDTO.getLastName());
        client.setFirstName(updateDTO.getFirstName());
        client.setMiddleName(updateDTO.getMiddleName());
        client.setPhone(updateDTO.getPhone());
        client.setEmail(updateDTO.getEmail());
        client.setBirthDate(updateDTO.getBirthDate());

        Client updated = clientRepository.save(client);
        return convertToDTO(updated);
    }

    @Transactional
    public void deleteClient(Integer id) {
        log.info("Deleting client with id: {}", id);
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getLastName(),
                client.getFirstName(),
                client.getMiddleName(),
                client.getPhone(),
                client.getEmail(),
                client.getBirthDate(),
                client.getRegistrationDate()
        );
    }
}