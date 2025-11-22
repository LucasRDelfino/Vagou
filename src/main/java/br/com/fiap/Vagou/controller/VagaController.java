package br.com.fiap.Vagou.controller;

import br.com.fiap.Vagou.dto.VagaDTO;
import br.com.fiap.Vagou.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin(origins = "*")
public class VagaController {

    private final VagaService vagaService;

    @Autowired
    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping
    public ResponseEntity<List<VagaDTO>> getAllVagas() {
        List<VagaDTO> vagas = vagaService.findActive();
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> getVagaById(@PathVariable Long id) {
        VagaDTO vaga = vagaService.findById(id);
        return ResponseEntity.ok(vaga);
    }

    @PostMapping
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<VagaDTO> createVaga(@Valid @RequestBody VagaDTO vagaDTO) {
        VagaDTO createdVaga = vagaService.create(vagaDTO);
        return ResponseEntity.ok(createdVaga);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<VagaDTO> updateVaga(@PathVariable Long id, @Valid @RequestBody VagaDTO vagaDTO) {
        VagaDTO updatedVaga = vagaService.update(id, vagaDTO);
        return ResponseEntity.ok(updatedVaga);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        vagaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<VagaDTO>> searchVagas(@RequestParam String termo) {
        List<VagaDTO> vagas = vagaService.search(termo);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/tecnologia/{tecnologia}")
    public ResponseEntity<List<VagaDTO>> getVagasByTecnologia(@PathVariable String tecnologia) {
        List<VagaDTO> vagas = vagaService.findByTecnologia(tecnologia);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/empresa/{empresa}")
    public ResponseEntity<List<VagaDTO>> getVagasByEmpresa(@PathVariable String empresa) {
        List<VagaDTO> vagas = vagaService.findByEmpresa(empresa);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/localizacao/{localizacao}")
    public ResponseEntity<List<VagaDTO>> getVagasByLocalizacao(@PathVariable String localizacao) {
        List<VagaDTO> vagas = vagaService.findByLocalizacao(localizacao);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<VagaDTO>> getVagasByNivel(@PathVariable String nivel) {
        List<VagaDTO> vagas = vagaService.findByNivel(nivel);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/contrato/{tipoContrato}")
    public ResponseEntity<List<VagaDTO>> getVagasByTipoContrato(@PathVariable String tipoContrato) {
        List<VagaDTO> vagas = vagaService.findByTipoContrato(tipoContrato);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/minhas-vagas")
    @PreAuthorize("hasRole('RECRUTADOR') or hasRole('ADMIN')")
    public ResponseEntity<List<VagaDTO>> getMinhasVagas() {
        // Implementar se necessário - vagas criadas pelo usuário atual
        return ResponseEntity.ok().build();
    }
}