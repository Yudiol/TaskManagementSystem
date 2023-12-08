package com.yudiol.taskManagementSystem.dto;

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
public class CommentDto {

    private Long commentId;

    private Long taskId;

    private Long authorId;

    private String authorName;

    private String authorSurname;

    private String description;

    private LocalDateTime dateRegistration;
}
