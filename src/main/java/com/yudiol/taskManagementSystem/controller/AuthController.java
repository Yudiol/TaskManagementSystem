package com.yudiol.taskManagementSystem.controller;

import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthLoginRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthResponseDto;
import com.yudiol.taskManagementSystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg")
    @Operation(summary = "Создание пользователя")
    public void register(@RequestBody AuthRegRequestDto userRequestDto) {
        authService.save(userRequestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public AuthResponseDto createAuthToken(@RequestBody @Valid AuthLoginRequestDto authRequestDto, BindingResult bindingResult) {
//        ErrorsValidationChecker.checkValidationErrors(bindingResult);
        AuthResponseDto authResponseDto = authService.createAuthToken(authRequestDto.getUsername().toLowerCase(), authRequestDto.getPassword());
//        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername().toLowerCase());
//        authResponseDto.setRefreshToken(refreshToken.getToken());
        return authResponseDto;
    }
}
