package com.example.task_flow.TaskFlow.mappers;

import com.example.task_flow.TaskFlow.entity.task.Task;
import com.example.task_flow.TaskFlow.entity.task.request.TaskRequestDto;
import com.example.task_flow.TaskFlow.entity.task.response.TaskResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task requestToEntity(TaskRequestDto dto) {
        Task newTask = new Task();
        newTask.setName(dto.getName());
        newTask.setIsCompleted(dto.getIsCompleted());
        newTask.setDate(dto.getDate());
        newTask.setPriority(dto.getPriority());
        newTask.setStatus(dto.getStatus());
        return newTask;
    }

    public TaskResponseDto entityToResponse(Task task) {
        TaskResponseDto response = new TaskResponseDto();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setIsCompleted(task.getIsCompleted());
        response.setDate(task.getDate());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        return response;
    }

    public void update(Task task, TaskRequestDto dto) {
        task.setName(dto.getName());
        task.setIsCompleted(dto.getIsCompleted());
        task.setDate(dto.getDate());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
    }

}
