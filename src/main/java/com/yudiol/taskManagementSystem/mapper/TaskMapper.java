package com.yudiol.taskManagementSystem.mapper;

import com.yudiol.taskManagementSystem.dto.TaskDto;
import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskResponseDto;
import com.yudiol.taskManagementSystem.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskRequestDto taskRequestDto);

//    TaskWithCommentsResponseDto toTaskResponseDto(Task task);

    TaskResponseDto toTaskResponseDto(TaskDto task);


}
