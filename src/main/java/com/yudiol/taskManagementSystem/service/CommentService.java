package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.CommentCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    IdResponseDto save(Long userId, CommentCreateRequestDto commentRequestDto);

    Comment findById(Long commentId);

    void delete(Long commentId);

    void update(Long commentId, String description);

    Map<Long, List<CommentWithAuthorFullNameResponseDto>> getListCommentsByListIds(List<Long> ids);
}
