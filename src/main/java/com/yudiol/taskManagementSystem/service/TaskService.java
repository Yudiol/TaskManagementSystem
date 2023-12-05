package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.model.Task;

public interface TaskService {
    Long save(TaskRequestDto taskRequestDto);

    Task findByTaskId(Long taskId);
}
