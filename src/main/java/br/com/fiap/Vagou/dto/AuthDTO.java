package br.com.fiap.Vagou.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public AuthDTO() {}

    public AuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
