package com.example.task_flow.TaskFlow.controller;

import com.example.task_flow.TaskFlow.config.JwtUserData;
import com.example.task_flow.TaskFlow.entity.task.Task;
import com.example.task_flow.TaskFlow.entity.task.request.TaskRequestDto;
import com.example.task_flow.TaskFlow.entity.task.response.TaskResponseDto;
import com.example.task_flow.TaskFlow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAll(@AuthenticationPrincipal JwtUserData jwtUser) {
        List<TaskResponseDto> list = this.taskService.getAllTasks(jwtUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody @Valid TaskRequestDto dto, @AuthenticationPrincipal JwtUserData jwtUser) {
        TaskResponseDto newTask = this.taskService.addTask(dto, jwtUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id, @RequestBody @Valid TaskRequestDto dto) {
        TaskResponseDto task = this.taskService.updateTask(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removed task ID: " + id);
    }

}
