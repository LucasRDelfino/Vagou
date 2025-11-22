package br.com.fiap.Vagou.dto;


import br.com.fiap.Vagou.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    private UserRole role;

    public UserDTO() {}

    public UserDTO(Long id, String email, String name, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
