package com.example.task_flow.TaskFlow.repository;

import com.example.task_flow.TaskFlow.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
