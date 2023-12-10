package com.test.kanban.kanban.repositiry;

import com.test.kanban.kanban.model.Kanban;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KanbanRepository extends JpaRepository<Kanban, Long> {
    Sort SORT_BY_START_DESC = Sort.by(Sort.Direction.DESC, "start");
    List<Kanban> findByName(String name);
}
