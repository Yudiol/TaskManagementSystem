package com.yudiol.taskManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String name;

    private String surname;

    private String password;

    private String email;

}
