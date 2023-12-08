package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskUpdateRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskWithCommentsResponseDto;
import com.yudiol.taskManagementSystem.security.JwtTokenUtils;
import com.yudiol.taskManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping
    @Operation(summary = "Создать задачу")
    public void create(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                       @RequestBody TaskCreateRequestDto taskRequestDto) {
        taskService.save(jwtTokenUtils.getUserIdWithBearer(authorizationHeader), taskRequestDto);
    }

    @PatchMapping("/change-status/{taskId}")
    @Operation(summary = "Изменить статус задачи")
    public void changeStatus(@PathVariable("taskId") Long taskId,
                             @RequestBody TaskChangeStatusRequestDto taskChangeStatusRequestDto) {
        taskService.changeStatus(taskId, taskChangeStatusRequestDto);
    }


    @PatchMapping("/{taskId}")
    @Operation(summary = "Обновить задачу")
//    @PreAuthorize("#taskRequestDto.taskId == 4")

    public void update(@RequestBody TaskUpdateRequestDto taskRequestDto,
                       @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        taskService.update(jwtTokenUtils.getUserIdWithBearer(authorizationHeader), taskRequestDto);
    }

    @GetMapping("with-comments/{taskId}")
    @Operation(summary = "Получить задачу с комментариями по taskId")
    public TaskWithCommentsResponseDto getTaskWithComments(@PathVariable("taskId") Long taskId) {
        return taskService.findByTaskId(taskId);
    }

    @GetMapping("with-comments/author/{authorId}")
    @Operation(summary = "Получить список задач автора с комментариями по authorId")
    public List<TaskWithCommentsResponseDto> getAllTasksByAuthorIdWithComments(@PathVariable("authorId") Long authorId) {
        return taskService.findAllByAuthorId(authorId);
    }

    @GetMapping("with-comments/performer/{performerId}")
    @Operation(summary = "Получить список задач исполнителя с комментариями по performerId")
    public List<TaskWithCommentsResponseDto> getAllTasksByPerformerIdWithComments(@PathVariable("performerId") Long performerId) {
        return taskService.findAllByPerformerId(performerId);
    }

    @GetMapping("filter")
    @Operation(summary = "Filter")
    public Page<TaskWithCommentsResponseDto> filter(
            @RequestParam(name = "authorName", defaultValue = "", required = false) String authorName,
            @RequestParam(name = "authorSurname", defaultValue = "", required = false) String authorSurname,
            @RequestParam(name = "performerName", defaultValue = "", required = false) String performerName,
            @RequestParam(name = "performerSurname", defaultValue = "", required = false) String performerSurname,
            @RequestParam(name = "startDate", defaultValue = "2000-01-01T00:00:00", required = false)
            @Schema(description = "Время начала поиска", example = "2000-01-01T00:00:00")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    String startDate
            ,
            @RequestParam(name = "endDate", defaultValue = "3000-01-01T00:00:00", required = false)
            @Schema(description = "Время конца поиска", example = "3000-01-01T00:00:00")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    String endDate,
            @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20", required = false) Integer limit


    ) {
        return taskService.filter(PageRequest.of(offset, limit), authorName, authorSurname, performerName, performerSurname, startDate, endDate);
    }

    @GetMapping("with-comments")
    @Operation(summary = "Получить все задачи")
    public List<TaskWithCommentsResponseDto> getAll() {
        return taskService.findAll();
    }
}
