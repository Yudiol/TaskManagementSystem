package com.yudiol.taskManagementSystem.repository;

import com.yudiol.taskManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
