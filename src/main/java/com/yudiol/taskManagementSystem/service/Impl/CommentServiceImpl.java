package com.yudiol.taskManagementSystem.service.Impl;

import com.yudiol.taskManagementSystem.dto.CommentDto;
import com.yudiol.taskManagementSystem.dto.CommentCreateRequestDto;
import com.yudiol.taskManagementSystem.dto.CommentWithAuthorFullNameResponseDto;
import com.yudiol.taskManagementSystem.dto.IdResponseDto;
import com.yudiol.taskManagementSystem.exception.errors.NotFoundException;
import com.yudiol.taskManagementSystem.mapper.CommentMapper;
import com.yudiol.taskManagementSystem.model.Comment;
import com.yudiol.taskManagementSystem.repository.CommentRepository;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import com.yudiol.taskManagementSystem.service.CommentService;
import com.yudiol.taskManagementSystem.service.TaskService;
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
    private final TaskRepository taskRepository;

    @Transactional
    public IdResponseDto save(Long userId, CommentCreateRequestDto commentDto) {
        taskRepository.findById(commentDto.getTaskId()).orElseThrow(() ->
                new NotFoundException("Задача", String.valueOf(commentDto.getTaskId())));
        Comment comment = commentMapper.toComment(commentDto);
        comment.setUserId(userId);
        comment.setDateRegistration(LocalDateTime.now());
        return new IdResponseDto(commentRepository.save(comment).getCommentId());
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Комментарий", String.valueOf(commentId)));
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }

    @Transactional
    public void update(Long commentId, String description) {
        commentRepository.update(commentId, description);
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
