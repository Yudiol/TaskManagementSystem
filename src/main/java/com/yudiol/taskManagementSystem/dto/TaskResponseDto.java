package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
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
public class TaskResponseDto {
    private Long taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private UserFullNameResponseDto author;
    private UserFullNameResponseDto performer;
    private LocalDateTime dateRegistration;
}
