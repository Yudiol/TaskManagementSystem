package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.Mapper.CommentMapper;
import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.repository.CommentRepository;
import com.yudiol.taskManagementSystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public Comment save(Long userId, CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.toComment(commentRequestDto);
        comment.setUserId(userId);
        comment.setDateRegistration(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}
