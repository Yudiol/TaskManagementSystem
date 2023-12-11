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
@NoArgsConstructor
@Schema(description = "Сущность \"Комментария\" response с ФИО пользователя")
public class CommentWithAuthorFullNameResponseDto {
    private Long commentId;
    private Long authorId;
    private String authorName;
    private String authorSurname;
    private String description;
    private LocalDateTime dateRegistration;
}
