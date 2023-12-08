package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.CommentDto;
import com.yudiol.taskManagementSystem.dto.CommentRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.mapper.CommentMapper;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.repository.CommentRepository;
import com.yudiol.taskManagementSystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    @Transactional
    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Map<Long, List<CommentWithAuthorFullNameResponseDto>> getListCommentsByListIds(List<Long> ids) {
        Map<Long, List<CommentWithAuthorFullNameResponseDto>> map =
                commentRepository.findAllByTaskIdInArr(ids).stream().collect(Collectors.groupingBy(CommentDto::getTaskId, Collectors.mapping(
                        commentMapper::toCommentWithAuthorFullNameResponseDto, Collectors.toList())));
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(CommentWithAuthorFullNameResponseDto::getDateRegistration))
                                .collect(Collectors.toList())
                ));
    }
}
