package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
