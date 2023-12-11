package com.yudiol.taskManagementSystem.mapper;

import com.yudiol.taskManagementSystem.dto.AuthRegRequestDto;
import com.yudiol.taskManagementSystem.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(AuthRegRequestDto userRequestDto);
}
