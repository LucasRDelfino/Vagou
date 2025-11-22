package br.com.fiap.Vagou.dto;


import br.com.fiap.Vagou.enums.StatusCandidatura;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CandidaturaDTO {
    private Long id;

    @NotNull
    private Long vagaId;

    private Long candidatoId;

    @NotBlank
    private String nomeCandidato;

    @Email
    @NotBlank
    private String email;

    private String telefone;

    private String experiencia;

    private String curriculoUrl;

    private LocalDateTime dataCandidatura;

    private StatusCandidatura status;

    public CandidaturaDTO() {}

    public CandidaturaDTO(Long id, Long vagaId, Long candidatoId, String nomeCandidato, String email,
                          String telefone, String experiencia, String curriculoUrl,
                          LocalDateTime dataCandidatura, StatusCandidatura status) {
        this.id = id;
        this.vagaId = vagaId;
        this.candidatoId = candidatoId;
        this.nomeCandidato = nomeCandidato;
        this.email = email;
        this.telefone = telefone;
        this.experiencia = experiencia;
        this.curriculoUrl = curriculoUrl;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }

    public Long getCandidatoId() { return candidatoId; }
    public void setCandidatoId(Long candidatoId) { this.candidatoId = candidatoId; }

    public String getNomeCandidato() { return nomeCandidato; }
    public void setNomeCandidato(String nomeCandidato) { this.nomeCandidato = nomeCandidato; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public String getCurriculoUrl() { return curriculoUrl; }
    public void setCurriculoUrl(String curriculoUrl) { this.curriculoUrl = curriculoUrl; }

    public LocalDateTime getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDateTime dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public StatusCandidatura getStatus() { return status; }
    public void setStatus(StatusCandidatura status) { this.status = status; }
}