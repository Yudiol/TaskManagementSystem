package com.yudiol.taskManagementSystem.exception.errors;

public class ForbiddenError extends RuntimeException {
    public ForbiddenError(String message) {
        super(message);
    }
}
