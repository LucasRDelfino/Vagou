package br.com.fiap.Vagou.dto;

import br.com.fiap.Vagou.enums.NivelExperiencia;
import br.com.fiap.Vagou.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public class VagaDTO {
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String empresa;

    @NotBlank
    private String localizacao;

    @NotNull
    private Double salario;

    @NotNull
    private TipoContrato tipoContrato;

    @NotNull
    private NivelExperiencia nivel;

    private Set<String> tecnologias;

    private LocalDateTime dataPublicacao;

    private Boolean ativa;

    private Long recrutadorId;

    public VagaDTO() {}

    public VagaDTO(Long id, String titulo, String descricao, String empresa, String localizacao,
                   Double salario, TipoContrato tipoContrato, NivelExperiencia nivel,
                   Set<String> tecnologias, LocalDateTime dataPublicacao, Boolean ativa, Long recrutadorId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.localizacao = localizacao;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
        this.nivel = nivel;
        this.tecnologias = tecnologias;
        this.dataPublicacao = dataPublicacao;
        this.ativa = ativa;
        this.recrutadorId = recrutadorId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public TipoContrato getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(TipoContrato tipoContrato) { this.tipoContrato = tipoContrato; }

    public NivelExperiencia getNivel() { return nivel; }
    public void setNivel(NivelExperiencia nivel) { this.nivel = nivel; }

    public Set<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(Set<String> tecnologias) { this.tecnologias = tecnologias; }

    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDateTime dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }

    public Long getRecrutadorId() { return recrutadorId; }
    public void setRecrutadorId(Long recrutadorId) { this.recrutadorId = recrutadorId; }
}