package com.beauty.salon.controller;

import com.beauty.salon.dto.ProcedureCreateUpdateDTO;
import com.beauty.salon.dto.ProcedureDTO;
import com.beauty.salon.service.ProcedureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping
    public ResponseEntity<List<ProcedureDTO>> getAll() {
        return ResponseEntity.ok(procedureService.getAllProcedures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(procedureService.getProcedureById(id));
    }

    @PostMapping
    public ResponseEntity<ProcedureDTO> create(@Valid @RequestBody ProcedureCreateUpdateDTO dto) {
        return new ResponseEntity<>(procedureService.createProcedure(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDTO> update(@PathVariable Integer id, @Valid @RequestBody ProcedureCreateUpdateDTO dto) {
        return ResponseEntity.ok(procedureService.updateProcedure(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.noContent().build();
    }
}