package com.yudiol.taskManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Priority {
    HIGH("Высокий"),
    MIDDLE("Средний"),
    LOW("Низкий");

    @JsonValue
    private String priority;

    Priority(String s) {
        this.priority = s;
    }
}
