package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.dto.CommentDto;
import com.yudiol.taskManagementSystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTaskId(Long taskId);

    List<Comment> findAllByTaskIdIn(List<Long> ids);

    @Query(value = " SELECT  new com.yudiol.taskManagementSystem.dto.CommentDto( " +
            " c.commentId AS commentId , " +
            " c.taskId AS taskId , " +
            " c.userId AS authorId , " +
            " u.name AS authorName , " +
            " u.surname AS authorSurname , " +
            " c.description AS description , " +
            " c.dateRegistration AS dateRegistration ) " +
            " FROM Comment AS c " +
            " INNER JOIN User AS u on c.userId = u.userId " +
            " WHERE c.taskId IN :ids ")
    List<CommentDto> findAllByTaskIdInArr(List<Long> ids);
}
