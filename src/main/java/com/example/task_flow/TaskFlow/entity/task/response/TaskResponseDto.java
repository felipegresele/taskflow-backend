package com.example.task_flow.TaskFlow.entity.task.response;

import com.example.task_flow.TaskFlow.enums.PriorityEnum;
import com.example.task_flow.TaskFlow.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String name;
    private Boolean isCompleted = false;
    private Date date;
    private PriorityEnum priority;
    private StatusEnum status;

}
