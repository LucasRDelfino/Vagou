package br.com.fiap.Vagou.service;


import br.com.fiap.Vagou.dto.AuthDTO;
import br.com.fiap.Vagou.dto.LoginResponseDTO;
import br.com.fiap.Vagou.dto.UserDTO;
import br.com.fiap.Vagou.enums.UserRole;
import br.com.fiap.Vagou.model.User;
import br.com.fiap.Vagou.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public LoginResponseDTO login(AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                token,
                "Bearer",
                user.getEmail(),
                user.getRole(),
                user.getName()
        );
    }

    public UserDTO register(User user) {
        if (user.getRole() == null) {
            user.setRole(UserRole.CANDIDATO);
        }
        return userService.createUser(user);
    }

    public UserDTO registerRecrutador(User user) {
        user.setRole(UserRole.RECRUTADOR);
        return userService.createUser(user);
    }
}