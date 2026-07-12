package com.example.task_flow.TaskFlow.entity.task.request;

import com.example.task_flow.TaskFlow.enums.PriorityEnum;
import com.example.task_flow.TaskFlow.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {

    @NotEmpty
    private String name;
    @NotNull
    private Boolean isCompleted = false;
    @NotNull
    private Date date;
    private PriorityEnum priority;
    private StatusEnum status;

}
