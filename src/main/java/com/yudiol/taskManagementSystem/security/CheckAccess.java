package com.yudiol.taskManagementSystem.security;

import com.yudiol.taskManagementSystem.exception.errors.ForbiddenError;
import com.yudiol.taskManagementSystem.repository.CommentRepository;
import com.yudiol.taskManagementSystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CheckAccess {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public Boolean accessTask(Long taskId, Long userId) {
        return checkAccess(userId, taskRepository.findAuthorIdByTaskId(taskId));
    }

    public Boolean accessChangeStatusTask(Long taskId, Long userId) {
        return checkAccess(userId, taskRepository.findPerformerIdByTaskId(taskId));
    }

    public Boolean accessComment(Long commentId, Long userId) {
        return checkAccess(userId, commentRepository.findUserIdByCommentId(commentId));
    }

    private Boolean checkAccess(Long taskId, Long userId) {
        if (Objects.equals(taskId, userId)) {
            return true;
        }
        throw new ForbiddenError("Доступ запрещён");
    }
}
