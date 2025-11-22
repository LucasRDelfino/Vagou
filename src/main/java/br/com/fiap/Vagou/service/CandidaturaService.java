package br.com.fiap.Vagou.service;

import br.com.fiap.Vagou.dto.CandidaturaDTO;
import br.com.fiap.Vagou.enums.StatusCandidatura;
import br.com.fiap.Vagou.exceptions.BusinessException;
import br.com.fiap.Vagou.exceptions.NotFoundException;
import br.com.fiap.Vagou.model.Candidatura;
import br.com.fiap.Vagou.model.User;
import br.com.fiap.Vagou.model.Vaga;
import br.com.fiap.Vagou.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final VagaService vagaService;
    private final UserService userService;

    @Autowired
    public CandidaturaService(CandidaturaRepository candidaturaRepository, VagaService vagaService, UserService userService) {
        this.candidaturaRepository = candidaturaRepository;
        this.vagaService = vagaService;
        this.userService = userService;
    }

    public List<CandidaturaDTO> findAll() {
        return candidaturaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CandidaturaDTO findById(Long id) {
        Candidatura candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidatura não encontrada"));
        return convertToDTO(candidatura);
    }

    public CandidaturaDTO create(CandidaturaDTO candidaturaDTO) {
        User currentUser = getCurrentUser();
        Vaga vaga = vagaService.getVagaEntityById(candidaturaDTO.getVagaId());

        // Verificar se a vaga está ativa
        if (!vaga.getAtiva()) {
            throw new BusinessException("Não é possível se candidatar a uma vaga inativa");
        }

        // Verificar se o usuário já se candidatou a esta vaga
        if (candidaturaRepository.existsByVagaIdAndCandidatoId(candidaturaDTO.getVagaId(), currentUser.getId())) {
            throw new BusinessException("Você já se candidatou a esta vaga");
        }

        Candidatura candidatura = convertToEntity(candidaturaDTO);
        candidatura.setVaga(vaga);
        candidatura.setCandidato(currentUser);
        candidatura.setNomeCandidato(currentUser.getName());
        candidatura.setEmail(currentUser.getEmail());

        Candidatura savedCandidatura = candidaturaRepository.save(candidatura);
        return convertToDTO(savedCandidatura);
    }

    public CandidaturaDTO updateStatus(Long id, StatusCandidatura status) {
        Candidatura candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidatura não encontrada"));

        // Verificar se o usuário é o recrutador da vaga ou admin
        User currentUser = getCurrentUser();
        if (!candidatura.getVaga().getRecrutador().getId().equals(currentUser.getId()) &&
                !currentUser.getRole().name().equals("ADMIN")) {
            throw new BusinessException("Apenas o recrutador responsável pode alterar o status da candidatura");
        }

        candidatura.setStatus(status);
        Candidatura updatedCandidatura = candidaturaRepository.save(candidatura);
        return convertToDTO(updatedCandidatura);
    }

    public void delete(Long id) {
        Candidatura candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidatura não encontrada"));

        // Verificar se o usuário é o candidato ou admin/recrutador
        User currentUser = getCurrentUser();
        boolean isCandidato = candidatura.getCandidato().getId().equals(currentUser.getId());
        boolean isRecrutador = candidatura.getVaga().getRecrutador().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getRole().name().equals("ADMIN");

        if (!isCandidato && !isRecrutador && !isAdmin) {
            throw new BusinessException("Apenas o candidato, recrutador ou admin podem excluir esta candidatura");
        }

        candidaturaRepository.delete(candidatura);
    }

    public List<CandidaturaDTO> findByVagaId(Long vagaId) {
        // Verificar se o usuário tem acesso às candidaturas desta vaga
        User currentUser = getCurrentUser();
        Vaga vaga = vagaService.getVagaEntityById(vagaId);

        if (!vaga.getRecrutador().getId().equals(currentUser.getId()) &&
                !currentUser.getRole().name().equals("ADMIN")) {
            throw new BusinessException("Apenas o recrutador responsável pode ver as candidaturas desta vaga");
        }

        return candidaturaRepository.findByVagaId(vagaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CandidaturaDTO> findByCandidatoId(Long candidatoId) {
        User currentUser = getCurrentUser();

        // Verificar se o usuário está acessando suas próprias candidaturas ou é admin
        if (!currentUser.getId().equals(candidatoId) && !currentUser.getRole().name().equals("ADMIN")) {
            throw new BusinessException("Apenas é possível visualizar suas próprias candidaturas");
        }

        return candidaturaRepository.findByCandidatoId(candidatoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CandidaturaDTO> findByStatus(StatusCandidatura status) {
        User currentUser = getCurrentUser();

        // Admin e recrutadores podem ver todas as candidaturas por status
        if (currentUser.getRole().name().equals("ADMIN") || currentUser.getRole().name().equals("RECRUTADOR")) {
            return candidaturaRepository.findByStatus(status.name()).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        // Candidatos só podem ver suas próprias candidaturas
        return candidaturaRepository.findByCandidatoIdAndStatus(currentUser.getId(), status.name()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CandidaturaDTO> findMinhasCandidaturas() {
        User currentUser = getCurrentUser();
        return candidaturaRepository.findByCandidatoId(currentUser.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private CandidaturaDTO convertToDTO(Candidatura candidatura) {
        return new CandidaturaDTO(
                candidatura.getId(),
                candidatura.getVaga().getId(),
                candidatura.getCandidato().getId(),
                candidatura.getNomeCandidato(),
                candidatura.getEmail(),
                candidatura.getTelefone(),
                candidatura.getExperiencia(),
                candidatura.getCurriculoUrl(),
                candidatura.getDataCandidatura(),
                candidatura.getStatus()
        );
    }

    private Candidatura convertToEntity(CandidaturaDTO dto) {
        return new Candidatura(
                dto.getId(),
                null, // vaga será setada no create
                null, // candidato será setado no create
                dto.getNomeCandidato(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getExperiencia(),
                dto.getCurriculoUrl(),
                dto.getDataCandidatura(),
                dto.getStatus()
        );
    }
}