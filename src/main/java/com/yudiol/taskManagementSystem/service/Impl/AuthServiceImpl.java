package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;
import com.yudiol.taskManagementSystem.dto.AuthResponseDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.exception.errors.UnauthorizedError;
import com.yudiol.taskManagementSystem.mapper.UserMapper;
import com.yudiol.taskManagementSystem.model.Role;
import com.yudiol.taskManagementSystem.model.User;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import com.yudiol.taskManagementSystem.security.JwtTokenUtils;
import com.yudiol.taskManagementSystem.security.UserDetailServiceImpl;
import com.yudiol.taskManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public IdResponseDto save(AuthRegRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateRegistration(LocalDate.now());
        user.setRole(Role.USER);
        return new IdResponseDto(userRepository.save(user).getUserId());
    }

    @Transactional
    public AuthResponseDto createAuthToken(String username, String password) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UnauthorizedError("Пользователь не аутентифицирован, если вы пытались войти в систему проверьте логин и пароль"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = getJwtToken(user.getUserId(), username);
        return new AuthResponseDto(user.getUserId(), token);
    }

    public String getJwtToken(Long userId, String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtils.generateToken(userId, userDetails);
    }
}
