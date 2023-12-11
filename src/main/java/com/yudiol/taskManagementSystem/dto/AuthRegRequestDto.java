package com.yudiol.taskManagementSystem.dto;

import com.yudiol.taskManagementSystem.util.validate.annotation.NotDuplicateEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_FORMAT_EMAIL;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_LENGTH_EMAIL;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_LENGTH_NAME;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_LENGTH_PASSWORD;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_LENGTH_SURNAME;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.INCORRECT_SYMBOL;
import static com.yudiol.taskManagementSystem.util.validate.ValidationMessage.NOT_EMPTY_VALUE;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность регистрации пользователя")
public class AuthRegRequestDto {

    @Pattern(message = "Поле 'Имя'" + INCORRECT_SYMBOL, regexp = "^[a-zA-Zа-яА-Я]{0,50}$")
    @Size(max = 50, message = INCORRECT_LENGTH_NAME)
    @Schema(description = "Имя", example = "Ирина")
    private String name;

    @Pattern(message = "Поле 'Фамилия'" + INCORRECT_SYMBOL, regexp = "^[a-zA-Zа-яА-Я]{0,50}$")
    @Size(max = 50, message = INCORRECT_LENGTH_SURNAME)
    @Schema(description = "Фамилия", example = "Савельева")
    private String surname;

    @NotBlank(message = "Поле 'Password'" + NOT_EMPTY_VALUE)
    @Size(min = 8, max = 50, message = INCORRECT_LENGTH_PASSWORD)
    @Schema(description = "Пароль", example = "TY89*tQW!k")
    private String password;

    @NotDuplicateEmail()
    @Size(max = 70, message = INCORRECT_LENGTH_EMAIL)
    @Email(message = INCORRECT_FORMAT_EMAIL)
    @Schema(description = "Email", example = "irinasav@yandex.ru")
    private String email;

}
