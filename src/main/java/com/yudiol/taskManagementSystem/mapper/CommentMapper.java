package com.yudiol.taskManagementSystem.mapper;

import com.yudiol.taskManagementSystem.dto.CommentDto;
import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentRequestDto commentRequestDto);

    CommentWithAuthorFullNameResponseDto toCommentWithAuthorFullNameResponse(Comment comment);

    CommentWithAuthorFullNameResponseDto toCommentWithAuthorFullNameResponseDto(CommentDto comment);
}
