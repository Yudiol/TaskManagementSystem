package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.dto.TaskDto;
import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    String QUERY = " SELECT new com.yudiol.taskManagementSystem.dto.TaskDto( " +
            " t.taskId AS taskId , " +
            " u.userId AS authorId , " +
            " u.name AS authorName , " +
            " u.surname AS authorSurname , " +
            " p.userId AS performerId , " +
            " p.name AS performerName , " +
            " p.surname AS performerSurname , " +
            " t.title AS title , " +
            " t.status AS status , " +
            " t.priority AS priority , " +
            " t.description AS description , " +
            " t.dateRegistration AS dateRegistration ) " +
            " FROM User AS u " +
            " INNER JOIN Task AS t on u.userId = t.authorId " +
            " INNER JOIN User AS p on t.performerId = p.userId " +
            " WHERE ";

    @Query(value = QUERY + " t.authorId = :authorId ")
    Page<TaskDto> findAllByAuthorId(Pageable pageable, Long authorId);

    @Query(value = QUERY + " t.performerId = :performerId ")
    Page<TaskDto> findAllByPerformerId(Pageable pageable, Long performerId);

    @Query(value = QUERY + " t.taskId = :taskId ")
    Optional<TaskDto> findTaskByTaskId(Long taskId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE tasks " +
            " SET status = :status " +
            " WHERE task_id = :taskId", nativeQuery = true)
    void changeStatus(Long taskId, TaskStatus status);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE tasks " +
            " SET title = :title, " +
            " description= :description , " +
            " status= :status , " +
            " priority = :priority, " +
            " performer_id = :performerId " +
            " WHERE task_id = :taskId", nativeQuery = true)
    void update(String title, String description, TaskStatus status, Priority priority, Long performerId, Long taskId);

    @Query(value = "SELECT author_id FROM tasks " +
            " WHERE task_id = :taskId", nativeQuery = true)
    Long findAuthorIdByTaskId(Long taskId);

    @Query(value = "SELECT performer_id FROM tasks " +
            " WHERE task_id = :taskId", nativeQuery = true)
    Long findPerformerIdByTaskId(Long taskId);

    void deleteByTaskId(Long taskId);
}
