package com.yudiol.taskManagementSystem.exception.errors;

public class UnauthorizedError extends RuntimeException {
    public UnauthorizedError(String message) {
        super(message);
    }
}
