package com.yudiol.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(description = "Сущность логин")
public class AuthLoginRequestDto {

    @Schema(description = "Email", example = "irinasav@yandex.ru")
    private String username;

    @Schema(description = "Пароль", example = "TY89*tQW!k")
    private String password;
}
