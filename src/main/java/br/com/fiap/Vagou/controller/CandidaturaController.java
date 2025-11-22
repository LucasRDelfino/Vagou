package br.com.fiap.Vagou.controller;


import br.com.fiap.Vagou.dto.CandidaturaDTO;
import br.com.fiap.Vagou.enums.StatusCandidatura;
import br.com.fiap.Vagou.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin(origins = "*")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    @Autowired
    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CandidaturaDTO>> getAllCandidaturas() {
        List<CandidaturaDTO> candidaturas = candidaturaService.findAll();
        return ResponseEntity.ok(candidaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidaturaDTO> getCandidaturaById(@PathVariable Long id) {
        CandidaturaDTO candidatura = candidaturaService.findById(id);
        return ResponseEntity.ok(candidatura);
    }

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATO')")
    public ResponseEntity<CandidaturaDTO> createCandidatura(@Valid @RequestBody CandidaturaDTO candidaturaDTO) {
        CandidaturaDTO createdCandidatura = candidaturaService.create(candidaturaDTO);
        return ResponseEntity.ok(createdCandidatura);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<CandidaturaDTO> updateStatus(@PathVariable Long id, @RequestParam StatusCandidatura status) {
        CandidaturaDTO updatedCandidatura = candidaturaService.updateStatus(id, status);
        return ResponseEntity.ok(updatedCandidatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidatura(@PathVariable Long id) {
        candidaturaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vaga/{vagaId}")
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<List<CandidaturaDTO>> getCandidaturasByVaga(@PathVariable Long vagaId) {
        List<CandidaturaDTO> candidaturas = candidaturaService.findByVagaId(vagaId);
        return ResponseEntity.ok(candidaturas);
    }

    @GetMapping("/candidato/{candidatoId}")
    public ResponseEntity<List<CandidaturaDTO>> getCandidaturasByCandidato(@PathVariable Long candidatoId) {
        List<CandidaturaDTO> candidaturas = candidaturaService.findByCandidatoId(candidatoId);
        return ResponseEntity.ok(candidaturas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CandidaturaDTO>> getCandidaturasByStatus(@PathVariable StatusCandidatura status) {
        List<CandidaturaDTO> candidaturas = candidaturaService.findByStatus(status);
        return ResponseEntity.ok(candidaturas);
    }

    @GetMapping("/minhas-candidaturas")
    @PreAuthorize("hasRole('CANDIDATO')")
    public ResponseEntity<List<CandidaturaDTO>> getMinhasCandidaturas() {
        List<CandidaturaDTO> candidaturas = candidaturaService.findMinhasCandidaturas();
        return ResponseEntity.ok(candidaturas);
    }

    @GetMapping("/minhas-candidaturas/status/{status}")
    @PreAuthorize("hasRole('CANDIDATO')")
    public ResponseEntity<List<CandidaturaDTO>> getMinhasCandidaturasByStatus(@PathVariable StatusCandidatura status) {
        List<CandidaturaDTO> candidaturas = candidaturaService.findByStatus(status);
        return ResponseEntity.ok(candidaturas);
    }
}