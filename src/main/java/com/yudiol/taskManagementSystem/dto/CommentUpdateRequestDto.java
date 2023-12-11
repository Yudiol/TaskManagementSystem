package com.yudiol.taskManagementSystem.dto;

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
@Schema(description = "Сущность обновления комментария")
public class CommentUpdateRequestDto {

    @Schema(description = "Комментарий", example = "Получилось очень хорошо)")
    private String description;
}
