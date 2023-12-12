package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность \"Задача\" response с ФИО автора и исполнителя задачи")
public class TaskResponseDto {

    @Schema(description = "Идентификатор задачи", example = "1")
    private Long taskId;

    @Schema(description = "Заголовок", example = "Заголовок")
    private String title;

    @Schema(description = "Описание", example = "Описание")
    private String description;

    @Schema(description = "Статус", example = "Создано")
    private TaskStatus status;

    @Schema(description = "Приоритет", example = "Высокий")
    private Priority priority;

    @Schema(description = "Идентификатор автора", example = "2")
    private Long authorId;

    @Schema(description = "Имя автора ", example = "Иван")
    private String authorName;

    @Schema(description = "Фамилия автора ", example = "Иванов")
    private String authorSurname;

    @Schema(description = "Идентификатор исполнителя", example = "3")
    private Long performerId;

    @Schema(description = "Имя исполнителя ", example = "Петр")
    private String performerName;

    @Schema(description = "Фамилия исполнителя ", example = "Петров")
    private String performerSurname;

    private LocalDateTime dateRegistration;

    private List<CommentWithAuthorFullNameResponseDto> comments;
}
