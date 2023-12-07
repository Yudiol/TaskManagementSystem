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
public class CommentWithAuthorFullNameResponseDto {
    private Long commentId;
    private UserFullNameResponseDto author;
    private String description;
    private LocalDateTime dateRegistration;
}
