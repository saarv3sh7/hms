package net.sarvesh.hms.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.hms.dto.AuthResponse;
import net.sarvesh.hms.dto.LoginRequest;
import net.sarvesh.hms.dto.RegisterRequest;
import net.sarvesh.hms.entity.User;
import net.sarvesh.hms.exception.ResourceNotFoundException;
import net.sarvesh.hms.repository.UserRepository;
import net.sarvesh.hms.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}