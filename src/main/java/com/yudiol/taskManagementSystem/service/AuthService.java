package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthResponseDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;

public interface AuthService {

    IdResponseDto save(AuthRegRequestDto userRequestDto);

    AuthResponseDto createAuthToken(String username, String password);
}
