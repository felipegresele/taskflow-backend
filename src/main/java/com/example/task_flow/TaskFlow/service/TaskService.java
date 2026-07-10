package com.example.task_flow.TaskFlow.service;

import com.example.task_flow.TaskFlow.entity.task.Task;
import com.example.task_flow.TaskFlow.entity.task.request.TaskRequestDto;
import com.example.task_flow.TaskFlow.entity.task.response.TaskResponseDto;
import com.example.task_flow.TaskFlow.mappers.TaskMapper;
import com.example.task_flow.TaskFlow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public TaskResponseDto getTaskById(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found ID: " + id));
        return this.taskMapper.entityToResponse(task);
    }

    public TaskResponseDto addTask(TaskRequestDto dto) {
        Task task = this.taskMapper.requestToEntity(dto);
        this.taskRepository.save(task);
        TaskResponseDto response = this.taskMapper.entityToResponse(task);
        return response;
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found ID: " + id));
        this.taskMapper.update(task, dto);
        this.taskRepository.save(task);
        return this.taskMapper.entityToResponse(task);
    }

    public void deleteTask(Long id) {
        if (id != null ) {
            this.taskRepository.deleteById(id);
        }
    }


}
