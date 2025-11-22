package br.com.fiap.Vagou.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("message", "Vagou API funcionando corretamente");
        status.put("version", "1.0.0");
        status.put("timestamp", java.time.LocalDateTime.now().toString());
        return status;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "Vagou API");
        info.put("version", "1.0.0");
        info.put("description", "Sistema de gerenciamento de vagas de emprego");
        info.put("documentation", "/swagger-ui.html");
        info.put("health", "/health");
        return info;
    }
}