package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface TaskService {
    IdResponseDto save(Long userId, TaskRequestDto taskRequestDto);

    void update(Long userId, TaskRequestDto taskRequestDto);

    TaskResponseDto findByTaskId(Long taskId, Boolean withComments);

    Page<TaskResponseDto> findAllByPerformerId(Pageable pageable, Long performerId, Boolean withComments);

    Page<TaskResponseDto> findAllByAuthorId(Pageable pageable, Long authorId, Boolean withComments);

    Page<TaskResponseDto> filter(Pageable pageable, String authorName, String authorSurname,
                                 String performerName, String performerSurname, LocalDateTime startDate, LocalDateTime endDate, Boolean withComments);

    void changeStatus(Long taskId, TaskChangeStatusRequestDto taskChangeStatusRequestDto);

    void delete(Long taskId);
}
