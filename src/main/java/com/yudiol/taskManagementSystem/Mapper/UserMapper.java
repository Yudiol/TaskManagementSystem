package com.yudiol.taskManagementSystem.Mapper;

import com.yudiol.taskManagementSystem.dto.UserRequestDto;
import com.yudiol.taskManagementSystem.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto userRequestDto);
}
