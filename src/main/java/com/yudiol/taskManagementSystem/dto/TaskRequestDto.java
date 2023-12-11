package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_LENGTH_NAME;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность создания задачи")
public class TaskRequestDto {


    @Size(max = 250, message = INCORRECT_LENGTH_NAME)
    @Schema(description = "Заголовок", example = "Выбрать статистику за месяц по продажам")
    private String title;

    @Schema(description = "Описание", example = "Выбрать статистику, исключить из статистики товара №")
    private String description;

    @Schema(description = "Статус выполнения задачи", example = "В ожидании")
    private String taskStatus;

    @Schema(description = "Приоритет выполнения задачи", example = "Высокий")
    private String taskPriority;

    @Schema(description = "Исполнитель", example = "1")
    private Long performerId;
}
