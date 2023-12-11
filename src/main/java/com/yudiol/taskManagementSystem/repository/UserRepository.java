package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.dto.TaskDto;
import com.yudiol.taskManagementSystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = " SELECT new com.yudiol.taskManagementSystem.dto.TaskDto( " +
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
            " WHERE lower(u.name) LIKE  lower(concat('%', :authorName,'%')) " +
            " AND lower(u.surname) LIKE  lower(concat('%', :authorSurname,'%')) " +
            " AND lower(p.name) LIKE  lower(concat('%', :performerName,'%')) " +
            " AND lower(p.surname) LIKE  lower(concat('%', :performerSurname,'%')) " +
            " AND t.dateRegistration BETWEEN :startDate AND :endDate "
    )
    Page<TaskDto> filter(Pageable pageable, String authorName, String authorSurname, String performerName,
                         String performerSurname, LocalDateTime startDate, LocalDateTime endDate);
}
