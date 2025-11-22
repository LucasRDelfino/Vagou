package br.com.fiap.Vagou.service;


import br.com.fiap.Vagou.dto.VagaDTO;
import br.com.fiap.Vagou.exceptions.BusinessException;
import br.com.fiap.Vagou.exceptions.NotFoundException;
import br.com.fiap.Vagou.model.User;
import br.com.fiap.Vagou.model.Vaga;
import br.com.fiap.Vagou.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VagaService {

    private final VagaRepository vagaRepository;
    private final UserService userService;

    @Autowired
    public VagaService(VagaRepository vagaRepository, UserService userService) {
        this.vagaRepository = vagaRepository;
        this.userService = userService;
    }

    // Métodos existentes...
    public List<VagaDTO> findAll() {
        return vagaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VagaDTO> findActive() {
        return vagaRepository.findByAtivaTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VagaDTO findById(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));
        return convertToDTO(vaga);
    }

    public VagaDTO create(VagaDTO vagaDTO) {
        User currentUser = getCurrentUser();

        Vaga vaga = convertToEntity(vagaDTO);
        vaga.setRecrutador(currentUser);

        Vaga savedVaga = vagaRepository.save(vaga);
        return convertToDTO(savedVaga);
    }

    public VagaDTO update(Long id, VagaDTO vagaDTO) {
        Vaga existingVaga = vagaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));

        // Verificar se o usuário é o dono da vaga ou admin
        User currentUser = getCurrentUser();
        if (!existingVaga.getRecrutador().getId().equals(currentUser.getId()) &&
                !currentUser.getRole().name().equals("ADMIN")) {
            throw new BusinessException("Apenas o recrutador responsável pode editar esta vaga");
        }

        updateEntityFromDTO(existingVaga, vagaDTO);
        Vaga updatedVaga = vagaRepository.save(existingVaga);
        return convertToDTO(updatedVaga);
    }

    public void delete(Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));

        // Verificar se o usuário é o dono da vaga ou admin
        User currentUser = getCurrentUser();
        if (!vaga.getRecrutador().getId().equals(currentUser.getId()) &&
                !currentUser.getRole().name().equals("ADMIN")) {
            throw new BusinessException("Apenas o recrutador responsável pode excluir esta vaga");
        }

        vagaRepository.delete(vaga);
    }

    public List<VagaDTO> search(String termo) {
        return vagaRepository.buscarPorTermo(termo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VagaDTO> findByTecnologia(String tecnologia) {
        return vagaRepository.findByTecnologiasContainingAndAtivaTrue(tecnologia).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // NOVOS MÉTODOS ADICIONADOS:

    public List<VagaDTO> findByEmpresa(String empresa) {
        return vagaRepository.findByEmpresaContainingIgnoreCase(empresa).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VagaDTO> findByLocalizacao(String localizacao) {
        return vagaRepository.findByLocalizacaoContainingIgnoreCase(localizacao).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VagaDTO> findByNivel(String nivel) {
        return vagaRepository.findByNivelAndAtivaTrue(nivel).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VagaDTO> findByTipoContrato(String tipoContrato) {
        return vagaRepository.findByTipoContratoAndAtivaTrue(tipoContrato).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Vaga getVagaEntityById(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));
    }

    // Métodos auxiliares
    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private VagaDTO convertToDTO(Vaga vaga) {
        return new VagaDTO(
                vaga.getId(),
                vaga.getTitulo(),
                vaga.getDescricao(),
                vaga.getEmpresa(),
                vaga.getLocalizacao(),
                vaga.getSalario(),
                vaga.getTipoContrato(),
                vaga.getNivel(),
                vaga.getTecnologias(),
                vaga.getDataPublicacao(),
                vaga.getAtiva(),
                vaga.getRecrutador().getId()
        );
    }

    private Vaga convertToEntity(VagaDTO dto) {
        return new Vaga(
                dto.getId(),
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getEmpresa(),
                dto.getLocalizacao(),
                dto.getSalario(),
                dto.getTipoContrato(),
                dto.getNivel(),
                dto.getTecnologias(),
                dto.getDataPublicacao(),
                dto.getAtiva(),
                null
        );
    }

    private void updateEntityFromDTO(Vaga vaga, VagaDTO dto) {
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setEmpresa(dto.getEmpresa());
        vaga.setLocalizacao(dto.getLocalizacao());
        vaga.setSalario(dto.getSalario());
        vaga.setTipoContrato(dto.getTipoContrato());
        vaga.setNivel(dto.getNivel());
        vaga.setTecnologias(dto.getTecnologias());
        vaga.setAtiva(dto.getAtiva());
    }
}