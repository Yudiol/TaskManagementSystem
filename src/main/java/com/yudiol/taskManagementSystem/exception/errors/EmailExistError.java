package com.yudiol.taskManagementSystem.exception.errors;

public class EmailExistError extends RuntimeException {
    public EmailExistError(String message) {
        super(message);
    }
}
