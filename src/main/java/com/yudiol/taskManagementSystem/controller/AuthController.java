package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.AuthLoginRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthResponseDto;
import com.yudiol.taskManagementSystem.exception.ApiError;
import com.yudiol.taskManagementSystem.service.AuthService;
import com.yudiol.taskManagementSystem.util.validate.ErrorsValidationChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
@Tag(name = "Аутентификация", description = "Производит регистрацию пользователя и логин.")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg")
    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрировался"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto register(@RequestBody @Valid AuthRegRequestDto userDto, BindingResult bindingResult) {
        ErrorsValidationChecker.checkValidationErrors(bindingResult);
        authService.save(userDto);
        return authService.createAuthToken(userDto.getEmail().toLowerCase(), userDto.getPassword());
    }


    @PostMapping("/login")
    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно вошел в систему"),
            @ApiResponse(responseCode = "400", description = "Не правильная структура JSON или не валидные данные", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован, если вы пытались войти в систему проверьте логин и пароль", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    public AuthResponseDto login(@RequestBody AuthLoginRequestDto authRequestDto) {
        return authService.createAuthToken(authRequestDto.getUsername().toLowerCase(), authRequestDto.getPassword());
    }
}
