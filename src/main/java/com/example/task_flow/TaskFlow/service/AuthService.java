package com.example.task_flow.TaskFlow.service;

import com.example.task_flow.TaskFlow.entity.user.User;
import com.example.task_flow.TaskFlow.entity.user.request.RegisterRequestDto;
import com.example.task_flow.TaskFlow.entity.user.response.RegisterResponseDto;
import com.example.task_flow.TaskFlow.mappers.AuthMapper;
import com.example.task_flow.TaskFlow.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    public AuthService(UserRepository userRepository, AuthMapper authMapper) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + username));
    }

    public RegisterResponseDto registerUser(RegisterRequestDto dto) {
        User user = this.authMapper.requestToUser(dto);
        this.userRepository.save(user);
        return this.authMapper.userToResponse(user);
    }
}
