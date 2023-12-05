package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.UserRequestDto;
import com.yudiol.taskManagementSystem.model.User;

public interface AuthService {

    void save(UserRequestDto userRequestDto);
}
