package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.Mapper.TaskMapper;
import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import com.yudiol.taskManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Transactional
    public Long save(Long userId,TaskRequestDto taskRequestDto) {
        Task task = taskMapper.toTask(taskRequestDto);
        task.setAuthorId(userId);
        task.setDateRegistration(LocalDateTime.now());
        System.out.println(task);
        return taskRepository.save(task).getTaskId();
    }

    @Override
    public Task findByTaskId(Long taskId) {
        return taskRepository.findByTaskId(taskId).orElseThrow();
    }
}
