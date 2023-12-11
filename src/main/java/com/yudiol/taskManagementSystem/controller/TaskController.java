package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.dto.TaskChangeStatusRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskRequestDto;
import com.yudiol.taskManagementSystem.dto.TaskResponseDto;
import com.yudiol.taskManagementSystem.exception.ApiError;
import com.yudiol.taskManagementSystem.security.JwtTokenUtils;
import com.yudiol.taskManagementSystem.service.TaskService;
import com.yudiol.taskManagementSystem.util.validate.ErrorsValidationChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Создание, получение, удаление, редактирование и изменение статуса.")
public class TaskController {

    private final TaskService taskService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping
    @Operation(summary = "Создать задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponseDto create(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody @Valid TaskRequestDto taskRequestDto,
            BindingResult bindingResult) {
        ErrorsValidationChecker.checkValidationErrors(bindingResult);
        return taskService.save(jwtTokenUtils.getUserIdWithBearer(authorizationHeader), taskRequestDto);
    }


    @PatchMapping("/change-status/{taskId}")
    @Operation(summary = "Изменить статус задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус задачи успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PreAuthorize("@checkAccess.accessChangeStatusTask(#taskId,@jwtTokenUtils.getUserIdWithBearer(#authorizationHeader))")
    public void changeStatus(
            @Parameter(description = "Идентификатор задачи")
            @PathVariable("taskId") @P("taskId") Long taskId,

            @RequestBody TaskChangeStatusRequestDto taskChangeStatusRequestDto,
            @RequestHeader(value = "Authorization", required = false) @P("authorizationHeader") String authorizationHeader) {
        taskService.changeStatus(taskId, taskChangeStatusRequestDto);
    }


    @PatchMapping("/{taskId}")
    @Operation(summary = "Обновить задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PreAuthorize("@checkAccess.accessTask(#taskId,@jwtTokenUtils.getUserIdWithBearer(#authorizationHeader))")
    public void update(
            @Parameter(description = "Идентификатор задачи")
            @PathVariable("taskId") @P("taskId") Long taskId,

            @RequestBody TaskRequestDto taskRequestDto,
            @RequestHeader(value = "Authorization", required = false) @P("authorizationHeader") String authorizationHeader) {
        taskService.update(taskId, taskRequestDto);
    }


    @DeleteMapping("/{taskId}")
    @Operation(summary = "Удалить задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно удалена"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PreAuthorize("@checkAccess.accessTask(#taskId,@jwtTokenUtils.getUserIdWithBearer(#authorizationHeader))")
    public void delete(
            @Parameter(description = "Идентификатор задачи")
            @PathVariable("taskId") @P("taskId") Long taskId,

            @RequestHeader(value = "Authorization", required = false) @P("authorizationHeader") String authorizationHeader) {
        taskService.delete(taskId);
    }


    @GetMapping("/{taskId}")
    @Operation(summary = "Получить задачу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно получена"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public TaskResponseDto getTask(
            @Parameter(description = "Идентификатор задачи")
            @PathVariable("taskId") Long taskId,

            @Parameter(description = "Получить задачи с комментариями")
            @RequestParam(name = "withComments", defaultValue = "true", required = false) Boolean withComments) {
        return taskService.findByTaskId(taskId, withComments);
    }


    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получить список задач автора по authorId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все задачи успешно получены"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public Page<TaskResponseDto> getAllTasksByAuthorId(
            @Parameter(description = "Идентификатор автора задачи")
            @PathVariable("authorId") Long authorId,

            @Parameter(description = "Получить задачи с комментариями")
            @RequestParam(name = "withComments", defaultValue = "true", required = false) Boolean withComments,

            @Parameter(description = "Номер страницы")
            @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,

            @Parameter(description = "Кол-во задач на странице")
            @RequestParam(value = "limit", defaultValue = "20", required = false) Integer limit) {
        return taskService.findAllByAuthorId(PageRequest.of(offset, limit), authorId, withComments);
    }


    @GetMapping("/performer/{performerId}")
    @Operation(summary = "Получить список задач исполнителя по performerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все задачи успешно получены"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public Page<TaskResponseDto> getAllTasksByPerformerId(

            @Parameter(description = "Идентификатор исполнителя задачи")
            @PathVariable("performerId") Long performerId,

            @Parameter(description = "Получить задачи с комментариями")
            @RequestParam(name = "withComments", defaultValue = "true", required = false) Boolean withComments,

            @Parameter(description = "Номер страницы")
            @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,

            @Parameter(description = "Кол-во задач на странице")
            @RequestParam(value = "limit", defaultValue = "20", required = false) Integer limit) {
        return taskService.findAllByPerformerId(PageRequest.of(offset, limit), performerId, withComments);
    }

    @GetMapping("/filter")
    @Operation(summary = "Получить отфильтрованный список задач")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все задачи успешно получены"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public Page<TaskResponseDto> filter(
            @Schema(description = "Имя автора задачи", example = "Ivan")
            @RequestParam(name = "authorName", defaultValue = "", required = false) String authorName,

            @Schema(description = "Фамилия автора задачи", example = "Ivanov")
            @RequestParam(name = "authorSurname", defaultValue = "", required = false) String authorSurname,

            @Schema(description = "Имя исполнителя задачи", example = "Petr")
            @RequestParam(name = "performerName", defaultValue = "", required = false) String performerName,

            @Schema(description = "Фамилия исполнителя задачи", example = "Petrov")
            @RequestParam(name = "performerSurname", defaultValue = "", required = false) String performerSurname,

            @Schema(description = "Время начала поиска", example = "2000-01-01T00:00:00")
            @RequestParam(name = "startDate", defaultValue = "2000-01-01T00:00:00", required = false) LocalDateTime startDate,

            @Schema(description = "Время конца поиска", example = "3000-01-01T00:00:00")
            @RequestParam(name = "endDate", defaultValue = "3000-01-01T00:00:00", required = false) LocalDateTime endDate,

            @Parameter(description = "Получить задачи с комментариями")
            @RequestParam(name = "withComments", defaultValue = "true", required = false) Boolean withComments,

            @Parameter(description = "Номер страницы")
            @RequestParam(value = "offset", defaultValue = "0", required = false) Integer offset,

            @Parameter(description = "Кол-во задач на странице")
            @RequestParam(value = "limit", defaultValue = "20", required = false) Integer limit
    ) {
        return taskService.filter(PageRequest.of(offset, limit), authorName, authorSurname, performerName, performerSurname, startDate, endDate, withComments);
    }
}
