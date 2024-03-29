package com.test.kanban.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.kanban.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByEmailEqualsIgnoreCase(String email);

    List<User> findUsersByNameEqualsIgnoreCase(String name);

    boolean existsUserById(Long id);
}
