package com.yudiol.taskManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter

public enum TaskStatus {
    CREATED("Создано"),
    DURING_PROCESSING("В процессе"),
    IN_WAITING("В ожидании"),
    COMPLETED("Завершено");

    @JsonValue
    private String status;

    TaskStatus(String s) {
        this.status = s;
    }
}
