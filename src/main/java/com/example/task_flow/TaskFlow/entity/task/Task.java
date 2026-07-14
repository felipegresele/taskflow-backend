package com.example.task_flow.TaskFlow.entity.task;

import com.example.task_flow.TaskFlow.entity.user.User;
import com.example.task_flow.TaskFlow.enums.PriorityEnum;
import com.example.task_flow.TaskFlow.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_tb")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isCompleted = false;
    private Date date;
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("tasks")
    private User user;

}
