package com.yudiol.taskManagementSystem.model;

public enum TaskStatus {
    CREATED("Создано"),
    DURING_PROCESSING("В процессе"),
    IN_WAITING("В ожидании"),
    COMPLETED("Завершено");

    TaskStatus(String s) {
    }
}
