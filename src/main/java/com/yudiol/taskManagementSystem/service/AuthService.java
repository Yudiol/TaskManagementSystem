package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.AuthResponseDto;
import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;

public interface AuthService {

    void save(AuthRegRequestDto userRequestDto);
    AuthResponseDto createAuthToken(String username, String password);
}
