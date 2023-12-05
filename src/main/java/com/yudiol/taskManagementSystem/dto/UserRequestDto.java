package com.yudiol.taskManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_FORMAT_EMAIL;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_LENGTH_EMAIL;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_LENGTH_NAME;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_LENGTH_PASSWORD;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_LENGTH_SURNAME;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.INCORRECT_SYMBOL;
import static com.yudiol.taskManagementSystem.util.ValidationMessage.NOT_SPACES_VALUE;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Pattern(message = "Поле 'Имя'" + INCORRECT_SYMBOL, regexp = "^[a-zA-Zа-яА-Я]{0,250}$")
    @Size(max = 250, message = INCORRECT_LENGTH_NAME)
    @Schema(description = "Имя", example = "Ирина")
    private String name;

    @Pattern(message = "Поле 'Фамилия'" + INCORRECT_SYMBOL, regexp = "^[a-zA-Zа-яА-Я]{0,250}$")
    @Size(max = 250, message = INCORRECT_LENGTH_SURNAME)
    @Schema(description = "Фамилия", example = "Савельева")
    private String surname;

    @Pattern(message = "Поле 'Пароль'" + NOT_SPACES_VALUE, regexp = "^(?![\\s\\S]*\\s)[\\s\\S]+$")
    @Size(max = 100, message = INCORRECT_LENGTH_PASSWORD)
    @Schema(description = "Пароль", example = "TY89*tQW!k")
    private String password;

    @Size(max = 250, message = INCORRECT_LENGTH_EMAIL)
    @Email(message = INCORRECT_FORMAT_EMAIL)
    @Schema(description = "Email", example = "irinasav@yandex.ru")
    private String email;

}
