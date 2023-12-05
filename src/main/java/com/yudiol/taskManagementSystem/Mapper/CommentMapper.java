package com.yudiol.taskManagementSystem.Mapper;

import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentRequestDto commentRequestDto);
}
