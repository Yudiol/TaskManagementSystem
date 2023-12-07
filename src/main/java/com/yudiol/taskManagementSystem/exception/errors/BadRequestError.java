package com.yudiol.taskManagementSystem.exception.errors;

public class BadRequestError extends RuntimeException {
    public BadRequestError(String message) {
        super(message);
    }
}
