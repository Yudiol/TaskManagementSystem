package com.yudiol.taskManagementSystem.util.validate.annotation;

import com.yudiol.taskManagementSystem.exception.errors.EmailExistError;
import com.yudiol.taskManagementSystem.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class NotDuplicateEmailValidator implements ConstraintValidator<NotDuplicateEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        System.out.println(email);
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailExistError("");
        }
        return true;
    }
}