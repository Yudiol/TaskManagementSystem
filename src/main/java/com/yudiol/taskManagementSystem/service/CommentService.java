package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.model.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    Comment save(Long userId, CommentRequestDto commentRequestDto);

    Comment findById(Long commentId);

    void deleteById(Long commentId);
    Map<Long, List<CommentWithAuthorFullNameResponseDto>> getListCommentsByListIds(List<Long> ids);
}
