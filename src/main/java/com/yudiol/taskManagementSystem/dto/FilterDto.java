package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class FilterDto {
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
