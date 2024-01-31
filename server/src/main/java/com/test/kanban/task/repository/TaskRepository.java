package com.test.kanban.task.repository;


import com.test.kanban.task.model.TaskStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.test.kanban.task.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOwnerId(Long ownerId, Pageable pageable);

    List<Task> findAllByOwnerIdAndStatus(Long ownerId, TaskStatus status, Pageable pageable);
}
