package br.com.fiap.Vagou.controller;


import br.com.fiap.Vagou.dto.AuthDTO;
import br.com.fiap.Vagou.dto.LoginResponseDTO;
import br.com.fiap.Vagou.dto.UserDTO;
import br.com.fiap.Vagou.model.User;
import br.com.fiap.Vagou.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthDTO authDTO) {
        LoginResponseDTO response = authService.login(authDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        UserDTO response = authService.register(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/recrutador")
    public ResponseEntity<UserDTO> registerRecrutador(@Valid @RequestBody User user) {
        UserDTO response = authService.registerRecrutador(user);
        return ResponseEntity.ok(response);
    }
}