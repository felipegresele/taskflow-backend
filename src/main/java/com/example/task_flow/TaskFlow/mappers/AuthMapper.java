package com.example.task_flow.TaskFlow.mappers;

import com.example.task_flow.TaskFlow.entity.user.User;
import com.example.task_flow.TaskFlow.entity.user.request.RegisterRequestDto;
import com.example.task_flow.TaskFlow.entity.user.response.RegisterResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    private final PasswordEncoder passwordEncoder;

    public AuthMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto userToResponse(User user) {
        return RegisterResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User requestToUser(RegisterRequestDto dto) {
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return newUser;
    }

}

