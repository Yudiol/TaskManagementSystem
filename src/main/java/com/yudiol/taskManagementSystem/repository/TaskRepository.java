package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
