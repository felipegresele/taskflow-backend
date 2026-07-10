package com.example.task_flow.TaskFlow.controller;

import com.example.task_flow.TaskFlow.config.TokenProvider;
import com.example.task_flow.TaskFlow.entity.user.User;
import com.example.task_flow.TaskFlow.entity.user.request.LoginRequestDto;
import com.example.task_flow.TaskFlow.entity.user.request.RegisterRequestDto;
import com.example.task_flow.TaskFlow.entity.user.response.LoginResponseDto;
import com.example.task_flow.TaskFlow.entity.user.response.RegisterResponseDto;
import com.example.task_flow.TaskFlow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private AuthController(AuthService authService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken userAuthenticated = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(userAuthenticated);

        User user = (User) authentication.getPrincipal();
        String token = this.tokenProvider.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        RegisterResponseDto newUser = this.authService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
