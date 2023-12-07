package com.yudiol.taskManagementSystem.service;

import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.model.Comment;

public interface CommentService {
    Comment save(Long userId, CommentRequestDto commentRequestDto);

    Comment findById(Long commentId);

    void deleteById(Long commentId);
}
