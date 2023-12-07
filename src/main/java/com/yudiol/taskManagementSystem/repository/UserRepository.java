package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.dto.FilterDto;
import com.yudiol.taskManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


//    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value = " select  new com.yudiol.taskManagementSystem.dto.FilterDto( u.userId AS authorId, " +
//            " p.userId AS performerId , " +
//            " t.taskId AS taskId , " +
//            " u.name AS name , " +
//            " u.surname AS surname ) " +
////            " u.user_id, u.name, u.surname, t.task_id, p.user_id, p.name ,p.surname " +
//            " from USER AS u " +
//            " inner join TASK AS t on u.userId = t.authorId  " +
//            " inner join USER AS p on t.performerId = p.userId " )//+
////            " where LOWER (u.name) LIKE ('%?1%') " )
////    @Query(value = "UPDATE tasks SET title = ?1, description= ?2 ,status= ?3, priority = ?4  WHERE task_id = ?5", nativeQuery = true)
//    List<FilterDto> filter(String title);
}
