package com.yudiol.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor@Schema(description = "Сущность \"Комментарий\" выборка данных из таблицы с ФИО автора комментария")
public class CommentDto {

    private Long commentId;

    private Long taskId;

    private Long authorId;

    private String authorName;

    private String authorSurname;

    private String description;

    private LocalDateTime dateRegistration;
}
