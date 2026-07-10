package com.example.task_flow.TaskFlow.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtUserData {

    private Long id;
    private String email;
    private String password;

}
