package com.yudiol.taskManagementSystem.mapper;

import com.yudiol.taskManagementSystem.dto.TaskCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskUpdateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskWithCommentsResponseDto;
import com.yudiol.taskManagementSystem.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskCreateRequestDto taskRequestDto);

    Task toTask(TaskUpdateRequestDto taskRequestDto);

    TaskWithCommentsResponseDto toTaskResponseDto(Task task);
}
