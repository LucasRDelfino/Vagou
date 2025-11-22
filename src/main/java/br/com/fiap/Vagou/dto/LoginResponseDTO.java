package br.com.fiap.Vagou.dto;


import br.com.fiap.Vagou.enums.UserRole;

public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private String email;
    private UserRole role;
    private String name;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String token, String type, String email, UserRole role, String name) {
        this.token = token;
        this.type = type;
        this.email = email;
        this.role = role;
        this.name = name;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
