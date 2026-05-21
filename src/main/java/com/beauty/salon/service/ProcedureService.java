package com.beauty.salon.service;

import com.beauty.salon.dto.ProcedureCreateUpdateDTO;
import com.beauty.salon.dto.ProcedureDTO;
import com.beauty.salon.entity.Procedure;
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
public class ProcedureService {

    private final ProcedureRepository procedureRepository;

    @Transactional(readOnly = true)
    public List<ProcedureDTO> getAllProcedures() {
        return procedureRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProcedureDTO getProcedureById(Integer id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));
        return convertToDTO(procedure);
    }

    @Transactional
    public ProcedureDTO createProcedure(ProcedureCreateUpdateDTO dto) {
        Procedure procedure = new Procedure();
        procedure.setName(dto.getName());
        procedure.setCost(dto.getCost());
        procedure.setDuration(dto.getDuration());
        return convertToDTO(procedureRepository.save(procedure));
    }

    @Transactional
    public ProcedureDTO updateProcedure(Integer id, ProcedureCreateUpdateDTO dto) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));
        procedure.setName(dto.getName());
        procedure.setCost(dto.getCost());
        procedure.setDuration(dto.getDuration());
        return convertToDTO(procedureRepository.save(procedure));
    }

    @Transactional
    public void deleteProcedure(Integer id) {
        if (!procedureRepository.existsById(id)) {
            throw new RuntimeException("Procedure not found with id: " + id);
        }
        procedureRepository.deleteById(id);
    }

    private ProcedureDTO convertToDTO(Procedure procedure) {
        return new ProcedureDTO(
                procedure.getId(),
                procedure.getName(),
                procedure.getCost(),
                procedure.getDuration()
        );
    }
}