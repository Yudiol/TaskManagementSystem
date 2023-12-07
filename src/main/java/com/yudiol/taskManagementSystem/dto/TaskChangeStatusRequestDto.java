package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.model.TaskStatus;
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
public class TaskChangeStatusRequestDto {
    private TaskStatus status;
}
