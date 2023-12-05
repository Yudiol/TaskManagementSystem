package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskId(Long taskId);
}
