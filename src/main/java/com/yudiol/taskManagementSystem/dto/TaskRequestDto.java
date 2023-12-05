package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_LENGTH_NAME;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {


    @Size(max = 250, message = INCORRECT_LENGTH_NAME)
    @Schema(description = "Заголовок", example = "Выбрать статистику за месяц по продажам")
    private String title;


    @Schema(description = "Описание", example = "Выбрать статистику, исключить из статистики товара №")
    private String description;

    @Schema(description = "Статус выполнения задачи", example = "В процессе")
    private TaskStatus status;
    @Schema(description = "Приоритет выполнения задачи", example = "Высокий")
    private Priority priority;

    @Schema(description = "Исполнитель", example = "2")
    private Long performer;
}
