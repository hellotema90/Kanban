package com.test.kanban.kanban.dto;

import com.test.kanban.task.model.Task;
import com.test.kanban.user.model.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KanbanDto {
    private Long id;
    private String name;
    private Task task;
    private User owner;
}
