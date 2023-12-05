package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.Mapper.UserMapper;
import com.yudiol.taskManagementSystem.dto.UserRequestDto;
import com.yudiol.taskManagementSystem.model.User;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import com.yudiol.taskManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void save(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        user.setDateRegistration(LocalDate.now());
        userRepository.save(user);
    }
}
