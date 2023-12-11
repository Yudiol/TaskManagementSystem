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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Сущность \"Задача\" выборка данных из таблицы с ФИО автора и исполнителя задачи")
public class TaskDto {
    private Long taskId;
    private Long authorId;
    private String authorName;
    private String authorSurname;
    private Long performerId;
    private String performerName;
    private String performerSurname;
    private String title;
    private TaskStatus status;
    private Priority priority;
    private String description;
    private LocalDateTime dateRegistration;
}
