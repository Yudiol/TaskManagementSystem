package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность изменение статуса задачи")
public class TaskChangeStatusRequestDto {
    @Schema(description = "Статус выполнения задачи", example = "В ожидании")
    private String taskStatus;
}
