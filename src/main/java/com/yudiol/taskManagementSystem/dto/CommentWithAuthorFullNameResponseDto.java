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

    @Schema(description = "Идентификатор комментария", example = "8")
    private Long commentId;

    @Schema(description = "Идентификатор автора", example = "1")
    private Long authorId;

    @Schema(description = "Имя автора ", example = "Иван")
    private String authorName;

    @Schema(description = "Фамилия автора ", example = "Иванов")
    private String authorSurname;

    @Schema(description = "Описание", example = "Описание")
    private String description;

    private LocalDateTime dateRegistration;
}
