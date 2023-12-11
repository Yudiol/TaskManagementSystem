package com.yudiol.taskManagementSystem.util.validate;

public class ValidationMessage {
    public static final String INCORRECT_JSON_OBJECT = "Неверная структура объекта: проверьте скобки, запятые и названия полей";
    public static final String INCORRECT_SYMBOL = " должно содержать только буквы";
    public static final String INCORRECT_LENGTH_NAME = "Поле 'Имя' должно содержать не больше 50 символов";
    public static final String INCORRECT_LENGTH_SURNAME = "Поле 'Фамилия' должно содержать не больше 50 символов";
    public static final String INCORRECT_LENGTH_PASSWORD = "Поле 'Пароль' должно содержать минимум 8 символов и не больше 50";
    public static final String DUPLICATE_EMAIL = "Пользователь с указанной электронной почтой уже существует в базе данных";
    public static final String INCORRECT_FORMAT_EMAIL = "Email должна иметь правильный формат. Пример ivan@yandex.ru или petr@gmail.com";
    public static final String INCORRECT_LENGTH_EMAIL = "Email должен быть не больше 70 знаков";
    public static final String NOT_EMPTY_VALUE = " не должно быть пустым";
}