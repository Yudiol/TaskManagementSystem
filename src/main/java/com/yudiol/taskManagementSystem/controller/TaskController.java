package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Создание Task")
    public void create(@RequestBody TaskRequestDto taskRequestDto) {
        System.out.println(taskRequestDto);

        taskService.save(3L,taskRequestDto);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Создание Task")
    public Task get(@PathVariable("taskId") Long taskId) {
        return taskService.findByTaskId(taskId);
    }
}
