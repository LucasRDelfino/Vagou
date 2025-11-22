package br.com.fiap.Vagou.model;

import br.com.fiap.Vagou.enums.StatusCandidatura;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;

@Entity
@Table(name = "candidaturas")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private User candidato;

    @NotBlank
    private String nomeCandidato;

    @Email
    @NotBlank
    private String email;

    private String telefone;

    @Column(length = 2000)
    private String experiencia;

    private String curriculoUrl;

    private LocalDateTime dataCandidatura;

    @Enumerated(EnumType.STRING)
    private StatusCandidatura status = StatusCandidatura.PENDENTE;

    // Construtores
    public Candidatura() {}

    public Candidatura(Long id, Vaga vaga, User candidato, String nomeCandidato, String email,
                       String telefone, String experiencia, String curriculoUrl,
                       LocalDateTime dataCandidatura, StatusCandidatura status) {
        this.id = id;
        this.vaga = vaga;
        this.candidato = candidato;
        this.nomeCandidato = nomeCandidato;
        this.email = email;
        this.telefone = telefone;
        this.experiencia = experiencia;
        this.curriculoUrl = curriculoUrl;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }

    public User getCandidato() { return candidato; }
    public void setCandidato(User candidato) { this.candidato = candidato; }

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

    @PrePersist
    public void prePersist() {
        if (dataCandidatura == null) {
            dataCandidatura = LocalDateTime.now();
        }
    }
}