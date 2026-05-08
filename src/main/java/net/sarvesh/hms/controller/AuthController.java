package net.sarvesh.hms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sarvesh.hms.dto.AuthResponse;
import net.sarvesh.hms.dto.LoginRequest;
import net.sarvesh.hms.dto.RegisterRequest;
import net.sarvesh.hms.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
