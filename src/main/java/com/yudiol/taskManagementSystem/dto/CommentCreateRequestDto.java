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
@NoArgsConstructor@Schema(description = "Сущность создания комментария")
public class CommentCreateRequestDto {

    private Long taskId;
    @Schema(description = "Комментарий", example = "Я бы сделал по другому)")
    private String description;
}
