package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.FilterDto;
import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskUpdateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskWithCommentsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TaskService {
    Long save(Long userId, TaskCreateRequestDto taskRequestDto);

    void update(Long userId, TaskUpdateRequestDto taskRequestDto);

    TaskWithCommentsResponseDto findByTaskId(Long taskId);

    List<TaskWithCommentsResponseDto> findAllByPerformerId(Long performerId);

    List<TaskWithCommentsResponseDto> findAllByAuthorId(Long authorId);

    List<TaskWithCommentsResponseDto> findAll();

    void changeStatus(Long taskId, TaskChangeStatusRequestDto taskChangeStatusRequestDto);

    Page<TaskWithCommentsResponseDto> filter(Pageable pageable, String authorName, String authorSurname, String performerName, String performerSurname, String startDate, String endDate);
}
