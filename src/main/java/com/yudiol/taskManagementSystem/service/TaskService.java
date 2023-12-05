package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.TaskRequestDto;

public interface TaskService {
    Long save(TaskRequestDto taskRequestDto);
}
