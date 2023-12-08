package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
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
public class TaskWithCommentsResponseDto {

    private Long taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private Long authorId;
    private String authorName;
    private String authorSurname;
    private Long performerId;
    private String performerName;
    private String performerSurname;
//    private UserFullNameResponseDto author;
//    private UserFullNameResponseDto performer;
    private LocalDateTime dateRegistration;
    private List<CommentWithAuthorFullNameResponseDto> comments;
}
