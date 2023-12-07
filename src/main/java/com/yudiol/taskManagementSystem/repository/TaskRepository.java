package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.model.Priority;
import com.yudiol.taskManagementSystem.model.Task;
import com.yudiol.taskManagementSystem.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskId(Long taskId);

    List<Task> findAllByAuthorId(Long authorId);

    List<Task> findAllByPerformerId(Long performerId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value = "DELETE FROM refresh r USING users u WHERE r.user_id=u.id AND u.email = ?1", nativeQuery = true)
    @Query(value = "UPDATE tasks SET status= :status WHERE task_id = :taskId", nativeQuery = true)
    void changeStatus(Long taskId, TaskStatus status);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value = "DELETE FROM refresh r USING users u WHERE r.user_id=u.id AND u.email = ?1", nativeQuery = true)
    @Query(value = "UPDATE tasks SET title = ?1, description= ?2 ,status= ?3, priority = ?4  WHERE task_id = ?5", nativeQuery = true)
    Long update(String title, String description, TaskStatus status, Priority priority, Long taskId);

}
