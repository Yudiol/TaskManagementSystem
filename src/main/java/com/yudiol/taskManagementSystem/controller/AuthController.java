package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.UserRequestDto;
import com.yudiol.taskManagementSystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @Operation(summary = "Создание пользователя")
    public void register(@RequestBody UserRequestDto userRequestDto) {
        authService.save(userRequestDto);
    }

}
