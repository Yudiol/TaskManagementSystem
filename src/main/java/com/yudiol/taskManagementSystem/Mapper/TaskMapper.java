package com.yudiol.taskManagementSystem.Mapper;

import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskRequestDto taskRequestDto);
}
