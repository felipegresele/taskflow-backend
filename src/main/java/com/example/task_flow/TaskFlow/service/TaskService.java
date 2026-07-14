package com.example.task_flow.TaskFlow.service;

import com.example.task_flow.TaskFlow.entity.task.Task;
import com.example.task_flow.TaskFlow.entity.task.request.TaskRequestDto;
import com.example.task_flow.TaskFlow.entity.task.response.TaskResponseDto;
import com.example.task_flow.TaskFlow.entity.user.User;
import com.example.task_flow.TaskFlow.mappers.TaskMapper;
import com.example.task_flow.TaskFlow.repository.TaskRepository;
import com.example.task_flow.TaskFlow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    public List<TaskResponseDto> getAllTasks(String taskName, Long userId) {
        List<Task> list = this.taskRepository.findByUserId(userId);
        if (taskName != null && !taskName.isBlank()) {
            list = list.stream()
                    .filter(task -> task.getName().equalsIgnoreCase(taskName))
                    .toList();
        }
        return this.taskMapper.entityListToResponseList(list);
    }

    public TaskResponseDto getTaskById(Long id, Long userId) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found ID: " + id));
        return this.taskMapper.entityToResponse(task);
    }

    public TaskResponseDto addTask(TaskRequestDto dto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Task task = this.taskMapper.requestToEntity(dto);
        task.setUser(user);
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
