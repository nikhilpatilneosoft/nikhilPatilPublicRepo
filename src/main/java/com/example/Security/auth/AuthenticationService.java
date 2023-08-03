package com.example.Security.auth;

import com.example.Security.auth.DTOs.AuthenticationRequset;
import com.example.Security.auth.DTOs.AuthenticationResponses;
import com.example.Security.auth.DTOs.RegisterRequset;
import com.example.Security.config.JwtService;
import com.example.Security.user.Role;
import com.example.Security.user.User;
import com.example.Security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponses register(RegisterRequset requset) {
        var user = User.builder()
                        .firstName(requset.getFirstName())
                        .lastName(requset.getLastName())
                .email(requset.getEmail())
                //.password(passwordEncoder.encode(requset.getPassword()))
                .password(new BCryptPasswordEncoder().encode(requset.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponses
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponses authenticate(AuthenticationRequset request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Resourse not found"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponses
                .builder()
                .token(jwtToken)
                .build();
    }
}
