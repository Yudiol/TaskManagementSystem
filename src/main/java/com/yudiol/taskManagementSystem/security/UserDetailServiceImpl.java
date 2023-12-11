package com.yudiol.taskManagementSystem.security;


import com.yudiol.taskManagementSystem.exception.errors.UnauthorizedError;
import com.yudiol.taskManagementSystem.model.User;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UnauthorizedError("Пользователь не аутентифицирован, если вы пытались войти в систему проверьте логин и пароль"));
        return new UserDetailsImpl(user);
    }
}
