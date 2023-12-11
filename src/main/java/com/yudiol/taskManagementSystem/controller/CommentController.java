package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.CommentCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentUpdateRequestDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.exception.ApiError;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.security.JwtTokenUtils;
import com.yudiol.taskManagementSystem.service.CommentService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@ResponseStatus(HttpStatus.OK)
@Tag(name = "Комментарии", description = "Создание, получение, удаление и редактирование")
public class CommentController {

    private final CommentService commentService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping
    @Operation(summary = "Создать комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Комментарий успешно создан"),
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
            @RequestBody @Valid CommentCreateRequestDto commentRequestDto,
            BindingResult bindingResult) {
        ErrorsValidationChecker.checkValidationErrors(bindingResult);
        return commentService.save(jwtTokenUtils.getUserIdWithBearer(authorizationHeader), commentRequestDto);
    }


    @PatchMapping("/{commentId}")
    @Operation(summary = "Обновить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PreAuthorize("@checkAccess.accessComment(#commentId,@jwtTokenUtils.getUserIdWithBearer(#authorizationHeader))")
    public void update(
            @Parameter(description = "Идентификатор комментария")
            @PathVariable("commentId") @P("commentId") Long commentId,

            @RequestBody CommentUpdateRequestDto commentRequestDto,
            @RequestHeader(value = "Authorization", required = false) @P("authorizationHeader") String authorizationHeader) {
        commentService.update(commentId, commentRequestDto.getDescription());
    }


    @GetMapping("/{commentId}")
    @Operation(summary = "Получить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно получен"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public Comment get(
            @Parameter(description = "Идентификатор комментария")
            @PathVariable("commentId") Long commentId) {
        return commentService.findById(commentId);
    }


    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удалить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно удален"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PreAuthorize("@checkAccess.accessComment(#commentId,@jwtTokenUtils.getUserIdWithBearer(#authorizationHeader))")
    public void delete(
            @Parameter(description = "Идентификатор комментария")
            @PathVariable("commentId") @P("commentId") Long commentId,
            @RequestHeader(value = "Authorization", required = false) @P("authorizationHeader") String authorizationHeader) {
        commentService.delete(commentId);
    }
}
